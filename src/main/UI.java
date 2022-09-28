package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    
    GamePanel gp;
    Graphics2D  g2;
    Font arial_35,arial_80B;
    //BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

    public UI(GamePanel gp){
        this.gp = gp;
        arial_35 = new Font("Arial", Font.PLAIN,35);
        arial_80B = new Font("Arial", Font.BOLD,80);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(arial_35);
        g2.setColor(Color.white);

        // PLAY STATE
        if(gp.gameState == gp.playState){
            // Do playState stuff later
        }

        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
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
