/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;
//import java.util.EnumSet;


/** λColor was redesigned 2021-2-9 in the then-incomplete wikibinator105 universal function,
so the lambda level can only see 1 color (2 if you count leaf, but thats only 1 other node),
so color doesnt need to be stored in ids of lambdas (so can use 256 bit instead of 512 bit ids),
and the nsat level below it can also see nonhalted colors.
Also, λ.opbyte() maybe should outerjoin these colors so theres 256 times more of them?
For example, the color of (S I I pair) is evalingAndWillHalt since it evals to (pair pair),
but everything is immutable/stateless so (S I I pair) is a specific node aka (((S I) I) pair)
which can be looked into deeper using L and R all the way down to λ/u/leaf/theUniversalFunction.
(pair pair) is halted. (S I I (S I I)) is evalingAndWontHalt.
The 1 color the lambda level can see does something similar to color
using its forest shape (seeable by L and R),
in axA (aka Op.ax λ) and axB aka (Op.ax (λ λ)) or (Op.ax anything_except_λ).
Forall x, (ax λ x) and (ax (λ λ) x) cant both exist at lambda level,
cuz if 1 of them is λColor.halted then the other must be λColor.evalingAndWontHalt.
Or both may be evalingAndWontHalt which happens if (x λ) will never halt.
*/
public enum λColor{
	
	/* λ/u/leaf/theUniversalFunction. */
	coLeaf(true,true,true),
	
	/** See comments in Op enum for what is and is not halted, based only on its forest shape,
	which can be known in bigO(1) if certain caches are used or bigO(numberOfNodesReachableBelow) without those caches.
	Its not a question of will it halt or not. Its a snapshot of computing state that is or is not halted already.
	*/
	coHaltedNonleaf(false,true,true),
	
	/** See comment of λColor.halted. If its not halted, its 1 of evalingAndWillHalt or evalingAndWontHalt.
	To know which of those, you need an average of infinite time and memory at the nsat level,
	of nsat constraints defined in the comments of Op enum (TODO formalize them) on
	2 bits (isHalted and willHalt) per node, or 1 bit per color per node,
	and a node is a unique binary forest shape,
	numberOfNodesAtOrBelowHeight(anyNegativeNumber)=0,
	and numberOfNodesAtOrBelowHeight(0)=1,
	and numberOfNodesAtOrBelowHeight(n)=numberOfNodesAtOrBelowHeight(n-1)^2+1,
	and numberOfNodesAtHeight(n)=numberOfNodesAtOrBelowHeight(n)-numberOfNodesAtOrBelowHeight(n-1).
	*/
	coWillHalt(false,false,true),
	
	/** wont halt. see comment of evalingAndWillHalt */
	coWontHalt(false,false,false);
	
	public final boolean isLeaf, isHalted, willHalt;
	
	private λColor(boolean isLeaf, boolean isHalted, boolean willHalt){
		this.isLeaf = isLeaf;
		this.isHalted = isHalted;
		this.willHalt = willHalt;
	}

}



