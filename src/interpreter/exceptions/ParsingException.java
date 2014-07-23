package interpreter.exceptions;

public class ParsingException extends Exception{

	public ParsingException(String msg){
		super(msg);
	}
	
	
	public String getMessage(){
		return super.getMessage();
	}
	
}
