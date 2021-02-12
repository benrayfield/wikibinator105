/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impls.marklar105b.evalers;
import static wikibinator105.impls.marklar105b.ImportStatic.*;

import wikibinator105.impls.marklar105b.fn;
import wikibinator105.spec.*;

public class InterpretedMode implements Evaler<fn>{
	
	public static final InterpretedMode instance = new InterpretedMode();
	
	public static final EvalerChain chain = new SimpleEvalerChain(instance);
	
	public $λ<fn> eval(long gas, fn func, fn z){
		if(gas <= 0) throw new RuntimeException("FIXME redesign maxSpend make this easier to pay, like in occamsfuncer you just call $(number) but I like it being a param instead of stateful");
		gas--;
		
		//FIXME pay 1 instantly, avoid infinite loops etc.
		
		fn ret = AxfprCache.getOrNull(func, z);
		if(ret != null) return new $λ(gas,ret);
		
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
		case wiki: case Wiki:
			ret = null; //FIXME
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
			//TODO xz and yz in parallel recursively (can become many threads), in some cases,
			//but not when it takes longer due to thread switch lag.
			fn xz = x.e(z);
			fn yz = y.e(z);
			ret = xz.e(yz);
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
			ret = x.e(y).equals(z) ? u : uu;
		}
		AxfprCache.put(func, z, ret);
		return new $λ(gas,ret);
	}
	
	public boolean are2CbtsOfSameSize(λ x, λ y){
		throw new RuntimeException("TODO");
	}

}








/*
public $λ eval(long maxSpend, λ func, λ param){
	
	long gas = maxSpend;
	//FIXME subtract from gas, but before each subtract check if it would be nonnegative.
	//TODO should gas be static long (or static double) like I used in occamsfuncer?
	int isLeafsByte = nextIsleafsByte(func.isLeafsByte())&0xff;
	boolean isDirty = TODO; //FIXME get from isLeafsByte
	//FIXME just create callpair/cp if theres not enuf curries,
	//considering that Op.ax evals at 6 and 7 curries, and everything else only at 7.
	λ ret;
	Op op = null; //FIXME get this from isLeafsByte
	
	//TODO handle Op.ax with 5 or 6 curries here (Todo choose 5 or 6 that eval should do something)...
	//	Get its 3 params (ignoring its 4th, hasnt got it yet) and ret = call one on the other then call
	//a derived equals function on what that returns to compare it to the return value in ax's param
	//and if they dont equal then infloop else its halted and TODO cache that.
	//Or... consider the existence of the 6 param form to be that cache, and call it at 5 (of 7) params...
	//That seems the better way... todo that.
	
	OLD...
	ukΩuuuw? (λ   λ     λ     λ     λ     λ     λ)    ? //wiki
	ukΩuuua? (λ   λ     λ     λ     λ     λ   (λ λ))  ? //isleaf
	ukΩuu∩l? (λ   λ     λ     λ     λ   (λ λ)   λ)    ? //getfunc/l
	ukΩuu∩r? (λ   λ     λ     λ     λ   (λ λ) (λ λ))  ? //getparam/r
	ukΩu∩t?? (λ   λ     λ     λ   (λ λ)   λ)    ?     ? //tru/t
	ukΩu∩f?? (λ   λ     λ     λ   (λ λ) (λ λ))  ?     ? //fal/f/fi
	ukΩ∩s??? (λ   λ     λ   (λ λ)   λ  )  ?     ?     ? //trecurse/s
	ukΩ∩p??? (λ   λ     λ   (λ λ) (λ λ))  ?     ?     ? //pair/p
	XXXXXXXX (λ   λ   (λ λ)   λ     λ     λ     λ)    ? //1
	XXXXXXXX (λ   λ   (λ λ)   λ     λ     λ   (λ λ))  ? //0
	XXXXXXXX (λ   λ   (λ λ)   λ     λ   (λ λ))  ?     ? //infcur_if_next_param_is_leaf_else_curry_if_its_unarynum
	XXXXXXXX (λ   λ   (λ λ)   λ   (λ λ))  ?     ?     ? //typeval_and_the_2_get_truthval_ops
	ukƱx???? (λ   λ   (λ λ) (λ λ))  ?     ?     ?     ? //ax/x/axiomOp
	
	
	//TODO make sure none of this logic uses loops or recursion outside of calling eval recursively,
	//such as in Op.ax to check if the observed return value equals the correct return val,
	//use the derived equals function.
	//This logic needs to be ported to a lambda so it can be used in emulation, dovetailing, etc.
	
	switch(op){ //7 curries, normal eval
	case wiki:
		throw new RuntimeException("TODO (wiki x) == ret in (ax ret wiki x) if thats known and isDirty(isLeafsByte). (wiki x) infloops if isClean(isLeafsByte).");
	case isleaf:
		ret = param.a()?t:f; //FIXME cost 1 gas, do at top of this eval func.
	case l:
		ret = param.l(); //FIXME cost 1 gas, do at top of this eval func.
	case r:
		ret = param.r(); //FIXME cost 1 gas, do at top of this eval func.
	case t:
		ret = func.r(); //FIXME cost 1 gas, do at top of this eval func.
	case fi:
		ret = param; //FIXME cost 1 gas, do at top of this eval func.
	case curry:
		λ x = func.l().r();
		λ y = func.r();
		TODO choose order of linkedlist used in currying, and if want to reverse there vs reverse it in syntax,
		as in [a b c d] being [[[a b] c] d] vs [a [b [c d]]].
	case cleancall:
		TODO
	case s:
		λ x = func.l().r();
		λ y = func.r();
		ret = (x param (y param)); //FIXME use gas, the e(long,λ) func.
	case pair: case typeval:
		λ x = func.l().r();
		λ y = func.r();
		ret = (param x y); //FIXME use gas, the e(long,λ) func.
	case ax:
		infloop;
	}
	TODO

}*/