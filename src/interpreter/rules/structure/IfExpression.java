package interpreter.rules.structure;

import interpreter.exceptions.ExecuteException;
import interpreter.rules.Expression;

import java.util.Collection;
import java.util.HashMap;

public class IfExpression implements Expression {
	private Expression m_true_case, m_false_case, m_condition;

	public IfExpression(Expression condition, Expression true_case, Expression false_case) {
		m_true_case = true_case;
		m_false_case = false_case;
		m_condition = condition;
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException {
		// 1=true
		// else = false
		if (m_condition.evaluate(variables, executeStack) == 1) {
			return m_true_case.evaluate(variables, executeStack);
		} else {
			if (m_false_case != null) {
				return m_false_case.evaluate(variables, executeStack);
			} else {
				return 0;
			}
		}

	}

}
