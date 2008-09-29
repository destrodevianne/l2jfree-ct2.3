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
package com.l2jfree.gameserver.handler.skillhandlers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.l2jfree.gameserver.handler.ISkillHandler;
import com.l2jfree.gameserver.model.L2Character;
import com.l2jfree.gameserver.model.L2Object;
import com.l2jfree.gameserver.model.L2Skill;
import com.l2jfree.gameserver.model.L2Skill.SkillType;
import com.l2jfree.gameserver.model.actor.instance.L2PcInstance;
import com.l2jfree.gameserver.model.zone.L2Zone;
import com.l2jfree.gameserver.model.restriction.AvailableRestriction;
import com.l2jfree.gameserver.model.restriction.ObjectRestrictions;
import com.l2jfree.gameserver.network.SystemMessageId;
import com.l2jfree.gameserver.network.serverpackets.ConfirmDlg;
import com.l2jfree.gameserver.network.serverpackets.SystemMessage;
import com.l2jfree.gameserver.util.Util;

/**
 * @authors BiTi, Sami
 * 
 */
public class SummonFriend implements ISkillHandler
{
	private static final Log			_log		= LogFactory.getLog(SummonFriend.class.getName());
	private static final SkillType[]	SKILL_IDS	=
													{ SkillType.SUMMON_FRIEND };

	public static boolean checkSummonerStatus(L2PcInstance summonerChar)
	{
		if (summonerChar == null)
			return false;

		if (summonerChar.isInOlympiadMode())
		{
			summonerChar.sendPacket(new SystemMessage(SystemMessageId.THIS_ITEM_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT));
			return false;
		}

		if (summonerChar.isInsideZone(L2Zone.FLAG_NOSUMMON))
		{
			summonerChar.sendPacket(new SystemMessage(SystemMessageId.YOUR_TARGET_IS_IN_AN_AREA_WHICH_BLOCKS_SUMMONING));
			return false;
		}
		return true;
	}

	public static boolean checkTargetStatus(L2PcInstance targetChar, L2PcInstance summonerChar)
	{
		if (targetChar == null)
			return false;

		if (targetChar.isAlikeDead())
		{
			SystemMessage sm = new SystemMessage(SystemMessageId.S1_IS_DEAD_AT_THE_MOMENT_AND_CANNOT_BE_SUMMONED);
			sm.addPcName(targetChar);
			summonerChar.sendPacket(sm);
			return false;
		}

		if (targetChar.isInStoreMode())
		{
			SystemMessage sm = new SystemMessage(SystemMessageId.S1_CURRENTLY_TRADING_OR_OPERATING_PRIVATE_STORE_AND_CANNOT_BE_SUMMONED);
			sm.addPcName(targetChar);
			summonerChar.sendPacket(sm);
			return false;
		}

		if (targetChar.isRooted() || targetChar.isInCombat())
		{
			SystemMessage sm = new SystemMessage(SystemMessageId.S1_IS_ENGAGED_IN_COMBAT_AND_CANNOT_BE_SUMMONED);
			sm.addPcName(targetChar);
			summonerChar.sendPacket(sm);
			return false;
		}

		if (targetChar.isInOlympiadMode())
		{
			summonerChar.sendPacket(new SystemMessage(SystemMessageId.YOU_CANNOT_SUMMON_PLAYERS_WHO_ARE_IN_OLYMPIAD));
			return false;
		}

		if (targetChar.isFestivalParticipant())
		{
			summonerChar.sendPacket(new SystemMessage(SystemMessageId.YOUR_TARGET_IS_IN_AN_AREA_WHICH_BLOCKS_SUMMONING));
			return false;
		}

		if (targetChar.isInsideZone(L2Zone.FLAG_NOSUMMON))
		{
			SystemMessage sm = new SystemMessage(SystemMessageId.S1_IN_SUMMON_BLOCKING_AREA);
			sm.addString(targetChar.getName());
			summonerChar.sendPacket(sm);
			return false;
		}

		if (ObjectRestrictions.getInstance()
				.checkRestriction(targetChar, AvailableRestriction.PlayerSummonFriend))
		{
			summonerChar.sendMessage("You cannot summon your friend due to his restrictions.");
			targetChar.sendMessage("You cannot be summoned due to a restriction.");
			return false;
		}
		
		if (summonerChar._inEventCTF || summonerChar._inEventTvT || summonerChar._inEventDM || summonerChar._inEventVIP 
				|| targetChar._inEventCTF || targetChar._inEventTvT || targetChar._inEventDM || targetChar._inEventVIP)
		{
			summonerChar.sendMessage("You cannot summon your friend due to events restrictions.");
			targetChar.sendMessage("You cannot be summoned due to events restriction.");
			return false;
		}

		return true;
	}

