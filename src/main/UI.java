package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Objects.Obj_Key;

public class UI {
    
    GamePanel gp;
    Font arial_35,arial_80B;
    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp){
        this.gp = gp;

        arial_35 = new Font("Arial", Font.PLAIN,35);
        arial_80B = new Font("Arial", Font.BOLD,80);
        Obj_Key key = new Obj_Key(gp);
        keyImage = key.getIcon(gp);;
        
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        if(gameFinished == true){
            g2.setFont(arial_35);
            g2.setColor(Color.white);

            String text;
            int textLenght;
            int x;
            int y;

            text = "You found the treasure!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.ScreenWidth/2 - textLenght/2;
            y = gp.ScreenHigh/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.ScreenWidth/2 - textLenght/2;
            y = gp.ScreenHigh/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }else{
            g2.setFont(arial_35);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString(" = "+ gp.player.hasKey, 70, 60);

            //message
            if(messageOn == true){

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*7);

                messageCounter++;

                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                } 
            }
        }
    }
}
