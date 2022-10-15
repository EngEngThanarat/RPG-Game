package Monster;

import java.util.Random;

import Entity.Entity;
import main.GamePanel;

public class Slime extends Entity{

    GamePanel gp;

    public Slime(GamePanel gp) {
        super(gp);

        this.gp = gp;

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
        up1 = setup("/Monster/Slime1",gp.tileSize,gp.tileSize);
        up2 = setup("/Monster/Slime2",gp.tileSize,gp.tileSize);
        up3 = setup("/Monster/Slime3",gp.tileSize,gp.tileSize);
        up4 = setup("/Monster/Slime4",gp.tileSize,gp.tileSize);
        up5 = setup("/Monster/Slime5",gp.tileSize,gp.tileSize);
        up6 = setup("/Monster/Slime6",gp.tileSize,gp.tileSize);
        down1 = setup("/Monster/Slime1",gp.tileSize,gp.tileSize);
        down2 = setup("/Monster/Slime2",gp.tileSize,gp.tileSize);
        down3 = setup("/Monster/Slime3",gp.tileSize,gp.tileSize);
        down4 = setup("/Monster/Slime4",gp.tileSize,gp.tileSize);
        down5 = setup("/Monster/Slime5",gp.tileSize,gp.tileSize);
        down6 = setup("/Monster/Slime6",gp.tileSize,gp.tileSize);
        left1 = setup("/Monster/Slime1",gp.tileSize,gp.tileSize);
        left2 = setup("/Monster/Slime2",gp.tileSize,gp.tileSize);
        left3 = setup("/Monster/Slime3",gp.tileSize,gp.tileSize);
        left4 = setup("/Monster/Slime4",gp.tileSize,gp.tileSize);
        left5 = setup("/Monster/Slime5",gp.tileSize,gp.tileSize);
        left6 = setup("/Monster/Slime6",gp.tileSize,gp.tileSize);
        right1 = setup("/Monster/Slime1",gp.tileSize,gp.tileSize);
        right2 = setup("/Monster/Slime2",gp.tileSize,gp.tileSize);
        right3 = setup("/Monster/Slime3",gp.tileSize,gp.tileSize);
        right4 = setup("/Monster/Slime4",gp.tileSize,gp.tileSize);
        right5 = setup("/Monster/Slime5",gp.tileSize,gp.tileSize);
        right6 = setup("/Monster/Slime6",gp.tileSize,gp.tileSize);
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

    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
