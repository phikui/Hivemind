package roboscript.interpreter.expressions;

import java.util.Collection;
import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;

public class RandomNumber implements Expression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack, Executable executer)
			throws ExecuteException {

		return Math.random();
	}

}
