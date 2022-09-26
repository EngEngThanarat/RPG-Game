package main;

import Objects.Obj_Chest;
import Objects.Obj_Door;
import Objects.Obj_Key;
import Objects.Obj_boots;

public class AssetSetter {
    GamePanel gp;
     
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        // key in the top and center
        gp.obj[0] = new Obj_Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        // key in the down and center 
        gp.obj[1] = new Obj_Key(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        // key in the top and right
        gp.obj[2] = new Obj_Key(gp);
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 9 * gp.tileSize;

        // door in the center and left
        gp.obj[3] = new Obj_Door(gp);
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;

        // door in the top and left
        gp.obj[4] = new Obj_Door(gp);
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;

        // door in the and left
        gp.obj[5] = new Obj_Door(gp);
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 24 * gp.tileSize;

        gp.obj[6] = new Obj_Chest(gp);
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 9 * gp.tileSize;

        gp.obj[7] = new Obj_boots(gp);
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 42 * gp.tileSize;
    }
}
