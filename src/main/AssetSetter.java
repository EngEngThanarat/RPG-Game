package main;

import Entity.NPC_Merchant;
import Entity.NPC_OldMan;
import Monster.Slime;
import Objects.Obj_Axe;
import Objects.Obj_Coin;
import Objects.Obj_Halberd;
import Objects.Obj_Heart;
import Objects.Obj_Potion_Red;
import Objects.Obj_rock_shield;

public class AssetSetter {
    GamePanel gp;
     
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new Obj_Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*25;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;

        gp.obj[mapNum][i] = new Obj_Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*24;
        gp.obj[mapNum][i].worldY = gp.tileSize*18;
        i++;

        gp.obj[mapNum][i] = new Obj_Halberd(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*38;
        gp.obj[mapNum][i].worldY = gp.tileSize*10;
        i++;

        gp.obj[mapNum][i] = new Obj_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*36;
        gp.obj[mapNum][i].worldY = gp.tileSize*40;
        i++;

        gp.obj[mapNum][i] = new Obj_rock_shield(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*36;
        gp.obj[mapNum][i].worldY = gp.tileSize*21;
        i++;

        gp.obj[mapNum][i] = new Obj_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*22;
        gp.obj[mapNum][i].worldY = gp.tileSize*27;
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
