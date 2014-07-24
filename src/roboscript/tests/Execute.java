package roboscript.tests;

import roboscript.executer.Executable;
import roboscript.parser.ScriptCompiler;

public class Execute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Executable x = ScriptCompiler.compile("./scripts/test.rs");
		x.boxed_execute();
	}

}
