package board.test;

import board.GameBoard;

public class MassiveBoardTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		GameBoard board = new GameBoard(5000,5000,0.3);
		board.addRobotFromFile("./scripts/test2.rs");
		
		
		while(true){
			board.executeRobots();
			board.printStatus(false, true);
			//System.out.println();
			Thread.sleep(2000);
		}
	}

}
