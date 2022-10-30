package main;

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
        int i = 0;
        gp.obj[i] = new Obj_Heart(gp);
        gp.obj[i].worldX = gp.tileSize*25;
        gp.obj[i].worldY = gp.tileSize*23;
        i++;

        gp.obj[i] = new Obj_Coin(gp);
        gp.obj[i].worldX = gp.tileSize*24;
        gp.obj[i].worldY = gp.tileSize*18;
        i++;

        gp.obj[i] = new Obj_Halberd(gp);
        gp.obj[i].worldX = gp.tileSize*38;
        gp.obj[i].worldY = gp.tileSize*10;
        i++;

        gp.obj[i] = new Obj_Axe(gp);
        gp.obj[i].worldX = gp.tileSize*36;
        gp.obj[i].worldY = gp.tileSize*40;
        i++;

        gp.obj[i] = new Obj_rock_shield(gp);
        gp.obj[i].worldX = gp.tileSize*36;
        gp.obj[i].worldY = gp.tileSize*21;
        i++;

        gp.obj[i] = new Obj_Potion_Red(gp);
        gp.obj[i].worldX = gp.tileSize*22;
        gp.obj[i].worldY = gp.tileSize*27;
        i++;
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
        int i = 0;
        gp.Monster[i] = new Slime(gp);
        gp.Monster[i].worldX = gp.tileSize*23;
        gp.Monster[i].worldY = gp.tileSize*36;
        i++;

        gp.Monster[i] = new Slime(gp);
        gp.Monster[i].worldX = gp.tileSize*22;
        gp.Monster[i].worldY = gp.tileSize*37;
        i++;

        gp.Monster[i] = new Slime(gp);
        gp.Monster[i].worldX = gp.tileSize*25;
        gp.Monster[i].worldY = gp.tileSize*19;
        i++;
    }
}
