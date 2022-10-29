package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Halberd extends Entity {

    public Obj_Halberd(GamePanel gp) {
        super(gp);
        
        type = type_halberd;
        name = "Halberd";
        down1 = setup("/Objects/halberd", gp.tileSize, gp.tileSize);
        attackValue = 3;
        description = "["+ name + "]\nAn Halberd \nmore attack distance \nthan a sword";
        attackArea.width = 42;
        attackArea.height = 42;
    }
}
