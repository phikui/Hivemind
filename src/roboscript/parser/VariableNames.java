package roboscript.parser;

import java.util.HashSet;
import java.util.Set;

public class VariableNames {
	private static Set<String> illegalNames, illegalAssignments;

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
