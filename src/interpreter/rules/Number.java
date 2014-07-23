package interpreter.rules;

import interpreter.exceptions.ExecuteException;

import java.util.Collection;
import java.util.HashMap;

public class Number implements Expression {
	private double m_number;

	public Number(double number) {
		m_number = number;
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack) throws ExecuteException {
		return m_number;
	}
}