/** OLD... (ax typeandinstance) calls (typeandinstance u) to get an even height or an odd height
(UPDATE: (typeandinstance u) returns u vs anything_except_u) OLD...
<br><br>
return value (so (ax typeandinstance) is colorAxEven or colorAxOdd) or if somehow its known
that it doesnt halt (mostly its there just for abstract math completeness) then colorAxNonhalt,
or if the node's left is not ax then its color is colorNormal.
<br><br>
"Create typeOfListOfPrimeSize and typeOfListOfNonprimeSize with it, given a func isPrimeSizeList
and debugStepOver etc" will be done by (ax typeandinstance) that contain typeOfListOfPrimeSize
or typeOfListOfNonprimeSize and
(ax typeandinstance x)->(typeandinstance (t x)) which is a normal call
since (ax typeandinstance) already has its 1 of 4 colors and if it wants to reproduce that
it just calls ax on itself again and can see itself by being in an op curry.
<br><br>
To be an axiomforest.TruthValue it would need 4 bits instead of the usual 2 bits,
even though the only valid values are 0000 (unknown, in normed form,
but lambda cant see this, only NSAT layer can, and its not even valid in NSAT but
will be stored, in some cases, as an optimization to merge maps of node to color
by ORing in sparse bloomfilter, where such a map is just a binary forest node
with colors there and everywhere below),
1000, 0100, 0010, 0001 (so actually only 4 valid values,
compared to the 2 or 3 valid values in TruthValue).
*

/* Requirements for choosing number of colors in a lambda system,
are that you can uniquely identify every possible lambda only by color here, color at left child, color at right child,
and recursively from that, and left and right childs are infinitely deep since (L u) is identityFunc and (R u) is u,
and that if something can be proven in finite time theres a color which says that
since thats the only kind of proofs available thru dovetailing.
I want the fewest number of colors which can do that.
Also, the lambda level cant see nonhalting, but the nsat level below it can,
in a pigeonholing way that anything the lambdas cant derive is nonhalting.
???
TODO? "lambda level only needs 1 bit of color the predicate [l is ax and r called on u returns something other than u]"
	(contradiction between that and the text below)
	but the NOT of that since normal nodes should be same color as (ax something) where (something u)->u aka proof,
	so color would be ONLY proof or disproof, and nonhalting goes in disproof,
	and can make that welldefined at nsat level by also including a doesnthalt color (more general than wordsalad),
	and maybe also a leaf color so all you need is colors
???
*

/** ax itself is a nonaxcall. (ax anything) is an axcall. (ax anythingA anythingB) is a nonaxcall. *
nonaxof1paramcallLeaf,

/** color of anything whose l() is not ax, regardless of if its halted or not.
ax itself is a nonaxcall. (ax anything) is an axcall. (ax anythingA anythingB) is a nonaxcall.
*
nonaxof1paramcallNormal,
//colorNormal;

/** ax itself is a nonaxcall. (ax anything) is an axcall. (ax anythingA anythingB) is a nonaxcall. *
nonaxof1paramcallNohalt,

/** color of halted (ax typeandinstance) IFF (typeandinstance u)->u.
Also, (ax x y)->(x (t y)), and (Ax x y)->(x (T y)), which is how to use a typed function.
ax itself is a nonaxcall. (ax anything) is an axcall. (ax anythingA anythingB) is a nonaxcall.
*
axof1paramcallProof,
//colorAxHaltLeaf,
//colorAxEven,

/** color of halted (ax typeandinstance) IFF (typeandinstance u) -> anything_except_u.
Also, (ax x y)->(x (t y)), and (Ax x y)->(x (T y)), which is how to use a typed function.
ax itself is a nonaxcall. (ax anything) is an axcall. (ax anythingA anythingB) is a nonaxcall.
*
axof1paramcallDisproof,
//colorAxHaltNonleaf,
//colorAxOdd,

/** color of nonhalting (ax typeandinstance) IFF (typeandinstance u) does not halt.
Also, (ax x y)->(x (t y)), and (Ax x y)->(x (T y)), which is how to use a typed function.
ax itself is a nonaxcall. (ax anything) is an axcall. (ax anythingA anythingB) is a nonaxcall.
*
wordsalad,
//colorAxNonhalt,
*
axof1paramcallNohalt;

public final EnumSet<λColor> set = EnumSet.of(this);

public static final EnumSet<λColor> none = EnumSet.noneOf(λColor.class);

public static final EnumSet<λColor> all = EnumSet.allOf(λColor.class);

//TODO these colors? proof disproof nonhalting leaf normal?




/*TODO can it be done with fewer colors?
replace normal with proof (proof that its normal, but proof would mean something else if l() is ax)?
get rid of wordsalad? its already never going to happen at lambda level but can happen at nsat level.
(ax typeandinstance).color would be either proof or disproof,
	depending if (typeandinstance u)->u vs [-> anything except u, or does not halt, both of thosecould be viewed as disproof?
	but not halting would never be observed at lambda level only at nsat level in some cases]
Problem with that is disproof is a claim that a finite size proof exists,
	compared to wordsalad which is a math abstraction thats the color of anything
	thats not in any finite time proven to be the other colors.
I could have 2 ax nodes, one that halts if (typeandinstance u)->u
	and one that halts if (typeandinstance u)->anythingExceptU,
	and get rid of color completely, but then I wouldnt know how to OR bloomfilters together efficiently
	as it would have exponential instead of average constant cost per node.
	But if I could, or ax just having another param thats u vs (u u),
	such as (ax ret lazy) is halted if (lazy u)->ret (no, thats more complex
	than ret always meaning to look for u vs anything_except_u),
	then it would be back to ONLY being a binary forest shape.
Or disproof could be in the proof of 2 things that cant both be true at once, x and y,
where (x u)->u and (y u)->u cant both be true at once,
but that extremely complicates things and (ax typeandinstance) is far simpler, except that it has color.
I could view it as a 4-way-tree where 2 childs are pointers and 2 are bits that choose between the 4 colors,
and thats still a forest shape, but not all possible forest shapes are allowed in that case
since color has to be derived.
In clean functions (which cant create dirty functions in any combo of calling them on eachother),
there is exactly 1 correct color for each binary forest node,
but even then for security we still should compute it as if there could be conflicting colors at the same node
such as if data is corrupted or things imported that werent derived by correct math, to detect and merge.
I could take impractically long to verify some things, or colorWordsalad costs an average of infinite time to verify,
so not every possible function is efficient to share across untrusted borders.
..
Probably best to keep the colors as they are... proof, disproof, wordsalad, normal.
*/

/*TODO should there be an isleaf color?
		
TODO should there be an ishaltedandisnormal color?
		
TODO should there be just 1 bit of color whenever something is halted (another bit for not being halted?)
	that if l() is ax then that bit means proof vs disproof,
	and if l() is not ax then that bit is always the nand of the 2 childs color bit below it?
	Problem is, at nsat level, it has to handle nonhalted nodes.

isproof
isdisproof
ishalted
isleaf
*/