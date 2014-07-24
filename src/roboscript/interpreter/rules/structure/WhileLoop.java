package roboscript.interpreter.rules.structure;

import java.util.Collection;
import java.util.HashMap;

import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.ValidExit;
import roboscript.interpreter.rules.Expression;

public class WhileLoop implements Expression {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Expression m_body, m_condition;

	public WhileLoop(Expression condition, Expression body) {
		m_body = body;
		m_condition = condition;
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException, ValidExit {
		int cond = (int) m_condition.evaluate(variables, executeStack);
		Expression sequence;
		if (cond == 1) {
			sequence = new Sequence(m_body, this);

			return sequence.evaluate(variables, executeStack);
		}

		return 1;
	}
}
