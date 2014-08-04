package board.score;

public class Score {

	public String id,origin,causeOfDeath;
	public int age;
	
	public Score(String id, String origin, int age) {
	this(id,origin,age,"");
	}
	
	public Score(String id, String origin, int age,String causeOfDeath){
		super();
		this.id = id;
		this.origin = origin;
		this.age = age;
		this.causeOfDeath = causeOfDeath;
	}
	
	
	
}
