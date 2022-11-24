package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import AI.PathFinding;
import Entity.Entity;
import Entity.Player;
import Tiles.TileManager;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    // Screen Setting
    final int originalTileSize = 16; // 16x16 Title
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;// 48x48 title
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;// 960 pixels
    public final int screenHeight = tileSize * maxScreenRow;// 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;
    public final int worldWidth = tileSize * maxWorldCol; // 2400
    public final int worldHeight = tileSize * maxWorldRow; // 2400

    // FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    // FPS
    int FPS = 60;

    // System
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    sound music = new sound();
    sound se = new sound();
    public CollisionChecker checker = new CollisionChecker(this);
    public AssetSetter setter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    public PathFinding PFinding = new PathFinding(this);
    Thread gameThread; // a thread is a small set of instructions designed to be scheduled

    // Entity and Object
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[maxMap][20];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity Monster[][] = new Entity[maxMap][20];
    public EventHandler event[] = new EventHandler[3];
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // if set True,all you drawing from component will be done in buffer
        this.addKeyListener(keyH); // call KeyHandler class
        this.setFocusable(true);
    }

    public void setupGame(){
        setter.setObject();
        setter.setNPC();
        setter.setMonster();
        // PlayMusic();
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();

        if(fullScreenOn == true){
            setFullScreen();
        }
    }

    public void retry(){
        player.setDefaultPosition();
        player.RestoreLife();
        setter.setNPC();
        setter.setMonster();
    }

    public void restart(){
        player.setDefaultValues();
        player.setItem();
        setter.setObject();
        setter.setNPC();
        setter.setMonster();
    }

    public void setFullScreen(){
        // GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInteval = 1000000000 / FPS; // 16666666.66
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInteval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                DrawTempScreen(); // draw everything to the buffered image
                DrawToScreen(); // draw the buffered image to the screen
                delta--; // reset delta
                drawCount++;
            }

            // show FPS
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        if(gameState == playState){
            // PLAYER
            player.update();
            // NPC
            for(int i = 0;i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }
            // MONSTER
            for(int i = 0; i < Monster[1].length; i++){
                if(Monster[currentMap][i] != null){
                    if(Monster[currentMap][i].alive == true && Monster[currentMap][i].dying == false){
                        Monster[currentMap][i].update();
                    }
                    if(Monster[currentMap][i].alive == false){
                        Monster[currentMap][i].checkDrop();
                        Monster[currentMap][i] = null;
                    }
                }
            }
        }
        if(gameState == pauseState){
            // nothing
        }
    }

    public void DrawTempScreen(){
        // Debug
        long drawStart = 0;
        if(keyH.showDebugText == true){
            drawStart = System.nanoTime();
        }

        // TITILE SCREEN
        if(gameState == titleState){
            ui.draw(g2);

        }
        // OTHERS
        else{
            // Tile
            tileM.draw(g2);

            entityList.add(player);
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    entityList.add(npc[currentMap][i]);
                }
            }

            for(int i = 0; i < obj[1].length ; i++){
                if(obj[currentMap][i] != null){
                    entityList.add(obj[currentMap][i]);
                }
            }

            for(int i = 0; i < Monster[1].length; i++){
                if(Monster[currentMap][i] != null){
                    entityList.add(Monster[currentMap][i]);
                }
            }

            // Sort
            Collections.sort(entityList, new Comparator<Entity>() { 
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            // EMPTY ENTITY LIST
            entityList.clear();

            //UI
            ui.draw(g2);
        }

        // DEBUG
        if(keyH.showDebugText == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial",Font.PLAIN,20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 450;
            int lineHeight = 20;

            g2.drawString("worldX : " + player.worldX, x, y); y += lineHeight;
            g2.drawString("worldY : " + player.worldY, x, y); y += lineHeight;
            g2.drawString("Col : " + (player.worldX +  player.solidArea.x)/tileSize, x, y); y += lineHeight;
            g2.drawString("Row : " + (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight+lineHeight;

            g2.drawString("Draw Time: "+passed, x , y);
        }
    }

    public void DrawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2,screenHeight2,null);
        g.dispose();
    }

    public void PlayMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void PlaySE(int i){
        se.setFile(i);
        se.play();
    }
}
