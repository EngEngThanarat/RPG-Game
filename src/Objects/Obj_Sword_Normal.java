package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Sword_Normal extends Entity{

    public Obj_Sword_Normal(GamePanel gp) {
        super(gp);
        
        type = type_sword;
        name = "Normal Sword";
        down1 = setup("/Objects/sword", gp.tileSize, gp.tileSize);
        attackValue = 3;
        description = "["+ name + "]\nAn Old Sword";
        attackArea.width = 36;
        attackArea.height = 36;
        knockBackPower = 2;
        price = 25;
    }
    
}
