package roboscript.interpreter.expressions.variables;

import java.util.Collection;
import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.SyntaxException;
import roboscript.interpreter.expressions.Expression;

public class DeleteVariable extends VariableFunction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeleteVariable(Variable value) throws SyntaxException {
		super(value);
		if (Assignment.isIllegal(value.getName())) {
			throw new SyntaxException("Can't delete " + value.getName());
		}

	}

	@Override
	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack, Executable executer)
			throws ExecuteException {
		String var = super.m_value.getName();
		if (variables.containsKey(var)) {
			variables.remove(var);
		} else {
			throw new ExecuteException(var + " does not exists");
		}
		return 1;
	}

}
