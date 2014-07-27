package board.test;

import board.GameBoard;

public class BoardTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		GameBoard board = new GameBoard(10,10,0.5);
		board.printBoard();
		board.addRobotFromFile("./scripts/test2.rs");
		board.printBoard();
		
		while(true){
			board.executeRobots();
			board.printBoard();
			System.out.println();
			Thread.sleep(2000);
		}
	}

}
