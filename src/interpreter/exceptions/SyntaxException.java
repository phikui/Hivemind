package interpreter.exceptions;
//Syntax exceptions found during parsing of the source file
public class SyntaxException extends ParsingException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SyntaxException(String msg){
		super("Syntax Error: "+msg);
	}
}
