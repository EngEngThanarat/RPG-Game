package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;

import Objects.Obj_Heart;
import Objects.SuperObject;

import java.awt.image.BufferedImage;

public class UI {
    
    GamePanel gp;
    Graphics2D  g2;
    Font Hormonica;
    //BufferedImage keyImage;

    BufferedImage heart_full,heart_half,heart_null;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commardNum = 0;
    public int titleScreenState = 0; // 0: the first Screen, 1: second Screen

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
        SuperObject heart = new Obj_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_null = heart.image3;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
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

    public void drawTitleScreen(){
        if(titleScreenState == 0){
            g2.setColor(new Color(70,120,80));
            g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHigh);

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
            x = gp.ScreenWidth/2 - (gp.tileSize*2)/2;
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
        int y = gp.ScreenHigh/(2);

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){
        // Window
        int x = gp.tileSize/2; // set location for x box dialouge
        int y = gp.tileSize/2; // // set location for x box dialouge
        int width = gp.ScreenWidth - (gp.tileSize*2); // set width
        int height = gp.tileSize*4; // set height
        SubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
        x += gp.tileSize;
        x += gp.tileSize;

        int Xtext = x/2; // Set location for text in x
        int Ytext = y*3; // Set location for text in y
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, Xtext, Ytext);
            Ytext += 40;
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

    public int getXforCenteredText(String Text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(Text,g2).getWidth();
        int x = gp.ScreenWidth/2 - lenght/2;
        return x;
    }
}
