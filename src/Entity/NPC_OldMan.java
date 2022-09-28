package Entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
		up1 = setup("/npc/Old_man_up1");
		up2 = setup("/npc/Old_man_up2");
		up3 = setup("/npc/Old_man_up3");
		up4 = setup("/npc/Old_man_up4");
        up5 = setup("/npc/Old_man_up5");
        up6 = setup("/npc/Old_man_up6");
		down1 = setup("/npc/Old_man_down1");
		down2 = setup("/npc/Old_man_down2");
		down3 = setup("/npc/Old_man_down3");
		down4 = setup("/npc/Old_man_down4");
        down5 = setup("/npc/Old_man_down5");
        down6 = setup("/npc/Old_man_down6");
		left1 = setup("/npc/Old_man_left1");
		left2 = setup("/npc/Old_man_left2");
		left3 = setup("/npc/Old_man_left3");
		left4 = setup("/npc/Old_man_left4");
        left5 = setup("/npc/Old_man_left5");
        left6 = setup("/npc/Old_man_left6");
		right1 = setup("/npc/Old_man_right1");
		right2 = setup("/npc/Old_man_right2");
		right3 = setup("/npc/Old_man_right3");
		right4 = setup("/npc/Old_man_right4");
        right5 = setup("/npc/Old_man_right5");
		right6 = setup("/npc/Old_man_right6");
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
