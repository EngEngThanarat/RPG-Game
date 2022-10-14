package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Heart extends Entity {
        
    GamePanel gp;

    public Obj_Heart(GamePanel gp){
        super(gp);

        name = "Heart";
        image = setup("/Objects/heart_full",gp.tileSize,gp.tileSize);
        image2 = setup("/Objects/heart_half",gp.tileSize,gp.tileSize);
        image3 = setup("/Objects/heart_null",gp.tileSize,gp.tileSize);
    }
}
