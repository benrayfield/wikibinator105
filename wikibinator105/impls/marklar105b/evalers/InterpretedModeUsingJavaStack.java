/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impls.marklar105b.evalers;
import static wikibinator105.impls.marklar105b.ImportStatic.*;
import wikibinator105.impls.marklar105b.*;
import wikibinator105.spec.*;

public class InterpretedModeUsingJavaStack implements Evaler<fn>{
	
	public static final InterpretedModeUsingJavaStack instance = new InterpretedModeUsingJavaStack();
	
	public static final EvalerChain chain = new SimpleEvalerChain(instance);
	
	public $<fn> Eval(long gas, fn func, fn z){
		if(gas <= 0) throw new RuntimeException("Since everything costs at least 1 gas, including calling this to check the gas, caller should not have called this. gas="+gas);
		//if(gas == 0) throw new RuntimeException("FIXME redesign maxSpend make this easier to pay, like in occamsfuncer you just call $(number) but I like it being a param instead of stateful");
		gas--;
		
		//FIXME pay 1 instantly, avoid infinite loops etc.
		
		fn ret = AxfprCache.getOrNull(func, z);
		if(ret != null) return new $<fn>(gas,ret);
		
		if(!z.isclean() && func.isclean()){
			//FIXME pay gas, as this forkEdits it recursively down to u/U
			//but TODO has average of constant cost, or instant constant cost, if create both clean/dirty forms in parallel
			//and they have pointer to eachother. TODO choose a design.
			z = z.clean();
		}
		
		//FIXME use maxSpend recursively
		
		//FIXME at 6 curries past _root, instead of 7+ as usual, of (ax u x) verify (x u)->u,
		//and verify (ax anythingY_except_u x) -> anythingZ except u.
		
		//TODO optimize by only calling these as needed in the switch,
		//or create func to get the fourth, third, and second last params.
		fn funcL = func.l();
		fn w = funcL.l().r();
		fn x = funcL.r();
		fn y = func.r();
		
		switch(func.op()){
		case _deeplazy:
			throw new RuntimeException("Shouldnt be any "+Op._deeplazy+" in this prototype as it uses java stack instead");
		case _root:
			throw new RuntimeException("Shouldnt be any "+Op._root+" in this prototype as thats mostly for mounting it into other systems such as axiomforest");
		case _chooser: case _Chooser:
			throw new RuntimeException("The switch only happens at 7+ params, so this shouldnt happen");
		case wiki:
			ret = null; //optimization of calling (S I I (S I I)) then running out of gas. Dont cache ret if run out of gas.
			//return funcThatInfloopsForAllPossibleParams.e(u); //(S I I (S I I))
		break;
		case Wiki:
			$<fn> WikiReturned = Wiki(gas, z);
			ret = WikiReturned.fn; //null if not enough gas, such as if dont (yet?) know what Wiki does for that param
			gas = WikiReturned.gas;
		case isLeaf:
			//TODO optimize. Dont need "z = z.clean();" in this case.
			ret = z.a() ? t : f;
		break;
		case IsLeaf:
			ret = z.a() ? T : F;
		break;
		case getFunc: case GetFunc:
			//TODO optimize. If "z = z.clean();" happened, should have instead only done it for z.l().clean()
			ret = z.l();
		break;
		case getParam: case GetParam:
			//TODO optimize. If "z = z.clean();" happened, should have instead only done it for z.r().clean()
			ret = z.r();
		break;
		case tru: case Tru:
			ret = y;
		break;
		case fal: case Fal:
			ret = z;
		break;
		case pair: case Pair:
			ret = null; //FIXME
		break;
		case trecurse: case Trecurse:
			/*
			//TODO xz and yz in parallel recursively (can become many threads), in some cases,
			//but not when it takes longer due to thread switch lag.
			fn xz = x.e(z);
			fn yz = y.e(z);
			ret = xz.e(yz);
			*/
			$<fn> forkReturned = Fork(gas, x, y, z); //this is singleThreaded, unless a subclass multithreads it in some cases
			ret = forkReturned.fn;
			gas = forkReturned.gas;
		break;
		case blob: case Blob:
			//(Op.blob u) is bit1, and (Op.blob (u u)) is bit0, which are both cbts of 1 bit,
			//and cbt of n bits called on anything returns a cbt of 2*n bits,
			//and if its param is not a cbt of same size then it returns (itself itself),m
			//else it returns (itself param).
			
			//TODO blob (clean bitstring) is up to a million times faster than Blob (dirty bitstring)
			//such as when optimized in lazycl (which uses lwjgl opencl),
			//but should Blob still compute the same thing just that 100-1000000 times slower?
			//Or should it eval to (S I I (S I I)) aka an infinite loop, or something like that?
			//Dont want to break code that converts everything in a param to dirty form,
			//though such code could be designed to check if its a blob and not convert that part.
			
			ret = cp(func, are2CbtsOfSameSize(func,z)?z:func);
		break;
		case isclean:
			//func.isclean(), cuz func.op()==Op.isclean instead of Op.Isclean, so return clean tru or clean fal.
			ret = z.isclean() ? t : f;
		break;
		case Isclean:
			//!func.isclean(), cuz func.op()==Op.Isclean instead of Op.isclean, so return dirty Tru or dirty Fal.
			ret = z.isclean() ? T : F;
		break;
		case curryOrInfcurOrTypeval: case CurryOrInfcurOrTypeval:
			ret = null; //FIXME
		break;
		case ax:
			//(ax u y) is halted IFF (y u)->u,
			//and (ax anything_except_u y) is halted IFF (y u) -> anything_except_u,
			//but that was an earlier call as this is (ax something y z).
			fn prefix = x.a() ? t : f;
			ret = y.e(prefix.e(z));
		break;
		case Ax:
			//(Ax u y) is halted IFF (y u)->u,
			//and (Ax anything_except_u y) is halted IFF (y u) -> anything_except_u,
			//but that was an earlier call as this is (Ax something y z).
			prefix = x.a() ? T : F;
			ret = y.e(prefix.e(z));
		break;
		case fpr: case Fpr:
			//TODO generate a lambda here which calls (when its called on leaf), but find more efficient way...
			//IF.e(equals.e(x.e(y)).e(z)).e(u.tOfMe()).e(u)
			//aka call x on y, and if what that returns equals z then return u else (u u), such as Op.ax uses.
			//That last u generates (u u) when IF calls it on u, similar to the IF generates u when it gets (t u).
			//For now, just do it on java stack, but even when using java stack,
			//but I want to use java stack less and less over future versions,
			//maybe eventually getting it like occamsfuncer callquads that completely do their own stack on heap.
			//.equals will be derived as combos of U/universalFunc called on itself, then an Evaler instance
			//created to call an idMaker on things recursively and compare by ids (which are cached in Axfpr),
			//after checking == and other fast checks to detect trivial nonequality or trivial equality first,
			//but != doesnt prove nonequality in DedupLevel.dlWeakCbtButStrongAboveIt,
			//and != does prove nonequality if all cbt up to id size are deduped by cbt content
			//and everything above them is deduped by hashtable using System.identityHashCode.
			//That will do perfect dedup without most things needing an id,
			//but its too slow to use cuz cant wrap large arrays such as int[5632453]
			//without creating all their internal nodes as binary forest of 256 or 128 bits each
			//and upward each internal node going into AxfprCache as (L x) called on (R x) -> x,
			//aka (L x (R x)) equals x forall x. The eager creation of all those internal nodes,
			//instead of lazy of it, would make it impractical to copy between GPU memory and CPU memory,
			//screen pixels, acyclicFlow (double,double)->double ops in evolved musical instruments in double[], etc.
			//So .equals is (TODO) lazy of generating id and comparing them
			//(inside derived lambda which computes equals, and that being an Evaled.java optimizations of it), TODO.
			ret = x.e(y).equals(z) ? u : uu;
		}
		if(gas == 0) ret = null;
		if(ret != null) AxfprCache.put(func, z, ret);
		//If ret == null, this means didnt have enough gas to do the requested calculation,
		//and giving back whatever amount of gas was not used.
		return new $(gas,ret);
	}
	
	public boolean are2CbtsOfSameSize(λ x, λ y){
		throw new RuntimeException("TODO");
	}

	public $<fn> Wiki(long maxSpend, fn param){
		return WikiState.bestKnownApproximationOfSparseSubsetOfWiki.apply(maxSpend,param);
	}

}