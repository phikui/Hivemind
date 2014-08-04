package board;

public class Cell {

	private Robot occupant;
	private Food food;
	private Position pos;
	private boolean needsRepaint;

	public Robot getOccupant() {
		return occupant;
	}

	public Cell(Position position) {
		pos = position;
		needsRepaint = true;
	}

	protected void deleteOccupant() {
		occupant = null;
		needsRepaint = true;
	}

	protected void addBot(Robot bot) {
		occupant = bot;
		if (hasFood()) {
			occupant.gainEnergy(food.getAmount());
			food = null;
		}
		needsRepaint = true;
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
		needsRepaint = true;
	}

	public Position getPosition() {
		return pos;
	}

	public void removeOccupant() {
		occupant = null;
		needsRepaint = true;
	}

	public boolean needsRepaint() {
		return needsRepaint;
	}

	public void wasPainted() {
		needsRepaint = false;
	}
}
