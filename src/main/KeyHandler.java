package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed , downPressed , leftPressed, rightPressed, enterPressed;
    // DEBUG
    boolean showDebugText = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // return the integer KeyCode associated with the key in this event

        // TITLE STATE  
        if(gp.gameState == gp.titleState){
            titleState(code);
        }
        // PlayState
        else if(gp.gameState == gp.playState){
            playState(code);
        }
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }

        // DIALOUGE STATE
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }

        // CHARACTER STATE
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }

        // OPTION STATE
        else if(gp.gameState == gp.optionState){
            optionState(code);
        }

        // GAME OVER STATE
        else if(gp.gameState == gp.gameOverState){
            gameOverState(code);
        }

        // TRADE STATE
        else if(gp.gameState == gp.tradeState){
            tradeState(code);
        }
    }

    public void titleState(int code){

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

    public void playState(int code){

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
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        } 
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionState;
        } 

        // DEBUG
        if(code == KeyEvent.VK_T){
            if(showDebugText == false){
                showDebugText = true;
            }
            else if(showDebugText == true){
                showDebugText = false;
            }
        }
        if(code == KeyEvent.VK_R){
            switch(gp.currentMap){
                case 0 : gp.tileM.loadMap("/maps/worldV2.txt",0); break;
                case 1 : gp.tileM.loadMap("/maps/interior01.txt",1); break; 
            }
        }
    }
 
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        } 
    }
   
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }
   
    public void characterState(int code){
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.SelectItem();;
        }
        playerInventory(code);
    }

    public void optionState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch(gp.ui.subState){
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }

        if(code == KeyEvent.VK_W){
            gp.ui.commardNum--;
            gp.PlaySE(9);
            if(gp.ui.commardNum < 0){
                gp.ui.commardNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commardNum++;
            gp.PlaySE(9);
            if(gp.ui.commardNum > maxCommandNum){
                gp.ui.commardNum = 0;
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.subState == 0){
                if(gp.ui.commardNum == 1 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.PlaySE(9);
                }
                if(gp.ui.commardNum == 2 && gp.se.volumeScale > 0){
                    gp.se.volumeScale--;
                    gp.PlaySE(9);
                }
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.subState == 0){
                if(gp.ui.commardNum == 1 && gp.music.volumeScale < 5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.PlaySE(9);
                }
                if(gp.ui.commardNum == 2 && gp.se.volumeScale < 5){
                    gp.se.volumeScale++;
                    gp.PlaySE(9);
                }
            }
        }
    }

    public void gameOverState(int code){
        if(code == KeyEvent.VK_W){
            gp.ui.commardNum--;
            if(gp.ui.commardNum < 0){
                gp.ui.commardNum = 1;
            }
            gp.PlaySE(9);
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commardNum++;
            if(gp.ui.commardNum > 1){
                gp.ui.commardNum = 0;
            }
            gp.PlaySE(9);
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commardNum == 0){
                gp.gameState = gp.playState;
                gp.retry();
                gp.PlayMusic(0);
            }
            else if(gp.ui.commardNum == 1){
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }

    public void tradeState(int code){
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        if(gp.ui.subState == 0){
            if(code ==  KeyEvent.VK_W){
                gp.ui.commardNum--;
                if(gp.ui.commardNum < 0){
                    gp.ui.commardNum = 2;
                }
                gp.PlaySE(9);
            }
            if(code ==  KeyEvent.VK_S){
                gp.ui.commardNum++;
                if(gp.ui.commardNum > 2){
                    gp.ui.commardNum = 0;
                }
                gp.PlaySE(9);
            }
        }
        
        if(gp.ui.subState == 1){
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }

        if(gp.ui.subState == 2){
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
    }

    public void playerInventory(int code){

        if(code == KeyEvent.VK_W){
            if(gp.ui.PlayerSlotRow != 0){
                gp.ui.PlayerSlotRow--;
                gp.PlaySE(9);
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.PlayerSlotCol != 0){
              gp.ui.PlayerSlotCol--;
                gp.PlaySE(9);  
            }  
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.PlayerSlotRow != 4){
                gp.ui.PlayerSlotRow++;
                gp.PlaySE(9);
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.PlayerSlotCol != 4){
                gp.ui.PlayerSlotCol++;
                  gp.PlaySE(9); 
            }
        }
    }

    public void npcInventory(int code){

        if(code == KeyEvent.VK_W){
            if(gp.ui.npcSlotRow != 0){
                gp.ui.npcSlotRow--;
                gp.PlaySE(9);
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.npcSlotCol != 0){
              gp.ui.npcSlotCol--;
                gp.PlaySE(9);  
            }  
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.npcSlotRow != 4){
                gp.ui.npcSlotRow++;
                gp.PlaySE(9);
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.npcSlotCol != 4){
                gp.ui.npcSlotCol++;
                  gp.PlaySE(9); 
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
