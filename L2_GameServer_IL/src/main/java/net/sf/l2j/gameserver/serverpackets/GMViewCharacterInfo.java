/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package net.sf.l2j.gameserver.serverpackets;

import net.sf.l2j.gameserver.model.Inventory;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
/**
 *
 * TODO Add support for Eval. Score
 *
 * dddddSdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddffffddddSddd   rev420
 * dddddSdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddffffddddSdddcccddhh  rev478
 * dddddSdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddffffddddSdddcccddhhddd rev551
 * @version $Revision: 1.2.2.2.2.8 $ $Date: 2005/03/27 15:29:39 $
 */
public class GMViewCharacterInfo extends L2GameServerPacket
{
	private static final String _S__04_USERINFO = "[S] 8F GMViewCharacterInfo";
	private L2PcInstance _cha;

	/**
	 * @param _characters
	 */
	public GMViewCharacterInfo(L2PcInstance cha)
	{
		_cha = cha;
	}

	protected final void writeImpl()
	{
		float moveMultiplier = _cha.getStat().getMovementSpeedMultiplier();
        int _runSpd = (int) (_cha.getStat().getRunSpeed() / moveMultiplier);
        int _walkSpd = (int) (_cha.getStat().getWalkSpeed() / moveMultiplier);

		writeC(0x8f);

		writeD(_cha.getX());
		writeD(_cha.getY());
		writeD(_cha.getZ());
		writeD(_cha.getHeading());
		writeD(_cha.getObjectId());
		writeS(_cha.getName());
		writeD(_cha.getRace().ordinal());
		writeD(_cha.getAppearance().getSex()? 1 : 0);
		writeD(_cha.getClassId().getId());
		writeD(_cha.getLevel());
		writeQ(_cha.getExp());
		writeD(_cha.getStat().getSTR());
		writeD(_cha.getStat().getDEX());
		writeD(_cha.getStat().getCON());
		writeD(_cha.getStat().getINT());
		writeD(_cha.getStat().getWIT());
		writeD(_cha.getStat().getMEN());
		writeD(_cha.getStat().getMaxHp());
		writeD((int) _cha.getStatus().getCurrentHp());
		writeD(_cha.getStat().getMaxMp());
		writeD((int)_cha.getStatus().getCurrentMp());
		writeD(_cha.getSp());
		writeD(_cha.getCurrentLoad());
		writeD(_cha.getMaxLoad());

		writeD(0x28);  // unknown

		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_UNDER));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_REAR));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEAR));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_NECK));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RFINGER));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LFINGER));

		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HEAD));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_RHAND));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LHAND));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_GLOVES));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_CHEST));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LEGS));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FEET));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_BACK));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_LRHAND));
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_HAIR));

		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_UNDER));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_REAR)); 
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEAR));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_NECK));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RFINGER));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LFINGER));

		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HEAD));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FEET));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_BACK));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_LRHAND));
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_HAIR));

		writeD(_cha.getStat().getPAtk(null));
		writeD(_cha.getPAtkSpd());
		writeD(_cha.getStat().getPDef(null));
		writeD(_cha.getStat().getEvasionRate(null));
		writeD(_cha.getStat().getAccuracy());
		writeD(_cha.getStat().getCriticalHit(null, null));
		writeD(_cha.getStat().getMAtk(null, null));

		writeD(_cha.getMAtkSpd());
		writeD(_cha.getPAtkSpd());

		writeD(_cha.getStat().getMDef(null, null));

		writeD(_cha.getPvpFlag()); // 0-non-pvp  1-pvp = violett name
		writeD(_cha.getKarma());

        writeD(_runSpd);
        writeD(_walkSpd);
        writeD(_runSpd); // swimspeed
        writeD(_walkSpd); // swimspeed
        writeD(_runSpd);
        writeD(_walkSpd);
        writeD(_runSpd);
        writeD(_walkSpd);
		writeF(moveMultiplier);
		writeF(_cha.getStat().getAttackSpeedMultiplier()); //2.9);//
		writeF(_cha.getTemplate().collisionRadius);  // scale
		writeF(_cha.getTemplate().collisionHeight); // y offset ??!? fem dwarf 4033
		writeD(_cha.getAppearance().getHairStyle());
		writeD(_cha.getAppearance().getHairColor());
		writeD(_cha.getAppearance().getFace());
		writeD(_cha.isGM() ? 0x01 : 0x00);	// builder level

		writeS(_cha.getTitle());
		writeD(_cha.getClanId());		// pledge id
		writeD(_cha.getClanCrestId());		// pledge crest id
		writeD(_cha.getAllyId());		// ally id 
        writeC(_cha.getMountType()); // mount type
        writeC(_cha.getPrivateStoreType());
        writeC(_cha.hasDwarvenCraft() ? 1 : 0);
		writeD(_cha.getPkKills());
		writeD(_cha.getPvpKills());

		writeH(_cha.getCharRecommendationStatus().getRecomLeft());
		writeH(_cha.getCharRecommendationStatus().getRecomHave()); //Blue value for name (0 = white, 255 = pure blue)
		writeD(_cha.getClassId().getId());
		writeD(0x00); // special effects? circles around player...
		writeD(_cha.getStat().getMaxCp());
		writeD((int) _cha.getStatus().getCurrentCp());
		
		//new c5 
       	writeC(_cha.isRunning() ? 0x01 : 0x00); //changes the Speed display on Status Window 
        
		writeD(_cha.getInventory().getPaperdollObjectId(Inventory.PAPERDOLL_FACE));
        
		writeD(_cha.getInventory().getPaperdollItemId(Inventory.PAPERDOLL_FACE));
        
        writeD(_cha.getPledgeClass()); //changes the text above CP on Status Window
        writeD(0x00);
        
        writeD(_cha.getAppearance().getNameColor());
        
        writeD(0x00);
	}

	/* (non-Javadoc)
	 * @see net.sf.l2j.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	public String getType()
	{
		return _S__04_USERINFO;
	}
}
