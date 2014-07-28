package board.score;

import java.util.HashMap;
import java.util.Map;

public class AccumulatedScore {
	public String origin;
	public double age;
	private static Map<String, Integer> count = new HashMap<String, Integer>();
	private static Map<String, Integer> sum = new HashMap<String, Integer>();

	public AccumulatedScore(String origin, int age) {
		super();
		this.origin = origin;

		if (count.containsKey(this.origin)) {
			count.put(this.origin, count.get(this.origin) + 1);
			sum.put(this.origin, sum.get(this.origin) + age);
		} else {
			count.put(this.origin, 1);
			sum.put(this.origin, age);
		}

		this.age = sum.get(this.origin)/count.get(this.origin);
	}

}
