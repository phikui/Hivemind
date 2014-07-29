package roboscript.interpreter.expressions;

import java.util.Collection;
import java.util.HashMap;

import roboscript.executer.Executable;
import roboscript.interpreter.exceptions.ExecuteException;

public class Number implements Expression {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double m_number;

	public Number(double number) {
		m_number = number;
	}
	
	public double getValue(){
		return m_number;
	}

	public double evaluate(HashMap<String, Expression> variables, Collection<Expression> executeStack, Executable executer) throws ExecuteException {
		return m_number;
	}
	public String toString(){
		return ""+m_number;
	}
}
