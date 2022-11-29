package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Chest extends Entity{
        
    GamePanel gp;
    Entity chest;
    boolean opened = false;

    public Obj_Chest(GamePanel gp, Entity chest){
        super(gp);
        this.gp = gp;
        this.chest = chest;

        type = type_obstacle;
        name = "Chest";
        image = setup("/objects/Box",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/Box_Open",gp.tileSize,gp.tileSize);
        down1 = image;
        collision = true;
        
        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact(){

        gp.gameState = gp.dialogueState;

        if(opened == false){
            gp.PlaySE(3);

            StringBuilder sb = new StringBuilder();
            sb.append("You open the chest and find a "+ chest.name + "!");
            
            if(gp.player.canObtainItem(chest) == false){
                sb.append("\n...But you cannot carry any more!");
            }
            else{
                sb.append("\nYou obtain the "+ chest.name + "!");
                down1 = image2;
                opened = true;
            }
            gp.ui.currentDialogue = sb.toString();
        }
        else{
            gp.ui.currentDialogue = "It's empty";
        }
    }
}
