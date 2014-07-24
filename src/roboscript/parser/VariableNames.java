package roboscript.parser;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import roboscript.StringDefinitions;

public class VariableNames {
	private static Set<String> illegalNames, illegalAssignments;

	public static Set<String> getIllegalVariableNames() {
		Properties keywords = StringDefinitions.getKeywords();
		if (illegalNames == null) {
			illegalNames = new HashSet<String>();
			Enumeration<?> e = keywords.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = keywords.getProperty(key);
				illegalNames.add(value);
			}


		}
		return illegalNames;
	}

	public static Set<String> getIllegalAssignments() {
		Properties constants = StringDefinitions.getConstants();
		if (illegalAssignments == null) {
			illegalAssignments = new HashSet<String>();
			Enumeration<?> e = constants.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = constants.getProperty(key);
				illegalAssignments.add(value);
			}
		
		}
		return illegalAssignments;
	}
}
