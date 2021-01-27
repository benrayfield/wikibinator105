/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;
import java.lang.ref.WeakReference;
import java.util.function.UnaryOperator;

/** TODO rewrite code and comments cuz copied alot from wikibinator104 to this wikibinator105.
immutable. binary forest node, defined ONLY by its forest shape, with no data in each node
except caches which can be derived from that shape.
A 7 param universal pattern combinator
(aka combinator, universal lambda function, and pattern calculus function)
whose first param is isDirty (leaf is clean, any nonleaf means dirty, which will be cached to
swap between clean vs dirty in bigo1), and leaf itself is clean.
The other 6 params are mostly the same as in earlier wikibinator versions
but will reorder some opcodes and put in
(axiomop ret param func) being halted if (func param)->ret
and (axiomop ret param func ignore) does infloop and that "ignore" param is the 7th of 7 params,
so unlike earlier wikibinator versions, it can infloop at the second last param,
which is similar to occamsfuncerV2's curry op which used its second last param for types that way.
As of 2021-1-21 github.com/benrayfield/occamsfuncer is version 3 but doesnt say what its version is,
and it doesnt have that infloop in curry behavior cuz its an axiom related thing thats hard to prove things about. 
*/
public interface λ extends UnaryOperator<λ>{
	
	//"wikibinatorDovetailForDebugstepoverAndDebugstepintoCuzThoseAreManyPathsToTheSameFuncparamreturnAndNeedDovetailingSo(L x (R x))EqualsXForXIs(axiomOp ... stepstuff)"
	
	//TODO copy designs from "wikibinator102Designing2021-1-21+"
	//	wikibinator104_usesTheDesignsIn(wikibinator102Designing2021-1-21+)WhichIsBadlyNamed
	
	/** isLeaf */
	public boolean a();
	
	/** func/L child, of the 2 childs in binary forest */
	public default λ l(){ return g(2); }
	
	/** param/R child, of the 2 childs in binary forest. Self is 1. left of x is x*2. right of x is x*2+1. */
	public default λ r(){ return g(3); }
	
	/** same as l().r() but may be more efficient, such as in the optimization used in Pair.java
	to store x and y but lazyEval (pair x) in (pair x y) and not trigger laziEval of that just to get x or y.
	*/
	public default λ lr(){ return g(5); }
	
	/** UPDATE: isSkip means ONLY that its normally created only when observed and is normally skiped
	when creating ids, such as Curnode, Pairnode, PairnodeWithFuncCache etc.
	<br><br> 
	Same indexs as g(int) and g(long).
	This is a cache and can change from one moment to the next, and it does not affect
	the behaviors of l() r() g(int) in terms of λ.equals(λ) and λ.hashCode() but may affect efficiency.
	<br><br>
	UPDATE: renamed this from isLazy to isLoaded then renamed to isSkip.
	Is loaded into memory andOr already created by being observed?
	x and z can be loaded while y is not loaded and x.l()==y and y.l()==z, or r() childs or any combos of l and r.
	<br><br>
	Index 1 is not lazy cuz thats self. left of x is x*2. right of x is x*2+1.
	In Curnode, Pairnode, PairnodeWithFuncCache, etc, some nodes within a few hops of here are lazy,
	while their childs are not lazy, which is strange since in most systems
	you dont have eager->lazy->eager in a forest, but here its a way to match whats in memory
	with the math abstraction of the wikibinator104 universal function
	which if it were used directly would create a few times more nodes that are rarely used
	except to get to their childs. Dedup (the instant partial kind and lazy perfect kind by globalIds)
	will work with this lazy stuff, even though its a merkle forest,
	the default kind of id will be designed so that you only need the ids of the eager parts
	a little farther below (in Curnode, PairNode, etc) to derive the id of the eager node farther above them,
	such as a single bit to say if its left child is pair and the other bits are the same,
	or actually create the ids in the middle during the hashing but garbcol them as temp calculations,
	which are 2 ways to do it. This system will support an unlimited kinds of merkle ids
	used simultaneously and matching them with eachother at runtime,
	as an idMaker is any λ which when called on a λ (which may be itself or any λ)
	returns a λ thats the id (such as cbt256).
	<br><br>
	Another common example is cbt wrapping a double[] or int[] or byte[] or float[] or java.nio.Buffer,
	which will create wrappers that share the same array but different powOf2 size ranges of it,
	and I'm unsure if that will happen from bottom up or top down
	but in occamsfuncer its top down it creates l() or r() then recurses, which is inefficient
	compared to creating the one you want directly at a binheapIndex
	but I'm unsure if going directly to it might create dedup problems.
	*/
	public default boolean isSkip(int binheapIndex){ return false; }
	
