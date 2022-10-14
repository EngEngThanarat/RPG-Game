package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Chest extends Entity{
        
    GamePanel gp;

    public Obj_Chest(GamePanel gp){
        super(gp);

        name = "Key";
        down1= setup("/objects/Box",gp.tileSize,gp.tileSize);
    }
}
