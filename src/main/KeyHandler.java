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

        // TITLE STATE  
        if(gp.gameState == gp.titleState){
            if(gp.ui.titleScreenState == 0){
                if(code == KeyEvent.VK_W){
                    gp.ui.commardNum--;
                    if(gp.ui.commardNum < 0){
                        gp.ui.commardNum = 2;
                    }
                }
                if(code == KeyEvent.VK_S){
                    gp.ui.commardNum++;
                    if(gp.ui.commardNum > 2){
                        gp.ui.commardNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commardNum == 0){
                        //gp.ui.titleScreenState = 1;
                        gp.gameState = gp.playState;
                        gp.PlayMusic(0);
                    }
                    if(gp.ui.commardNum == 1){
                        // add later
                    }
                    if(gp.ui.commardNum == 2){
                        System.exit(0);
                    }
                }
            }

        //    else if(gp.ui.titleScreenState == 1){
        //        if(code == KeyEvent.VK_W){
        //            gp.ui.commardNum--;
        //            if(gp.ui.commardNum < 0){
        //                gp.ui.commardNum = 3;
        //            }
        //        }
        //        if(code == KeyEvent.VK_S){
        //            gp.ui.commardNum++;
        //            if(gp.ui.commardNum > 3){
        //                gp.ui.commardNum = 0;
        //            }
        //        }
        //        if(code == KeyEvent.VK_ENTER){
        //            if(gp.ui.commardNum == 0){
        //                System.out.println("Do some fighter specific stuff");
        //                gp.gameState = gp.playState;
        //                gp.PlayMusic(0);
        //            }
        //            if(gp.ui.commardNum == 1){
        //                System.out.println("Do some thief specific stuff");
        //                gp.gameState = gp.playState;
        //                gp.PlayMusic(0);
        //            }
        //            if(gp.ui.commardNum == 2){
        //                System.out.println("Do some sorcerer specific stuff");
        //                gp.gameState = gp.playState;
        //                gp.PlayMusic(0);
        //            }
        //            if(gp.ui.commardNum == 3){
        //                gp.ui.titleScreenState = 0;
        //            }
        //        }
        //    }
        }

        // PlayState
        else if(gp.gameState == gp.playState){

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
