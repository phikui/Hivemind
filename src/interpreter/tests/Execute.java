package interpreter.tests;

import interpreter.parser.Executable;
import interpreter.parser.ScriptCompiler;

public class Execute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Executable x = ScriptCompiler.compile("./test2.rs");
		x.boxed_execute();
	}

}
