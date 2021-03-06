package roboscript.executer;

import java.util.HashMap;
import java.util.Properties;

import roboscript.StringDefinitions;
import roboscript.interpreter.expressions.Expression;
import roboscript.interpreter.expressions.Number;
import roboscript.interpreter.expressions.RandomDirection;
import roboscript.interpreter.expressions.RandomNumber;

public class VariableMapGenerator {

	public static HashMap<String, Expression> getNewVariableMap() {
		Properties constants = StringDefinitions.getConstants();
		HashMap<String, Expression> variableMap;

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

		// Directions
		/*
		 * 1 2 3
		 * 
		 * 4 0 5
		 * 
		 * 6 7 8
		 */

		variableMap.put(constants.getProperty("stay"), new Number(0));
		variableMap.put(constants.getProperty("northwest"), new Number(1));
		variableMap.put(constants.getProperty("north"), new Number(2));
		variableMap.put(constants.getProperty("northeast"), new Number(3));
		variableMap.put(constants.getProperty("west"), new Number(4));
		variableMap.put(constants.getProperty("east"), new Number(5));
		variableMap.put(constants.getProperty("southwest"), new Number(6));
		variableMap.put(constants.getProperty("south"), new Number(7));
		variableMap.put(constants.getProperty("southeast"), new Number(8));
		variableMap.put(constants.getProperty("random_direction"), new RandomDirection());
		

		
		return variableMap;

	}

}
