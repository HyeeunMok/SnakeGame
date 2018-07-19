package snakeGame;

import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame object = new JFrame();
		Gameplay gameplay = new Gameplay();
		
		object.setBounds(10, 10, 905, 700);
		object.setBackground(Color.DARK_GRAY);
		object.setResizable(false);
		object.setVisible(true);
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		object.add(gameplay);

	}

}
