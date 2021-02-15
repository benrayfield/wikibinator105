/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impls.marklar105b.nodes;
import static wikibinator105.impls.marklar105b.ImportStatic.*;
import static wikibinator105.impls.marklar105b.ids.MarklarId105b.*;
import wikibinator105.impls.marklar105b.ids.MarklarId105b;
import wikibinator105.impls.marklar105b.*;
import wikibinator105.spec.*;

public class Simpleλ extends AbstractFn{
	
	//TODO a λ which is a Blob, from lazycl's Blob interface
	
	public final fn l, r;
	
	public final long header;
	
	public final byte lizif;
	
	/** nonleaf */
	public Simpleλ(fn l, fn r){
		this.l = l;
		this.r = r;
		
		long lh = l.marklar105bHeader();
		long rh = r.marklar105bHeader();
		long myHeader = 0;
		byte myLizif = 0;
		boolean isLiteralCbt256 = false;
		if(isLiteralCbt128(lh)){ //dont need to check rh cuz a cbt called on anything is a cbt twice as big
			long lFirstLong = l.j(0);
			if(lFirstLong>>56 != noncbt256firstbyte){ //this (whose childs are l and r) is a literal cbt256
				myHeader = lFirstLong;
				isLiteralCbt256 = true;
			}
		}
		"FIXME set myLizif where?"
		if(!isLiteralCbt256) myHeader = parentHeaderIfLeftIsNotACbt128(lh, l.lizif(), rh, r.lizif());;
		header = myHeader;
		lizif = myLizif;
				
				
			
		
		/* 
		//public Simpleλ(λColor color, fn l, fn r){
		//this.opByte = parentOpByte(l.opByte(),r.opByte());
		//this.opByte = parentOpByte(l.opByte(),r.opByte());
		
		return MarklarId105b.isLiteralCbt256(marklar105bHeader());
		
		public default boolean isLiteralCbt128(){
			return MarklarId105b.heigh marklar105bHeader());
		}
		
		this.header = MarklarId105b.parentHeader(l.marklar105bHeader(), l.lizif(), r.marklar105bHeader(), r.lizif());
		*/
		
		//long[] headerAndBize = parentHeaderAndBize(color, l.header(), l.bize(), r.header(), r.bize()); //TODO optimize by not creating long[2]?
		//this.header = headerAndBize[0];
		//this.bize = headerAndBize[1];
		
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

	public fn l(){ return l; }

	public fn r(){ return r; }

	public long header(){
		return header;
	}

	public fn g(long binheapIndex){
		throw new RuntimeException("TODO");
	}


	public fn G(long binheapIndex){
		throw new RuntimeException("TODO");
	}


	public fn e(fn r){
		throw new RuntimeException("TODO");
	}


	public $ e(long maxSpend, fn r){
		throw new RuntimeException("TODO");
	}


	public fn apply(fn t){
		throw new RuntimeException("TODO");
	}


	public long marklar105bHeader(){
		throw new RuntimeException("TODO");
	}


	public boolean isclean(){
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


	public fn w(Object wrapMe){
		throw new RuntimeException("TODO");
	}


	public byte lizif(){
		return lizif;
	}

}
