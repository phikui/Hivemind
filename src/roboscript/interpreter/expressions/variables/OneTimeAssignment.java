package roboscript.interpreter.expressions.variables;

import java.util.Collection;
import java.util.HashMap;

import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.SyntaxException;
import roboscript.interpreter.exceptions.ValidExit;
import roboscript.interpreter.expressions.Expression;

//only assigns a value if variable has none
public class OneTimeAssignment extends Assignment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OneTimeAssignment(String variable_name, Expression value) throws SyntaxException {
		super(variable_name, value);

	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException, ValidExit {
		if (!variables.containsKey(super.m_variable_name)) {
			return super.evaluate(variables, executeStack);
		} else {
			return 1;
		}
	}

}
