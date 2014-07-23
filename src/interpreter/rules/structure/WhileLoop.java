package interpreter.rules.structure;

import interpreter.exceptions.ExecuteException;
import interpreter.rules.Expression;

import java.util.Collection;
import java.util.HashMap;

public class WhileLoop implements Expression {
	private Expression m_body, m_condition;

	public WhileLoop(Expression condition, Expression body) {
		m_body = body;
		m_condition = condition;
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException {
		int cond = (int) m_condition.evaluate(variables, executeStack);
		Expression sequence;
		if (cond == 1) {
			sequence = new Sequence(m_body, this);

			return sequence.evaluate(variables, executeStack);
		}

		return 1;
	}
}