	/*
	public default boolean isLoaded_g(int binheapIndex){ return isLoaded_g((long)binheapIndex); }
	
	public boolean isLoaded_g(long binheapIndex);
	
	/** same indexs as G(int) and G(long) *
	public default boolean isLoaded_G(int binheapIndex){ return isLoaded_G((long)binheapIndex); }
	
	public boolean isLoaded_G(long binheapIndex);
	*/
	
	
	
	/** each bit in binheapIndex chooses l() vs r(). Self is 1. left of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public default λ g(int binheapIndex){ return g((long)binheapIndex); }
	
	/** each bit in binheapIndex chooses l() vs r(). Self is 1. left of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public λ g(long binheapIndex);
	
	/** each bit in binheapIndex chooses lr() vs r() which is useful for pairs and cbts.
	Self is 1. left.right of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public default λ G(int binheapIndex){ return G((long)binheapIndex); }
	
	/** each bit in binheapIndex chooses lr() vs r() which is useful for pairs and cbts.
	Self is 1. left.right of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public λ G(long binheapIndex);
	
	/** callpair of this and param, without checking if thats a valid thing to do,
	since (todo choose a design?) only halted nodes are allowed.
	*/
	public λ p(λ r);
	
	/** lambda call (eval this on) by eager eval. For lazy eval (todo choose a design?) use this.p(λ)??? */
	public λ e(λ r);
	
	/** Returns the same return as e(λ),
	if it has enough gas (compute resources such as time and memory and num of compiles),
	and the $λ.gas is how much of that maxSpend remains (was not spent).
	FIXME how should it say theres lots of gas left but it didnt spend that part cuz it knew there
	wasnt enough to finisht he calculation? Should $λ.fn be null?
	<br><br>
	Nondeterministic, normally called thru wiki opcode, which will
	have various nondeterministic opcodes inside it such as spend and (solve salt x) -> any y where (x y)->leaf,
	and will deterministicly fork 1 of 2 ways recursively a salt param (128 or 256 bits?)
	in most nondeterministic calls so it becomes very unlikely to ever repeat those calls
	since the same func and param must always give the same return or its return may not be known,
	and it needs to do those calls multiple times in the forest, temporarily (such as in .00001 second),
	cuz the S lambda (Op.s) is the main kind of controlFlow and (s x y z)->((x z)(y z)) aka (x z (y z)),
	and those (x z) and (y z) often do the same call multiple times using the <func param return> caches
	which will be in (Op.ax ret param func) or in optimizations of that in hashtables in the VM.
	*/
	public $λ e(long maxSpend, λ r);
	
	/** lambda call (eval this on) by eager eval. For lazy eval (todo choose a design?) use this.p(λ)??? 
	UnaryOperator<λ>, but I want the func names used very often to have short names like e(λ) instead of apply(λ).
	*/
	public default λ apply(λ r){
		return e(r);
	}
	
	/** cache of 8 of (isLeaf this) .. (isLeaf this.l.l.l.l.l.l.l)
	or FIXME is it (isLeaf this.r) .. (isLeaf this.l.l.l.l.l.l.l.r)
	since a currylist is for example (u u (u u) u (u u) ...params...),
	and want to know which things before ...params... are` u vs anything other than u.
	Might need to pad a 1 bit to mark a bitstring size of 0..7 bits like did in an earlier wikibinator version
	called the "op byte"??? If so, rename this to opByte.
	*
	public byte isLeafsByte();
	*/
	
	/** similar to isLeafsByte (obsoleted) except its 0..7 bits then a high 1 bit like a binheapIndex,
	for is each param of leaf, is it leaf vs any nonleaf (default is (leaf leaf)).
	*/
	public byte opByte();
	
	public default λ clean(){
		throw new RuntimeException("TODO returns a forkEdit of this where first param after leaf is leaf, unless it already is. TODO optimize this in Simpleλ by each keeping a ptr to the opposite clean/dirty.");
	}
	
	public default λ dirty(){
		throw new RuntimeException("TODO returns a forkEdit of this where first param after leaf is (leaf leaf), unless it already is something other than leaf. TODO optimize this in Simpleλ by each keeping a ptr to the opposite clean/dirty.");
	}
	
	public EvalerChain compiled();
	
	public void setCompiled(EvalerChain c);
	
	/** If this is a cbt (complete binary tree of pair of pair... of t vs f),
	either as pure interpreted lambdas or a wrapper of a byte, long, float, array, nio Buffer, etc,
	then view it as Blob (immutable).
	TODO can this be null?
	*/
	public Blob blob();
	
	/** WeakReference to this λ. TODO should this be a field in Simpleλ vs created every time this is called?
	The default implementation of this creates a new WeakReference each time.
	*/
	public default WeakReference<λ> weakref(){
		return new WeakReference(this);
	}

}