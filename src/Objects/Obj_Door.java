package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Door extends Entity {
        
    GamePanel gp;

    public Obj_Door(GamePanel gp){
        super(gp);

        name = "Key";
        down1= setup("/objects/door");
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
