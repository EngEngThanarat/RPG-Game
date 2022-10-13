package main;

import Entity.NPC_OldMan;
import Objects.Obj_Door;

public class AssetSetter {
    GamePanel gp;
     
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0]= new Obj_Door(gp);
        gp.obj[0].worldX = gp.tileSize*10;
        gp.obj[0].worldY = gp.tileSize*12;

        gp.obj[1]= new Obj_Door(gp);
        gp.obj[1].worldX = gp.tileSize*12;
        gp.obj[1].worldY = gp.tileSize*23;
    }

    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;

        gp.npc[1] = new NPC_OldMan(gp);
        gp.npc[1].worldX = gp.tileSize*12;
        gp.npc[1].worldY = gp.tileSize*21;

        gp.npc[2] = new NPC_OldMan(gp);
        gp.npc[2].worldX = gp.tileSize*31;
        gp.npc[2].worldY = gp.tileSize*21;
    }
}
