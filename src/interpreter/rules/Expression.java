package interpreter.rules;

import interpreter.exceptions.ExecuteException;
import interpreter.exceptions.ValidExit;

import java.util.Collection;
import java.util.HashMap;

public interface Expression extends  java.io.Serializable{
	double evaluate(HashMap<String,Expression> variables, Collection<Expression> executeStack) throws ExecuteException, ValidExit;
}
