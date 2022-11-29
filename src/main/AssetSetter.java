package main;

import Entity.NPC_Merchant;
import Entity.NPC_OldMan;
import Monster.Slime;
import Objects.Obj_Chest;
import Objects.Obj_Door;
import Objects.Obj_Key;


public class AssetSetter {
    GamePanel gp;
     
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        int mapNum = 0;
        int i = 0;

        gp.obj[mapNum][i] = new Obj_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*14;
        gp.obj[mapNum][i].worldY = gp.tileSize*28;
        i++;

        gp.obj[mapNum][i] = new Obj_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*10;
        gp.obj[mapNum][i].worldY = gp.tileSize*12;
        i++;

        gp.obj[mapNum][i] = new Obj_Chest(gp,new Obj_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*34;
        gp.obj[mapNum][i].worldY = gp.tileSize*36;
        i++;
    }

    public void setNPC(){
        int mapNum = 0;
        int i = 0;

        // MAP 0
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*21;
        gp.npc[mapNum][i].worldY = gp.tileSize*21;
        i++;

        // MAP 1
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*12;
        gp.npc[mapNum][i].worldY = gp.tileSize*7;
        i++;
    }

    public void setMonster(){
        int mapNum = 0;
        int i = 0;
        gp.Monster[mapNum][i] = new Slime(gp);
        gp.Monster[mapNum][i].worldX = gp.tileSize*23;
        gp.Monster[mapNum][i].worldY = gp.tileSize*36;
        i++;

        gp.Monster[mapNum][i] = new Slime(gp);
        gp.Monster[mapNum][i].worldX = gp.tileSize*22;
        gp.Monster[mapNum][i].worldY = gp.tileSize*37;
        i++;

        gp.Monster[mapNum][i] = new Slime(gp);
        gp.Monster[mapNum][i].worldX = gp.tileSize*25;
        gp.Monster[mapNum][i].worldY = gp.tileSize*19;
        i++;
    }
}
