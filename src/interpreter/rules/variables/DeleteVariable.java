package interpreter.rules.variables;

import interpreter.exceptions.ExecuteException;
import interpreter.exceptions.SyntaxException;
import interpreter.rules.Expression;

import java.util.Collection;
import java.util.HashMap;

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
	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack)
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
