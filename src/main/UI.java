package main;

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

    public UI(GamePanel gp){
        this.gp = gp;
        arial_35 = new Font("Arial", Font.PLAIN,35);
        arial_80B = new Font("Arial", Font.BOLD,80);
//       Obj_Key key = new Obj_Key(gp);
//       keyImage = key.getIcon(gp);; 
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(arial_35);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){
            // Do playState stuff later
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.ScreenHigh/(2);

        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String Text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(Text,g2).getWidth();
        int x = gp.ScreenWidth/2 - lenght/2;
        return x;
    }
}
