package wikibinator105.spec.err;

public class λerr extends RuntimeException{
	
	public λerr(){}
	
	public λerr(String message){
		super(message);
	}
	
	public λerr(String message, Throwable cause){
		super(message, cause);
	}
	
	/*public final λ target;
	
	public λerr(λ target, String message, Throwable cause){
		super(message, cause);
	}
	
	public λerr(λ target, String message, Throwable cause){
		super(message, cause);
	}*/

}
