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
package com.l2jfree.gameserver.skills.effects;

import com.l2jfree.gameserver.ai.CtrlIntention;
import com.l2jfree.gameserver.model.L2Effect;
import com.l2jfree.gameserver.model.actor.L2Playable;
import com.l2jfree.gameserver.model.actor.instance.L2PcInstance;
import com.l2jfree.gameserver.model.actor.instance.L2SiegeSummonInstance;
import com.l2jfree.gameserver.network.serverpackets.MyTargetSelected;
import com.l2jfree.gameserver.skills.Env;
import com.l2jfree.gameserver.templates.effects.EffectTemplate;
import com.l2jfree.gameserver.templates.skills.L2EffectType;

/**
 * 
 * @author -Nemesiss-
 */
public class EffectTargetMe extends L2Effect
{
	public EffectTargetMe(Env env, EffectTemplate template)
	{
		super(env, template);
	}
	
	/**
	 * 
	 * @see com.l2jfree.gameserver.model.L2Effect#getEffectType()
	 */
	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.TARGET_ME;
	}
	
	/**
	 * 
	 * @see com.l2jfree.gameserver.model.L2Effect#onStart()
	 */
	@Override
	public boolean onStart()
	{
		if (getEffected() instanceof L2Playable)
		{
			if (getEffected() instanceof L2SiegeSummonInstance)
				return false;

			if (getEffected().getTarget() != getEffector())
			{
				// Target is different - stop autoattack and break cast
				getEffected().setTarget(getEffector());
				getEffected().abortAttack();
				getEffected().abortCast();
				if (getEffected() instanceof L2PcInstance)
					getEffected().sendPacket(new MyTargetSelected(getEffector().getObjectId(), 0));
				getEffected().getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
			}
			((L2Playable)getEffected()).setLockedTarget(getEffector());
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @see com.l2jfree.gameserver.model.L2Effect#onExit()
	 */
	@Override
	public void onExit()
	{
		if (getEffected() instanceof L2Playable)
			((L2Playable)getEffected()).setLockedTarget(null);
	}
	
	/**
	 * 
	 * @see com.l2jfree.gameserver.model.L2Effect#onActionTime()
	 */
	@Override
	public boolean onActionTime()
	{
		// nothing
		return false;
	}
}
