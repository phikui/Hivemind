package roboscript.tests;

import roboscript.executer.Executable;
import roboscript.parser.ScriptCompiler;

public class ReadExecute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Executable x = ScriptCompiler.readFromFile("./scripts/test.rb");
		x.boxed_execute();
	}

}
