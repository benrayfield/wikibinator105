/** Ben F Rayfield offers this software opensource MIT license */
package axiomforest;

import java.nio.Buffer;

/** Immutable 4-way forest where all paths lead to leaf, and 2 of those 4 are child pointers,
and the others are 2 bits meaning TruthValue.unknown, TruthValue.yes, TruthValue.no, or TruthValue.bull,
and leaf.l==((leaf leaf)(leaf leaf)) aka identityFunc, and leaf.r==leaf,
and (leaf (leaf leaf)) means the bit 0 like in bitstrings, and ((leaf leaf) leaf) means bit 1,
and bitstrings are padded with 1000000... until the next powOf2.
<br><br>
UPDATE: 2-way forest instead of 3-way, cuz I thought of a way
to put the bloom filter inside the 6 param wikibinator function,
so any other systems can still go in the same bloomfilter.
Will still sometimes include 3 childs (instead of 4) cuz of including superposition()
which is a cache and superposition().allUnknown() is true. superposition() can be derived from binary forest.
<br><br>
TODO I'm trying to merge the 2 kinds of trinary forest nodes (1 with a bit and 1 without)
into a trinary forest node type that has TruthValue, and it be TruthValue.unknown for without the bit,
and TruthValue.yes vs TruthValue.no for with the bit,
but problems are...
-- Object.hashCode and Object.equals.
-- The one with a bit needs bits in all those reachable thru childs recursively,
and the one without a bit needs none of the childs recursively to have bits,
and I could put in 2 bits to say if they all are those 2 ways or not,
but it might make it harder to verify in the network.
-- The one with a bit (allObserve) has pointer to the one with no bits (allUnknown), so is that another child?
<br><br>
TODO rewrite the below comments cuz merging from 2 kinds of 3-way forest class...
<br><br>
This was axiomforest.observe.Λ ...
<br><br>
A 3 way forest shape where self and all reachable by child pointers
are each observed to be a specific YES/1 vs NO/0.
<br><br>
Renamed this from Observedλ ("Observed" then lowercase lambda) to Λ (capital lambda),
therefore due to case insensitivity of some systems it had to go in a different java package.
<br><br>
same as modelofallpossibleaxioms.λ but a layer above that with an observed TruthValue.yes or TruthValue.no
with each of them and those TruthValues are part of the forest shape (aka affect merkle ids).
Since any trinaryForestNodes x and y, if both are true, imply <trinaryForestLeaf x y> is true,
Or TODO expand that so that <trinaryForestLeaf x y> is true for all x and y regardless of the truth or falseness of x and y???
... This might create a problem for the selfreference fix I'm trying to do where
all possible statements in the merkle forest must be viewable by a halted lambda as idMaker
so (idMaker someLazyEvaledFormOf_aWrongStatement) -> cbt its id, and as long as the wrong statement
is marked as TruthValue.no its a true statement that its TruthValue is no so can be wrapped that way, in theory.
All nodes should have a specific yes or no attached to them the first time they're observed
since SetOfAxioms.joiner.apply(λ,λ)->λ generates the YES statement <trinaryForestLeaf x y>,
and getAnyInSet(SetOfAxioms.impliesYes,Random).apply(<trinaryForestLeaf x y>)->λ generates a YES statement,
and getAnyInSet(SetOfAxioms.impliesNo,Random).apply(<trinaryForestLeaf x y>)->λ generates a NO statement,
and any recursions in the computing of lambdas such as callquad.stack and callquad.cacheKey etc (in trinary form)
will be done in terms of those axioms (or optimizations of them that dont actually call those slow axioms,
would themselves be UnaryOperator<λ> which are axioms, so an optimization of an axiom
(such as by lazycl to lwjgl opencl/gpu optimize matrix multiply, neuralnets, and other number crunching) is an axiom
and can go in the same SetOfAxioms. An optimization of an axiom is an axiom.
<br><br>
This was axiomforest.superposition.λ ...
<br><br>
immuable trinary forest node where all paths lead to leaf. Does not have any truthvalue or color,
as those would be stored in Observedλ which is a layer above this.
<br><br>
OLD... TODO undecided if TruthValue (instead of color) will be a child (part of merkle forest) vs
used in a separate map(s) of trinaryForestNode to TruthValue,
and am undecided if will use TruthValue instead of color
(such as 32 TruthValues in wikibinator HeaderBits long header,
some or all of those being part of a color or a whole color?
but trinary forest nodes are a lower level than wikibinator opcodes).
*/
public interface λ<Node extends λ<Node>> extends Cbt{
	//FIXME extends Blob or Cbt?
	
