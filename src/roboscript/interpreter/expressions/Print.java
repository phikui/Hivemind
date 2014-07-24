package roboscript.interpreter.expressions;

import java.util.Collection;
import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;
import roboscript.interpreter.exceptions.ValidExit;

public class Print implements Expression{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Expression m_msg;
	
	public Print (Expression msg){
		m_msg = msg;

	}
	
	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack, Executable executer) throws ExecuteException, ValidExit {
		
		executer.getConsole().print(m_msg.evaluate(variables, executeStack, executer));
		
		return 1;
	}
	
}
