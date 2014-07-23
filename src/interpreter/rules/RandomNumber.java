package interpreter.rules;

import interpreter.exceptions.ExecuteException;

import java.util.Collection;
import java.util.HashMap;

public class RandomNumber implements Expression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException {

		return Math.random();
	}

}
