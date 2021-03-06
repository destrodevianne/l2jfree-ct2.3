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
package com.l2jfree.gameserver.network.packets.server;

import java.util.List;

import com.l2jfree.Config;
import com.l2jfree.gameserver.gameobjects.L2Player;
import com.l2jfree.gameserver.model.items.L2ItemInstance;
import com.l2jfree.gameserver.network.packets.L2ServerPacket;

/**
 * This class ...
 * 
 * @version $Revision: 1.4.2.1.2.3 $ $Date: 2005/03/27 15:29:39 $
 */
public class TradeStart extends L2ServerPacket
{
	private static final String _S__2E_TRADESTART = "[S] 1E TradeStart";
	private final L2Player _activeChar;
	private final List<L2ItemInstance> _itemList;
	
	public TradeStart(L2Player player)
	{
		_activeChar = player;
		_itemList =
				_activeChar.getInventory().getAvailableItems(true,
						(_activeChar.isGM() && Config.GM_TRADE_RESTRICTED_ITEMS));
	}
	
	@Override
	protected final void writeImpl()
	{//0x2e TradeStart   d h (h dddhh dhhh)
		if (_activeChar.getActiveTradeList() == null || _activeChar.getActiveTradeList().getPartner() == null)
			return;
		
		writeC(0x14);
		writeD(_activeChar.getActiveTradeList().getPartner().getObjectId());
		//writeD((_char != null || _char.getTransactionRequester() != null)? _char.getTransactionRequester().getObjectId() : 0);
		
		writeH(_itemList.size());
		for (L2ItemInstance item : _itemList)
		{
			writeH(item.getItem().getType1()); // item type1
			writeD(item.getObjectId());
			writeD(item.getItemDisplayId());
			writeCompQ(item.getCount());
			writeH(item.getItem().getType2()); // item type2
			writeH(0x00); // ?
			
			writeD(item.getItem().getBodyPart()); // rev 415  slot    0006-lr.ear  0008-neck  0030-lr.finger  0040-head  0080-??  0100-l.hand  0200-gloves  0400-chest  0800-pants  1000-feet  2000-??  4000-r.hand  8000-r.hand
			writeH(item.getEnchantLevel()); // enchant level
			writeH(0x00);
			writeH(item.getCustomType2());
			
			writeElementalInfo(item); //8x h or d
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.l2jfree.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__2E_TRADESTART;
	}
}
