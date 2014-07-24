package roboscript.tests;

import java.io.IOException;
import java.text.ParseException;

import roboscript.executer.Executable;
import roboscript.parser.ScriptCompiler;

public class CompileWriteRead {

	/**
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException {

		Executable x = ScriptCompiler.compile("./scripts/test3.rs");
		ScriptCompiler.savetoFile("./scripts/test3.rb", x);
		x.boxed_execute();

		

		Executable x2 = ScriptCompiler.readFromFile("./scripts/test3.rb");
		x2.boxed_execute();
		System.out.println(x.getConsole().toString().equals(x2.getConsole().toString()));
		//System.out.println(x.getConsole().toString());
		//System.out.println();
		//System.out.println("___________________");
		//System.out.println();
		//System.out.println(x2.getConsole().toString());
	}

}
