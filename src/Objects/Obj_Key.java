package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Key extends Entity {
    
    GamePanel gp;

    public Obj_Key(GamePanel gp){
        super(gp);

        name = "Key";
        down1= setup("/objects/Key1",gp.tileSize,gp.tileSize);
        description = "["+ name + "]\nAn Open The Door";
        price = 100;
    }
}
