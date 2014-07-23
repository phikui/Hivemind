package interpreter.exceptions;

public class ExecuteException extends Exception{

	public ExecuteException(String msg){
		super("Runtime Error: "+msg);
	}
	
	
	public String getMessage(){
		return super.getMessage();
	}
}
