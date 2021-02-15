/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impls.marklar105b.nodes;
import static wikibinator105.impls.marklar105b.ImportStatic.*;
import wikibinator105.impls.marklar105b.fn;
import wikibinator105.impls.marklar105b.ids.MarklarId105b;
import wikibinator105.spec.*;

public class CleanLeaf extends AbstractFn{
	
	public static final fn instance = new CleanLeaf();

	/** true, this is the leaf */
	public boolean a(){ return true; }

	public fn l(){ return i; }

	public fn r(){ return this; }

	public long marklar105bHeader(){
		return MarklarId105b.headerOfCleanLeaf;
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

	public $<fn> e(long maxSpend, fn r){
		throw new RuntimeException("TODO");
	}

	public byte opbyte(){
		throw new RuntimeException("TODO");
	}

	public boolean containsAxOf2Params(){
		throw new RuntimeException("TODO");
	}

	/** 0 cuz not a cbt so its bize is 0 */
	public byte lizif(){
		return 0;
	}

}
