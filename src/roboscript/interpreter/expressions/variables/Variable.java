package roboscript.interpreter.expressions.variables;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.SyntaxException;
import roboscript.interpreter.exceptions.ValidExit;
import roboscript.interpreter.expressions.Expression;
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
		char[] m_name_symbols = m_name.toCharArray();
		char last_symbol = m_name_symbols[m_name_symbols.length-1];
		if(last_symbol == '_'){
			throw new SyntaxException ("Variable name can't end with _");
		}

		boolean is_valid = m_name.matches("^[a-zA-Z0-9_]*$");
		String lower = m_name.toLowerCase(); // Illegal check should not be
												// case sensitive
		String upper = m_name.toUpperCase();
		if ((!is_valid) ) {
			throw new SyntaxException("Illegal variable name " + m_name+". Please only use alphanumerical symbols including underscore (_)");
			
		} else if (illegals.contains(lower) || illegals.contains(upper)){
			throw new SyntaxException("Illegal variable name " + m_name+". "+ m_name.toUpperCase() +" is a reserved keyword (not case sensetive).");
		}
	}

	public String getName() {
		return m_name;
	}

	public boolean hasValue(HashMap<String, Expression> context) {
		return context.containsKey(m_name);
	}

	public double evaluate(HashMap<String, Expression> context, Collection<Expression> executeStack, Executable executer) throws ExecuteException, ValidExit {
		if (context.containsKey(m_name)) {
			return context.get(m_name).evaluate(context, executeStack, executer);
		} else {
			throw new ExecuteException("Variable " + m_name + " has no value");
		}
	}

}
