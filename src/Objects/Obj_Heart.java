package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Heart extends Entity {
        
    GamePanel gp;

    public Obj_Heart(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = "Heart";
        value = 2;
        down1 = setup("/Objects/Pickup_Heart",gp.tileSize,gp.tileSize);
        image = setup("/Objects/heart_full",gp.tileSize,gp.tileSize);
        image2 = setup("/Objects/heart_half",gp.tileSize,gp.tileSize);
        image3 = setup("/Objects/heart_null",gp.tileSize,gp.tileSize);
    }
    
    public void use(Entity entity) {
        gp.PlaySE(2);
        gp.ui.addMessage("Life + "+value);
        entity.life += value;
    }
}
