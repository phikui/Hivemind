package board.test;

import util.DataPlotter;
import board.GameBoard;
import board.gui.Renderer;

public class MassiveBoardTest {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		GameBoard board = new GameBoard(800, 300, 0.1);
		Renderer renderer = new Renderer(board,2,30);
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
			Thread.sleep(10);
		}
		
		board.printHighScoreTopN(20);
		System.out.println();
		System.out.println();
		board.printAverageScore();
		renderer.done();
		DataPlotter.showData("Population", "year","living bots",board.getPopulation());
		//System.exit(0);
	}

}
