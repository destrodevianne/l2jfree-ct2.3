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


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.l2jfree.Config;
import com.l2jfree.gameserver.datatables.SkillTable;
import com.l2jfree.gameserver.model.L2Skill;
import com.l2jfree.gameserver.model.L2Skill.SkillType;
import com.l2jfree.gameserver.model.actor.instance.L2PcInstance;
import com.l2jfree.gameserver.model.restriction.AvailableRestriction;
import com.l2jfree.gameserver.model.restriction.ObjectRestrictions;
import com.l2jfree.gameserver.network.serverpackets.ActionFailed;

/**
 * This class ...
 * 
 * @version $Revision: 1.7.2.1.2.3 $ $Date: 2005/03/27 15:29:30 $
 */
public class RequestMagicSkillUse extends L2GameClientPacket
{
	private static final String _C__2F_REQUESTMAGICSKILLUSE = "[C] 2F RequestMagicSkillUse";
	private final static Log _log = LogFactory.getLog(RequestMagicSkillUse.class.getName());

	private int _magicId;
	private boolean _ctrlPressed;
	private boolean _shiftPressed;
	
	/**
	 * packet type id 0x2f
	 * format:		cddc
	 * @param rawPacket
	 */
	@Override
	protected void readImpl()
	{
		_magicId	  = readD();			// Identifier of the used skill
		_ctrlPressed  = readD() != 0;		// True if it's a ForceAttack : Ctrl pressed
		_shiftPressed = readC() != 0;		// True if Shift pressed
	}

	@Override
	protected void runImpl()
	{
		//Get the current L2PcInstance of the player
		L2PcInstance activeChar = getClient().getActiveChar();
		
		if (activeChar == null)
			return;
		
		if (ObjectRestrictions.getInstance()
        		.checkRestriction(activeChar, AvailableRestriction.PlayerChat)) {
			activeChar.sendMessage("You cannot cast a skill due to a restriction.");
			return;
		}

		// Get the level of the used skill
		int level = activeChar.getSkillLevel(_magicId);
		if (level <= 0) 
		{
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (activeChar.isOutOfControl())
		{
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return; 
		}

		// Get the L2Skill template corresponding to the skillID received from the client
		L2Skill skill = SkillTable.getInstance().getInfo(_magicId, level);

		// Check the validity of the skill
		if (skill != null && skill.getSkillType() != SkillType.NOTDONE)
		{
			// If Alternate rule Karma punishment is set to true, forbid skill Return to player with Karma
			if (skill.getSkillType() == L2Skill.SkillType.RECALL && !Config.ALT_GAME_KARMA_PLAYER_CAN_TELEPORT && activeChar.getKarma() > 0)
				return;
			
			activeChar.useMagic(skill, _ctrlPressed, _shiftPressed);
		}
		else
		{
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			if (_log.isDebugEnabled()) _log.debug("No skill Id "+_magicId+" found!");
		}
	}

	/* (non-Javadoc)
	 * @see com.l2jfree.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__2F_REQUESTMAGICSKILLUSE;
	}
}