	//FIXME isBlob func is needed if going to extend blob, cuz not everything is a cbt,
	//and not every cbt is known to be a cbt yet cuz short header only knows height up to 126.
	
	/*FIXME if this is part of axiomforest, where ((u u) u) is 1 and (u (u u)) is 0,
	then it cant put in the extra bits of "boolean flo" and "byte piz" (such as float is a 1<<5 bit flo and piz is 5).
	Axiomforest can know something is or is not a bitstring and its length, and thats it.
	Also, axiomforest supports unlimited size bitstrings, so there needs to be a way to say
	that even though the interface only has longs and is bit aligned, that its bigger.
	Axiomforest's header knows log2 of bitstring size up to about cbt size 2^124 bits,
	but it also has a way to say its either bigger or not a bitstring.
	*/
	
	/** This is core data. Is [the 3 way forest shape form of this (allUnknown)] a true statement,
	such as a certain lambda called on a certain 3 lambdas x called on y returns z?
	*/
	public TruthValue tv();
	
	/** See axiomforest.HeaderBits.
	This is a cache derived from only the 4-way forest shape where 2 of those are child pointers and 2 are TruthValue bits.
	All the boolean functions of this class are derived from this header.
	This has all the info you need other than merkle hash, so you can append 30 bytes of any secureHash of 2 childs
	and self's normed form to this, to get an id256.
	Also, after the lambdas are working in that system, any lambda can be used as an idMaker at runtime,
	and use multiple kinds of ids simultaneously andOr do migrations and realtime translations between
	systems that use different kinds of merkle ids.
	Lambdas can see axiomforest nodes (λ) cuz the system is near godel quality of self reference,
	but in theory avoids the paradoxes that godel ran into by going for slightly different design goals.
	Its suggested that all those other id types include the same 16 bit header somewhere,
	but technically they only have to be a unique id (except hash collisions) for an immutable 4-way-forest
	where 2 of those are child pointers and 2 are bits, since the 16 bit header can be derived from that.
	*/
	public short header();
	
	/** This is core data. The leaf's TruthValue is TruthValue.yes, and so is <leaf x y> for any x and y regardless of their TruthValues. */
	public boolean isLeaf();
	
	/** This is core data. The +1bit of the 3+1bit childs. TruthValue.yes or TruthValue.no.
	VmState, 1 of 3 trinary forest childs. When vmState is leaf, func and param can be anything,
	so to start a lambda call of anything on anything (even the stuff found inside vmState,
	which can see into using λ.v(), λ.l(), and λ.r() recursively, and λ.a() to check when you've reached the leaf,
	and practically there will be any size bitstrings wrapped in cbt (see occamsfuncer and wikibinator docs about cbt)),
	call node(leaf(), anythingA, anythingB) aka anythingA.p(anythingB),
	then to eval it keep looping (OLD... use TruthValue instead of Color as in SetOfAxioms
	having 2 sets of UnaryOperator<λ> one for impliesYes and one for impliesNo, and a BinaryOperator<λ> joiner)
	λ state <- anAxiom.next(ToIntFunction<λ> colors, λ state)
	(OLD...) and updating with new ToIntFunction<λ> (such as VM.setColor(λ,int)) with the Coloredλ returned.
	*
	public λ v();
	*/
	
	/** This is core data. Func, 1 of 2 binary forest childs. To start, all you have is leaf, so call that on itself, then you have 2 nodes. */
	public Node l();
	//FIXME should ((u u)(u u)) be identityFunc and the left child of u, and u is the right child of u?
	
	/** This is core data. Param, 1 of 2 binary forest childs. To start, all you have is leaf, so call that on itself, then you have 2 nodes. */
	public Node r();
	//FIXME should ((u u)(u u)) be identityFunc and the left child of u, and u is the right child of u?
	
	//Whats above are the primary storage of data. Whats below is cache of it or of it in childs recursively,
	//or functions for creating combos of it etc.
	
