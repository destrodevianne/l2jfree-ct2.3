import sys
from net.sf.l2j.gameserver.model.quest.jython import QuestJython as JQuest
from net.sf.l2j.gameserver.serverpackets import MagicSkillUser
from net.sf.l2j.gameserver.ai import CtrlIntention

# Angel spawns...when one of the angels in the keys dies, the other angel will spawn.

class polymorphing_angel(JQuest) :

    # init function.  Add in here variables that you'd like to be inherited by subclasses (if any)
    def __init__(self,id,name,descr):
        self.AngelSpawns ={
                20830:20859,
                21067:21068,
                21062:21063,
                20831:20860,
                21070:21071
                }
        # finally, don't forget to call the parent constructor to prepare the event triggering
        # mechanisms etc.
        JQuest.__init__(self,id,name,descr)

    def onKill (self,npc,player):
        npcId = npc.getNpcId()
        if self.AngelSpawns.has_key(npcId) :
            objId = self.getPcSpawn(player).addSpawn(self.AngelSpawns[npcId],npc, False)
            newNpc = self.getPcSpawn(player).getSpawn(objId).getLastSpawn()
            newNpc.addDamageHate(player,0,100)
        return 

# now call the constructor (starts up the ai)
QUEST		= polymorphing_angel(-1,"group_template","ai")
for i in QUEST.AngelSpawns.keys() :
    QUEST.addKillId(i)

print "AI: group template: Angel Spawns...loaded!"
