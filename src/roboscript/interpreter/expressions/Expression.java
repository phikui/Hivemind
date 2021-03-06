package roboscript.interpreter.expressions;

import java.util.Collection;
import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.ValidExit;

public interface Expression extends  java.io.Serializable{
	double evaluate(HashMap<String,Expression> variables, Collection<Expression> executeStack, Executable executer) throws ExecuteException, ValidExit;
}
