package main;

import javax.swing.JFrame;

public class Main {

	public static JFrame window;

	public static void main(String[] args) {

		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when you click X
		window.setResizable(false);
		window.setTitle("2D RPG Advanture");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		gamePanel.config.loadConfig();
		if(gamePanel.fullScreenOn == true){
			window.setUndecorated(true);
		}

		window.pack(); // method sizes the frame so that all its contents are at or above their
					   // preferred sizes.

		window.setLocationRelativeTo(null); // Display will show on the center of the screen
		window.setVisible(true);

		gamePanel.setupGame();
		gamePanel.startGameThread();
	}
}
