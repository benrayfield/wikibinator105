package wikibinator105.spec;

public enum DedupLevel{
	
	/** impractical but the math is (todo few details to work out 2021-2-5+) well defined,
	that numberOfBinaryForestShapesAtHeight(n)=numberOfBinaryForestShapesAtHeight(n-1)^2+1
	and numberOfBinaryForestShapesAtHeight(0)=1, plus you have to count color at each node,
	so you can for exponential cost have a unique integer for each function. 
	This is here for math proofs and not expected to be used for computing lambdas
	except maybe very small combos of them as experiments.
	*/
	dlPerfect,
	
	/** Things will be as efficient as RandomAccessMachine on average,
	times a very large constant for tree hashing such as using 192 bits of the output ofsha3_224.
	This is used when functions cross untrusted borders, for saving on harddrive, and for treemap keys.
	dlWeakCbtButStrongAboveIt computes this, using any chosen func(s) as idMaker(s) (idMaker x)->idOfX,
	at runtime, and can use multiple id types (idMaker funcs) at once matching them to eachother
	but you only need 1 strongly secure idMaker. You may want other kinds to balance between security
	and efficiency or to match ids to an external system that also uses some kind of merkle ids,
	andOr to strengthen security by having multiple idMakers ready and some ids already made by them,
	in case some of them are cracked
	(which has nothing to do with this system, only the math of the hash algorithms itself)
	then it would have multiple ids per function already in the p2p network
	ready to use without missing a step, without so much as hearing any sound distortion
	in a live musical instrument depending on such hash ids while playing it,
	while sound between speakers and microphone is echoing through the air in a feedback loop,
	the system could adapt so fast you wouldnt feel it,
	but probably people wont want to use extra compute resources on multiple id types
	at once except in functions where important things depend on them,
	so it can be varied sparsely tuning between security efficiency redundancy low-lag etc per object.
	*/
	dlPerfectUnlessHashCollision,
	
	"TODO what about 'A lambda could generate a hash thats the same for (axA x) and (axB x),\n"
	+ "	for any x, but different for axA vs axB, or something like that"
	
	/** Things will be as efficient as RandomAccessMachine on average,
	times a large constant for hashtable cache misses. The normal DedupLevel that
	wraps byte[], float[], java.nio.Buffer, opencl memory, etc, in cbt without deduping it
	and compares those by ==, and uses a hashtable of (func param)->return
	to guarantee perfect dedup other than the same cbt content may exist in multiple cbt objects
	that differ by ==. For example, "hello" == "hello" may be true or false,
	but if x == "hello" and y == "world" then (pair x y) == (s (t (pair x)) (t y) u). 
	*/
	dlWeakCbtButStrongAboveIt,
	
	/** things will cost exponential. */ 
	dlWeakOrNever;
	

}
