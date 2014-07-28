package roboscript.interpreter.expressions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.ValidExit;

public class RandomDirection implements Expression{

	private Random rand = new Random();
	
	@Override
	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack,
			Executable executer) throws ExecuteException, ValidExit {
		
		return rand.nextInt(9);
	}

}