	/** This is a cache. x.superposition().allUnknown()==true aka only the forest shape. */
	public Node superposition();
	
	
	
	
	
	/** This is a cache or derived. True if this and all those below are TruthValue.yes. This is a subset of allObserve that only needs 256 bit id.
	If all are yes or all unknown, then you only need a 256 bit id instead of 2 of those as a 512 bit id.
	*/
	public boolean allYes();
	
	/** This is a cache or derived. Are all tv() of [this and below in the 3 way forest] yes and no and none are unknown or bull?
	This replaces the axiomforest.observe.Λ class.
	*/
	public boolean allObserve();
	
	/** This is a cache or derived. True if this and all those below are TruthValue.unknown.
	If all are yes or all unknown, then you only need a 256 bit id instead of 2 of those as a 512 bit id.
	This replaces the axiomforest.superposition.λ class.
	This is a subset of allUnknownBelow().
	*/
	public boolean allUnknown();
	
	/** This is a cache or derived. Same as v().allUnknown()&l().allUnknown()&r().allUnknown().
	This can be a key which is allUnknown (just 3 way forest shape) and a TruthValue as its val,
	like a Map.Entry<threeWayForestNodeWithoutTruthValue,TruthValue>.
	This replaces the axiomforest.observe.λtv class.
	*/
	public boolean allUnknownBelow();
	
	/** This is a cache or derived. Are any of [this and below in the 3 way forest] bull? */
	public boolean anyBull();
	
	public boolean heightIsAtLeast127();
	
	/** True if contains ((u u) u) aka 1.
	Also, (u (u u)) is 0, which is often relevant to the caller
	since if know its a cbt of size 1 and it does not contain 1 then its 0,
	and bitstrings are padded with 10000000... until next powOf2 so if know its all 0s
	then can skip that sparse range, and if know its a cbt thats all 0s then know its not a bitstring
	since it lacks that padding. This is normally used with is_cbt0_to_cbt124 and height.
	<br><br>
	x contains1 if x is ((u u) u) or if x.l.contains1 | x.r.contains1.
	<br><br>
	OLD:
	isAll0s_and_is_cbt0_to_cbt124 - way to efficiently skip sparse ranges such as bitstring padding or 1d sparse array. *
	public boolean isAll0s_and_is_cbt0_to_cbt124();
	*/
	public boolean contains1();
	
	/** is_cbt0_to_cbt124 aka bitstring up to size 2^124-1 (excluding 1000000... padding to next powOf2). */
	public boolean is_cbt0_to_cbt124();
	
	/** This is a cache or derived. Like vlr(long) except only does l() and r() so only needs 1 bit per branch, so can go twice as deep for same long.
	This will be very useful for large bitstrings (cbt).
	*/
	public Node go(long sequence);
	//FIXME should ((u u)(u u)) be identityFunc and the left child of u, and u is the right child of u?
	
	/** same as at(sequence).tv() but maybe more efficient by not creating any nodes */
	public TruthValue tv(long sequence);
	//FIXME should ((u u)(u u)) be identityFunc and the left child of u, and u is the right child of u?
	
	/** This is a cache or derived. By trinary forest shape and TruthValue at each node.
	allYes, allObserve, allUnknown, and anyBull are cache of TruthValues reachable below,
	but for security normally go in content to merkle hash.
	*/
	public boolean equals(Object o);
	
	/** This is a cache or derived, and nondeterministic but deterministic within a run of the JVM.
	Not part of the axioms or datastruct but needed by java maps etc.
	By trinary forest shape and TruthValue at each node.
	allYes, allObserve, allUnknown, and anyBull are cache of TruthValues reachable below,
	but for security normally go in content to merkle hash.
	*/
	public int hashCode();
	
	/** a constant, viewable as generic Node type.
	u/leaf/theUniversalFunction. This is a cache or derived. Returns the one leaf which all paths lead to.
	This does not depend on which instance λ its called from except they may optimize it different ways.
	Left/right childs of u() are i() and u(), so (L x (R x)) equals x forall x.
	*/
	public Node _u();
	
	/** (L x (R x)) equals x forall x, so u/leaf's left child is identityFunc and its right child is itself */
	public default Node _leftChildOfU(){
		return _i();
	}
	
