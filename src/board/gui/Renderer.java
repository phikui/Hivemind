package board.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import board.Cell;
import board.GameBoard;

public class Renderer extends Thread {
	private GameBoard board;
	private JFrame frame;
	private int scaling;
	private int ups, real_ups; // updates per second
	private boolean done;

	public Renderer(GameBoard board, int scaling, int framesPerSecond) {
		this.board = board;
		this.scaling = scaling;
		if(framesPerSecond < 0){ //interpret as undbounded fps
			ups = 300;
		} else {
		ups = framesPerSecond;
		}
		
		frame = new JFrame();
		frame.setBounds(0, 0, board.getX_dimension() * scaling, board.getY_dimension() * scaling);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocation(5000, 5000);
		frame.setIgnoreRepaint(true);
		boolean success = false;
		do {
			try {
				frame.createBufferStrategy(2);
				success = true;
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (!success);
	}

	private void paintBoard() {
		frame.setLocation(0, 0);
		BufferStrategy bufferStrategy = frame.getBufferStrategy();
		Graphics g = bufferStrategy.getDrawGraphics();

		for (int i = 0; i < board.getX_dimension(); i++) {
			for (int j = 0; j < board.getY_dimension(); j++) {
				paintCell(i, j, g);
			}
		}
		bufferStrategy.show();
		Toolkit.getDefaultToolkit().sync();
		g.dispose();

	}

	private void paintCell(int i, int j, Graphics g) {
		Cell cell = board.getCells()[i][j];
		if (cell == null) {
			g.setColor(Color.RED);
		} else {
			// if (!cell.needsRepaint()){
			// return;
			// }
			if (cell.getOccupant() != null) {
				g.setColor(Color.BLACK);
			} else if (cell.hasFood()) {
				g.setColor(Color.green);
			} else {
				g.setColor(Color.WHITE);
			}

			board.getCells()[i][j].wasPainted();
		}

		g.fillRect(i * scaling, j * scaling, scaling, scaling);

	}

	public void run() {
		done = false;
		long lastupdate = 0;
		long now=0;
		long timer = 1;

		while (!done) {
			frame.setTitle("Age: " + board.getAge() + "    fps: " + real_ups);
			now = System.currentTimeMillis();
			timer = now - lastupdate; // How many milliseconds have passed since
										// last update
			if (timer >= (1000 / ups)) { // if at least 1000/frequency ms have
										// passed
				// System.out.println("update!");
				paintBoard();
				timer = now - lastupdate;
				real_ups =  (int) (1000/timer);
				
				frame.setTitle("Age: " + board.getAge() + "    fps: " + real_ups);
				
				lastupdate = System.currentTimeMillis();
			} else {
				try {
					Thread.sleep((1000 / ups)-timer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		frame.setVisible(false);
	}

	public void done() {
		done = true;
	}
}
