package interpreter.tests;

import interpreter.parser.Executable;
import interpreter.parser.ScriptCompiler;

public class ReadExecute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Executable x = ScriptCompiler.readFromFile("test.rb");
		x.boxed_execute();
	}

}
