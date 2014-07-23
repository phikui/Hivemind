package interpreter.exceptions;

//exceptions thrown during script execution
public class ExecuteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ExecuteException(String msg){
		super("Runtime Error: "+msg);
	}
	
	
	public String getMessage(){
		return super.getMessage();
	}
}
