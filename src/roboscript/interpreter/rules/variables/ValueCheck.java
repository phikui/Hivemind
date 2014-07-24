package roboscript.interpreter.rules.variables;

import java.util.Collection;
import java.util.HashMap;

import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.rules.Expression;

public class ValueCheck extends VariableFunction {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValueCheck(Variable value) {
		super(value);
	}

	@Override
	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException {
		double result;
		
			Variable var = super.m_value;
			if (var.hasValue(variables)) {
				result = 1;
			} else {
				result = 0;
			}
		
		return result;
	}

}
