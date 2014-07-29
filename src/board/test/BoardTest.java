package board.test;

import board.GameBoard;

public class BoardTest {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		GameBoard board = new GameBoard(40, 40, 0.3);
		board.printStatus(true, false, true);
		 board.addRobotFromFile("./scripts/random_valid_direction.rs");
		//board.addMultipleBots(new String[] { "./scripts/random_foodcheck.rs", "./scripts/test2.rs" }, 5);

		board.printStatus(true, true, true);

		while (true) {
			board.executeRobots();
			board.printStatus(true, true, true);
			// System.out.println();
			Thread.sleep(2000);
		}
	}

}
