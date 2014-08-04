package board.test;

import java.awt.Color;

import board.GameBoard;
import board.gui.Renderer;

public class BoardTest {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		GameBoard board = new GameBoard(40, 40, 0.3);
		board.printStatus(false, false, true);
		board.addRobotFromFile("./scripts/test3.rs",Color.MAGENTA);
		 board.addRobotFromFile("./scripts/random_valid_direction.rs");
		 board.addRobotFromFile("./scripts/random_foodcheck.rs",Color.BLUE);
		 board.addRobotFromFile("./scripts/random_direction.rs",Color.GRAY);
		//board.addMultipleBots(new String[] { "./scripts/random_foodcheck.rs", "./scripts/test2.rs" }, 5);

		 Renderer renderer = new Renderer(board,10,30);
		 renderer.start();
		board.printStatus(false, true, true);

		while (true) {
			board.executeRobots();
			board.printStatus(false, true, true);
			// System.out.println();
			Thread.sleep(100);
			if (board.getAlive() == 0) {
				break;
			}
		}
		
		board.printHighScoreTopN(20);
		System.out.println();
		System.out.println();
		board.printAverageScore();
		renderer.done();
		System.exit(0);
	}

}
