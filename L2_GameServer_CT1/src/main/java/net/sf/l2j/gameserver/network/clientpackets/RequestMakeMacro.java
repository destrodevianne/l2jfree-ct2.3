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
package net.sf.l2j.gameserver.network.clientpackets;

import net.sf.l2j.gameserver.model.L2Macro;
import net.sf.l2j.gameserver.model.L2Macro.L2MacroCmd;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.SystemMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestMakeMacro extends L2GameClientPacket
{
	private L2Macro _macro;
    private int _commandsLenght = 0;
    private final static Log _log = LogFactory.getLog(RequestMakeMacro.class);
        
	private static final String _C__C1_REQUESTMAKEMACRO = "[C] C1 RequestMakeMacro";
	private static final int MAX_MACRO_LENGTH = 12;
	
	/**
	 * packet type id 0xc1
	 * 
	 * sample
	 * 
	 * c1
	 * d // id
	 * S // macro name
     * S // unknown  desc
     * S // unknown  acronym
     * c // icon
     * c // count
     * 
     * c // entry
     * c // type
     * d // skill id
     * c // shortcut id
     * S // command name
	 * 
	 * format:		cdSSScc (ccdcS)
	 * @param decrypt
	 */
    @Override
    protected void readImpl()
	{
		int _id = readD();
        String _name = readS();
		String _desc = readS();
		String _acronym = readS();
		int _icon = readC();
		int _count = readC();
		if (_count > MAX_MACRO_LENGTH) _count = MAX_MACRO_LENGTH;
		
		L2MacroCmd[] commands = new L2MacroCmd[_count];  
        if (_log.isDebugEnabled()) _log.debug("Make macro id:"+_id+"\tname:"+_name+"\tdesc:"+_desc+"\tacronym:"+_acronym+"\ticon:"+_icon+"\tcount:"+_count);
        for (int i = 0; i < _count; i++)
        {
            int entry      = readC();
            int type       = readC(); // 1 = skill, 3 = action, 4 = shortcut
            int d1         = readD(); // skill or page number for shortcuts
            int d2         = readC();
            String command = readS();
            _commandsLenght += command.length();
			commands[i] = new L2MacroCmd(entry, type, d1, d2, command);
            if (_log.isDebugEnabled()) _log.debug("entry:"+entry+"\ttype:"+type+"\td1:"+d1+"\td2:"+d2+"\tcommand:"+command);
        }
		_macro = new L2Macro(_id, _icon, _name, _desc, _acronym, commands);
	}

    @Override
    protected void runImpl()
	{
		L2PcInstance  player = getClient().getActiveChar(); 
		if (player == null)
		    return;
		if (_commandsLenght > 255)
		{
           //Invalid macro. Refer to the Help file for instructions.
			player.sendPacket(new SystemMessage(SystemMessageId.INVALID_MACRO));
           return;
		}
        if (player.getMacroses().getAllMacroses().length > 48)
		{
			// In CT1, players are able to create up to 48 macros.
			player.sendPacket(new SystemMessage(SystemMessageId.YOU_MAY_CREATE_UP_TO_48_MACROS));
           return;
		}
		if (_macro.name.length() == 0)
		{
           //Enter the name of the macro.
			player.sendPacket(new SystemMessage(SystemMessageId.ENTER_THE_MACRO_NAME));
           return;
		}
		if (_macro.descr.length() > 32)
		{
           //Macro descriptions may contain up to 32 characters.
			player.sendPacket(new SystemMessage(SystemMessageId.MACRO_DESCRIPTION_MAX_32_CHARS));
           return;
		}
		player.registerMacro(_macro);
	}

	/* (non-Javadoc)
	 * @see net.sf.l2j.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__C1_REQUESTMAKEMACRO;
	}
}
