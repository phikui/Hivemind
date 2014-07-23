package interpreter.rules.variables;

import interpreter.exceptions.ExecuteException;
import interpreter.exceptions.SyntaxException;
import interpreter.rules.Expression;

import java.util.Collection;
import java.util.HashMap;

//only assigns a value if variable has none
public class OneTimeAssignment extends Assignment {

	public OneTimeAssignment(String variable_name, Expression value) throws SyntaxException {
		super(variable_name, value);

	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException {
		if (!variables.containsKey(super.m_variable_name)) {
			return super.evaluate(variables, executeStack);
		} else {
			return 1;
		}
	}

}
