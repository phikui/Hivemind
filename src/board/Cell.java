package board;

public class Cell {

	private Robot occupant;
	private Food food;
	private Position pos;

	public Robot getOccupant() {
		return occupant;
	}

	public Cell(Position position) {
		pos = position;
	}

	protected void deleteOccupant() {
		occupant = null;
	}

	protected void addBot(Robot bot) {
		occupant = bot;
		if (hasFood()) {
			occupant.gainEnergy(food.getAmount());
			food = null;
		}
	}

	public boolean isOccupied() {
		return occupant != null;
	}

	public boolean hasFood() {
		return food != null;
	}

	public void addFood(Food food) {
		if (this.food == null) {
			this.food = food;
		} else {
			this.food.increaseAmount(food.getAmount());
		}
	}

	public Position getPosition() {
		return pos;
	}

	public void removeOccupant() {
		occupant = null;
	}
}
