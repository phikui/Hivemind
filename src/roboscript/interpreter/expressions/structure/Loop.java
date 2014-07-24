package roboscript.interpreter.expressions.structure;

import java.util.Collection;
import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.ValidExit;
import roboscript.interpreter.expressions.Expression;

public class Loop implements Expression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Expression m_body, m_count;

	public Loop(Expression count, Expression body) {
		m_body = body;
		m_count = count;
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack, Executable executer)
			throws ExecuteException, ValidExit {
		int count = (int) m_count.evaluate(variables, executeStack, executer);
		Expression sequence;
		if (count > 1) {
			sequence = new Sequence(m_body, m_body);
		} else if (count == 1) {
			m_body.evaluate(variables, executeStack, executer);
			return 1;
		} else {
			return 1;
		}
		for (int i = 2; i < count; i++) {
			sequence = new Sequence(m_body, sequence);
		}
		sequence.evaluate(variables, executeStack, executer);
		return 1;
	}

}
