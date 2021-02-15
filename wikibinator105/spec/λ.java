/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;
//import java.lang.ref.WeakReference;
import java.util.EnumSet;
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
public interface λ<Subclass extends λ> extends UnaryOperator<Subclass>/*, Blob*/{
	
	/*Here's the plan: see λColor and superposition()
	TODO merge wikibinator105 node with axiomforest node, but how much can they merge?
	*/
	
	/*TODO implement ids like that page of text near the top of readme as of 2021-1-29
	about nsat, axiomforest header 16 bits, merging (something like?) axiomforest node with wikibinator node,
	and having 2 or 3 childs like axiomforestnode depending if all are yes or all unknown or if its mixed,
	the third child being deriveable from binary forest shape plus the truthvalue per node,
	and it being a stateless model of all possible wiki states etc in a 3^numberOfPossibleBinaryForestNodes bayes node way.
	
	OLder... TODO implement ids like in "indat qtodo.txt 2021-1-28" (incomplete design)
	
	TODO wikibinator105_axOpDoesntNeed2OpcodeesAtSecondLastParamCuzShould((fn,fn)->fn_inBigo1)DovetaillikeGoThroughCallquadlikeStuffThenBackToItselfHalted
	*/
	
	//"wikibinatorDovetailForDebugstepoverAndDebugstepintoCuzThoseAreManyPathsToTheSameFuncparamreturnAndNeedDovetailingSo(L x (R x))EqualsXForXIs(axiomOp ... stepstuff)"
	
	//TODO copy designs from "wikibinator102Designing2021-1-21+"
	//	wikibinator104_usesTheDesignsIn(wikibinator102Designing2021-1-21+)WhichIsBadlyNamed
	
	/** isLeaf */
	public boolean a();
	
	/** func/L child, of the 2 childs in binary forest */
	public default Subclass l(){ return g(2); }
	
	/** param/R child, of the 2 childs in binary forest. Self is 1. left of x is x*2. right of x is x*2+1. */
	public default Subclass r(){ return g(3); }
	
	/** You can probably ignore this, unless you're creating types of λ at the NSAT level.
	"λColor was redesigned 2021-2-9 in the then-incomplete wikibinator105 universal function,
	so the lambda level can only see 1 color (2 if you count leaf, but thats only 1 other node),
	so color doesnt need to be stored in ids of lambdas (so can use 256 bit instead of 512 bit ids),
	and the nsat level below it can also see nonhalted colors.
	Also, λ.opbyte() maybe should outerjoin these colors so theres 256 times more of them?"
	--λColor.java comment 2021-2-12. Read more there.
	*/
	public default λColor color(){
		return a() ? (isclean() ? λColor.coCleanLeaf : λColor.coDirtyLeaf) : λColor.coHaltedNonleaf;
	}
	
	/** UPDATE: superposition() will be done in user level code
	which generates an id thats intentionally designed to be the same bits for (axA x) and (axB x)
	but different bits for axA vs axB by itself, for the purpose of verifying
	theres at most 1 of (axA x) vs (axB x) forall x.
	<br><br>
	OLD...
	<br><br>
	UPDATE: This is 1 of the 2 192 bit hashes in id512, so do keep this comment here,
	but dont return it as a λ. This is only visible at the NSAT level and in some kinds of ids.
	Old...
	UPDATE: use as many bits of bloomfilter per node as λColor,
	and all those bits are 0 in the superposition/axiomforestnormcachedderivedchild,
	and its onehot for the usual nodes (not normed/unknown form) so can OR them together
	where this superposition/norm matches. Dont need to store this except for verifying purposes,
	and can generated it as needed so actually could just not store it
	as long as you dont need very very large forests that you cant fit all of in memory at once
	or have to deal with alot of p2p turing complete syncing.
	On just 1 computer, its ok to only store l() and r() and generate superposition() as needed.
	In p2p might use 512 bit ids as 2 of id256 the one with color and the one with unknown/superposition color. 
	<br><br>
	axiomforest node's third child is a cache deriveable from the other 2 childs and TruthValue,
	where its the same binary forest shape but every TruthValue is UNKNOWN,
	<br><br>
	and if all TruthValue here and reachable downward in l() and r() recursively are YES or all are UNKNOWN
	then this third child as cache doesnt need to be stored since all yes and all unknown
	differ by only 1 (or a few? todo verify) bits in id256, as in axiomforest header 16 bits,
	which will be in long λ.header().
	*
	public λ superposition();
	*/
	
