package roboscript.interpreter.expressions.structure;

import java.util.Collection;
import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.ValidExit;
import roboscript.interpreter.expressions.Expression;

public class IfExpression implements Expression {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Expression m_true_case, m_false_case, m_condition;

	public IfExpression(Expression condition, Expression true_case, Expression false_case) {
		m_true_case = true_case;
		m_false_case = false_case;
		m_condition = condition;
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack, Executable executer)
			throws ExecuteException, ValidExit {
		// 1=true
		// else = false
		if (m_condition.evaluate(variables, executeStack, executer) == 1) {
			return m_true_case.evaluate(variables, executeStack, executer);
		} else {
			if (m_false_case != null) {
				return m_false_case.evaluate(variables, executeStack, executer);
			} else {
				return 0;
			}
		}

	}

}
