package board;

public class Position {
	protected int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
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
		}
		newPos = new Position(new_x, new_y);
		return newPos;
	}

	public boolean isValid() {
		return (x >= 0 && y >= 0);
	}
}
