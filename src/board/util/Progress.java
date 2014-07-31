package board.util;

public class Progress {

	private double total, current, previous, amount;
	private int steps;

	public Progress(double total, int steps) {
		this.total = total;
		this.steps = steps;
		amount = 0;
		current = 0;
		previous = 0;
	}

	public void increment(int i) {
		amount += i;
		current = amount / total * 100;
	}

	public void printProgress() {
		if (current >= (previous + steps)) {
			System.out.println(((int)Math.floor(current)) + "%");
			previous = current;
		}
	}
}
