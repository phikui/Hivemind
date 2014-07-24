package roboscript.interpreter.expressions.structure;

import java.util.Collection;
import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.ValidExit;
import roboscript.interpreter.expressions.Expression;

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

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack, Executable executer)
			throws ExecuteException, ValidExit {
		int cond = (int) m_condition.evaluate(variables, executeStack, executer);
		Expression sequence;
		if (cond == 1) {
			sequence = new Sequence(m_body, this);

			return sequence.evaluate(variables, executeStack, executer);
		}

		return 1;
	}
}
