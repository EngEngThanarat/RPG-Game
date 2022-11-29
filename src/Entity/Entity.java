package Entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.Color;

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
    public BufferedImage death1,death2,death3,death4,death5,death6;
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
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean knockBack = false;
    public boolean onPath = false;
    public Entity chest;
    public boolean opened = false;

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    int knockBackCounter = 0;

    // CHARACTER ATTRIBUTES
    public String name;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int NextLevel;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;

    // ITEM ATTRIBUTE
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int MaxInventorySize = 25;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int knockBackPower = 0;
    public int price = 0;
    public boolean stackable = false;
    public int amout = 1;

    // TYPE
    public int type; // 0 = player, 1 = npc, 2 = monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_Monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_halberd = 5;
    public final int type_shield = 6;
    public final int type_consumable = 7;
    public final int type_pickUpOnly = 8;
    public final int type_obstacle = 9;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public int getLeftX(){
        return worldX + solidArea.x;
    }

    public int getRightX(){
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY(){
        return worldY + solidArea.y;
    }

    public int getButtomY(){
        return worldY + solidArea.y + solidArea.height;
    }

    public int getCol(){
        return (worldX + solidArea.x)/gp.tileSize;
    }

    public int getRow(){
        return (worldY + solidArea.y)/gp.tileSize;
    }

    public void setLoot(Entity loot){
        
    }

    public void setAction(){}

    public void damageReaction(){}

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

    public void interact(){
        
    }

    public boolean use(Entity entity){
        return false;
    }

    public void checkDrop(){}

    public void dropItem(Entity droppedItem){
        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX; // the dead Monster worldX
                gp.obj[gp.currentMap][i].worldY = worldY; // the dead Monster worldY
                break;
            }
        }
    }

    public void update(){

        if(knockBack == true){
            
            checkCollision();

            if(collisionOn == true){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if(collisionOn == false){
                switch(gp.player.direction){
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
                }
            }

            knockBackCounter++;
            if(knockBackCounter == 10){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }

        }else{
            setAction();
            checkCollision();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {
                
                switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
                }
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

    public void checkCollision(){

        collisionOn = false;
        gp.checker.checkTile(this);
        gp.checker.checkObject(this, false);
        gp.checker.checkEntity(this, gp.npc);
        gp.checker.checkEntity(this, gp.Monster);
        boolean contactPlayer = gp.checker.checkPlayer(this);

        if(this.type == type_Monster && contactPlayer == true){
            damagePlayer(attack);
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

            // Monter hp bar
            if(type == 2 && hpBarOn == true){

                // hp bar = 48 pixel 
                double oneScale = (double)gp.tileSize/maxLife; // check lenght of the bar
                double hpBarValue = oneScale*life; // if life is 4 when hit 1 time show hp = 12 pixel

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1, screenY-14, gp.tileSize+2, 8);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX, screenY-13, (int)hpBarValue, 6);

                hpBarCounter++;

                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }
            
            if(invincible == true){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if(dying == true){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            
            changeAlpha(g2, 1f);
            
        }
        
        // SOLID AREA
        // int solidtempX = screenX + solidArea.x;
        // int solidtempY = screenY + solidArea.y;
        // g2.setColor(Color.white);
        // g2.fillRect(solidtempX, solidtempY, solidArea.width, solidArea.height);
    }

    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;

        int i = 5;
        
        if(dyingCounter <= i){changeAlpha(g2, 0f);}
        if(dyingCounter > i && dyingCounter <= i*2){changeAlpha(g2, 1f);}
        if(dyingCounter > i*2 && dyingCounter <= i*3){changeAlpha(g2, 0f);}
        if(dyingCounter > i*3 && dyingCounter <= i*4){changeAlpha(g2, 1f);}
        if(dyingCounter > i*4 && dyingCounter <= i*5){changeAlpha(g2, 0f);}
        if(dyingCounter > i*5 && dyingCounter <= i*6){changeAlpha(g2, 1f);}
        if(dyingCounter > i*6 && dyingCounter <= i*7){changeAlpha(g2, 0f);}
        if(dyingCounter > i*7 && dyingCounter <= i*8){changeAlpha(g2, 1f);}
        if(dyingCounter > i*8){
            dying = false; 
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
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

    public void damagePlayer(int attack){
        if(gp.player.invincible == false){
            // we can give damage
            gp.PlaySE(6);

            int damage = attack - gp.player.defense;
            if(damage < 0){
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public void searchPath(int goalCol,int goalRow){

        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.PFinding.setNode(startCol, startRow, goalCol, goalRow, this);

        if(gp.PFinding.search() == true){
            // Next worldX & WorldY
            int nextX = gp.PFinding.pathList.get(0).col * gp.tileSize;
            int nextY = gp.PFinding.pathList.get(0).row * gp.tileSize;

            // Entity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;
            // SOLID AREA

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize){
                // Left or Right
                if(enLeftX > nextX){
                    direction = "left";
                }
                if(enLeftX < nextX){
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                // up or left
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                // up or right
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                // down or left
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                // down or right
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }

            // if reaches the goal, stop search
//            int nextCol = gp.PFinding.pathList.get(0).col;
//            int nextRow = gp.PFinding.pathList.get(0).row;
//            if(nextCol == goalCol && nextRow == goalRow){
//                onPath = false;
//            } 
        }
    }

    public int getDetected(Entity user, Entity target[][], String targetName){

        int index = 999;

        // Check the surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch(user.direction){
            case "up": nextWorldY = user.getTopY()-gp.player.speed; break;
            case "down": nextWorldY = user.getButtomY()+gp.player.speed; break;
            case "left": nextWorldX = user.getLeftX()-gp.player.speed; break;
            case "right": nextWorldX = user.getRightX()+gp.player.speed; break;
        }

        int col = nextWorldX/gp.tileSize; // 13.99
        int row = nextWorldY/gp.tileSize;

        for(int i = 0; i < target[1].length; i++){
            if(target[gp.currentMap][i] != null){
                if(target[gp.currentMap][i].getCol() == col &&
                    target[gp.currentMap][i].getRow() == row && 
                    target[gp.currentMap][i].name.equals(targetName)){
                       
                        index = i;
                        break;
                    }
            }
        }
        return index;
    }
}
