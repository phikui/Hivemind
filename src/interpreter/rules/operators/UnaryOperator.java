package interpreter.rules.operators;

import interpreter.exceptions.ExecuteException;
import interpreter.exceptions.ValidExit;
import interpreter.rules.Expression;

import java.util.Collection;
import java.util.HashMap;

public class UnaryOperator implements Expression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String m_operator;
	public Expression m_value;

	public UnaryOperator(String op, Expression value) {
		m_operator = op;
		m_value = value;
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException, ValidExit {
		double result = 0;
		double a = m_value.evaluate(variables, executeStack);
		if (m_operator.equals("!")) {
			if (a == 0) {
				result = 1;
			} else {
				result = 0;
			}
		} else if (m_operator.equals("sqrt")) {
			result = Math.sqrt(a);
		} else if (m_operator.equals("abs")) {
			result = Math.abs(a);
		} else if (m_operator.equals("log")) {
			result = Math.log(a);
		}

		return result;
	}

}
