package Entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.AlphaComposite;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Rectangle;

public class Entity {

    GamePanel gp;
    public BufferedImage up1, up2, up3, up4 , up5, up6, 
                         down1, down2, down3, down4, down5, down6,
                         left1, left2, left3, left4, left5, left6, 
                         right1, right2, right3, right4 , right5, right6 ;
    public BufferedImage attackUp1,attackUp2,attackUp3,attackUp4,attackUp5,attackUp6,
                         attackDown1,attackDown2,attackDown3,attackDown4,attackDown5,attackDown6,
                         attackLeft1,attackLeft2,attackLeft3,attackLeft4,attackLeft5,attackLeft6,
                         attackRight1,attackRight2,attackRight3,attackRight4,attackRight5,attackRight6;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0,0,40,40);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialougues[] = new String[20];
    
    // STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialougeIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;

    // CHARACTER ATTRIBUTES
    public int type; // 0 = player, 1 = npc, 2 = monster
    public String name;
    public int speed;
    public int maxLife;
    public int life;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){}

    public void speak(){
        if(dialougues[dialougeIndex] == null){
            dialougeIndex = 0;
        }

        gp.ui.currentDialogue = dialougues[dialougeIndex];
        dialougeIndex++;

        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update(){
        setAction();

        collisionOn = false;
        gp.checker.checkTile(this);
        gp.checker.checkObject(this, false);
        gp.checker.checkEntity(this, gp.npc);
        gp.checker.checkEntity(this, gp.Monster);
        boolean contactPlayer = gp.checker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                // we can give damage
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if (collisionOn == false) {
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

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                    if (spriteNum == 3) {image = up3;}
                    if (spriteNum == 4) {image = up4;}
                    if (spriteNum == 5) {image = up5;}
                    if (spriteNum == 6) {image = up6;}
                    break;
                case "down":
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                    if (spriteNum == 3) {image = down3;}
                    if (spriteNum == 4) {image = down4;}
                    if (spriteNum == 5) {image = down5;}
                    if (spriteNum == 6) {image = down6;}
                    break;
                case "left":
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                    if (spriteNum == 3) {image = left3;}
                    if (spriteNum == 4) {image = left4;}
                    if (spriteNum == 5) {image = left5;}
                    if (spriteNum == 6) {image = left6;}
                    break;
                case "right":
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                    if (spriteNum == 3) {image = right3;}
                    if (spriteNum == 4) {image = right4;}
                    if (spriteNum == 5) {image = right5;}
                    if (spriteNum == 6) {image = right6;}
                    break;
            }

            if(invincible == true){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
