package wikibinator105.spec;

import com.sun.tools.sjavac.Log.Level;

/** (ax typeandinstance) calls (typeandinstance u) to get an even height or an odd height
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
*/
public enum λColor{
	
	/** color of halted (ax typeandinstance) IFF (typeandinstance u)->u.
	Also, (ax x y)->(x (t y)), and (Ax x y)->(x (T y)), which is how to use a typed function.
	*/
	proof,
	//colorAxHaltLeaf,
	//colorAxEven,
	
	/** color of halted (ax typeandinstance) IFF (typeandinstance u) -> anything_except_u.
	Also, (ax x y)->(x (t y)), and (Ax x y)->(x (T y)), which is how to use a typed function.
	*/
	disproof,
	//colorAxHaltNonleaf,
	//colorAxOdd,
	
	/** color of nonhalting (ax typeandinstance) IFF (typeandinstance u) does not halt.
	Also, (ax x y)->(x (t y)), and (Ax x y)->(x (T y)), which is how to use a typed function.
	*/
	wordsalad,
	//colorAxNonhalt,
	
	/** color of anything whose l() is not ax, regardless of if its halted or not. */
	normal;
	//colorNormal;
	
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
	
	TODO should there be an isleaf color?
			
	TODO should there be an ishaltedandisnormal color?
			
	TODO should there be just 1 bit of color whenever something is halted (another bit for not being halted?)
		that if l() is ax then that bit means proof vs disproof,
		and if l() is not ax then that bit is always the nand of the 2 childs color bit below it?
		Problem is, at nsat level, it has to handle nonhalted nodes.
	
	isproof
	isdisproof
	ishalted
	isleaf

}