	public static void teleToTarget(L2PcInstance targetChar, L2PcInstance summonerChar, L2Skill summonSkill)
	{
		if (targetChar == null || summonerChar == null || summonSkill == null)
			return;

		if (!checkSummonerStatus(summonerChar))
			return;
		if (!checkTargetStatus(targetChar, summonerChar))
			return;

		int itemConsumeId = summonSkill.getTargetConsumeId();
		int itemConsumeCount = summonSkill.getTargetConsume();
		if (itemConsumeId != 0 && itemConsumeCount != 0)
		{
			if (targetChar.getInventory().getInventoryItemCount(itemConsumeId, 0) < itemConsumeCount)
			{
				SystemMessage sm = new SystemMessage(SystemMessageId.S1_REQUIRED_FOR_SUMMONING);
				sm.addItemName(itemConsumeId);
				targetChar.sendPacket(sm);
				return;
			}
			targetChar.getInventory().destroyItemByItemId("Consume", itemConsumeId, itemConsumeCount, summonerChar, targetChar);
			SystemMessage sm = new SystemMessage(SystemMessageId.S1_HAS_DISAPPEARED);
			sm.addItemName(itemConsumeId);
			targetChar.sendPacket(sm);
		}
		targetChar.teleToLocation(summonerChar.getX(), summonerChar.getY(), summonerChar.getZ(), true);
	}

	public void useSkill(L2Character activeChar, L2Skill skill, L2Object... targets)
	{
		if (!(activeChar instanceof L2PcInstance))
			return; // currently not implemented for others

		L2PcInstance activePlayer = (L2PcInstance) activeChar;
		if (!checkSummonerStatus(activePlayer))
			return;

		try
		{
			for (L2Object element : targets)
			{
				if (!(element instanceof L2Character))
					continue;

				L2Character target = (L2Character) element;

				if (activeChar == target)
					continue;

				if (target instanceof L2PcInstance)
				{
					L2PcInstance targetChar = (L2PcInstance) target;

					if (!checkTargetStatus(targetChar, activePlayer))
						continue;

					if (!Util.checkIfInRange(0, activeChar, target, false))
					{
						if (!targetChar.teleportRequest(activePlayer, skill))
 						{
							SystemMessage sm = new SystemMessage(SystemMessageId.S1_ALREADY_SUMMONED);
							sm.addString(target.getName());
							activePlayer.sendPacket(sm);
							continue;
						}

						if (skill.getId() == 1403) //summon friend
						{
							// Send message
							ConfirmDlg confirm = new ConfirmDlg(SystemMessageId.S1_WISHES_TO_SUMMON_YOU_FROM_S2_DO_YOU_ACCEPT.getId());
							confirm.addCharName(activeChar);
							confirm.addZoneName(activeChar.getX(), activeChar.getY(), activeChar.getZ());
							confirm.addTime(30000);
							confirm.addRequesterId(activePlayer.getCharId());
							target.sendPacket(confirm);
						}
						else
						{
							teleToTarget(targetChar, activePlayer, skill);
							targetChar.teleportRequest(null, null);
						}
					}
				}
			}
		}
		catch (Throwable e)
		{
			_log.error(e.getMessage(), e);
		}
	}

	public SkillType[] getSkillIds()
	{
		return SKILL_IDS;
	}
}
