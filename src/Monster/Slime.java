package Monster;

import java.util.Random;

import Entity.Entity;
import main.GamePanel;

public class Slime extends Entity{

    public Slime(GamePanel gp) {
        super(gp);

        type = 2;
        name = "Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setup("/Monster/Slime1");
        up2 = setup("/Monster/Slime2");
        up3 = setup("/Monster/Slime3");
        up4 = setup("/Monster/Slime4");
        up5 = setup("/Monster/Slime5");
        up6 = setup("/Monster/Slime6");
        down1 = setup("/Monster/Slime1");
        down2 = setup("/Monster/Slime2");
        down3 = setup("/Monster/Slime3");
        down4 = setup("/Monster/Slime4");
        down5 = setup("/Monster/Slime5");
        down6 = setup("/Monster/Slime6");
        left1 = setup("/Monster/Slime1");
        left2 = setup("/Monster/Slime2");
        left3 = setup("/Monster/Slime3");
        left4 = setup("/Monster/Slime4");
        left5 = setup("/Monster/Slime5");
        left6 = setup("/Monster/Slime6");
        right1 = setup("/Monster/Slime1");
        right2 = setup("/Monster/Slime2");
        right3 = setup("/Monster/Slime3");
        right4 = setup("/Monster/Slime4");
        right5 = setup("/Monster/Slime5");
        right6 = setup("/Monster/Slime6");
    }
    
    public void setAction(){
        actionLockCounter++ ;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a number from 1 to 100
                if(i <= 25){
                    direction = "up";
                }
                if(i > 25 && i <= 50){
                    direction = "down";
                }
                if(i > 50 && i <= 75 ){
                    direction = "left";
                }
                if(i > 75 && i <= 100 ){
                    direction = "right";
                }
            actionLockCounter = 0;
        }
    }
}
