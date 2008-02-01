/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.l2j.gameserver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import javolution.util.FastList;
import net.sf.l2j.Config;
import net.sf.l2j.gameserver.ai.CtrlEvent;
import net.sf.l2j.gameserver.datatables.DoorTable;
import net.sf.l2j.gameserver.datatables.SpawnTable;
import net.sf.l2j.gameserver.instancemanager.DayNightSpawnManager;
import net.sf.l2j.gameserver.model.L2Character;
import net.sf.l2j.gameserver.model.L2Spawn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class ...
 * 
 * @version $Revision: 1.1.4.8 $ $Date: 2005/04/06 16:13:24 $
 */
public class GameTimeController
{
	final static Log					_log				= LogFactory.getLog(GameTimeController.class.getName());
	
	public static final int				TICKS_PER_SECOND	= 10; // not able to change this without checking through code
	public static final int				MILLIS_IN_TICK		= 1000 / TICKS_PER_SECOND;
	
	private static GameTimeController	_instance			= new GameTimeController();
	
	protected static int				_gameTicks;
	protected static long				_gameStartTime;
	protected static boolean			_isNight			= false;
	
	private static FastList<L2Character>_movingObjects		= new FastList<L2Character>();
	
	protected static TimerThread		_timer;
	private ScheduledFuture<?>			_timerWatcher;
	
	/**
	 * one ingame day is 240 real minutes
	 */
	public static GameTimeController getInstance()
	{
		return _instance;
	}
	
