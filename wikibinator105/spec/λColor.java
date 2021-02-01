package wikibinator105.spec;

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
	
	/** color of halted (ax typeandinstance) IFF (typeandinstance u)->u */
	proof,
	//colorAxHaltLeaf,
	//colorAxEven,
	
	/** color of halted (ax typeandinstance) IFF (typeandinstance u) -> anything_except_u */
	disproof,
	//colorAxHaltNonleaf,
	//colorAxOdd,
	
	/** color of nonhalting (ax typeandinstance) IFF (typeandinstance u) does not halt */
	wordsalad,
	//colorAxNonhalt,
	
	/** color of anything whose l() is not ax, regardless of if its halted or not. */
	normal;
	//colorNormal;

}
