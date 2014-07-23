package interpreter.tests;

import interpreter.parser.ScriptCompiler;

public class CompileWrite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ScriptCompiler.savetoFile("test.rb", ScriptCompiler.compile("./test.rs"));
		System.out.println("saved");
	}

}
