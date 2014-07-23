package interpreter.tests;

import interpreter.parser.Executable;
import interpreter.parser.ScriptCompiler;

public class ExecutionSpeedTest {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Executable prog = ScriptCompiler.compile("./scripts/speed.txt");
		double java, script, start, finish;

		// program start
		start = System.currentTimeMillis();

		int max = 10000;
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max; j++) {

				double z = Math.pow(Math.random(), Math.random());
				// System.out.println(z);
			}
		}
		// program finish
		finish = System.currentTimeMillis();
		java = finish - start;

		System.out.println("Java finished");

		// program start
		start = System.currentTimeMillis();
		prog.boxed_execute();
		// program finish
		finish = System.currentTimeMillis();
		script = finish - start;

		java = java / 1000;
		script = script / 1000;

		System.out.println("Java execution time: " + java);
		System.out.println("Script execution time: " + script);
		double fraction = ((double) script) / ((double) java);
		System.out.println("Java is " + fraction + " times faster");
	}

}
