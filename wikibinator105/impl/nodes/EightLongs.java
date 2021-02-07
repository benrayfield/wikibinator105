package wikibinator105.impl.nodes;
import static wikibinator105.impl.ImportStatic.*;
import wikibinator105.spec.Blob;

public class EightLongs implements Blob{
	
	//"TODO make this be a λ. Will λ be a Blob or have a func to return a Blob? Either way, this class can be both."
	
	private final long[] data;
	
	public EightLongs(long... data){
		this.data = data;
		if(data.length != 8) throw new RuntimeException("Wrong size");
	}

	public long bize(){
		throw new RuntimeException("TODO");
	}

}