	/** (L x (R x)) equals x forall x, so u/leaf's left child is identityFunc and its right child is itself */
	public default Node _rightChildOfU(){
		return _u();
	}
	
	/** a constant, viewable as generic Node type. */
	public default Node _uu(){
		return _u().p(_u());
	}
	
	/** a constant, viewable as generic Node type. */
	public default Node _zero(){
		return _u().p(_uu());
	}
	
	/** a constant, viewable as generic Node type. */
	public default Node _one(){
		return _uu().p(_u());
	}
	
	/** a constant, viewable as generic Node type. identityFunc.
	Left/right childs of u() are i() and u(), so (L x (R x)) equals x forall x.
	*/
	public default Node _i(){
		return _uu().p(_uu());
	}
	
	/** a constant, viewable as generic Node type.
	(typeval cbt_of_the_utf8_text_image_slash_jpg cbt_of_jpg_bytes) is a jpg image with contentType of "image/jpeg",
	aka (typeval "image/jpeg" ...bytesOfTheJpg...), if using a syntax that knows first param of typeval is a string,
	but other places if you want that syntax the bytes of the string go in (typeval "text/plain") which will be automatic.
	(typeval "image/jpeg" ...bytesOfTheJpg...) is YES or NO depending if its a valid jpg file bytes,
	but if you dont know then you can use UNKNOWN.
	If the first param of typeval is a cbt, then its viewed as the utf8 bytes of a contentType.
	In other places, if you want to use utf8 strings, or utf16 or whatever contentType,
	you put it in a typeval. There will be optimizations to check for prefixes like (typeval "text/plain")
	or (typeval "application/octet-stream") or (typeval "text/javascript" "(function(x,y){ return x*x+y; })") etc,
	but just cuz you can say something is a contentType does not mean you should execute it outside the sandbox.
	Axiomforest runs only by functions of (node,node)->node such as implements universal lambda logic
	or other specialized optimized kinds of logic might hook in as plugins.
	The universal lambda logic is sandboxed, so anything thats defined that way, which may be optimized
	such as using Compiled.java (in occamsfuncer or wikibinator etc), which may act anywhere in the axiom forest
	to prove things are TruthValue.yes or TruthValue.no.... Thats sandboxed,
	but if you just download out of the sandbox something like a (typeval "text/javascript" "while(true);")
	and run that outside a sandbox then you would be choosing to run while(true); which is an infinite loop
	and your browser tab would crash or worse things could happen if its outside a browser,
	so its strongly suggested to stay inside the sandbox
	where infinite loops dont happen cuz all calculations happen in small pieces of (node,node)->node
	and the only thing it ever does is set some things to TruthValue.yes or TruthValue.no and look into
	why conflicts happen, if conflicts ever happen which eventually they should not anymore
	as we could converge toward a consistent set of axioms that never generate any TruthValue.bull.
	*/
	public default Node _typeval(){
		return _u().p(_i());
	}
	
	/** a constant, viewable as generic Node type. This always maps to TruthValue.yes.
	You cant have something that always maps to TruthValue.bull since that disproves it,
	and its maybe not stable to have something that always maps to TruthValue.unknown
	since someone could claim its YES or claim its NO and nobody would be able to disprove it
	since only the opposite NO or YES can disprove that.
	*/
	public default Node _yes(){
		return _u().p(_one());
	}
	
	/** a constant, viewable as generic Node type. This always maps to TruthValue.no. See comment of _yes(). */
	public default Node _no(){
		return _u().p(_zero());
	}
	
	/** Call pair, independent of this instance, giving the TruthValue and its 2 childs. This could have been a static func.
	<br><br>
	OLD, cuz its 2-way forest now (actually 3 if include superposition() cache which can be derived).
	This is a cache or derived. This could have been a static func, but it seems better to put it here than in (ToIntFunction<λ>)VM.
	This does not depend on which instance λ its called from except they may optimize it different ways.
	All trinary forest nodes are valid, but most of them will be ignored aka you wont find,
	or would be very very hard to find, some Coloredλ returned from next whose λ is that one,
	which is cuz λ.v is "vm state" that only proceeds in certain ways,
	while the other 2 childs (λ.l and λ.r) can be anything when λ.v is leaf,
	which is the way to start a lambda call of l on r.
	*/
	public Node p(TruthValue tv, Node l, Node r);
	//public Node node(λ v, λ l, λ r);
	
