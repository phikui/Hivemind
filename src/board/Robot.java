package board;

import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.interpreter.expressions.Number;
import roboscript.executer.VariableMapGenerator;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.expressions.Expression;

public class Robot {
	private static final int max_energy = 100;
	private static final int max_health = 100;
	private static int counter;
	private final String origin;
	private String unique_id;
	private int energy;
	private int health;
	private Executable code;
	protected HashMap<String, Expression> variables;

	private Cell position;
	private int age;

	public HashMap<String, Expression> getVariables() {
		return variables;
	}

	protected Cell getPosition() {
		return position;
	}

	protected void setPosition(Cell position) {
		this.position = position;
	}

	public int getEnergy() {
		return energy;
	}

	public int getHealth() {
		return health;
	}

	public void setVariables(GameBoard board) {
		variables.put("MOVE_DIRECTION", new Number(-1));
		// for every possible direction
		for (int i = 0; i < 9; i++) {
			String name = Position.getCellName(i);

			// variables for valid positions
			int value;
			Position newPos = position.getPosition().move(i);
			Cell newCell = board.getCellFromPosition(newPos);
			if (newPos.isValid(board)) {
				value = 1;
			} else {
				value = 0;
			}
			variables.put("IS_VALID_" + name, new Number(value));
			

			// variables for is occupied
			if ((newCell != null) && newCell.isOccupied()) {
				value = 1;
			} else {
				value = 0;
			}
			variables.put("IS_OCCUPIED_" + name, new Number(value));
			

			if ((newCell != null) && newCell.hasFood()) {
				value = 1;
			} else {
				value = 0;
			}

			variables.put("HAS_FOOD_" + name, new Number(value));

		}
		
	}

	public Robot(Executable code, String id) {
		variables = VariableMapGenerator.getNewVariableMap();
		this.code = code;
		energy = max_energy;
		health = max_health;
		this.origin = id;
		setAge(0);
		unique_id = "" + counter;
		counter++;

	}

	protected void incrementAge() {
		setAge(getAge() + 1);
	}

	public void gainEnergy(int amount) {
		energy += amount;
		energy = Math.min(energy, max_energy);
	}

	public void gainHealth(int amount) {
		health += amount;
		health = Math.min(health, max_health);
	}

	public String getID() {
		return unique_id;
	}

	public long execute() throws ExecuteException {
		
		
		long runtime = code.execute(variables);
		

		return runtime;
	}

	public void loseEnergy(int i) {
		energy -= i;

	}

	public int getAge() {
		return age;
	}

	private void setAge(int age) {
		this.age = age;
	}

	public void loseHealth(int damage) {
		health -= damage;

	}

	public String getOrigin() {
		return origin;
	}

}
