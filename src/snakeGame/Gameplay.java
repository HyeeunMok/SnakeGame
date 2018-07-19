package snakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	
	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];

	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private int lengthOfSnake = 3;
	
	private Timer timer;
	private int delay = 100;
	private int moves = 0;
	private ImageIcon snakebody;

	private int[] foodXposition = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 
			300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650,
			675, 700, 750, 775, 800, 825, 850};
	private int[] foodYposition = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325,
			350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	private ImageIcon food;
	
	private Random random = new Random();
	
	private int xposition = random.nextInt(33);
	private int yposition = random.nextInt(23);
	
	private int score = 0;
	
	private ImageIcon titleImage;
	
	public Gameplay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		
		if (moves == 0) {
			
			snakeXlength[0] = 100;
			snakeXlength[1] = 75;
			snakeXlength[2] = 50;

			snakeYlength[0] = 100;
			snakeYlength[1] = 100;
			snakeYlength[2] = 100;
		}
		
		// draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		// draw the title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		// draw border for gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		// draw background for the gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		// draw scores
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 18));
		g.drawString("Scores: " + score, 780, 30);

		// draw length
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 18));
		g.drawString("Length: " + lengthOfSnake, 780, 50);

		rightmouth = new ImageIcon("rightmouth.png");
		leftmouth = new ImageIcon("leftmouth.png");
		upmouth = new ImageIcon("upmouth.png");
		downmouth = new ImageIcon("downmouth.png");
		snakebody = new ImageIcon("snakebody.png");
		
		rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		for (int i = 0; i < lengthOfSnake; i++) {
			if (i == 0 && right) {
				rightmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if (i == 0 && left) {
				leftmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if (i == 0 && up) {
				upmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if (i == 0 && down) {
				downmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if (i != 0) {
				snakebody.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
		}
		
		food = new ImageIcon("food.png");
		
		if ((foodXposition[xposition] == snakeXlength[0]) && foodYposition[yposition] == snakeYlength[0]) {
			score++;
			lengthOfSnake++;
			xposition = random.nextInt(33);
			yposition = random.nextInt(23);
		}
		food.paintIcon(this, g, foodXposition[xposition], foodYposition[yposition]);
		
		for (int i = 1; i < lengthOfSnake; i++) {
			if (snakeXlength[i] == snakeXlength[0] && snakeYlength[i] == snakeYlength[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 60));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 30));
				g.drawString("Space to RESTART", 350, 340);
			}
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (right) {
			for (int i = lengthOfSnake - 1; i >= 0; i--) {
				snakeYlength[i + 1] = snakeYlength[i];
			}
			for (int i = lengthOfSnake; i >= 0; i--) {
				if (i == 0) {
					snakeXlength[i] = snakeXlength[i] + 25;
				} else {
					snakeXlength[i] = snakeXlength[i - 1];
				}
				if (snakeXlength[i] > 850) {
					snakeXlength[i] = 25;
				}
			}
			
			repaint();
		}
		if (left) {
			for (int i = lengthOfSnake - 1; i >= 0; i--) {
				snakeYlength[i + 1] = snakeYlength[i];
			}
			for (int i = lengthOfSnake; i >= 0; i--) {
				if (i == 0) {
					snakeXlength[i] = snakeXlength[i] - 25;
				} else {
					snakeXlength[i] = snakeXlength[i - 1];
				}
				if (snakeXlength[i] < 25) {
					snakeXlength[i] = 850;
				}
			}
			
			repaint();
		}
		if (up) {
			for (int i = lengthOfSnake - 1; i >= 0; i--) {
				snakeXlength[i + 1] = snakeXlength[i];
			}
			for (int i = lengthOfSnake; i >= 0; i--) {
				if (i == 0) {
					snakeYlength[i] = snakeYlength[i] - 25;
				} else {
					snakeYlength[i] = snakeYlength[i - 1];
				}
				if (snakeYlength[i] < 75) {
					snakeYlength[i] = 625;
				}
			}
			
			repaint();
		}
		if (down) {
			for (int i = lengthOfSnake - 1; i >= 0; i--) {
				snakeXlength[i + 1] = snakeXlength[i];
			}
			for (int i = lengthOfSnake; i >= 0; i--) {
				if (i == 0) {
					snakeYlength[i] = snakeYlength[i] + 25;
				} else {
					snakeYlength[i] = snakeYlength[i - 1];
				}
				if (snakeYlength[i] > 625) {
					snakeYlength[i] = 75;
				}
			}
			
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lengthOfSnake = 3;
			repaint();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right = true;
			if (!left) {
				right = true;
			} else {
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			if (!right) {
				left = true;
			} else {
				right = true;
				left = false;
			}
			up = false;
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			if (!down) {
				up = true;
			} else {
				up = false;
				down = true;
			}
			right = false;
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			if (!up) {
				down = true;
			} else {
				up = true;
				down = false;
			}
			right = false;
			left = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
