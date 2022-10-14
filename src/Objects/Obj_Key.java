package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Key extends Entity {
    
    GamePanel gp;

    public Obj_Key(GamePanel gp){
        super(gp);

        name = "Key";
        down1= setup("/objects/Key",gp.tileSize,gp.tileSize);
    }
}
