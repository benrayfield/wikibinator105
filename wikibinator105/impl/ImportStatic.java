/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl;
import wikibinator105.impl.nodes.*;
import wikibinator105.spec.*;

public class ImportStatic{
	
	public static String toString(Object o){
		return o==null ? "null" : o.toString();
	}
	
	public static void lg(Object o){
		lg(toString(o));
	}
	
	public static void lg(String line){
		System.out.println(line);
	}
	
	public static Λ cp(Λ func, Λ param){
		if(func == ax || func == Ax) throw new RuntimeException("func is ax or Ax: "+func+" so it has to call its param on leaf to know its color proof, color disproof, or if it doesnt halt (which you probably wouldnt know) its color wordsalad. else its color normal.");
		return cp(λColor.normal, func, param);
	}
	
	/** call pair */
	public static Λ cp(λColor color, Λ func, Λ param){
		
		//FIXME check for Pairnode and Curnode so dedup works with their lazyEval and use of g(int) funcs,
		//but maybe do it in a more general way using an int
		//as a way to say which parts (1 2 4 8 16) are lazyEvaled (but inside them is not, skipping parts), etc,
		//though there might be deeper than that in treemap optimizations (it might have 4-7 params?) so need long?
		//
		//Use λ.isSkip(int binheapIndex) for that? Thats what its designed for, but I'm unsure if its an efficient way.
		
		//FIXME use hashtable for instant partial dedup (and ids for lazy perfect dedup)
		return new Simpleλ(color, func, param);
	}
	
	public static Λ cp(Λ... list){
		Λ x = list[0];
		for(int i=1; i<list.length; i++) x = cp(x,list[i]);
		return x;
	}
	
	public static void x(String throwMessage){
		throw new RuntimeException(throwMessage);
	}
	
	public static Λ bootOp(Λ... params){
		Λ x = u;
		for(Λ param : params) x = cp(x,param);
		return x;
	}
	
	/** the isLeafsByte() for parent whose 2 childs are funcsIsleafsByte and anything. */
	public static byte nextIsleafsByte(byte funcsIsleafsByte){
		return (byte)((funcsIsleafsByte<<1)|1);		
	}
	
	/*FIXME??? u and (u u) must both be clean, so clean ops can be defined with those in their prefix.
	but FIXME I want there to be only 1 dirtyWiki (first param of u is the dirty one, then rest of prefix leads to wiki).
	Should (u (u u)) be the dirty prefix?
	That would make (u (u u) u u u u u) be dirtyWiki, but (u "hello" u u u u u) is also a dirtyWiki,
	if we go by the params being leaf vs nonleaf.
	Or could define wiki as infinitelooping if its first param (of 7) is not 1 of u or uu.
	Or could define that (u u) is the prefix of dirty things but u and (u u) are both clean.*/
	
	
	/*OLD... Op.deepLazy(0)
	Op.wiki(1)
	Op.isLeaf(1)
	Op.l(1)
	Op.r(1)
	Op.t(2)
	Op.fi(2)
	Op.curry(2)
	Op.cleanCall(2)
	Op.s(3)
	Op.pair(3)
	Op.typeval(3)
	Op.ax(4)*/
	
	
	/*
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
	*/
	

	
	/** the universal function aka the leaf which all paths in binary forest of call pairs lead to */
	public static final Λ u = Leaf.instance;
	//There is no dirty u.
	
	public static final Λ uu = u.p(u);
	//There is no dirty (u u).
	
	//There is no dirty u or dirty (u u), but anything else has 2 forms: clean and dirty,
	//except FIXME what if theres a clean thing or dirty thing in first param of u other than u vs uu.
	//I dont plan to put anything but u or uu in first param of u, but it will happen in dovetailing
	//and in just randomly calling funcs on eachother, so the cleanness vs dirtyness of it must be defined.
	//Probably this will simply be (isClean x) means is its first param u or is it equal to u.
	
	
	
	//lowercase op name is clean, like wiki. Capital op name is dirty, like Wiki.
	//u is clean.
	//(u u) is clean.
	//(u (u u)) is dirty.
	//(u anythingExceptUAndUU) is dirty.
	
	//FIXME reverse order and swap u with uu, in the following?
	
	
	//Op.deeplazy is not a λ in this prototype cuz this prototype only has halted λs and uses java stack for the nonhalted parts,
	//but you can build something similar to occamsfuncer callquads inside calls of Op.ax
	//(dovetailing may be needed as an abstraction, but compute it procedurally forward efficiently using Evaler/Compiled).
	
	public static final Λ wiki      = bootOp(u,	u,	u,	u,	u,	u);
	public static final Λ Wiki      = wiki.dirty();
	
	public static final Λ isleaf    = bootOp(u,	u,	u,	u,	u,	uu);
	public static final Λ Isleaf    = isleaf.dirty();
	
	public static final Λ l         = bootOp(u,	u,	u,	u,	uu,	u);
	public static final Λ L         = l.dirty();
	
	public static final Λ r         = bootOp(u,	u,	u,	u,	uu,	uu);
	public static final Λ R         = r.dirty();
	
	public static final Λ t         = bootOp(u,	u,	u,	uu,	u);
	public static final Λ T         = t.dirty();
	
