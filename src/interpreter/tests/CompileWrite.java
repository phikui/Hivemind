package interpreter.tests;

import interpreter.parser.ScriptCompiler;

public class CompileWrite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ScriptCompiler.savetoFile("./scripts/test.rb", ScriptCompiler.compile("./scripts/test.rs"));
		System.out.println("saved");
	}

}