	/** color of halted (ax typeandinstance) IFF (typeandinstance u)->u.
	Also, (ax x y)->(x (t y)), and (Ax x y)->(x (T y)), which is how to use a typed function.
	*
	public default boolean isProof(){
		return tv()==λColorTruthValue.proof;
	}
	
	/** color of halted (ax typeandinstance) IFF (typeandinstance u) -> anything_except_u.
	Also, (ax x y)->(x (t y)), and (Ax x y)->(x (T y)), which is how to use a typed function.
	*
	public default boolean isDisproof(){
		return tv()==λColorTruthValue.disproof;
	}
	
	/** color of nonhalting (ax typeandinstance) IFF (typeandinstance u) does not halt.
	Also, (ax x y)->(x (t y)), and (Ax x y)->(x (T y)), which is how to use a typed function.
	<br><br>
	In the wikibinator105 prototype, this could always be false since all nodes are halted
	and anything nonhalted uses java stack, but in other implementations this could be true
	during use of simulated stack similar to occamsfuncer callquads simulate the stack on the heap
	as a callquad's childs include:
	func, param, stack, cacheKey, isParentsFunc (on stack, else is parents param on stack),
	and in wikibinator105 things like callquads (to implement debugStepOver, debugStepInto, etc)
	will be created at user level (not part of VM except some user level stuff can have EvalerChain
	optimizations in VM, which the user level stuff would not know about other than it happens to eval faster)...
	those things could be built using (ax typeandinstance) whose color is λColor.proof
	(theres an op to check for proof and an op to check for disproof),
	and to use callquad-like things on eachother, such as one callquad is a func param return cache
	that another callquad is a stack state looking for what does (func param) return,
	then you use (ax typeandinstanceX typeandinstanceY) -> (typeandinstanceX (t typeandinstanceY))
	which typeandinstanceX may be designed to return another call of ax to create or find some other
	typed object, such as another callquad or just a normal lambda (thats not a call of ax).
	For example, you could have 2 typed lambdas that are listOfPrimeSize and listOfNonprimeSize
	which take a param and append it to their list and return a listOfPrimeSize or listOfNonprimeSize
	depending on the size of that next list. Its a turingCompleteTypeSystem.
	<br><br>
	TODO move some of the above text into design docs, as its more generally relevant than isWordsalad.
	*
	public default boolean isWordsalad(){
		return tv()==λColorTruthValue.wordsalad;
	}
	
	/** color of anything whose l() is not ax, regardless of if its halted or not.
	Most nodes are this color, which means they're not claiming anything (by ax)
	and are just are a binary forest shape of call pairs,
	other than they may contain such claims if you look in l() and r() deeply.
	*
	public default boolean isNormal(){
		return tv()==λColorTruthValue.normal;
	}
	
	/** unknown which of λColor.proof λColor.disproof λColor.wordsalad λColor.normal, similar to TruthValue.unknown.
	This is used in the normed form, aka λ.superposition(), thats just the binary forest shape without colors,
	so different merkle forests can be matched node for node to compare their colors and OR the colors into eachother,
	changing unknown to any of those 4 colors or to bull where theres 2 or more different colors claimed,
	which would mark the bull bit in the default kind of id256 so anything which contains bull knows instantly in id256.
	*
	public default boolean isUnknown(){
		//return tv()==λColorTruthValue.unknown;
		return colors().isEmpty();
	}
	
	/** claim of at least 2 of λColor.proof λColor.disproof λColor.wordsalad λColor.normal, similar to TruthValue.bull *
	public default boolean isBull(){
		//return tv()==λColorTruthValue.bull;
		return colors().size()>1;
	}
	
	/** has exactly 1 color? At lambda level, everything does, but at NSAT level, it can be any in powerset of colors per node. *
	public default boolean isObserve(){
		return colors().size()==1;
	}
	
	public default boolean has(λColor color){
		return colors().contains(color);
	}
	
	public default boolean is(λColor color){
		return isObserve() && color()==color;
	}*/
	
	/** this as next bit in opbyte which has 0..7 bits for the first 0..7 params of Op._root being u vs anything_except_u. */
	public default boolean opbit(){
		return a() && isclean();
	}
	
