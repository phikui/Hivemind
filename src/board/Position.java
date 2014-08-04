package board;

import java.util.ArrayList;
import java.util.List;

import util.IntervalSampler;

public class Position {
	public int x, y;
	protected static int max_x, max_y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean containsPosition(List<Position> list, Position x) {
		for (Position y : list) {
			if (y.equals(x)) {
				return true;
			}
		}
		return false;
	}

	public Position move(int direction) {
		Position newPos;
		int new_x = x, new_y = y;

		// Directions
		/*
		 * 1 2 3
		 * 
		 * 4 0 5
		 * 
		 * 6 7 8
		 */

		switch (direction) {
		case 1:
			new_x--;
			new_y--;
			break;
		case 2:
			new_y--;
			break;
		case 3:
			new_y--;
			new_x++;
			break;
		case 4:
			new_x--;
			break;
		case 5:
			new_x++;
			break;
		case 6:
			new_y++;
			new_x--;
			break;
		case 7:
			new_y++;
			break;
		case 8:
			new_x++;
			new_y++;
			break;
		default:
			break;
		}
		newPos = new Position(new_x, new_y);
		return newPos;
	}

	public boolean isValid(GameBoard board) {
		if (x >= 0 && y >= 0 && x <= max_x && y <= max_y) {
			if (board.cells[x][y] != null) {
				return true;
			}
		}

		return false;
	}

	public List<Position> get_k_neighborhood(GameBoard board, int k) {
		List<Position> neighborhood = new ArrayList<Position>();

		// bounding box of size 2k
		for (int i = x - k; i <= x + k; i++) {
			for (int j = y - k; j <= y + k; j++) {
				Position new_pos = new Position(i, j);
				if (new_pos.isValid(board)) {
					neighborhood.add(new_pos);
				}
			}
		}

		return neighborhood;

	}

	public List<Position> getRandomPath(int length, GameBoard board) {
		List<Position> path = new ArrayList<Position>();
		Position pos = this;
		path.add(pos);

		for (int i = 0; i < length; i++) {
			int tries = 0;
			int d = IntervalSampler.sampleInteger("[0,8]");
			while ((!pos.move(d).isValid(board))
					|| containsPosition(path, pos.move(d))) {
				d = IntervalSampler.sampleInteger("[0,8]");
				tries++;

				if (tries > 1000) {
					break;
				}
			}
			pos = pos.move(d);
			path.add(pos);
		}

		return path;
	}

	public boolean equals(Position pos) {
		return (this.x == pos.x && this.y == pos.y);
	}

	public static String getCellName(int d) {
		String name = "";
		// Directions
		/*
		 * 1 2 3
		 * 
		 * 4 0 5
		 * 
		 * 6 7 8
		 */

		switch (d) {
		case 0:
			name = "STAY";
			break;
		case 1:
			name = "NW";
			break;
		case 2:
			name = "N";
			break;
		case 3:
			name = "NE";
			break;
		case 4:
			name = "W";
			break;
		case 5:
			name = "E";
			break;
		case 6:
			name = "SW";
			break;
		case 7:
			name = "S";
			break;
		case 8:
			name = "SE";
			break;
		default:
			break;
		}

		return name;
	}

	public String toString() {
		return "(" + x + "," + y + ")";

	}
}
