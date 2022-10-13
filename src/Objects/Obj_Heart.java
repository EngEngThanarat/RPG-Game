package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Heart extends Entity {
        
    GamePanel gp;

    public Obj_Heart(GamePanel gp){
        super(gp);

        name = "Heart";
        image = setup("/Objects/heart_full");
        image2 = setup("/Objects/heart_half");
        image3 = setup("/Objects/heart_null");
    }
}
