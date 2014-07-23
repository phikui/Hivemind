package interpreter.exceptions;

public class SyntaxException extends ParsingException{

	public SyntaxException(String msg){
		super("Syntax Error: "+msg);
	}
}
