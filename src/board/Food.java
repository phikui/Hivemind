package board;

public class Food {
	private int amount;
	private Cell place;

	protected void increaseAmount(int amount) {
		this.amount += amount;
	}

	public Food(int amount, Cell place) {
		this.amount = amount;
		this.place = place;
	}

	protected int getAmount() {
		return amount;
	}

	protected Cell getPlace() {
		return place;
	}

}
