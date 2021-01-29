/** Ben F Rayfield offers this software opensource MIT license */
package axiomforestexperimentalidtypes;

public class MarklarId{
	private MarklarId(){} //use static funcs
	
	/*TODO in some other class implement a default sha3_256 (its last 30 bytes, else 1..128 bits of literal) id,
	with a heightAndOrIsliteralvsnotisliteraletcByte
	and truthvalueAnd6BitsOfCacheByte_or1LessIfTheHeightByteTellsIsLeafVsNotLeaf,
	and implement all 0s being null, and implement the 2 childs and 1 normed form hashing,
	and implement the 128 byte datastruct made of 4 ids (parent parent.l parent.r parent.norm).
	Since it skips the pair func, this will be nearly as efficient as wikibinators planned way to store binary
	so wikibinator and other experiments etc can use this kind of id at least in early experiments,
	even though they will need custom kinds of ids for efficiency for containing custom kinds of cache,
	but they will all be completely defined inside axiomforest and their ids are just caches
	of various patterns of axiomforest shape and TruthValue per node.
	The system will support multiple simultaneous custom id types aligning to eachother at runtime,
	and this is just a prototype.
	
	TODO id differs by only 1 bit if its allYes vs allUnknown,
	and maybe similar (1-2 more bits?) for those vs allUnknown vs allUnknownBelow.
	*/
	

}
