package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Key extends Entity {
    
    GamePanel gp;

    public Obj_Key(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Key";
        down1= setup("/objects/Key1",gp.tileSize,gp.tileSize);
        description = "["+ name + "]\nAn Open The Door";
        price = 100;
        stackable = true;
    }

    public boolean use(Entity entity){
        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Door");

        if(objIndex != 999){
            gp.ui.currentDialogue = "You use the " + name + " and open the door";
            gp.PlaySE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else{
            gp.ui.currentDialogue = "What are you doing?";
            return false;
        }
    }
}
