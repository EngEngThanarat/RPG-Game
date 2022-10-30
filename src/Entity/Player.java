package Entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Objects.Obj_Key;
import Objects.Obj_Shield_Wood;
import Objects.Obj_Sword_Normal;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int MaxInventorySize = 20;

    public Player(GamePanel gp, KeyHandler keyH2) {
        super(gp); // recrive gp from entity

        this.keyH = keyH2;

        screenX = gp.ScreenWidth / 2 - (gp.tileSize / 2); // 360 pixel
        screenY = gp.ScreenHigh / 2 - (gp.tileSize / 2); // 264 pixel

        // create collision Area 
        solidArea = new Rectangle();
        solidArea.x = 8; // distance from x 
        solidArea.y = 16; // distance from y
        solidArea.width = 32; // size of rectangle
        solidArea.height = 32; // size of rectangle
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItem();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23; //1104 pil
        worldY = gp.tileSize * 21; //1104 pixe

        speed = 4;
        direction = "down";

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1; // The more strength he has, the more damage he gives.
        dexterity = 1; // The more dexterity he has, the less damage he receives
        exp = 0;
        NextLevel = 5;
        coin = 0;
        currentWeapon = new Obj_Sword_Normal(gp);
        currentShield = new Obj_Shield_Wood(gp);
        attack = getAttack(); // The total attack value is decided by strength and weapon
        defense = getDefense(); // The total defense value is decided by dexteritery and shield
    }

    public void setItem(){
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new Obj_Key(gp));
        inventory.add(new Obj_Key(gp));
    }

    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
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
        if(currentWeapon.type == type_sword){
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
        if(currentWeapon.type == type_axe){
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
        if(currentWeapon.type == type_halberd){
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

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false && keyH.enterPressed == false) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            if(keyH.enterPressed == true && attackCanceled == false){
                gp.PlaySE(7);
                attacking = true;
                spriteCounter = 0;
            }
            attackCanceled = false;

            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }else if (spriteNum == 2) {
                    spriteNum = 3;
                }else if (spriteNum == 3) {
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
        if(life > maxLife){
            life = maxLife;
        }
    }

    private void contactMonster(int i) {
        if(i != 999){
            if(invincible == false){
                gp.PlaySE(6);

                int damage = gp.Monster[i].attack - defense;
                if(damage < 0){
                    damage = 0;
                }
                life -= damage;	
                invincible = true;

                // IF LIFE LESS THAN 0 LIFE EQUAL 0
                if(life < 0){
                    life = 0;
                }
            }
        }
    }

    public void damageMonster(int i){
        if(i != 999){
            if(gp.Monster[i].invincible == false){
                gp.PlaySE(5);

                int damage = attack - gp.Monster[i].defense;
                if(damage < 0){
                    damage = 0;
                }
                gp.Monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                gp.Monster[i].invincible = true;
                gp.Monster[i].damageReaction();

                if(gp.Monster[i].life <= 0){
                    gp.Monster[i].dying = true;
                    gp.ui.addMessage("Killed the "+gp.Monster[i].name + "!");
                    gp.ui.addMessage("Exp + "+gp.Monster[i].exp + "!");
                    exp += gp.Monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void attacking(){

        spriteCounter++;
        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 10){
            spriteNum = 2;
        }
        if(spriteCounter > 10 && spriteCounter <= 15){
            spriteNum = 3;
        }
        if(spriteCounter > 15 && spriteCounter <= 20){
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
        if(spriteCounter > 20 && spriteCounter <= 25){
            spriteNum = 5;
        }
        if(spriteCounter > 25 && spriteCounter <= 30){
            spriteNum = 6;
        }
        if(spriteCounter > 30){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i){
        if(i != 999){

            // PICKUP ONLY ITEM
            if(gp.obj[i].type == type_pickUpOnly){
                gp.obj[i].use(this);
                gp.obj[i] = null;
            }
            // INVENTORY ITEMS
            else{
                String text;

                if(inventory.size() != MaxInventorySize){
                    inventory.add(gp.obj[i]);
                    gp.PlaySE(1);
                    text = "Got a "+ gp.obj[i].name + "!";
                }
                else{
                    text = "You cannot carry any more";
                }
                gp.ui.addMessage(text);
                gp.obj[i] = null;
            }

            
        }
    }

    public void interactNPC(int i){
        if(gp.keyH.enterPressed == true){
            if(i != 999){
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }
    
    public void checkLevelUp(){
        if(exp >= NextLevel){
            level++;
            NextLevel = NextLevel*2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.PlaySE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level "+ level + " now!\n"+"You feel stronger!";
        }
    }

    public void SelectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot();
        
        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_sword || selectedItem.type == type_axe || selectedItem.type == type_halberd){
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable){
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        // DEBUG
        // ATTACK AREA
        int attacktempX = screenX + solidArea.x;
        int attacktempY = screenY + solidArea.y;
        switch(direction){
            case "up": attacktempY = screenY - attackArea.height;break;
            case "down": attacktempY = screenY + gp.tileSize;break;
            case "left": attacktempX = screenX - attackArea.width;break;
            case "right": attacktempX = screenX + gp.tileSize;break;
        }
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(1));
        g2.drawRect(attacktempX, attacktempY, attackArea.width, attackArea.height);

        // SOLID AREA
        // int solidtempX = screenX + solidArea.x;
        // int solidtempY = screenY + solidArea.y;
        // g2.setColor(Color.white);
        // g2.fillRect(solidtempX, solidtempY, solidArea.width, solidArea.height);

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
            changeAlpha(g2, 0.3f);
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset alpha
        changeAlpha(g2, 1f);

        // DEBUG
        // g2.setFont(new Font("Arial",Font.PLAIN, 26));
        // g2.setColor(Color.white);
        // g2.drawString("invincible:"+invincibleCounter, 10, 400);
    }
}
