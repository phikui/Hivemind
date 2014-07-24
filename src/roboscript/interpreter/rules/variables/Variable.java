package roboscript.interpreter.rules.variables;

import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.SyntaxException;
import roboscript.interpreter.exceptions.ValidExit;
import roboscript.interpreter.rules.Expression;
import roboscript.parser.VariableNames;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Variable implements Expression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String m_name;
	private static Set<String> illegals = VariableNames.getIllegalVariableNames();

	public Variable(String name) throws SyntaxException{
		m_name = name;

		boolean is_valid = m_name.matches("^[a-zA-Z0-9_]*$");
		String lower = m_name.toLowerCase(); // Illegal check should not be
												// case sensitive
		String upper = m_name.toUpperCase();
		if ((!is_valid) || illegals.contains(lower) || illegals.contains(upper)) {
			throw new SyntaxException("Illegal variable name " + m_name+". Please only use alphanumerical symbols including underscore (_) and no reserved keywords.");
			
		}
	}

	public String getName() {
		return m_name;
	}

	public boolean hasValue(HashMap<String, Expression> context) {
		return context.containsKey(m_name);
	}

	public double evaluate(HashMap<String, Expression> context, Collection<Expression> executeStack) throws ExecuteException, ValidExit {
		if (context.containsKey(m_name)) {
			return context.get(m_name).evaluate(context, executeStack);
		} else {
			throw new ExecuteException("Variable " + m_name + " has no value");
		}
	}

}
