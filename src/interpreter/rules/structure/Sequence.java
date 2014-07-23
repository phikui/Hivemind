package interpreter.rules.structure;

import interpreter.exceptions.ExecuteException;
import interpreter.exceptions.ValidExit;
import interpreter.rules.Expression;

import java.util.Collection;
import java.util.HashMap;

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

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException, ValidExit {

		if (m_b != null) {
			executeStack.add(m_b);
		}
		if (m_a != null) {
			return m_a.evaluate(variables, executeStack);
		}
		return 0;

	}

}
