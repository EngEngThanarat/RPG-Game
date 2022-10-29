package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Axe extends Entity {

    public Obj_Axe(GamePanel gp) {
        super(gp);
        
        type = type_axe;
        name = "Axe";
        down1 = setup("/Objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        description = "["+ name + "]\nAn Old axe \nCan cutting tree";
        attackArea.width = 36;
        attackArea.height = 36;
    }
    
}
