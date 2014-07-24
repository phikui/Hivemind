package roboscript.interpreter.expressions.structure;

import java.util.Collection;
import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.ValidExit;
import roboscript.interpreter.expressions.Expression;

public class Sequence implements Expression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Expression m_a, m_b;

	public Sequence(Expression a, Expression b) {
		m_a = a;
		m_b = b;
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack, Executable executer)
			throws ExecuteException, ValidExit {

		if (m_b != null) {
			executeStack.add(m_b);
		}
		if (m_a != null) {
			return m_a.evaluate(variables, executeStack, executer);
		}
		return 0;

	}

}
