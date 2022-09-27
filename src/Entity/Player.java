package Entity;

import java.awt.image.BufferedImage;
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

		screenX = gp.ScreenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.ScreenHigh / 2 - (gp.tileSize / 2);

		// create collision Area
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}

	public void getPlayerImage() {
		up1 = setup("/Player/char_up1");
		up2 = setup("/Player/char_up2");
		up3 = setup("/Player/char_up3");
		up4 = setup("/Player/char_up4");
		up5 = setup("/Player/char_up5");
		up6 = setup("/Player/char_up6");
		down1 = setup("/Player/char_down1");
		down2 = setup("/Player/char_down2");
		down3 = setup("/Player/char_down3");
		down4 = setup("/Player/char_down4");
		down5 = setup("/Player/char_down5");
		down6 = setup("/Player/char_down6");
		left1 = setup("/Player/char_left1");
		left2 = setup("/Player/char_left2");
		left3 = setup("/Player/char_left3");
		left4 = setup("/Player/char_left4");
		left5 = setup("/Player/char_left5");
		left6 = setup("/Player/char_left6");
		right1 = setup("/Player/char_right1");
		right2 = setup("/Player/char_right2");
		right3 = setup("/Player/char_right3");
		right4 = setup("/Player/char_right4");
		right5 = setup("/Player/char_right5");
		right6 = setup("/Player/char_right6");
	}

	public void update() {
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) {
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
			else {
				standCounter++;
				if(standCounter == 20){
					spriteNum = 1;
				}else if(standCounter == 40){
					spriteNum =  2;
				}
			}
		}
	}
	
	public void pickUpObject(int i){
		if(i != 999){
			
		}
	}

	public void interactNPC(int i){
		if(i != 999){
			System.out.println("You are hitting an NPC!");
		}
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		switch (direction) {
			case "up":
				if (spriteNum == 1) {
					image = up1;
				}
				if (spriteNum == 2) {
					image = up2;
				}
				if (spriteNum == 3) {
					image = up3;
				}
				if (spriteNum == 4) {
					image = up4;
				}
				if (spriteNum == 5) {
					image = up5;
				}
				if (spriteNum == 6) {
					image = up6;
				}
				break;
			case "down":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = down2;
				}
				if (spriteNum == 3) {
					image = down3;
				}
				if (spriteNum == 4) {
					image = down4;
				}
				if (spriteNum == 5) {
					image = down5;
				}
				if (spriteNum == 6) {
					image = down6;
				}
				break;
			case "left":
				if (spriteNum == 1) {
					image = left1;
				}
				if (spriteNum == 2) {
					image = left2;
				}
				if (spriteNum == 3) {
					image = left3;
				}
				if (spriteNum == 4) {
					image = left4;
				}
				if (spriteNum == 5) {
					image = left5;
				}
				if (spriteNum == 6) {
					image = left6;
				}
				break;
			case "right":
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
				}
				if (spriteNum == 3) {
					image = right3;
				}
				if (spriteNum == 4) {
					image = right4;
				}
				if (spriteNum == 5) {
					image = right5;
				}
				if (spriteNum == 6) {
					image = right6;
				}
				break;
			}
		g2.drawImage(image, screenX, screenY, null);
	}
}
