package board;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.executer.VariableMapGenerator;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.expressions.Expression;

public class Robot {
	private static final int max_energy = 100;
	private static final int max_health = 100;
	private final String id;
	private int energy;
	private int health;
	private Executable code;
	private HashMap<String, Expression> variables;
	private Cell position;

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

	public Robot(Executable code) {
		variables = VariableMapGenerator.getNewVariableMap();
		this.code = code;
		energy = max_energy;
		health = max_health;
		SecureRandom random = new SecureRandom();
		id = new BigInteger(50, random).toString(32);
		
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
		return id;
	}
	
	public long execute() throws ExecuteException{
		return code.execute(variables);
	}

	public void loseEnergy(int i) {
		energy -= i;
		
	}

}
