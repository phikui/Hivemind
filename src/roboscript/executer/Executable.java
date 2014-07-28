package roboscript.executer;

import java.util.HashMap;
import java.util.Stack;

import roboscript.InputOutput;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.ValidExit;
import roboscript.interpreter.expressions.Expression;
import roboscript.interpreter.expressions.Number;
public class Executable implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final long maxExecutionTime = 1 * 1000;
	private static final long maxVariableMapSize = 100;

	private Console console;
	private Expression m_head;
	private Stack<Expression> execution_stack = new Stack<Expression>();
	private String sourcefile = "NONE_DEFINED";

	public Console getConsole(){
		return console;
	}
	
	public String getSourceFile() {
		return sourcefile;
	}

	public void setSourceFile(String source) {
		sourcefile = source;
	}

	public Executable(Expression head) {
		m_head = head;
		console = new Console();
	}

	public Executable(Expression head, String source) {
		m_head = head;
		sourcefile = source;
		console = new Console();
	}

	// execute that handles exceptions
	public void boxed_execute() {
		console.clear();
		try {
			execute(VariableMapGenerator.getNewVariableMap());
		} catch (ExecuteException e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}

	}

	// execute without exception handling
	public long execute(HashMap<String, Expression> variables) throws ExecuteException {
		long startTime = System.currentTimeMillis();
		long currentExecutionTime = 0;
		execution_stack.clear();
		execution_stack.add(m_head);
		//clear outputs
		variables.put(InputOutput.getOutput().getProperty("move_direction"), new Number(0));
		variables.put(InputOutput.getOutput().getProperty("atack_strength"), new Number(0));
		while (!execution_stack.isEmpty()) {
			currentExecutionTime = System.currentTimeMillis() - startTime;
			if (currentExecutionTime > maxExecutionTime || variables.size() > maxVariableMapSize) {
				throw new ExecuteException("Exceeded maximum execution time of " + (maxExecutionTime / 1000)
						+ " seconds");
			}
			try {
				execution_stack.pop().evaluate(variables, execution_stack, this);
			} catch (ValidExit e) {
				currentExecutionTime = System.currentTimeMillis() - startTime;
				return currentExecutionTime;
			}

		}
		currentExecutionTime = System.currentTimeMillis() - startTime;
		return currentExecutionTime;
	}

}
