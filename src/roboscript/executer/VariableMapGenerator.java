package roboscript.executer;

import java.util.HashMap;
import java.util.Properties;

import roboscript.StringDefinitions;
import roboscript.interpreter.expressions.Expression;
import roboscript.interpreter.expressions.Number;
import roboscript.interpreter.expressions.RandomNumber;

public class VariableMapGenerator {

	private static HashMap<String, Expression> variableMap;

	public static HashMap<String, Expression> getVariableMap() {
		Properties constants = StringDefinitions.getConstants();
		if (variableMap == null) {

			variableMap = new HashMap<String, Expression>();
			// predefined constants
			variableMap.put(constants.getProperty("pi"), new Number(Math.PI));
			variableMap.put(constants.getProperty("euler"), new Number(Math.E));
			variableMap.put(constants.getProperty("true"), new Number(1));
			variableMap.put(constants.getProperty("false"), new Number(0));
			// variables.put("true", new Number(1));
			// variables.put("false", new Number(0));
			variableMap.put(constants.getProperty("maximum_number"), new Number(Double.MAX_VALUE));
			variableMap.put(constants.getProperty("minimum_number"), new Number(Double.MIN_VALUE));
			variableMap.put(constants.getProperty("random_number"), new RandomNumber());

		}
		return variableMap;

	}

}
