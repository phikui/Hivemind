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
		int swarmSize=100;
		String one = "./scripts/random_valid_direction.rs";
		String two = "./scripts/random_direction.rs";
		String three = "./scripts/random_foodcheck.rs";
		
		
		board.addMultipleBots(new String[]{one,two,three}, swarmSize);
	
		while (true) {
			board.executeRobots();
			board.printStatus(false, false, false);
			if (board.getAlive() == 0) {
				break;
			}
			// System.out.println();
			//Thread.sleep(2000);
		}
		
		board.printHighScoreTopN(20);
		System.out.println();
		System.out.println();
		board.printAverageScore();
	}

}
