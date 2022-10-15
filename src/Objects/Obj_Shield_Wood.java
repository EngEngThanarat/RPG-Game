package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Shield_Wood extends Entity {

    public Obj_Shield_Wood(GamePanel gp) {
        super(gp);
    
        name = "Wood Shield";
        down1 = setup("/Objects/shields", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }
}
