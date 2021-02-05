/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;

/** I'm undecided if will use this vs just 4 bits for the 4 colors, plus other parts of long header of course. */
public enum λColorTruthValue{
	
	//FIXME redesigning what the λColors are, so need to change them here too 
	
	/** λColor.proof */
	proof,
	
	/** λColor.disproof */
	disproof,
	
	/** λColor.wordsalad */
	wordsalad,
	
	/** λColor.normal */
	normal,
	
	/** unknown which of λColor.proof λColor.disproof λColor.wordsalad λColor.normal, similar to TruthValue.unknown.
	This is used in the normed form, aka λ.superposition(), thats just the binary forest shape without colors,
	so different merkle forests can be matched node for node to compare their colors and OR the colors into eachother,
	changing unknown to any of those 4 colors or to bull where theres 2 or more different colors claimed,
	which would mark the bull bit in the default kind of id256 so anything which contains bull knows instantly in id256.
	*/
	unknown,
	
	/** claim of at least 2 of λColor.proof λColor.disproof λColor.wordsalad λColor.normal, similar to TruthValue.bull */
	bull;

}
