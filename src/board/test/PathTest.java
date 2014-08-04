package board.test;

import java.util.ArrayList;
import java.util.List;

import board.GameBoard;
import board.Position;

public class PathTest {

	public static void main(String[] args) throws InterruptedException {
		GameBoard board = new GameBoard(11, 11, 0.3);
		board.printStatus(true, false, false);

		Position pos = new Position(0, 0);
		List<Position> path = pos.getRandomPath(30, board);
		List<Position> destroy = new ArrayList<Position>();
		for (Position x : path) {
			destroy.add(x);
			destroy.addAll(x.get_k_neighborhood(board, 1));
			// printPos(x);

		}
		board.destroyCells(destroy);
		board.printStatus(true, false, false);
	}

	private static void printPos(Position pos) {
		for (int y = 0; y < pos.y; y++) {
			System.out.println(" ");
		}
		for (int x = 0; x < pos.x; x++) {
			System.out.print(" ");
		}
		System.out.println("X");

		for (int y = pos.y; y < 11; y++) {
			System.out.println();
		}
	}

}
