package Entity;

import Objects.Obj_Axe;
import Objects.Obj_Halberd;
import Objects.Obj_Key;
import Objects.Obj_Potion_Red;
import Objects.Obj_Shield_Wood;
import Objects.Obj_Sword_Normal;
import Objects.Obj_rock_shield;
import main.GamePanel;

public class NPC_Merchant extends Entity{

    public NPC_Merchant(GamePanel gp){
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
        setItem();
    }

    public void getImage() {
		up1 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/merchant_down2",gp.tileSize,gp.tileSize);
		up3 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
		up4 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
        up5 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
        up6 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
		down1 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/merchant_down2",gp.tileSize,gp.tileSize);
		down3 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
		down4 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
        down5 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
        down6 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
		left1 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/merchant_down2",gp.tileSize,gp.tileSize);
		left3 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
		left4 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
        left5 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
        left6 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
		right1 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/merchant_down2",gp.tileSize,gp.tileSize);
		right3 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
		right4 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
        right5 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
		right6 = setup("/npc/merchant_down1",gp.tileSize,gp.tileSize);
	}

    public void setDialogue(){
        dialougues[0] = "He he, so you found me.\nI have some good stuff.\nDo you want to trade?";
    }

    public void setItem(){
        inventory.add(new Obj_Potion_Red(gp));
        inventory.add(new Obj_Key(gp));
        inventory.add(new Obj_Sword_Normal(gp));
        inventory.add(new Obj_Axe(gp));
        inventory.add(new Obj_Halberd(gp));
        inventory.add(new Obj_Shield_Wood(gp));
        inventory.add(new Obj_rock_shield(gp));
    }

    public void speak(){
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
