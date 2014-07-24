package roboscript.executer;

import java.util.HashMap;

import roboscript.interpreter.expressions.Expression;
import roboscript.interpreter.expressions.Number;
import roboscript.interpreter.expressions.RandomNumber;

public class VariableMapGenerator {

	private static HashMap<String, Expression> variableMap;
	

	public static HashMap<String, Expression> getVariableMap() {
		if (variableMap == null) {

			variableMap = new HashMap<String, Expression>();
			// predefined constants
			variableMap.put("PI", new Number(Math.PI));
			variableMap.put("E", new Number(Math.E));
			variableMap.put("TRUE", new Number(1));
			variableMap.put("FALSE", new Number(0));
			// variables.put("true", new Number(1));
			// variables.put("false", new Number(0));
			variableMap.put("MAX", new Number(Double.MAX_VALUE));
			variableMap.put("MIN", new Number(Double.MIN_VALUE));
			variableMap.put("rand", new RandomNumber());
		}
		return variableMap;

	}

	

	

}