	/** There are 2 leafs: cleanLeaf and dirtyLeaf.
	dirty can create clean or dirty.
	clean can only create clean.
	If you call clean on dirty, the dirty is truncated to clean first.
	There is only 1 different ability between dirty and clean,
	that in clean, (wiki anything) evals to (S I I (S I I)) aka an infinite loop,
	and in dirty (wiki anything) evals to ret in any (ax (fpr wiki anything ret))
	that exists and has color λColor.axof1paramcallProof
	(instead of λColor.axof1paramcallDisproof which can occur
	if (ax (fpr wiki anything someOtherRet)) has color  λColor.axof1paramcallProof and ret != someOtherRet
	cuz for the same (func param) lambda call there must be at most 1 unique return value
	ever across the whole internet, or tries to find in p2p network etc or create an (ax (fpr wiki anything ret))
	for the requested (wiki anything) call, which creates increasing difficulty of sync described by SyncLevel.
	*/
	public default boolean isclean(){
		//FIXME what if this is called where op().isCertainlyClean and op().isCertainlyDirty are both false
		//such as in Op._deeplazy or Op._root. Those should never occur in this prototype,
		//ut TODO check test cases, security, etc.
		return op().isCertainlyClean;
	}
	
	/** OLD...
	<br><br>
	normally all TruthValue.yes, but there are 2 Op (typeval u u) and (typeval u (u u))
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
	*
	public TruthValue tv();
	*/
	
	
	/** see superposition() and comment in λColor.java
	FIXME should this be λColorTruthValue? Or 4 bits? Those 4 bits will be in long header().
		cuz otherwise, what does superposition().color() return? Its λColorTruthValue.unknown.
	*
	public λColor color(); //throws Unknown, Bull?
	*
	public λColorTruthValue tv();
	*/
	
	/** powerset of λColors per node. empty set is Unknown. If colors().size()>1 thats Bull. Either way thats NotObserve.
	At lambda level each node has exactly 1 color, but at NSAT level it can be any in the powerset of colors per node.
	*
	public EnumSet<λColor> colors();
	
	/** TODO override for efficiency, else it creates iterator of colors() to get first.
	At lambda level each node has exactly 1 color, but at NSAT level it can be any in the powerset of colors per node.
	*
	public default λColor color() throws NotObserve{
		EnumSet<λColor> colors = colors();
		if(colors.size() != 1){
			throw new NotObserve();
			//throw NotObserve.instance; //TODO new NotObserve() so get stacktrace but is much slower?
		}
		return colors().iterator().next();
	}*/
	
	
	/** same as l().r() but may be more efficient, such as in the optimization used in Pair.java
	to store x and y but lazyEval (pair x) in (pair x y) and not trigger laziEval of that just to get x or y.
	*/
	public default Subclass lr(){ return g(5); }
	
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
	*
	public default boolean isSkip(int binheapIndex){ return false; }
	*/
	
	
	
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
	public default Subclass g(int binheapIndex){ return g((long)binheapIndex); }
	
	/** each bit in binheapIndex chooses l() vs r(). Self is 1. left of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public Subclass g(long binheapIndex);
	
	/** 0 if not a cbt. get bits n*8..n*8+7 if this is a cbt.
	TODO throw vs represent it as 0s outside that range or partially outside.
	This is efficient for wrappers of byte[] etc.
	*/
	public byte b(int n);
	
	/** 0 if not a cbt. get bits n*16..n*16+15 if this is a cbt.
	TODO throw vs represent it as 0s outside that range or partially outside.
	This is efficient for wrappers of short[] or char[] or String etc.
	*/
	public short s(int n);
	
	public default char c(int n){
		return (char)s(n);
	}
	
	/** 0 if not a cbt. get bits n*32..n*32+31 if this is a cbt.
	TODO throw vs represent it as 0s outside that range or partially outside.
	This is efficient for wrappers of int[] or float[] etc.
	*/
	public int i(int n);
	
	public default float f(int n){
		return Float.intBitsToFloat(i(n));
	}
	
	/** 0 if not a cbt. get bits n*64..n*64+63 if this is a cbt.
	TODO throw vs represent it as 0s outside that range or partially outside.
	This is efficient for wrappers of long[] or double[] etc.
	*/
	public long j(int n);
	
	public default double d(int n){
		return Double.longBitsToDouble(j(n));
	}
	
	/** each bit in binheapIndex chooses lr() vs r() which is useful for pairs and cbts.
	Self is 1. left.right of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public default Subclass G(int binheapIndex){ return G((long)binheapIndex); }
	
	/** each bit in binheapIndex chooses lr() vs r() which is useful for pairs and cbts.
	Self is 1. left.right of x is x*2. right of x is x*2+1.
	g(...) can do the same as about half as deep in G(...) in cbt. g can do a gigabit. G an exabit.
	*/
	public Subclass G(long binheapIndex);
	
	/** callpair of this and param, without checking if thats a valid thing to do,
	since (todo choose a design?) only halted nodes are allowed.
	*/
	public Subclass p(Subclass r);
	
	/** lambda call (eval this on) by eager eval. For lazy eval (todo choose a design?) use this.p(λ)??? */
	public Subclass e(Subclass r);
	
