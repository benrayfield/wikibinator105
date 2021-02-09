/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl.nodes;
import static wikibinator105.impl.ImportStatic.*;

import java.util.EnumSet;

import wikibinator105.impl.*;
import wikibinator105.spec.*;

public class Leaf extends AbstractFn{
	
	/** leaf.l is identityFunc. leaf.r is leaf. Derive leafsByte from that.
	TODO choose design of bigEndian of littleEndian.
	*/
	public static final byte isLeafsByte = 0; //FIXME
	
	public static final fn instance = new Leaf();

	/** true, this is the leaf */
	public boolean a(){ return true; }

	public fn l(){ return i; }

	public fn r(){ return this; }

	public long header(){
		throw new RuntimeException("TODO");
	}

	public long bize(){
		throw new RuntimeException("TODO");
	}

	public λColorTruthValue tv(){
		throw new RuntimeException("TODO");
	}

	public fn g(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public fn G(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public $λ<fn> e(long maxSpend, fn r){
		throw new RuntimeException("TODO");
	}

	public byte opByte(){
		throw new RuntimeException("TODO");
	}

	public Blob blob(){
		throw new RuntimeException("TODO");
	}

	public EnumSet<λColor> colors(){
		throw new RuntimeException("TODO");
	}

}
