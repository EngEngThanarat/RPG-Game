package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Entity.Entity;
import Objects.Obj_Coin;
import Objects.Obj_Heart;

import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D  g2;
    Font Hormonica;
    //BufferedImage keyImage;

    BufferedImage heart_full,heart_half,heart_null,coin;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commardNum = 0;
    public int titleScreenState = 0; // 0: the first Screen, 1: second Screen
    public int PlayerSlotCol = 0;
    public int PlayerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity npc;

    public UI(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            Hormonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CREATE HUB OBJECT
        Entity heart = new Obj_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_null = heart.image3;
        Entity goldcoin = new Obj_Coin(gp);
        coin = goldcoin.down1;
    }

    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(Hormonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        // PLAY STATE
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            drawMessage();
        }

        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }

        // CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory(gp.player,true);
        }

        // OPTION STATE
        if(gp.gameState == gp.optionState){
            drawOptionScreen();
        }

        // GAME OVER STATE
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
        // TRANSITION STATE
        if(gp.gameState == gp.transitionState){
            drawTransitionScreen();
        }
        // TRADE STATE
        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
        }
    }

    private void drawPlayerLife() {

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // DRAW MAX LIFE
        while(i< gp.player.maxLife/2){
            g2.drawImage(heart_null, x, y,null) ;
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // DRAW CURRENT LIFE
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y,null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawMessage(){
        int messageX = gp.tileSize ; 
        int messageY = gp.tileSize * 5;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32f));

        for(int i = 0; i < message.size(); i++){
            if(message.get(i) != null){
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);
                
                int counter = messageCounter.get(i) + 1; // messageCounter++
                messageCounter.set(i, counter); // set the counter to the array
                messageY += 50;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawTitleScreen(){
        if(titleScreenState == 0){
            g2.setColor(new Color(70,120,80));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = "RPG Adventure";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            // SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x+5, y+5);
            // MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // HERO IMAGE
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y = gp.tileSize*5;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2 , gp.tileSize*2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

            text = "New Game";
            x = getXforCenteredText(text);
            y += gp.tileSize*3.5;
            g2.drawString(text, x, y);
            if(commardNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Load Game";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commardNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Quit";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commardNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        //    // Charecter Class Selection
        //    else if(titleScreenState == 1){
        //        g2.setColor(Color.white);
        //        g2.setFont(g2.getFont().deriveFont(42f));

        //        String text = "Select your class";
        //        int x = getXforCenteredText(text);
        //        int y = gp.tileSize*3;
        //        g2.drawString(text, x, y);

        //        text = "Fighter";
        //        x = getXforCenteredText(text);
        //        y += gp.tileSize*3;
        //        g2.drawString(text, x, y);
        //        if(commardNum == 0){
        //            g2.drawString(">", x-gp.tileSize, y);
        //        }
        //        text = "Thief";
        //        x = getXforCenteredText(text);
        //        y += gp.tileSize;
        //        g2.drawString(text, x, y);
        //        if(commardNum == 1){
        //            g2.drawString(">", x-gp.tileSize, y);
        //        }
        //        text = "Sorcerer";
        //        x = getXforCenteredText(text);
        //        y += gp.tileSize;
        //        g2.drawString(text, x, y);
        //        if(commardNum == 2){
        //            g2.drawString(">", x-gp.tileSize, y);
        //        }
        //        text = "Back";
        //        x = getXforCenteredText(text);
        //        y += gp.tileSize*2;
        //        g2.drawString(text, x, y);
        //        if(commardNum == 3){
        //            g2.drawString(">", x-gp.tileSize, y);
        //        }
        //    }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight/(2);

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){
        // Window
        int x = gp.tileSize/2; // set location for x box dialouge
        int y = (int) (gp.tileSize*7.5); // // set location for y box dialouge
        int width = gp.screenWidth - gp.tileSize; // set width
        int height = gp.tileSize*4; // set height
        SubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
        x += gp.tileSize;
        x += gp.tileSize;

        int Xtext = x/2; // Set location for text in x
        int Ytext = y+45; // Set location for text in y
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, Xtext, Ytext);
            Ytext += 40;
        }
    }

    public void drawCharacterScreen(){
        // CREATE A FRAME
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize *5;
        final int frameHeight = gp.tileSize *10;
        SubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 36;

        // NAME
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight+20;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight+20;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight+20;
        
        // VALUE
        int tailX = (frameX + frameWidth) - 30;
        // Reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        // CHECK LIFE IF LESS THAN 0 SHOW 0
        if(gp.player.life < 0){
            value = String.valueOf("0/" + gp.player.maxLife);
            textX = getXforAlignToRightText(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeight;
        }else{
            value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
            textX = getXforAlignToRightText(value, tailX);
            g2.drawString(value, textX, textY);
            textY += lineHeight;
        }
        

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.NextLevel);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize+1, textY-14, null);
        textY += gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize+1, textY-14, null);
        textY += gp.tileSize;
    }

    public void drawInventory(Entity entity, Boolean cursor){

        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if(entity == gp.player){
            frameX = gp.tileSize*12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*6;
            slotCol = PlayerSlotCol;
            slotRow = PlayerSlotRow;
        }
        else{
            frameX = gp.tileSize*2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*6;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        // FRAME
        SubWindow(frameX, frameY, frameWidth, frameHeight);
        
        // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;

        // DRAW PLAYER'S ITEM
        for(int i = 0; i < entity.inventory.size(); i++){

            // EQUIP CURSOR
            if(entity.inventory.get(i) == entity.currentWeapon ||
                    entity.inventory.get(i) == entity.currentShield){
                g2.setColor(new Color(255,219,88));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);


            // DISPLAY AMOUNT
            if(entity == gp.player &&entity.inventory.get(i).amout > 1){
                g2.setFont(g2.getFont().deriveFont(32f));
                int amoutX;
                int amoutY;

                String s = "" + entity.inventory.get(i).amout;
                amoutX = getXforAlignToRightText(s, slotX + 44);
                amoutY = slotY + gp.tileSize;

                // SHADOW
                g2.setColor(new Color(60,60,60));
                g2.drawString(s, amoutX, amoutY);
                // NUMBER
                g2.setColor(Color.white);
                g2.drawString(s, amoutX-3, amoutY-3);;

            }

            slotX += slotSize;

            if(i == 4 || i == 9 || i == 14 || i == 19){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // CURSOR
        if(cursor == true){
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;
    
            // DRAW CURSOR
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    
            // DESCRIPTION FRAME
            int DescriptionFrameX = frameX;
            int DescriptionFrameY = frameY + frameHeight;
            int DescriptionFrameWidth = frameWidth;
            int DescriptionFrameHeight = gp.tileSize*4;
            
            // DRAW DESCRIPTION TEXT
            int textX = DescriptionFrameX + 20;
            int textY = DescriptionFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(28F));
    
            int itemIndex = getItemIndexOnSlot(slotCol,slotRow);
    
            if(itemIndex < entity.inventory.size()){
    
                SubWindow(DescriptionFrameX, DescriptionFrameY, DescriptionFrameWidth, DescriptionFrameHeight);
    
                for(String line : entity.inventory.get(itemIndex).description.split("\n")){
    
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
            }
        }
    }

    public void drawOptionScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gp.tileSize*6;
        int frameY =  gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        SubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState){
            case 0: option_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX, frameY); break;
            case 2: options_control(frameX, frameY); break;
            case 3: options_endGameConfirmation(frameX, frameY); break;
        }

        gp.keyH.enterPressed = false;
    }

    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);

        // Main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commardNum == 0){
            g2.drawString(">", x-40, y);
        }

        // Back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commardNum == 1){
            g2.drawString(">", x-40, y);
        }
    }

    public void option_top(int frameX,int frameY){
        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Full Screen", textX, textY);
        if(commardNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                if(gp.fullScreenOn == false){
                    gp.fullScreenOn = true;
                }
                else if(gp.fullScreenOn == true){
                    gp.fullScreenOn = false;
                }
                subState = 1;
            } 
        }

        // MUSIC
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commardNum == 1){
            g2.drawString(">", textX-25, textY);
        }

        // SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if(commardNum == 2){
            g2.drawString(">", textX-25, textY);
        }

        // CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commardNum == 3){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 2;
                commardNum = 0;
            }
        }

        // END GAME
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(commardNum == 4){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 3;
                commardNum = 0;
            }
        }

        // BACK
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if(commardNum == 5){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
                commardNum = 0;
            }
        }

        // FULL SCREEN CHECK BOX
        textX = frameX + gp.tileSize *5;
        textY = frameY + gp.tileSize *2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY,24,24);
        if(gp.fullScreenOn == true){
            g2.fillRect(textX, textY,24,24);
        }

        // MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY,120,24); // 120/5 = 24
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        // SE VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY,120,24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();
    }

    public void options_fullScreenNotification(int frameX,int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "The change will take \neffect after restarting \nthe game";

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // BACK
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back",textX,textY);
        if(commardNum ==0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0; 
            }
        }
    }

    public void options_control(int frameX, int frameY){
        int textX;
        int textY;

        // TITLE
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Character Screen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY); textY += gp.tileSize;

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("C", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;

        // BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commardNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commardNum = 3;
            }
        }
    }

    public void options_endGameConfirmation(int frameX,int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "Quit the game and \nreturn to the title screen";
        
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;    
        }

        // YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commardNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }

        // NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commardNum == 1){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commardNum = 4;
            }
        }
    }

    public int getItemIndexOnSlot(int slotCol, int slotRow){
        int itemIndex = slotCol +(slotRow*5);
        return itemIndex;
    }

    public void drawTradeScreen(){
        switch(subState){
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }

    public void trade_select(){
        drawDialogueScreen();

        // DRAW WINDOW
        int x = (int) (gp.tileSize * 16); // 15 or 16.5
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int)(gp.tileSize * 3.5);
        SubWindow(x, y, width, height);

        // DRAW TEXT
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if(commardNum == 0){
            g2.drawString(">", x-24, y);
            if(gp.keyH.enterPressed == true){
                subState = 1;
            }
        }
        y += gp.tileSize;

        g2.drawString("Sell", x, y);
        if(commardNum == 1){
            g2.drawString(">", x-24, y);
            if(gp.keyH.enterPressed == true){
                subState = 2;
            }
        }
        y += gp.tileSize;

        g2.drawString("Leave", x, y);
        if(commardNum == 2){
            g2.drawString(">", x-24, y);
            if(gp.keyH.enterPressed == true){
                commardNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "Come Again hehe";
            }
        }
    }

    public void trade_buy(){
        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, false);
        // DRAW NPC INVENTORY
        drawInventory(npc, true);

        // DRAW HINT WINDOW
        int x = gp.tileSize*12;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        SubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x+24, y+60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize*12;
        y = gp.tileSize*7;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        SubWindow(x, y, width, height);
        g2.drawString("Your Coin: "+gp.player.coin, x+24, y+60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if(itemIndex < npc.inventory.size()){
            x = (int) (gp.tileSize * 5.5);
            y = (int) (gp.tileSize * 6.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            SubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8, 32,32, null);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize*8-20);
            g2.drawString(text, x, y+34);

            // BUY AN ITEM
            if(gp.keyH.enterPressed == true){
                if(npc.inventory.get(itemIndex).price > gp.player.coin){
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You need more coin to buy that!";
                    drawDialogueScreen();
                }else{
                    if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true){
                        gp.player.coin -= npc.inventory.get(itemIndex).price;
                    }else{
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        currentDialogue = "You cannot carry any more!";
                    }              
                }
                // else if(gp.player.inventory.size() == gp.player.MaxInventorySize){
                //     subState = 0;
                //     gp.gameState = gp.dialogueState;
                //     currentDialogue = "You cannot carry any more";
                // }else{
                //     gp.player.coin -= npc.inventory.get(itemIndex).price;
                //     gp.player.inventory.add(npc.inventory.get(itemIndex));
                // }
            }
        }
    }

    public void trade_sell(){
        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, true);

        int x;
        int y;
        int width;
        int height;

        // DRAW HINT WINDOW
        x = gp.tileSize*2;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        SubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x+24, y+60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize*2;
        y = gp.tileSize*7;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        SubWindow(x, y, width, height);
        g2.drawString("Your Coin: "+gp.player.coin, x+24, y+60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(PlayerSlotCol, PlayerSlotRow);
        if(itemIndex < gp.player.inventory.size()){
            x = (int) (gp.tileSize * 15.5);
            y = (int) (gp.tileSize * 6.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            SubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8, 32,32, null);

            int price = gp.player.inventory.get(itemIndex).price/2;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize*18-20);
            g2.drawString(text, x, y+34);

            // SELL AN ITEM
            if(gp.keyH.enterPressed == true){
                if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || 
                   gp.player.inventory.get(itemIndex) == gp.player.currentShield){
                    commardNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You cannot sell an equipped item";
                }
                else{
                    if(gp.player.inventory.get(itemIndex).amout > 1){
                        gp.player.inventory.get(itemIndex).amout--;
                    }
                    else{
                        gp.player.inventory.remove(itemIndex);   
                    }
                    gp.player.coin += price;
                }
            }
        }
    }

    public void SubWindow(int x,int y,int width,int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public void drawTransitionScreen(){
        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if(counter == 50){
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY; 
        }
    }

    public int getXforCenteredText(String Text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(Text,g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return x;
    }

    public int getXforAlignToRightText(String Text,int tailX){
        int lenght = (int)g2.getFontMetrics().getStringBounds(Text,g2).getWidth();
        int x = tailX - lenght;
        return x;
    }
}
