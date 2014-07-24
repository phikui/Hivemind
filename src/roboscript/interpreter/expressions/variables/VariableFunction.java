package roboscript.interpreter.expressions.variables;

import roboscript.interpreter.expressions.Expression;

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
