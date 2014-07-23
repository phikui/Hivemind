package interpreter.rules;

import interpreter.exceptions.ExecuteException;
import interpreter.exceptions.ValidExit;

import java.util.Collection;
import java.util.HashMap;

public class ExitExpression implements Expression{

	@Override
	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException {
		
		throw new ValidExit();
	}

}
