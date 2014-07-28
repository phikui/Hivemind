package roboscript;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import roboscript.interpreter.expressions.Expression;
import roboscript.interpreter.expressions.Number;
import board.Robot;

public class InputOutput {
	private static Properties inputVariables, outputVariables;
	
	
	
	
	
	public static int getOutputMoveDirection(Robot bot){
		String variableName = getOutput().getProperty("move_direction");
		Expression value = bot.getVariables().get(variableName);
		if (value instanceof  Number){
			return  (int) ((Number) value).getValue();
		} else {
			return -1;
		}

	}
	
	public static int getAttackStrength(Robot bot){
		String variableName = getOutput().getProperty("atack_strength");
		Expression value = bot.getVariables().get(variableName);
		if (value instanceof  Number){
			return  (int) ((Number) value).getValue();
		} else {
			return -1;
		}
	}
	
	
	
	

	public static Properties getInput() {
		if (inputVariables == null) {
			inputVariables = new Properties();
			InputStream input = null;

			try {

				input = new FileInputStream("./properties/roboscript/input.properties");

				// load a properties file
				inputVariables.load(input);

			} catch (IOException ex) {
				// ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						// e.printStackTrace();
					}
				}
			}
		}

		return inputVariables;
	}

	public static Properties getOutput() {
		if (outputVariables == null) {
			outputVariables = new Properties();
			InputStream input = null;

			try {

				input = new FileInputStream("./properties/roboscript/output.properties");

				// load a properties file
				outputVariables.load(input);

			} catch (IOException ex) {
				// ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						// e.printStackTrace();
					}
				}
			}
		}
		return outputVariables;
	}

}
