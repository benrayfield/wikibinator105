package wikibinator105.impls.marklar105b.nodes;
import static wikibinator105.impls.marklar105b.ImportStatic.*;
import wikibinator105.spec.*;

public class FourLongs /*implements Blob*/{
	
	//"TODO make this be a λ. Will λ be a Blob or have a func to return a Blob? Either way, this class can be both."
	
	private final long[] data;
	
	public FourLongs(long... data){
		this.data = data;
		if(data.length != 4) throw new RuntimeException("Wrong size");
	}

	/*public long bize(){
		throw new RuntimeException("TODO");
	}*/

}
