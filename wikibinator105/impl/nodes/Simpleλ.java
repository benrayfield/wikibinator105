/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl.nodes;
import static wikibinator105.impl.ImportStatic.*;

import axiomforest.TruthValue;
import wikibinator105.spec.*;

public class Simpleλ extends Abstractλ{
	
	//TODO a λ which is a Blob, from lazycl's Blob interface
	
	public final λ l, r;
	
	//public final byte opByte;
	
	public final long header;
	
	/** leaf *
	public Simpleλ(){
		isLeafsByte = TODO this must be a specific thing given that leaf.l is identityFunc and leaf.r is leaf.
	}*/
	
	/** nonleaf */
	public Simpleλ(TruthValue tv, λ l, λ r){
		this.l = l;
		this.r = r;
		//this.opByte = parentOpByte(l.opByte(),r.opByte());
		this.opByte = parentOpByte(l.opByte(),r.opByte());
		
		// |1 cuz this is not a leaf, cuz this has 2 childs.
		//The 2 childs of leaf are identityFunc and leaf, but that would eval instantly and return leaf
		//and is not a valid node by itself.
		//isLeafsByte = nextIsleafsByte(l.isLeafsByte());
		//FIXME opposite order of bits? Should (isLeaf this) be the high or the low bit?		
		
		//verify isHalted. All λ must be halted, but if viewed in axiomforest,
		//TruthValue.yes means isHalted and TruthValue.no means !isHalted,
		//so it might be useful to allow nonhalted nodes to exist for that,
		//just so TruthValue.no can say they are an invalid node.
		//This could cause a problem for Op.ax since if its halted at second last param
		//that means that for 3 of its params ret func param: (func param)->ret,
		//and that usually cant be verified in bigo1 cuz it may never halt at all or halt in a million years,
		//so if allow creating that λ (a call of Op.ax on ret func param) before (func param) halts
		//then must store a bit (or maybe a TruthValue?) for has it halted yet,
		//and the design is theres no data in λ except what can be derived from the 2 childs below
		//when its created, aka caches derived from the binary forest shape of call pairs,
		//and technically if something halts or not is determined only by its forest shape
		//or at least in clean/deterministic mode (first param is not leaf).
	}


	/** false, this is not a leaf */
	public boolean a(){ return false; }

	public λ l(){ return l; }

	public λ r(){ return r; }

}
