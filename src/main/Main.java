package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when you click X
		window.setVisible(false);
		window.setTitle("2D RPG Advanture");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack(); // method sizes the frame so that all its contents are at or above their
						// preferred sizes.

		window.setLocationRelativeTo(null); // Display will show on the center of the screen
		window.setVisible(true);

		gamePanel.setupGame();
		gamePanel.startGameThread();
	}
}
