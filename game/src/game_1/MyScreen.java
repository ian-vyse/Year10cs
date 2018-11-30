package game_1;

import javax.swing.JFrame;

public class MyScreen extends JFrame {
	public MyScreen() {
		this.setSize(1440,420);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Emoji Racers");
	}
	public static void main(String [] args) {
		MyScreen screen = new MyScreen();
		MyCanvas canvas = new MyCanvas();
		screen.getContentPane().add(canvas);
	}

}