	public default Subclass e(Object r){
		return e(w(r));
	}
	
	/** wrap. Does not depend on (this) instance, other than maybe which optimizations it might use
	varies by implementation of wikibinator105. TODO move this to a VM.java instance etc?
	*/
	public Subclass w(Object wrapMe);
	
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
	public $<Subclass> e(long maxSpend, Subclass r);
	
	public default $<Subclass> e(long maxSpend, Object r){
		return e(maxSpend,w(r));
	}
	
	/** lambda call (eval this on) by eager eval. For lazy eval (todo choose a design?) use this.p(λ)??? 
	UnaryOperator<λ>, but I want the func names used very often to have short names like e(λ) instead of apply(λ).
	TODO generalize to Object param and use w(Object) like e(Object) and e(long,Object) do?
	*/
	public default Subclass apply(Subclass r){
		return e(r);
	}
	
	/** If this.isclean, returns (t this), else returns (T this).
	(T this) would work either way but be less efficient when its param of a clean
	cuz that would truncateToClean before the clean sees it.
	*/
	public default λ tOfMe(){
		return op(isclean() ? Op.tru : Op.Tru).e(this);
	}
	
	
	public default λ op(Op o){
		throw new RuntimeException("TODO derive it from forest shape, and let subclasses use a switch to return a constant such as ImportStatic.s is Op.s");
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
	
	/** This is a bitstring of 0..7 bits, for the first 0..7 params of Op._root,
	where 1 means that param is λ/cleanLeaf, and 0 means that param is anything except λ/cleanLeaf.
	For params 8 and higher, copy opbyte from l().opByte() aka left child's opbyte,
	unless the right child is 0/_deeplazy then its 0/_deeplazy
	cuz a node is nonhalted if either child is nonhalted,
	and its _deeplazy if thats the last param such as (Op.trecurse x y z) evals to (x z (y z)).
	*/
	public byte opbyte();
	
	/** See SyncLevel for why you'd want to know this */
	public boolean containsAxOf2Params();
	
	/** what to name this func? cbt? todo rename to "cob" meaning is Clean blOB? isCleanBlob()? blob()?
	Only Op.blob (clean), not Op.Blob (dirty), cuz
	only clean blob is optimized to wrap bitstrings, such as megaflops vs teraflops in GPU,
	wrappers of float[], byte[], java.nio.Buffer, lwjgl opencl CLMem, java.lang.String, char[],
	memory mapping, RandomAccessFile, remote file systems, bittorrent or ipfs remote memory mapping, etc,
	whatever people might want to hook in as long as its constant even if its a deterministic lazyeval.
	*/
	public default boolean isblob(){
		return op()==Op.blob;
	}
	
	/** returns the first 3 SyncLevels but never the fourth cuz this is a lambda and thats for
	things lambdas cant see such as NSAT, neuralnet, or bayesnet calculation of combos of λColor.
	FIXME: lambdas cant see nonhalted nodes either, but theres still Op.deeplazy which is nonhalted.
	*/
	public default SyncLevel sl(){
		//written in order of increasing difficulty of sync...
		if(isclean()){
			return !containsAxOf2Params() ? SyncLevel.slCleanAxless : SyncLevel.slCleanWithAx;
		}else{
			return SyncLevel.slDirty;
		}
	}
	
	public default Op op(){
		return Op.atOpbyte(opbyte());
	}
	
	public default Subclass clean(){
		throw new RuntimeException("TODO returns a forkEdit of this where first param after leaf is leaf, unless it already is. TODO optimize this in Simpleλ by each keeping a ptr to the opposite clean/dirty.");
	}
	
	public default Subclass dirty(){
		throw new RuntimeException("TODO returns a forkEdit of this where first param after leaf is (leaf leaf), unless it already is something other than leaf. TODO optimize this in Simpleλ by each keeping a ptr to the opposite clean/dirty.");
	}
	
	public EvalerChain compiled();
	
	public void setCompiled(EvalerChain c);
	
	/** FIXME should λ implement Blob instead of returning one, and Blob have a function to say if it is or is not a Blob?
	<br><br>
	If this is a cbt (complete binary tree of pair of pair... of t vs f),
	either as pure interpreted lambdas or a wrapper of a byte, long, float, array, nio Buffer, etc,
	then view it as Blob (immutable).
	TODO can this be null?
	*
	public Blob blob();
	*/
	
	/** WeakReference to this λ. TODO should this be a field in Simpleλ vs created every time this is called?
	The default implementation of this creates a new WeakReference each time.
	*
	public default WeakReference<λ> weakref(){
		return new WeakReference(this);
	}*/

}