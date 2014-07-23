package interpreter.executer;

import interpreter.exceptions.ExecuteException;
import interpreter.exceptions.ValidExit;
import interpreter.rules.Expression;

import java.util.HashMap;
import java.util.Stack;

public class Executable implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final long maxExecutionTime = 10 * 1000;
	private static final long maxVariableMapSize = 100;

	private Expression m_head;
	private Stack<Expression> execution_stack = new Stack<Expression>();
	private String sourcefile = "NONE_DEFINED";

	public String getSourceFile() {
		return sourcefile;
	}

	public void setSourceFile(String source) {
		sourcefile = source;
	}

	public Executable(Expression head) {
		m_head = head;
	}

	public Executable(Expression head, String source) {
		m_head = head;
		sourcefile = source;
	}

	// execute that handles exceptions
	public void boxed_execute() {
		try {
			execute(VariableMapGenerator.getVariableMap());
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

		while (!execution_stack.isEmpty()) {
			currentExecutionTime = System.currentTimeMillis() - startTime;
			if (currentExecutionTime > maxExecutionTime || variables.size() > maxVariableMapSize) {
				throw new ExecuteException("Exceeded maximum execution time of " + (maxExecutionTime / 1000)
						+ " seconds");
			}
			try {
				execution_stack.pop().evaluate(variables, execution_stack);
			} catch (ValidExit e) {
				return currentExecutionTime;
			}

		}
		return currentExecutionTime;
	}

}
