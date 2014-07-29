package board.util;

import java.util.Random;

public class IntervalSampler {
	static Random rand = new Random();

	public static int sampleInteger(String interval) {
		char start = interval.charAt(0);
		char end = interval.charAt(interval.length() - 1);

		interval = interval.replaceAll("\\(|\\)|\\[|\\]", "");
		String[] tokens = interval.split(",");
		int a = Integer.parseInt(tokens[0]);
		int b = Integer.parseInt(tokens[1]);

		if (start == '(') { // the low value is always sampled inclusively
			a++;
		}
		if (end == ']') { // the high value is always sampled exlucisevely
			b++;
		}

		return rand.nextInt(b - a) + a;
	}

	public static double sampleDouble(String interval) {
		char from = interval.charAt(0);
		char to = interval.charAt(interval.length() - 1);

		interval = interval.replaceAll("\\(|\\)|\\[|\\]", "");
		String[] tokens = interval.split(",");

		double start = Double.parseDouble(tokens[0]);
		double end = Double.parseDouble(tokens[1]);
		boolean is_valid = false;
		double result = 0;
		while (!is_valid) { //reject according to braces
			is_valid = true;
			double random = rand.nextDouble();
			result = start + (random * (end - start));
			if (from == '(' && random == start) {
				is_valid = false;
			}
			if (to == ')' && random == end) {
				is_valid = false;
			}

		}
		return result;
	}

}