	public static final Λ fi        = bootOp(u,	u,	u,	uu,	uu);
	public static final Λ Fi        = fi.dirty();
	
	public static final Λ curry     = bootOp(u,	u,	uu,	u,	u);
	public static final Λ Curry     = curry.dirty();
	
	public static final Λ cleancall = bootOp(u,	u,	uu,	u,	uu);
	//even though its dirty, it still converts params to clean and returns a clean
	public static final Λ Cleancall = cleancall.dirty();
	
	public static final Λ s         = bootOp(u,	u,	uu,	uu);
	public static final Λ S         = s.dirty();
	
	public static final Λ pair      = bootOp(u,	uu,	u,	u);
	public static final Λ Pair      = pair.dirty();
	
	public static final Λ typeval   = bootOp(u,	uu,	u,	uu);
	public static final Λ Typeval   = typeval.dirty();
	
	public static final Λ ax        = bootOp(u,	uu,	uu);
	public static final Λ Ax        = ax.dirty();
	
	
	
	/** identityFunc */
	public static final Λ i       = cp(fi,u);
	public static final Λ I       = i.dirty();
	
	/** like cleancall except it just has 1 param, the thing to clean,
	which forkEdits param recursively to have u as all first params of u. There are no nonnormed clean forms.
	<br><br>
	FIXME update comments other places which say nonleaf is clean. Instead, leaf is clean, and nonleaf is dirty,
	in th at first param, such as (u u u u u u u) is cleanwiki and (u (u u) u u u u u) is a dirtywiki,
	and of course all forms of the wiki opcode use the same wiki.
	*/
	public static final Λ cleanone = cleancall.p(i);
	//even though its dirty, it still converts param to clean and returns it
	public static final Λ Cleanone = cleanone.dirty();
	
	/** counterpart of cleanone and Cleanone. Returns a dirty form of its param,
	by forkEditing it recursively for all first params (of leaf) to be a nonleaf,
	and if they are already not a leaf then leaves them as they are else uses (leaf leaf) aka (u u).
	*/
	public static final Λ Dirtyonepassive = null; //FIXME not null
	
	/** returns the normed dirty form, where all first params (of leaf) are (leaf leaf) aka (u u).
	Similar to Dirtyonepassive except which nonleaf in first param.
	*/
	public static final Λ Dirtyonenorm = null; //FIXME not null
	
	public static final Λ callParamOnItself = cp(cp(s,i),i);
	
	/** Called from Op.curry to get funcbody to call on [...linkedlist...] containing that funcbody and its params.
	The last (displayed on left, as its the [] kind of linkedlist, not <> kind) is comment (which is λ by default).
	From PairnodeWithFuncCache QUOTE
	TODO create an Evaler instance that looks for secondLast cache,
	and ImportStatic.secondLast.setCompiled(Evaler),
	and maybe create secondLast func in λ so dont have to cast that, and cuz its a calculation implied by Op.curry,
	BUT also consider that all ops must finish in bigo1 other than if they're implemented in java stack etc
	then they must create a lambda and call it instead of recursing on java stack OTHER THAN
	they are allowed to recurse into Evaler.eval(long,λ,λ) (aka λ.e(long,λ) but anything else has to be bigo1,
	so based on that, λ should NOT have a secondLast func but could still have a secondLastCacheElseNull func
	that doesnt recurse and just returns it if it knows it already.
	UNQUOTE.
	...
	Also do that for Secondlast aka secondlast.dirty() so call setCompiled on that too but
	a different func that does the dirty form of it.
	*/
	public static final Λ secondlast = null;
	public static final Λ Secondlast = null; //FIXME should be secondlast.dirty(), and read comment in that about setCompiled here
	
	public static Λ t(Λ x){ return cp(t,x); }
	public static Λ T(Λ x){ return cp(T,x); }
	
	/** TODO also create lazig, which is a λfunc.λparam.λignore.(func param).
	FIXME handle clean vs dirty.
	*/
	public static Λ lazy(Λ func, Λ param){
		return cp(s,t(func),t(param));
	}
	
	public static Λ Lazy(Λ func, Λ param){
		return cp(S,T(func),T(param));
	}
	
	public static final λ funcThatInfloopsForAllPossibleParams = lazy(callParamOnItself,callParamOnItself);
	
	public static final λ FuncThatInfloopsForAllPossibleParams = funcThatInfloopsForAllPossibleParams.dirty();
	
	/*
	public static λ normedOp_slow(byte isLeafsByte){
		int i = isLeafsByte&0xff;
		if(i == 0) x("Must have high 1 bit in binheapIndex: "+i);
		λ x = u;
		while(i != 1){
			boolean z = i&
		}
	}
	
	public static λ normedOp_slow(boolean... ){
	*
	
	public static λ normedOp_slow(boolean isClean, Op op){
		λ ret = isClean ? uu : u;
		int sum = 0;
		for(Op x : Op.values()){
			if(x == op) break;
			sum += 1<<x.params;
		}
	}*/
	
	public static byte parentOpByte(byte leftOpByte, byte rightOpByte){
		throw new RuntimeException("TODO");
	}
	
	/** returns long[]{header,bize} */
	public static long[] parentHeaderAndBize(λColor color, long leftHeader, long leftBize, long rightHeader, long rightBize){
		throw new RuntimeException("TODO");
	}

}
