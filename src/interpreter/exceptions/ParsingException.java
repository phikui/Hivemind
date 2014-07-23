package interpreter.exceptions;

//exceptions found during parsing of source files
public class ParsingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ParsingException(String msg){
		super(msg);
	}
	
	
	public String getMessage(){
		return super.getMessage();
	}
	
}
