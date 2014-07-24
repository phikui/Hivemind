package roboscript.interpreter.expressions.variables;

import java.util.Collection;
import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.expressions.Expression;

public class ValueCheck extends VariableFunction {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValueCheck(Variable value) {
		super(value);
	}

	@Override
	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack, Executable executer)
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
