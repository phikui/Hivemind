package roboscript.executer;

public class Console  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StringBuffer console;

	
	public Console(){
		console = new StringBuffer();
	}
	
	public void print(double d){
		console.append(d);
	}
	
	public void println(double d){
		print(d);
		console.append("\n");
	}
	public void clear(){
		console = new StringBuffer();
	}
	
	/**
	 * @return
	 * @see java.lang.StringBuffer#length()
	 */
	public int length() {
		return console.length();
	}

	/**
	 * @return
	 * @see java.lang.StringBuffer#toString()
	 */
	public String toString() {
		return console.toString();
	}
	
}
