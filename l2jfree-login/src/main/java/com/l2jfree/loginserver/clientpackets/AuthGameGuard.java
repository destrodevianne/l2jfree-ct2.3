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
package com.l2jfree.loginserver.clientpackets;

import com.l2jfree.loginserver.L2LoginClient.LoginClientState;
import com.l2jfree.loginserver.serverpackets.GGAuth;
import com.l2jfree.loginserver.serverpackets.LoginFail;

/**
 * @author -Wooden-
 * Format: ddddd
 * 
 */
public class AuthGameGuard extends L2LoginClientPacket
{
	private int	_sessionId;
	private int	_data1;
	private int	_data2;
	private int	_data3;
	private int	_data4;

	public int getSessionId()
	{
		return _sessionId;
	}

	public int getData1()
	{
		return _data1;
	}

	public int getData2()
	{
		return _data2;
	}

	public int getData3()
	{
		return _data3;
	}

	public int getData4()
	{
		return _data4;
	}

	/**
	 * @see com.l2jfree.loginserver.clientpackets.L2LoginClientPacket#readImpl()
	 */
	@Override
	protected boolean readImpl()
	{
		if (getAvaliableBytes() >= 20)
		{
			_sessionId = readD();
			_data1 = readD();
			_data2 = readD();
			_data3 = readD();
			_data4 = readD();
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * @see com.l2jfree.mmocore.network.ReceivablePacket#run()
	 */
	@Override
	public void run()
	{
		if (_sessionId == getClient().getSessionId())
		{
			getClient().setState(LoginClientState.AUTHED_GG);
			getClient().sendPacket(new GGAuth(getClient().getSessionId()));
		}
		else
		{
			//this.getClient().closeLogin(LoginFail.REASON_ACCESS_FAILED_TRY_AGAIN);
			getClient().closeLogin(LoginFail.REASON_IGNORE);
		}
	}
}
