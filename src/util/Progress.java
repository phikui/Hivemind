package util;

import javax.swing.ProgressMonitor;

public class Progress {
	private int total, amount;
	private double current, previous;
	private int steps;
	ProgressMonitor monitor;

	public Progress(int total, int steps, String TaskDescription) {
		this.total = total;
		this.steps = steps;
		amount = 0;
		current = 0;
		previous = 0;
		monitor = new ProgressMonitor(null, TaskDescription, "", 0, (int) total);
	}

	
	public Progress (int total, String TaskDescription){
		this(total,5,TaskDescription);
	}
	
	public void increment(int i) {
		amount += i;
		current = (double) amount / (double) total * 100;
		
		String message = String.format("Completed %d%%.\n", (int) current);
		monitor.setNote(message);
		monitor.setProgress(amount);
		
		if(monitor.isCanceled()){
			System.err.println("Cancelled");
			System.exit(0);
		}
	}

	public void printProgress() {
		if (current >= (previous + steps)) {
			System.out.println(((int) Math.floor(current)) + "%");
			previous = current;
		}
	}
}
