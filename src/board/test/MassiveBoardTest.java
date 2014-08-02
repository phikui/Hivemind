package board.test;

import board.GameBoard;
import board.gui.Renderer;

public class MassiveBoardTest {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		GameBoard board = new GameBoard(180, 100, 0.2);
		Renderer renderer = new Renderer(board,10,10);
		int swarmSize=100;
		String one = "./scripts/random_valid_direction.rs";
		String two = "./scripts/random_direction.rs";
		String three = "./scripts/random_foodcheck.rs";
		
		
		board.addMultipleBots(new String[]{one,two,three}, swarmSize);
	
		
		 renderer.start();
		
		while (true) {
			board.executeRobots();
			board.printStatus(false, false, false);
			if (board.getAlive() == 0) {
				break;
			}
			// System.out.println();
			Thread.sleep(1000);
		}
		
		board.printHighScoreTopN(20);
		System.out.println();
		System.out.println();
		board.printAverageScore();
		renderer.done();
	}

}
