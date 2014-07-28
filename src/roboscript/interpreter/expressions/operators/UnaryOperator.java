package roboscript.interpreter.expressions.operators;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import board.Position;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.ValidExit;
import roboscript.interpreter.expressions.Expression;

public class UnaryOperator implements Expression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String m_operator;
	public Expression m_value;
	private Random rand;

	public UnaryOperator(String op, Expression value) {
		m_operator = op;
		m_value = value;
		rand = new Random();
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack,
			Executable executer) throws ExecuteException, ValidExit {
		double result = 0;
		double a = m_value.evaluate(variables, executeStack, executer);
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
		} else if (m_operator.equals("randi")) {
			result = rand.nextInt((int) (a + 1));
		} else if (m_operator.equals("IS_VALID")) {
			result = variables.get("IS_VALID_" + Position.getCellName((int) a)).evaluate(variables, executeStack,
					executer);
		} else if (m_operator.equals("IS_OCCUPIED")) {
			result = variables.get("IS_OCCUPIED_" + Position.getCellName((int) a)).evaluate(variables, executeStack,
					executer);
		} else if (m_operator.equals("HAS_FOOD")) {
			result = variables.get("HAS_FOOD_" + Position.getCellName((int) a)).evaluate(variables, executeStack,
					executer);
		}

		return result;
	}

}
