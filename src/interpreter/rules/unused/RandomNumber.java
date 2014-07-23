package interpreter.rules.unused;

import interpreter.exceptions.ExecuteException;
import interpreter.rules.Expression;

import java.util.Collection;
import java.util.HashMap;

public class RandomNumber implements Expression {

	@Override
	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException {

		return Math.random();
	}

}
