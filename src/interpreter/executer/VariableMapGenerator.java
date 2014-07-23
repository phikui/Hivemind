package interpreter.executer;

import interpreter.rules.Expression;
import interpreter.rules.Number;
import interpreter.rules.RandomNumber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class VariableMapGenerator {

	private static HashMap<String, Expression> variableMap;
	private static Set<String> illegalNames, illegalAssignments;

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

	public static Set<String> getIllegalVariableNames() {
		if (illegalNames == null) {
			illegalNames = new HashSet<String>();
			illegalNames.add("IF");
			illegalNames.add("ENDIF");
			illegalNames.add("THEN");
			illegalNames.add("FOR");
			illegalNames.add("LOOP");
			illegalNames.add("DO");
			illegalNames.add("ENDLOOP");
			illegalNames.add("endfor");
			illegalNames.add("RETURN");
			illegalNames.add("NOP");
			illegalNames.add("log");
			illegalNames.add("sqrt");
			illegalNames.add("abs");
			illegalNames.add("EXIT");
		}
		return illegalNames;
	}

	public static Set<String> getIllegalAssignments() {
		if (illegalAssignments == null) {
			illegalAssignments = new HashSet<String>();
			illegalAssignments.add("TRUE");
			illegalAssignments.add("true");
			illegalAssignments.add("FALSE");
			illegalAssignments.add("false");
			illegalAssignments.add("PI");
			illegalAssignments.add("E");
			illegalAssignments.add("MAX");
			illegalAssignments.add("MIN");
			illegalAssignments.add("rand");
		}
		return illegalAssignments;
	}

}
