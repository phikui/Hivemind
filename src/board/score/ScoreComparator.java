package board.score;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {

	@Override
	public int compare(Score arg0, Score arg1) {
		return arg1.age - arg0.age;
		
		
	}

}
