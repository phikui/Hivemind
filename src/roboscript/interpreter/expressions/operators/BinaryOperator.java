package roboscript.interpreter.expressions.operators;

import java.util.Collection;
import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.ValidExit;
import roboscript.interpreter.expressions.Expression;

public class BinaryOperator implements Expression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String m_operator;
	public Expression m_left, m_right;

	public BinaryOperator(String op, Expression left, Expression right) {
		m_operator = op;
		m_left = left;
		m_right = right;
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack, Executable executer)
			throws ExecuteException, ValidExit {
		double result = 0.;
		double a = m_left.evaluate(variables, executeStack, executer);
		double b = m_right.evaluate(variables, executeStack, executer);

		if (m_operator.equals("+")) {
			result = a + b;
		} else if (m_operator.equals("-")) {
			result = a - b;
		} else if (m_operator.equals("*")) {
			result = a * b;
		} else if (m_operator.equals("/")) {
			if (b == 0) {
				throw new ExecuteException("Divison by 0");
			}
			result = a / b;
		} else if (m_operator.equals("<")) {
			if (a < b) {
				result = 1;
			} else {
				result = 0;
			}
		} else if (m_operator.equals(">")) {
			if (a > b) {
				result = 1;
			} else {
				result = 0;
			}
		} else if (m_operator.equals("<=")) {
			if (a <= b) {
				result = 1;
			} else {
				result = 0;
			}
		} else if (m_operator.equals(">=")) {
			if (a >= b) {
				result = 1;
			} else {
				result = 0;
			}
		} else if (m_operator.equals("|")) {
			if (a == 0 && b == 0) {
				result = 0;
			} else
				result = 1;

		} else if (m_operator.equals("%")) {
			result = a % b;
		} else if (m_operator.equals("&")) {
			if (a == 0 || b == 0) {
				result = 0;
			} else
				result = 1;
		} else if (m_operator.equals("?")) {
			result = Math.pow(a, b);
		} else if (m_operator.equals("==")) {
			if (a == b) {
				result = 1;
			} else {
				result = 0;
			}
		} else if (m_operator.equals("!=")) {
			if (a != b) {
				result = 1;
			} else {
				result = 0;
			}
		}

		return result;
	}

}
