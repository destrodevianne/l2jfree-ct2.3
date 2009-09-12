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
package com.l2jfree.gameserver.model.actor.view;

import com.l2jfree.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author NB4L1
 */
public final class PcView extends CharView<L2PcInstance> implements UniversalCharView
{
	public PcView(L2PcInstance activeChar)
	{
		super(activeChar);
	}
	
	@Override
	protected void refreshImpl()
	{
		super.refreshImpl();
		
		float moveMultiplier = _activeChar.getStat().getMovementSpeedMultiplier();
		
		_runSpd = (int) (_activeChar.getRunSpeed() / moveMultiplier);
		_walkSpd = (int) (_activeChar.getStat().getWalkSpeed() / moveMultiplier);
	}
}
