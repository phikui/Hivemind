package interpreter.tests;

import interpreter.executer.Executable;
import interpreter.parser.ScriptCompiler;

public class Execute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Executable x = ScriptCompiler.compile("./scripts/test2.rs");
		x.boxed_execute();
	}

}
