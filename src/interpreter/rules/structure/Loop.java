package interpreter.rules.structure;

import interpreter.exceptions.ExecuteException;
import interpreter.rules.Expression;

import java.util.Collection;
import java.util.HashMap;

public class Loop implements Expression {

	private Expression m_body, m_count;

	public Loop(Expression count, Expression body) {
		m_body = body;
		m_count = count;
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException {
		int count = (int) m_count.evaluate(variables, executeStack);
		Expression sequence;
		if (count > 1) {
			sequence = new Sequence(m_body, m_body);
		} else if (count == 1) {
			m_body.evaluate(variables, executeStack);
			return 1;
		} else {
			return 1;
		}
		for (int i = 2; i < count; i++) {
			sequence = new Sequence(m_body, sequence);
		}
		sequence.evaluate(variables, executeStack);
		return 1;
	}

}
