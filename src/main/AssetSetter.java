package main;

import Entity.NPC_OldMan;
import Monster.Slime;

public class AssetSetter {
    GamePanel gp;
     
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

    }

    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;

        //gp.npc[0] = new NPC_OldMan(gp);
        //gp.npc[0].worldX = gp.tileSize*9;
        //gp.npc[0].worldY = gp.tileSize*10;
    }

    public void setMonster(){
        gp.Monster[0] = new Slime(gp);
        gp.Monster[0].worldX = gp.tileSize*23;
        gp.Monster[0].worldY = gp.tileSize*36;

        gp.Monster[1] = new Slime(gp);
        gp.Monster[1].worldX = gp.tileSize*22;
        gp.Monster[1].worldY = gp.tileSize*37;

        gp.Monster[2] = new Slime(gp);
        gp.Monster[2].worldX = gp.tileSize*9;
        gp.Monster[2].worldY = gp.tileSize*11;

        gp.Monster[3] = new Slime(gp);
        gp.Monster[3].worldX = gp.tileSize*10;
        gp.Monster[3].worldY = gp.tileSize*8;

        gp.Monster[4] = new Slime(gp);
        gp.Monster[4].worldX = gp.tileSize*9;
        gp.Monster[4].worldY = gp.tileSize*10;

        gp.Monster[5] = new Slime(gp);
        gp.Monster[5].worldX = gp.tileSize*10;
        gp.Monster[5].worldY = gp.tileSize*11;

        gp.Monster[6] = new Slime(gp);
        gp.Monster[6].worldX = gp.tileSize*8;
        gp.Monster[6].worldY = gp.tileSize*11;
    }
}
