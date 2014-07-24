package roboscript.interpreter.rules;

import java.util.Collection;
import java.util.HashMap;

import roboscript.interpreter.exceptions.ExecuteException;

public class Number implements Expression {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double m_number;

	public Number(double number) {
		m_number = number;
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack) throws ExecuteException {
		return m_number;
	}
}
