package Entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

        getImage();
        setDialogue();
    }

    public void getImage() {
		up1 = setup("/npc/Old_man_up1",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/Old_man_up2",gp.tileSize,gp.tileSize);
		up3 = setup("/npc/Old_man_up3",gp.tileSize,gp.tileSize);
		up4 = setup("/npc/Old_man_up4",gp.tileSize,gp.tileSize);
        up5 = setup("/npc/Old_man_up5",gp.tileSize,gp.tileSize);
        up6 = setup("/npc/Old_man_up6",gp.tileSize,gp.tileSize);
		down1 = setup("/npc/Old_man_down1",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/Old_man_down2",gp.tileSize,gp.tileSize);
		down3 = setup("/npc/Old_man_down3",gp.tileSize,gp.tileSize);
		down4 = setup("/npc/Old_man_down4",gp.tileSize,gp.tileSize);
        down5 = setup("/npc/Old_man_down5",gp.tileSize,gp.tileSize);
        down6 = setup("/npc/Old_man_down6",gp.tileSize,gp.tileSize);
		left1 = setup("/npc/Old_man_left1",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/Old_man_left2",gp.tileSize,gp.tileSize);
		left3 = setup("/npc/Old_man_left3",gp.tileSize,gp.tileSize);
		left4 = setup("/npc/Old_man_left4",gp.tileSize,gp.tileSize);
        left5 = setup("/npc/Old_man_left5",gp.tileSize,gp.tileSize);
        left6 = setup("/npc/Old_man_left6",gp.tileSize,gp.tileSize);
		right1 = setup("/npc/Old_man_right1",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/Old_man_right2",gp.tileSize,gp.tileSize);
		right3 = setup("/npc/Old_man_right3",gp.tileSize,gp.tileSize);
		right4 = setup("/npc/Old_man_right4",gp.tileSize,gp.tileSize);
        right5 = setup("/npc/Old_man_right5",gp.tileSize,gp.tileSize);
		right6 = setup("/npc/Old_man_right6",gp.tileSize,gp.tileSize);
	}

    public void setDialogue(){
        dialougues[0] = "Hello, Eng.";
        dialougues[1] = "So you come to this island to find Treasure?";
        dialougues[2] = "I used to be a warrior but... \nI'm too old for taking and advanture.";
        dialougues[3] = "Well, good luck on you.";
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

    public void speak(){
        // Do this charecter specigic stuff
        super.speak();
    }
}
