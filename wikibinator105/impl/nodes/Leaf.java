/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl.nodes;
import static wikibinator105.impl.ImportStatic.*;
import wikibinator105.impl.*;
import wikibinator105.spec.*;

public class Leaf extends AbstractΛ{
	
	/** leaf.l is identityFunc. leaf.r is leaf. Derive leafsByte from that.
	TODO choose design of bigEndian of littleEndian.
	*/
	public static final byte isLeafsByte = 0; //FIXME
	
	public static final Λ instance = new Leaf();

	/** true, this is the leaf */
	public boolean a(){ return true; }

	public Λ l(){ return i; }

	public Λ r(){ return this; }

	public long header(){
		throw new RuntimeException("TODO");
	}

	public long bize(){
		throw new RuntimeException("TODO");
	}

	public λColorTruthValue tv(){
		throw new RuntimeException("TODO");
	}

	public Λ g(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public Λ G(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public $λ<Λ> e(long maxSpend, Λ r){
		throw new RuntimeException("TODO");
	}

	public byte opByte(){
		throw new RuntimeException("TODO");
	}

	public Blob blob(){
		throw new RuntimeException("TODO");
	}

}
