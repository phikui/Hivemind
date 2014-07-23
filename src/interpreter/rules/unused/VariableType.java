package interpreter.rules.unused;

public class VariableType {
	private int int_value = 0;
	private String string_value = "";
	private double double_value = 0.0;
	private boolean boolean_value;
	private Type type;

	private enum Type {
		INTEGER, STRING, DOUBLE,BOOLEAN;
	}
	
	

	public VariableType(int value) {
		int_value = value;
		type = Type.INTEGER;
	}

	public VariableType(String value) {
		string_value = value;
		type = Type.STRING;
	}

	public VariableType(double value) {
		double_value = value;
		type = Type.DOUBLE;
	}

	public String get_string_value() {
		if (type == Type.STRING) {
			return string_value;
		} else if (type == Type.INTEGER) {
			return "" + int_value;
		} else {
			return "" + double_value;
		}
	}
	
	public int get_int_value() {
		if (type == Type.STRING) {
			return 0;
		} else if (type == Type.INTEGER) {
			return int_value;
		} else {
			return (int) double_value;
		}
	}
	
	public double get_double_value() {
		if (type == Type.STRING) {
			return 0;
		} else if (type == Type.INTEGER) {
			return int_value;
		} else {
			return  double_value;
		}
	}

}
