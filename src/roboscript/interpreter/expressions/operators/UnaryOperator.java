package roboscript.interpreter.expressions.operators;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

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

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack, Executable executer)
			throws ExecuteException, ValidExit {
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
		} else if (m_operator.equals("randi")){
			result = rand.nextInt((int) (a+1));
		}

		return result;
	}

}
