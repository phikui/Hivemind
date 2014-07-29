package board.test;

import board.GameBoard;

public class MassiveBoardTest {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		GameBoard board = new GameBoard(5000, 5000, 0.4);
		int swarmSize=1000;
		String one = "./scripts/random_valid_direction.rs";
		String two = "./scripts/random_direction.rs";
		String three = "./scripts/random_foodcheck.rs";
		
		System.out.println("adding bots");
		board.addMultipleBots(new String[]{one,two,three}, swarmSize);
		System.out.println("done.");
		while (true) {
			board.executeRobots();
			board.printStatus(false, true, true);
			if (board.getAlive() == 0) {
				break;
			}
			// System.out.println();
			//Thread.sleep(2000);
		}
		
		board.printHighScore();
		System.out.println();
		System.out.println();
		board.printAverageScore();
	}

}
