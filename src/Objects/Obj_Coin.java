package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Coin extends Entity{

    GamePanel gp;

    public Obj_Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = "Coin";
        value = 1;
        down1 = setup("/Objects/coin", gp.tileSize, gp.tileSize);
    }

    public boolean use(Entity entity){
        gp.PlaySE(1);
        gp.ui.addMessage("Coin + "+value);
        gp.player.coin += value;
        return true;
    }
}
