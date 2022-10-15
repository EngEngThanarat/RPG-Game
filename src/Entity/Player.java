package Entity;

import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH2) {
        super(gp); // recrive gp from entity

        this.keyH = keyH2;

        screenX = gp.ScreenWidth / 2 - (gp.tileSize / 2); // 360 pixel
        screenY = gp.ScreenHigh / 2 - (gp.tileSize / 2); // 264 pixel

        // create collision Area
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 32;
        attackArea.height = 32;
        
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23; //1104 pil
        worldY = gp.tileSize * 21; //1104 pixe

        //worldX = gp.tileSize * 10; //1104 pixel
        //worldY = gp.tileSize * 12; //1104 pixel
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;

    }

    public void getPlayerImage() {
        up1 = setup("/Player/char_up1",gp.tileSize,gp.tileSize);
        up2 = setup("/Player/char_up2",gp.tileSize,gp.tileSize);
        up3 = setup("/Player/char_up3",gp.tileSize,gp.tileSize);
        up4 = setup("/Player/char_up4",gp.tileSize,gp.tileSize);
        up5 = setup("/Player/char_up5",gp.tileSize,gp.tileSize);
        up6 = setup("/Player/char_up6",gp.tileSize,gp.tileSize);
        down1 = setup("/Player/char_down1",gp.tileSize,gp.tileSize);
        down2 = setup("/Player/char_down2",gp.tileSize,gp.tileSize);
        down3 = setup("/Player/char_down3",gp.tileSize,gp.tileSize);
        down4 = setup("/Player/char_down4",gp.tileSize,gp.tileSize);
        down5 = setup("/Player/char_down5",gp.tileSize,gp.tileSize);
        down6 = setup("/Player/char_down6",gp.tileSize,gp.tileSize);
        left1 = setup("/Player/char_left1",gp.tileSize,gp.tileSize);
        left2 = setup("/Player/char_left2",gp.tileSize,gp.tileSize);
        left3 = setup("/Player/char_left3",gp.tileSize,gp.tileSize);
        left4 = setup("/Player/char_left4",gp.tileSize,gp.tileSize);
        left5 = setup("/Player/char_left5",gp.tileSize,gp.tileSize);
        left6 = setup("/Player/char_left6",gp.tileSize,gp.tileSize);
        right1 = setup("/Player/char_right1",gp.tileSize,gp.tileSize);
        right2 = setup("/Player/char_right2",gp.tileSize,gp.tileSize);
        right3 = setup("/Player/char_right3",gp.tileSize,gp.tileSize);
        right4 = setup("/Player/char_right4",gp.tileSize,gp.tileSize);
        right5 = setup("/Player/char_right5",gp.tileSize,gp.tileSize);
        right6 = setup("/Player/char_right6",gp.tileSize,gp.tileSize);
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/Player/char_attack_up1",gp.tileSize*2,gp.tileSize*2);
        attackUp2 = setup("/Player/char_attack_up2",gp.tileSize*2,gp.tileSize*2);
        attackUp3 = setup("/Player/char_attack_up3",gp.tileSize*2,gp.tileSize*2);
        attackUp4 = setup("/Player/char_attack_up4",gp.tileSize*2,gp.tileSize*2);
        attackUp5 = setup("/Player/char_attack_up5",gp.tileSize*2,gp.tileSize*2);
        attackUp6 = setup("/Player/char_attack_up6",gp.tileSize*2,gp.tileSize*2);
        attackDown1 = setup("/Player/char_attack_down1",gp.tileSize*2,gp.tileSize*2);
        attackDown2 = setup("/Player/char_attack_down2",gp.tileSize*2,gp.tileSize*2);
        attackDown3 = setup("/Player/char_attack_down3",gp.tileSize*2,gp.tileSize*2);
        attackDown4 = setup("/Player/char_attack_down4",gp.tileSize*2,gp.tileSize*2);
        attackDown5 = setup("/Player/char_attack_down5",gp.tileSize*2,gp.tileSize*2);
        attackDown6 = setup("/Player/char_attack_down6",gp.tileSize*2,gp.tileSize*2);
        attackLeft1 = setup("/Player/char_attack_left1",gp.tileSize*2,gp.tileSize*2);
        attackLeft2 = setup("/Player/char_attack_left2",gp.tileSize*2,gp.tileSize*2);
        attackLeft3 = setup("/Player/char_attack_left3",gp.tileSize*2,gp.tileSize*2);
        attackLeft4 = setup("/Player/char_attack_left4",gp.tileSize*2,gp.tileSize*2);
        attackLeft5 = setup("/Player/char_attack_left5",gp.tileSize*2,gp.tileSize*2);
        attackLeft6 = setup("/Player/char_attack_left6",gp.tileSize*2,gp.tileSize*2);
        attackRight1 = setup("/Player/char_attack_right1",gp.tileSize*2,gp.tileSize*2);
        attackRight2 = setup("/Player/char_attack_right2",gp.tileSize*2,gp.tileSize*2);
        attackRight3 = setup("/Player/char_attack_right3",gp.tileSize*2,gp.tileSize*2);
        attackRight4 = setup("/Player/char_attack_right4",gp.tileSize*2,gp.tileSize*2);
        attackRight5 = setup("/Player/char_attack_right5",gp.tileSize*2,gp.tileSize*2);
        attackRight6 = setup("/Player/char_attack_right6",gp.tileSize*2,gp.tileSize*2);
    }

    public void update() {

        if(attacking == true){
            attacking();
        }

        else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true || keyH.enterPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.checker.checkTile(this);

            // Check tile collision
            int  objIndex = gp.checker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.checker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int MonsterIndex = gp.checker.checkEntity(this, gp.Monster);
            contactMonster(MonsterIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false && keyH.enterPressed == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                }else if (spriteNum == 4) {
                    spriteNum = 5;
                }else if (spriteNum == 5) {
                    spriteNum = 6;
                }else if (spriteNum == 6) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        // This needs to be outside of key if statement
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    private void contactMonster(int i) {
        if(i != 999){
            if(invincible == false){
                gp.PlaySE(6);
                life -= 1;	
                invincible = true;
            }
        }
    }

    public void damageMonster(int i){
        if(i != 999){
            if(gp.Monster[i].invincible == false){
                gp.PlaySE(5);
                gp.Monster[i].life -= 1;
                gp.Monster[i].invincible = true;
                gp.Monster[i].damageReaction();

                if(gp.Monster[i].life <= 0){
                    gp.Monster[i].dying = true;
                }
            }
        }
    }

    public void attacking(){

        spriteCounter++;
        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 15){
            spriteNum = 2;
        }
        if(spriteCounter > 16 && spriteCounter <= 25){
            spriteNum = 3;
        }
        if(spriteCounter > 26 && spriteCounter <= 35){
            spriteNum = 4;
            
            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea
            switch(direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }
            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            // Check monster co-llistion with the updated
            int monsterIndex = gp.checker.checkEntity(this, gp.Monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 36 && spriteCounter <= 45){
            spriteNum = 5;
        }
        if(spriteCounter > 46 && spriteCounter <= 55){
            spriteNum = 6;
        }
        if(spriteCounter > 55){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i){
        if(i != 999){

        }
    }

    public void interactNPC(int i){
        if(gp.keyH.enterPressed == true){
            if(i != 999){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            
            }
            else{
                gp.PlaySE(7);
                attacking = true;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        // DEBUG
        // ATTACK AREA
        int tempX = screenX + solidArea.x;
        int tempY = screenY + solidArea.y;
        switch(direction){
            case "up": tempY = screenY - attackArea.height;break;
            case "down": tempY = screenY + gp.tileSize;break;
            case "left": tempX = screenX - attackArea.width;break;
            case "right": tempX = screenX + gp.tileSize;break;
        }
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(1));
        g2.drawRect(tempX, tempY, attackArea.width, attackArea.height);

        switch (direction) {
            case "up":
                if(attacking == false){
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                    if (spriteNum == 3) {image = up3;}
                    if (spriteNum == 4) {image = up4;}
                    if (spriteNum == 5) {image = up5;}
                    if (spriteNum == 6) {image = up6;}
                }
                if(attacking == true){
                    tempScreenY = screenY - gp.tileSize;
                    tempScreenX = screenX - gp.tileSize/2;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                    if (spriteNum == 3) {image = attackUp3;}
                    if (spriteNum == 4) {image = attackUp4;}
                    if (spriteNum == 5) {image = attackUp4;}
                    if (spriteNum == 6) {image = attackUp6;}
                }
                break;
            case "down":
                if(attacking == false){
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                    if (spriteNum == 3) {image = down3;}
                    if (spriteNum == 4) {image = down4;}
                    if (spriteNum == 5) {image = down5;}
                    if (spriteNum == 6) {image = down6;}
                }
                if(attacking == true){
                    tempScreenX = screenX - gp.tileSize/2;
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                    if (spriteNum == 3) {image = attackDown3;}
                    if (spriteNum == 4) {image = attackDown4;}
                    if (spriteNum == 5) {image = attackDown5;}
                    if (spriteNum == 6) {image = attackDown6;}
                }
                break;
            case "left":
                if(attacking == false){
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                    if (spriteNum == 3) {image = left3;}
                    if (spriteNum == 4) {image = left4;}
                    if (spriteNum == 5) {image = left5;}
                    if (spriteNum == 6) {image = left6;}
                }
                if(attacking == true){
                    tempScreenY = screenY - gp.tileSize/2;
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                    if (spriteNum == 3) {image = attackLeft3;}
                    if (spriteNum == 4) {image = attackLeft4;}
                    if (spriteNum == 5) {image = attackLeft5;}
                    if (spriteNum == 6) {image = attackLeft6;}
                }
                break;
            case "right":
                if(attacking == false){
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                    if (spriteNum == 3) {image = right3;}
                    if (spriteNum == 4) {image = right4;}
                    if (spriteNum == 5) {image = right5;}
                    if (spriteNum == 6) {image = right6;}
                }
                if(attacking == true){
                    tempScreenY = screenY - gp.tileSize/2;
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                    if (spriteNum == 3) {image = attackRight3;}
                    if (spriteNum == 4) {image = attackRight4;}
                    if (spriteNum == 5) {image = attackRight5;}
                    if (spriteNum == 6) {image = attackRight6;}
                }
                break;
        }

        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG
        // g2.setFont(new Font("Arial",Font.PLAIN, 26));
        // g2.setColor(Color.white);
        // g2.drawString("invincible:"+invincibleCounter, 10, 400);
    }
}
