package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_boots extends Entity{

    GamePanel gp;

    public Obj_boots(GamePanel gp){
        super(gp);

        name = "Key";
        down1= setup("/objects/speed.png");
    }
}
