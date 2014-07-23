package interpreter.rules.variables;

import interpreter.rules.Expression;
import interpreter.exceptions.ExecuteException;
import interpreter.exceptions.SyntaxException;
import interpreter.exceptions.ValidExit;
import interpreter.executer.VariableMapGenerator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Variable implements Expression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String m_name;
	private static Set<String> illegals = VariableMapGenerator.getIllegalVariableNames();

	public Variable(String name) throws SyntaxException{
		m_name = name;

		boolean hasNonAlpha = m_name.matches("^.*[^a-zA-Z0-9 ].*$");
		String lower = m_name.toLowerCase(); // Illegal check should not be
												// case sensitive
		String upper = m_name.toUpperCase();
		if (hasNonAlpha || illegals.contains(lower) || illegals.contains(upper)) {
			throw new SyntaxException("Illegal variable name " + m_name);
			
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
