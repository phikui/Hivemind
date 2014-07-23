package interpreter.tests;

import interpreter.parser.Executable;
import interpreter.parser.ScriptCompiler;

import java.io.IOException;
import java.text.ParseException;

public class CompileWriteRead {

	/**
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException {

		Executable x = ScriptCompiler.compile("./scripts/test.rs");
		x.boxed_execute();

		ScriptCompiler.savetoFile("./scripts/test.rb", x);

		Executable x2 = ScriptCompiler.readFromFile("./scripts/test.rb");
		x2.boxed_execute();

	}

}
