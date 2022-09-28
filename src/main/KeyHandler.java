package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed , downPressed , leftPressed, rightPressed, enterPressed;
    // DEBUG
    boolean CheckDrawTime = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // return the integer KeyCode associated with the key in this event

        // PlayState
        if(gp.gameState == gp.playState){

            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            } 
    
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            } 
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            } 
    
            // DEBUG
            if(code == KeyEvent.VK_T){
                if(CheckDrawTime == false){
                    CheckDrawTime = true;
                }
                else if(CheckDrawTime == true){
                    CheckDrawTime = false;
                }
            }
        }
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            } 
        }

        // DIALOUGE STATE
        else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        } 
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
