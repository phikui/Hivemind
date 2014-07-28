package board.score;

import java.util.Comparator;

public class AccumulatedScoreComparator implements Comparator<AccumulatedScore>{

	@Override
	public int compare(AccumulatedScore o1, AccumulatedScore o2) {
		
		return (int) ( o2.age - o1.age);
	}

}
