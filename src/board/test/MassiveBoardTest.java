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
		
		// for progress calculation ____________
				int total = 3*swarmSize;
				int amount = 0;
				double current_p = 0;
				double previous_p = 0;
				// _____________________________________
		System.out.println("adding bots");
		for (int i = 0; i < swarmSize; i++) {
			board.addRobotFromFile("./scripts/random_direction.rs");
			
			// for progress calculation ____________
			amount++;
			current_p = ((double) amount) / ((double) total) * 100;
			if (current_p >= (previous_p + 5)) {
				System.out.println(Math.floor(current_p) + "%");
				previous_p = current_p;
			}
			// _____________________________________
			
		}

		for (int i = 0; i < swarmSize; i++) {
			board.addRobotFromFile("./scripts/random_valid_direction.rs");
			
			// for progress calculation ____________
			amount++;
			current_p = ((double) amount) / ((double) total) * 100;
			if (current_p >= (previous_p + 5)) {
				System.out.println(Math.floor(current_p) + "%");
				previous_p = current_p;
			}
			// _____________________________________
			
		}
		
		for (int i = 0; i < swarmSize; i++) {
			board.addRobotFromFile("./scripts/random_foodcheck.rs");
			
			// for progress calculation ____________
			amount++;
			current_p = ((double) amount) / ((double) total) * 100;
			if (current_p >= (previous_p + 5)) {
				System.out.println(Math.floor(current_p) + "%");
				previous_p = current_p;
			}
			// _____________________________________
			
		}
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
