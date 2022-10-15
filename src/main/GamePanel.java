package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Entity.Entity;
import Entity.Player;
import Tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // Screen Setting
    final int originalTileSize = 16; // 16x16 Title
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;// 48x48 title
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int ScreenWidth = tileSize * maxScreenCol;// 768 pixels
    public final int ScreenHigh = tileSize * maxScreenRow;// 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol; // 2400
    public final int worldHeight = tileSize * maxWorldRow; // 2400

    // FPS
    int FPS = 60;

    // System
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    sound music = new sound();
    sound se = new sound();
    public CollisionChecker checker = new CollisionChecker(this);
    public AssetSetter setter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread; // a thread is a small set of instructions designed to be scheduled

    // Entity and Object
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity Monster[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHigh));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // if set True,all you drawing from component will be done in buffer
        this.addKeyListener(keyH); // call KeyHandler class
        this.setFocusable(true);
    }

    public void setupGame(){
        setter.setObject();
        setter.setNPC();
        setter.setMonster();
        //	PlayMusic(0);
        gameState = titleState;
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
                repaint();
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
            for(int i = 0;i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
            // MONSTER
            for(int i = 0; i < Monster.length; i++){
                if(Monster[i] != null){
                    if(Monster[i].alive == true && Monster[i].dying == false){
                        Monster[i].update();
                    }
                    if(Monster[i].alive == false){
                        Monster[i] = null;
                    }
                }
            }
        }
        if(gameState == pauseState){
            // nothing
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Debug
        long drawStart = 0;
        if(keyH.CheckDrawTime == true){
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
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }

            for(int i = 0; i < obj.length ; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            for(int i = 0; i < Monster.length; i++){
                if(Monster[i] != null){
                    entityList.add(Monster[i]);
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
        if(keyH.CheckDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: "+passed, 10 , 400);
            System.out.println("Draw Time: "+passed);
        }
        g2.dispose();
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
