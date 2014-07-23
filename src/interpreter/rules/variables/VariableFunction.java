package interpreter.rules.variables;

import interpreter.rules.Expression;

public abstract class VariableFunction implements Expression {

	protected Variable m_value;

	public VariableFunction(Variable value) {
		m_value = value;
	}
}
