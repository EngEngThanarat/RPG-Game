package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Potion_Red extends Entity {

    GamePanel gp;

    public Obj_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp = gp;
        
        type = type_consumable;
        name = "Red Potion";
        value = 5;
        down1 = setup("/Objects/red_potion", gp.tileSize, gp.tileSize);
        description = "["+ name + "]\nAn potion\nfor heal your life by " + value + ".";
    }
    
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the "+name+"!\n"+"Your life has been recovered by "+ value + ".";
        entity.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        gp.PlaySE(2);
    }
}