	private GameTimeController()
	{
		_gameStartTime = System.currentTimeMillis() - 3600000; // offset so that the server starts a day begin
		_gameTicks = 3600000 / MILLIS_IN_TICK; // offset so that the server starts a day begin
		
		_timer = new TimerThread();
		_timer.start();
		
		_timerWatcher = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new TimerWatcher(), 0, 1000);
		// [L2J_JP ADD SANDMAN]
		ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new OpenPiratesRoom(), 2000, 600000);
		ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new BroadcastSunState(), 0, 600000);
		_log.info("GameTimeController: timerthreads scheduled");
		
	}
	
	public boolean isNowNight()
	{
		return _isNight;
	}
	
	public int getGameTime()
	{
		return (_gameTicks / (TICKS_PER_SECOND * 10));
	}
	
	public static int getGameTicks()
	{
		return _gameTicks;
	}
	
	/**
	 * Add a L2Character to _movingObjects of GameTimeController.<BR>
	 * <BR>
	 * <B><U> Concept</U> :</B><BR>
	 * <BR>
	 * All L2Character in movement are identified in <B>_movingObjects</B> of GameTimeController.<BR>
	 * <BR>
	 * 
	 * @param cha
	 *            The L2Character to add to _movingObjects of GameTimeController
	 */
	public void registerMovingObject(L2Character cha)
	{
		if (cha == null)
			return;
		synchronized (_movingObjects)
		{
			if (!_movingObjects.contains(cha)) _movingObjects.add(cha);
		}
	}
	
	/**
	 * Move all L2Characters contained in _movingObjects of GameTimeController.<BR>
	 * <BR>
	 * <B><U> Concept</U> :</B><BR>
	 * <BR>
	 * All L2Character in movement are identified in <B>_movingObjects</B> of GameTimeController.<BR>
	 * <BR>
	 * <B><U> Actions</U> :</B><BR>
	 * <BR>
	 * <li>Update the position of each L2Character </li>
	 * <li>If movement is finished, the L2Character is removed from _movingObjects </li>
	 * <li>Create a task to update the _knownObject and _knowPlayers of each L2Character that finished its movement and of their already known L2Object then
	 * notify AI with EVT_ARRIVED </li>
	 * <BR>
	 * <BR>
	 */
	protected void moveObjects()
	{
		// Create an FastList to contain all L2Character that are arrived to destination
		FastList<L2Character> ended = null;
		boolean end;

		// Go throw the table containing L2Character in movement
		synchronized (_movingObjects)
		{
			for (FastList.Node<L2Character> ch = _movingObjects.head(), e = _movingObjects.tail(); (ch = ch.getNext()) != e;)
			{
				L2Character cha = ch.getValue();

				// If movement is finished, the L2Character is removed from
				// movingObjects and added to the ArrayList ended
				if (cha != null)
				{
					if (cha.updatePosition(_gameTicks))
					{
						ch = ch.getPrevious();
						_movingObjects.remove(cha);
						if (ended == null)
							ended = new FastList<L2Character>();
						
						ended.add(cha);
					}
				}
			}
		}
		
		// Create a task to update the _knownObject and _knowPlayers of each L2Character that finished its movement and of their already known L2Object
		// then notify AI with EVT_ARRIVED
		// KnownObjects updates is kinda expensive operation, so i splited tasks to avoid blocking of too many parts of code (Julian)
		if (ended != null)
		{
			for (FastList.Node<L2Character> ch = ended.head(), e = ended.tail(); (ch = ch.getNext()) != e;)
				ThreadPoolManager.getInstance().executeTask(new MovingObjectArrived(ch.getValue()));
			ended.clear();
		}
		
	}
	
	public void stopTimer()
	{
		_timerWatcher.cancel(true);
		_timer.interrupt();
	}
	
	class TimerThread extends Thread
	{
		protected Exception	_error;
		
		public TimerThread()
		{
			super("GameTimeController");
			setDaemon(true);
			setPriority(MAX_PRIORITY);
			_error = null;
		}
		
		@Override
		public void run()
		{
			try
			{
				for (;;)
				{
					int _oldTicks = _gameTicks; // save old ticks value to avoid moving objects 2x in same tick
					long runtime = System.currentTimeMillis() - _gameStartTime; // from server boot to now
					
					_gameTicks = (int) (runtime / MILLIS_IN_TICK); // new ticks value (ticks now)
					
					if (_oldTicks != _gameTicks)
						moveObjects();
					// but I think it can't make that effect. is it better to call moveObjects() twice in same
					// tick to make-up for missed tick ? or is it better to ignore missed tick ?
					// (will happen very rarely but it will happen ... on garbage collection definitely)
					
					runtime = (System.currentTimeMillis() - _gameStartTime) - runtime;
					
					// calculate sleep time... time needed to next tick minus time it takes to call moveObjects()
					int sleepTime = 1 + MILLIS_IN_TICK - ((int) runtime) % MILLIS_IN_TICK;
					
					// _log.finest("TICK: "+_gameTicks);
					
					sleep(sleepTime); // hope other threads will have much more cpu time available now
					// SelectorThread most of all
				}
			} catch (Exception e)
			{
				_error = e;
			}
		}
	}
	
	class TimerWatcher implements Runnable
	{
		public void run()
		{
			if (!_timer.isAlive())
			{
				String time = (new SimpleDateFormat("HH:mm:ss")).format(new Date());
				_log.warn(time + " TimerThread stop with following error. restart it.");
				if (_timer._error != null)
					_log.error(_timer._error.getMessage(), _timer._error);
				_timer = null;
				_timer = new TimerThread();
				_timer.start();
			}
		}
	}
	
	/**
	 * Update the _knownObject and _knowPlayers of each L2Character that finished its movement and of their already known L2Object then notify AI with
	 * EVT_ARRIVED.<BR>
	 * <BR>
	 */
	class MovingObjectArrived implements Runnable
	{
		private final L2Character _ended;

		MovingObjectArrived(L2Character ended)
		{
			_ended = ended;
		}

		public void run()
		{
			try
			{
				_ended.getKnownList().updateKnownObjects();
				_ended.getAI().notifyEvent(CtrlEvent.EVT_ARRIVED);
			}
			catch (NullPointerException e)
			{
			}
		}
	}

	/**
	 * @param rise
	 */
	class BroadcastSunState implements Runnable
	{
		public void run()
		{
			int h = (getGameTime() / 60) % 24; // Time in hour
			boolean tempIsNight = (h < 6);
			
			if (tempIsNight != _isNight)
			{ // If diff day/night state
				_isNight = tempIsNight; // Set current day/night varible to value of temp varible
				
				// Zaken cannot be damaged during the night.
				if (_isNight)
				{
					for (L2Spawn spawn : SpawnTable.getInstance().getSpawnTable().values())
					{
						if (spawn.getTemplate().getNpcId() == 29022)
							spawn.getLastSpawn().setIsInvul(true);
					}
				}
				else
				{
					for (L2Spawn spawn : SpawnTable.getInstance().getSpawnTable().values())
					{
						if (spawn.getTemplate().getNpcId() == 29022)
							spawn.getLastSpawn().setIsInvul(false);
					}
				}
				
				DayNightSpawnManager.getInstance().notifyChangeMode();
			}
		}
	}
	
	// [L2J_JP ADD]
	// Open door of pirate's room at AM0:00 every day in game.
	class OpenPiratesRoom implements Runnable
	{
		public void run()
		{
			DoorTable _doorTable = DoorTable.getInstance();
			int _OpenTime = Config.TIME_IN_A_DAY_OF_OPEN_A_DOOR;
			int _CloseTime = Config.TIME_OF_OPENING_A_DOOR;
			int h = (getGameTime() / 60) % 24;
			
			if (h == _OpenTime)
			{
				try
				{
					_doorTable.getDoor(21240006).openMe();
					// The door will be closed in '_CloseTime' minutes.
					ThreadPoolManager.getInstance().scheduleEffect(new ClosePiratesRoom(), (_CloseTime * 60 * 1000));
				} catch (Exception e)
				{
					_log.warn(e.getMessage());
				}
				
			}
		}
	}
	
	// [L2J_JP ADD]
	// Close door of pirate's room.
	class ClosePiratesRoom implements Runnable
	{
		final DoorTable	_doorTable	= DoorTable.getInstance();
		
		public void run()
		{
			try
			{
				_doorTable.getDoor(21240006).closeMe();
			} catch (Exception e)
			{
				_log.warn(e.getMessage());
			}
		}
	}
}
