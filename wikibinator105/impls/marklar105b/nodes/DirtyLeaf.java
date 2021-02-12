/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impls.marklar105b.nodes;
import static wikibinator105.impls.marklar105b.ImportStatic.*;

import wikibinator105.impls.marklar105b.fn;
import wikibinator105.impls.marklar105b.ids.MarklarId105b;
import wikibinator105.spec.*;

public class DirtyLeaf extends AbstractFn{
	
	/** leaf.l is identityFunc. leaf.r is leaf. Derive leafsByte from that.
	TODO choose design of bigEndian of littleEndian.
	*/
	public static final byte isLeafsByte = 0; //FIXME
	
	public static final fn instance = new DirtyLeaf();

	/** true, this is the leaf */
	public boolean a(){ return true; }

	public fn l(){ return I; }

	public fn r(){ return this; }

	public long marklar105bHeader(){
		return MarklarId105b.headerOfDirtyLeaf;
	}

	public fn g(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public byte b(int n){
		throw new RuntimeException("TODO");
	}

	public short s(int n){
		throw new RuntimeException("TODO");
	}

	public int i(int n){
		throw new RuntimeException("TODO");
	}

	public long j(int n){
		throw new RuntimeException("TODO");
	}

	public fn G(long binheapIndex){
		throw new RuntimeException("TODO");
	}

	public fn w(Object wrapMe){
		throw new RuntimeException("TODO");
	}

	public $λ<fn> e(long maxSpend, fn r){
		throw new RuntimeException("TODO");
	}

	public boolean containsAxOf2Params(){
		throw new RuntimeException("TODO");
	}

}
