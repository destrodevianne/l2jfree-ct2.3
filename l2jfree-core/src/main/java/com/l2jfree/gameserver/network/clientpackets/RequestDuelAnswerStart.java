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
package com.l2jfree.gameserver.network.clientpackets;

import com.l2jfree.gameserver.instancemanager.DuelManager;
import com.l2jfree.gameserver.model.actor.instance.L2PcInstance;
import com.l2jfree.gameserver.network.SystemMessageId;
import com.l2jfree.gameserver.network.serverpackets.SystemMessage;

/**
 * Format:(ch) ddd
 * @author  -Wooden-
 */
public final class RequestDuelAnswerStart extends L2GameClientPacket
{
	private static final String _C__D0_28_REQUESTDUELANSWERSTART = "[C] D0:28 RequestDuelAnswerStart";
	
	private int _partyDuel;
	//private int _unk1;
	private int _response;
	
	@Override
	protected void readImpl()
	{
		_partyDuel = readD();
		/*_unk1 = */readD();
		_response = readD();
	}
	
	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();
		if (player == null)
			return;
		L2PcInstance requestor = player.getActiveRequester();
		if (requestor == null)
		{
			sendAF();
			return;
		}
		
		if (_response == 1)
		{
			SystemMessage msg1 = null, msg2 = null;
			if (requestor.isInDuel())
			{
				msg1 = new SystemMessage(SystemMessageId.C1_CANNOT_DUEL_BECAUSE_C1_IS_ALREADY_ENGAGED_IN_A_DUEL);
				msg1.addString(requestor.getName());
				requestFailed(msg1);
				return;
			}
			else if (player.isInDuel())
			{
				requestFailed(SystemMessageId.YOU_ARE_UNABLE_TO_REQUEST_A_DUEL_AT_THIS_TIME);
				return;
			}
			
			if (_partyDuel == 1)
			{
				msg1 = new SystemMessage(SystemMessageId.YOU_ACCEPTED_C1_CHALLENGE_TO_PARTY_DUEL);
				msg1.addString(requestor.getName());
				
				msg2 = new SystemMessage(SystemMessageId.C1_ACCEPTED_YOUR_CHALLENGE_TO_A_PARTY_DUEL);
				msg2.addString(player.getName());
			}
			else
			{
				msg1 =
						new SystemMessage(
								SystemMessageId.YOU_HAVE_ACCEPTED_C1_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS);
				msg1.addString(requestor.getName());
				
				msg2 =
						new SystemMessage(
								SystemMessageId.C1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS);
				msg2.addString(player.getName());
			}
			
			sendPacket(msg1);
			requestor.sendPacket(msg2);
			
			DuelManager.getInstance().addDuel(requestor, player, _partyDuel);
		}
		else
		{
			SystemMessage msg;
			if (_partyDuel == 1)
				msg = SystemMessageId.THE_OPPOSING_PARTY_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL.getSystemMessage();
			else
			{
				msg = new SystemMessage(SystemMessageId.C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL);
				msg.addString(player.getName());
			}
			requestor.sendPacket(msg);
		}
		
		sendAF();
		
		player.setActiveRequester(null);
		requestor.onTransactionResponse();
	}
	
	@Override
	public String getType()
	{
		return _C__D0_28_REQUESTDUELANSWERSTART;
	}
}
