/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;
import java.lang.ref.WeakReference;
import java.util.function.UnaryOperator;
import axiomforest.TruthValue;

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
	
	TODO merge wikibinator105 node with axiomforest node, but how much can they merge?
	
	TODO implement ids like that page of text near the top of readme as of 2021-1-29
	about nsat, axiomforest header 16 bits, merging (something like?) axiomforest node with wikibinator node,
	and having 2 or 3 childs like axiomforestnode depending if all are yes or all unknown or if its mixed,
	the third child being deriveable from binary forest shape plus the truthvalue per node,
	and it being a stateless model of all possible wiki states etc in a 3^numberOfPossibleBinaryForestNodes bayes node way.
	
	OLder... TODO implement ids like in "indat qtodo.txt 2021-1-28" (incomplete design)
	
	TODO wikibinator105_axOpDoesntNeed2OpcodeesAtSecondLastParamCuzShould((fn,fn)->fn_inBigo1)DovetaillikeGoThroughCallquadlikeStuffThenBackToItselfHalted
	
	//"wikibinatorDovetailForDebugstepoverAndDebugstepintoCuzThoseAreManyPathsToTheSameFuncparamreturnAndNeedDovetailingSo(L x (R x))EqualsXForXIs(axiomOp ... stepstuff)"
	
	//TODO copy designs from "wikibinator102Designing2021-1-21+"
	//	wikibinator104_usesTheDesignsIn(wikibinator102Designing2021-1-21+)WhichIsBadlyNamed
	
	/** isLeaf */
	public boolean a();
	
	/** func/L child, of the 2 childs in binary forest */
	public default λ l(){ return g(2); }
	
	/** param/R child, of the 2 childs in binary forest. Self is 1. left of x is x*2. right of x is x*2+1. */
	public default λ r(){ return g(3); }
	
	/** axiomforest node's third child is a cache deriveable from the other 2 childs and TruthValue,
	and if all TruthValue here and reachable downward in l() and r() recursively are YES or all are UNKNOWN
	then this third child as cache doesnt need to be stored since all yes and all unknown
	differ by only 1 (or a few? todo verify) bits in id256, as in axiomforest header 16 bits,
	which will be in long λ.header().
	*/
	public default λ superposition(){ return g(3); }
	
	/** normally all TruthValue.yes, but there are 2 Op (typeval u u) and (typeval u (u u))
	which get the 2 bits (as t vs f) in the TruthValue of any node,
	and the only condition a TruthValue should ever become no is when the same (func param) call
	returns 2 different things, so at most 1 of those can be yes (or neither) at a time,
	and as axiomforest models the space of all possible turing complete wiki functions,
	without modelling its internal call pair structure but instead just its param/return pairs
	and recursively outward in all possible halting combos of thing called on thing returns thing,
	all TruthValue.no (and therefore also TruthValue.bull if someone isnt following the protocol
	or if multiple wiki states are trying to be used together which is allowed in the protocol
	but the protocol is to converge toward a shared wiki state)...
	then TruthValue is yes everywhere except where Op.ax finds multiple returns for the same call
	and echoing outward from that.
	<br><br>
	Wikibinator105 doesnt have ability to write TruthValue, only to read it,
	and the writing of it (by forkEdit) is at a lower level than wikibinator105
	where a set of NSAT constraints model the space of all possible turing completness
	including all possible behaviors of all possible wiki functions
	and will be an optimization for p2p network sync (at gaming-low-lag and number-crunching)
	to only compute the universal function defined in Op.java more efficiently
	and to fit that puzzle together using a "TURING COMPLETE ENERGY FUNCTION" (see README.md)
	which converges toward (at least approx) consistent (func param)->returnVal
	when given a bunch of sparse claims that some func called on some other func returns another func
	(all of those are λs), and some of those may have different wiki states than others,
	but the wiki state is not stored directly, instead is in the puzzle defined by
	Op.ax calls which each say (ax ret func param) or (ax λ type instance x)->(param x)
	or technically could be (ax anyRet func param x). Ax is a turing complete type system,
	compared to Op.typeval which is a loose type system for things like "image/jpeg" and "text/plain".
	*/
	public TruthValue tv();
	
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