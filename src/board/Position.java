package board;

public class Position {
	protected int x, y;
	protected static int max_x, max_y;

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
		default:
			break;
		}
		newPos = new Position(new_x, new_y);
		return newPos;
	}

	public boolean isValid() {
		return (x >= 0 && y >= 0 && x <= max_x && y <= max_y);
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
}
