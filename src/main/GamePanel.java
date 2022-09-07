package main;

import java.awt.*;

import javax.swing.JPanel;
import Entity.Player;
import Objects.SuperObject;
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
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	// FPS
	int FPS = 60;

	// System
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	sound Sound =new sound();
	public CollisionChecker checker = new CollisionChecker(this);
	public AssetSetter setter = new AssetSetter(this);
	Thread gameThread; // a thread is a small set of instructions designed to be scheduled

	// Entity and Object
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];

	public GamePanel() {
		this.setPreferredSize(new Dimension(ScreenWidth, ScreenHigh));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // if set True,all you drawing from component will be done in buffer
		this.addKeyListener(keyH); // call KeyHandler class
		this.setFocusable(true);
	}

	public void setupGame(){
		setter.setObject();

		PlayMusic(0);
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
		player.update();
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Tile
		tileM.draw(g2);

		// Object
		for(int i = 0; i < obj.length; i++){
			if(obj[i] != null){
				obj[i].draw(g2, this);
			}
		}

		// Player
		player.draw(g2);

		g2.dispose();
	}

	public void PlayMusic(int i){
		Sound.setFile(i);
		Sound.play();
		Sound.loop();
	}

	public void stopMusic(){
		Sound.stop();
	}

	public void PlaySE(int i){
		Sound.setFile(i);
		Sound.play();
	}
}
