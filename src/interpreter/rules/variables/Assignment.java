package interpreter.rules.variables;

import interpreter.exceptions.ExecuteException;
import interpreter.exceptions.SyntaxException;
import interpreter.exceptions.ValidExit;
import interpreter.parser.VariableMapGenerator;
import interpreter.rules.Expression;
import interpreter.rules.Number;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Assignment implements Expression {

	protected String m_variable_name;
	private Expression m_value;
	private static Set<String> illegal_assignments = VariableMapGenerator.getIllegalAssignments();

	public Assignment(String variable_name, Expression value) throws SyntaxException {
		if (isIllegal(variable_name)) {
			throw new SyntaxException("Can't assign value to " + variable_name);
		}
		m_variable_name = variable_name;
		m_value = value;
	}

	protected static boolean isIllegal(String name) {
		// if(name.toUpperCase().equals(name)){ //check for all upper case
		// return true;
		// }

		if (illegal_assignments.contains(name)) {
			return true;
		} else {
			return false;
		}
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
			throws ExecuteException, ValidExit {

		variables.put(m_variable_name, new Number(m_value.evaluate(variables, executeStack)));

		return 1;
	}

}
