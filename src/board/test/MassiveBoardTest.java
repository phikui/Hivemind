package board.test;

import board.GameBoard;

public class MassiveBoardTest {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		GameBoard board = new GameBoard(5000, 5000, 0.3);
		for (int i = 0; i < 10; i++) {
			board.addRobotFromFile("./scripts/random_direction.rs");
		}

		for (int i = 0; i < 10; i++) {
			board.addRobotFromFile("./scripts/random_valid_direction.rs");
		}
		
		for (int i = 0; i < 10; i++) {
			board.addRobotFromFile("./scripts/random_foodcheck.rs");
		}
		
		while (true) {
			board.executeRobots();
			board.printStatus(false, true, true);
			if (board.getAlive() == 0) {
				break;
			}
			// System.out.println();
			Thread.sleep(2000);
		}
		
		board.printHighScore();
	}

}
