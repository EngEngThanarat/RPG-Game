package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_rock_shield extends Entity {

    public Obj_rock_shield(GamePanel gp) {
        super(gp);
    
        type = type_shield;
        name = "Rock Shield";
        down1 = setup("/Objects/rock_shield", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "["+ name + "]\nAn Rock Shield";
    }
}