	/** call pair with TruthValue.
	<br><br>
	OLD... This is a cache or derived. Lazy call l on r (params of this func) *
	public default λ node(λ l, λ r){
		return node(leaf(), l, r);
	}*/
	public default Node p(TruthValue tv, Node r){
		return p(tv, (Node)this, r);
	}
	
	/** call pair with TruthValue.yes */
	public default Node p(Node r){
		return p(TruthValue.yes, r);
	}
	
	/*FIXME if w(int...) is powOf2 number of ints, then there should be a choice to
	wrap it in cbt (without padding) vs blob (with padding), and similar for all the other primitive types.
	
	TODO λ.java implement immutable.util.Blob and similar for these wrapping functions?
			
	TODO should these copy the data or wrap it and the caller agrees not to modify it, or use wrapb vs wrapc for that?
	aka w(...) vs W(...)?
	*/
	
	/** immutable. wrap in cbt and TruthValue.yes. ((u u) u) is 1. (u (u u)) is 0. Pads with 1000000... up to next powOf2. */
	public λ w(boolean... data);
	
	/** immutable. wrap in cbt and TruthValue.yes. ((u u) u) is 1. (u (u u)) is 0. Pads with 1000000... up to next powOf2. */
	public λ w(byte... data);
	
	/** immutable. wrap in cbt and TruthValue.yes. ((u u) u) is 1. (u (u u)) is 0. Pads with 1000000... up to next powOf2. */
	public λ w(short... data);
	
	/** immutable. wrap in cbt and TruthValue.yes. ((u u) u) is 1. (u (u u)) is 0. Pads with 1000000... up to next powOf2. */
	public λ w(char... data);
	
	/** immutable. wrap in cbt and TruthValue.yes. ((u u) u) is 1. (u (u u)) is 0. Pads with 1000000... up to next powOf2. */
	public λ w(float... data);
	
	/** immutable. wrap in cbt and TruthValue.yes. ((u u) u) is 1. (u (u u)) is 0. Pads with 1000000... up to next powOf2. */
	public λ w(int... data);
	
	/** immutable. immutable. wrap in cbt and TruthValue.yes. ((u u) u) is 1. (u (u u)) is 0. Pads with 1000000... up to next powOf2. */
	public λ w(long... data);
	
	/** immutable. wrap in cbt and TruthValue.yes. ((u u) u) is 1. (u (u u)) is 0. Pads with 1000000... up to next powOf2. */
	public λ w(double... data);
	
	/** immutable. wrap in cbt and TruthValue.yes. ((u u) u) is 1. (u (u u)) is 0. Pads with 1000000... up to next powOf2. */
	public λ w(Buffer data);
	
	
	/*TODO make sure these funcs are flexible enough for lazycl, which is 1 of the places where Blob.java is,
	and it has LazyBlob which is derived only from a key val key val key val... map of string to LazyBlob
	as a vararg call lazycl(String LazyBlob String LazyBlob...). That would of course be just something
	that uses axiomforest, similar to wikibinator will use axiomforest.
	WARNING: lazycl is not sandboxed, and wikibinator etc is, so lazycl is not a good thing to put in axiomforest
	until its sandboxed, since just cuz some opencl andOr java code is in the axiomforest does not mean
	you should eval it outside the axiomforest. A BinaryOperator<λ> which implements wikibinator,
	andOr which implements lazycl, is ok to be in axiomforest only if its ok to call on all possible params
	which means it needs to be sandboxed.
	*/
	
	
	
	/** This is a cache or derived. Lazy call this on param *
	public default λ p(λ param){
		return node(this,param);
	}*/

	/** same as SetOfAxioms.node(SetOfAxioms.leaf(), this, r);
	create or find λ node where node.v().a() aka isLeaf and node.l().equals(this) and node.r().equals(r) *
	public λ p(λ r);
	*/
	
	/** TODO part of merkle childs? instead of color.
	Each axiom is 2 functions of trinary forest node x to 2 trinary forest nodes y and z,
	where if x is true then that implies y is true and z is false,
	and forall b forall c: if b and c are true then <trinaryForestLeaf b c> is true,
	and trinaryForestLeaf is true.
	*
	public TruthValue tv();
	*/

}
