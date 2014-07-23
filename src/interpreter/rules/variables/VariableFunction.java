package interpreter.rules.variables;

import interpreter.rules.Expression;

public abstract class VariableFunction implements Expression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Variable m_value;

	public VariableFunction(Variable value) {
		m_value = value;
	}
}
