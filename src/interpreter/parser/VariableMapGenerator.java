package interpreter.parser;

import interpreter.rules.Expression;
import interpreter.rules.Number;
import interpreter.rules.unused.RandomNumber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class VariableMapGenerator {

	public static HashMap<String, Expression> getVariableMap() {
		HashMap<String, Expression> variables = new HashMap<String, Expression>();
		// predefined constants
		variables.put("PI", new Number(Math.PI));
		variables.put("E", new Number(Math.E));
		variables.put("TRUE", new Number(1));
		variables.put("FALSE", new Number(0));
		// variables.put("true", new Number(1));
		// variables.put("false", new Number(0));
		variables.put("MAX", new Number(Double.MAX_VALUE));
		variables.put("MIN", new Number(Double.MIN_VALUE));
		variables.put("rand", new RandomNumber());
		return variables;

	}

	public static Set<String> getIllegalVariableNames() {
		Set<String> illegal = new HashSet<String>();
		illegal.add("IF");		
		illegal.add("ENDIF");
		illegal.add("THEN");		
		illegal.add("FOR");		
		illegal.add("LOOP");
		illegal.add("DO");
		illegal.add("ENDLOOP");
		illegal.add("endfor");
		illegal.add("RETURN");
		illegal.add("NOP");
		illegal.add("log");
		illegal.add("sqrt");
		illegal.add("abs");
		illegal.add("EXIT");
		return illegal;
	}

	public static Set<String> getIllegalAssignments() {
		Set<String> illegal = new HashSet<String>();
		illegal.add("TRUE");
		illegal.add("true");
		illegal.add("FALSE");
		illegal.add("false");
		illegal.add("PI");
		illegal.add("E");
		illegal.add("MAX");
		illegal.add("MIN");
		illegal.add("rand");
		return illegal;
	}

}
