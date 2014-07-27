package board;

public class Cell {

	private Robot occupant;
	private Food food;
	private Position pos;

	public Cell(Position position) {
		pos = position;
	}

	public boolean isOccupied() {
		return occupant != null;
	}

	public boolean hasFood() {
		return food != null;
	}

	public void addFood(Food food) {
		this.food = food;
	}

	public void addBot(Robot bot) {
		occupant = bot;
	}

	public Position getPosition() {
		return pos;
	}

	public void removeOccupant() {
		occupant = null;
	}
}
