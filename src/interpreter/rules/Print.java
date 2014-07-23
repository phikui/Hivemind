package interpreter.rules;

import interpreter.exceptions.ExecuteException;
import interpreter.exceptions.ValidExit;

import java.util.Collection;
import java.util.HashMap;

public class Print implements Expression{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Expression m_msg;
	
	public Print (Expression msg){
		m_msg = msg;

	}
	
	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack) throws ExecuteException, ValidExit {
		
		System.out.println(m_msg.evaluate(variables, executeStack));
		
		return 1;
	}
	
}
