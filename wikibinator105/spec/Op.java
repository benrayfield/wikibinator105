/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;


public enum Op{
	
	
	
	/** opByte0. The other 255 opBytes are for each of the first 7 params being λ vs anything_except_λ,
	then pad a high 1 bit after however many params it is 0..7 if its at most 7 params,
	and if its more than 7 params it copies opByte from its l().
	When or if an opByte0 finishes evaling, it becomes 1 of those 255 opBytes.
	opByte is part of the long (64 bits) header, which is part of id256 and that long is used in memory
	even before id256 is created.
	<br><br>
	(TODO maybe used or not used? probably not in wikibinator105, but TODO think about it)
	not used in the wikibinator105 prototype since everything is either halted or evaling on java stack,
	but other implementations might use this for call pairs that are not halted.
	TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	Anything thats not halted is deepLazy,
	other than if its l() and r() are halted but it is not cuz r() is its
	7th param (FIXME its vararg, though !isStrange ops all take 7 params, other than deepLazy
	which isnt really an op or unsure how to categorize it),
	that uses the same op as r()'s op.
	This is a low level of lazy that does not exist in the wikibinator104 prototype
	but may in other implementations of wikibinator105.
	<br><br>
	Wikibinator105 is (TODO after work out details) a specific pure math function not a specific system.
	<br><br>
	Wikibinator105 can (in theory) be mounted into (axiomforestLeaf "wikibinator105"),
	using axiomforest's kind of strings for that,
	where wikibinator104Leaf is (axiomforestLeaf "wikibinator104") and wikibinator104
	cant see anything outside of (axiomforestLeaf "wikibinator104"),
	and calling it on anything outside that is axiomforest.TruthValue.no,
	so for it to see other systems it would have to emulate axiomforest such as using
	(pair cbt16_axiomforestHeader (pair axiomforestLeftChild axiomforestRightChild)) for example.
	*/
	deepLazy_isNotCleanOrDirtyCuzIsNotHalted(0b00000000,true,false,0),

	
	/** all nondeterminism goes here. TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	if strict/clean this is (S I I (S I I)), else/dirty is loose
	and (wiki x) == ret in (ax (fpr wiki x ret)) being color colorAxEven to say thats true
	or being colorAxOdd to say it returns something other than ret but it does return/halt,
	else being color colorAxNonhalt to mean (wiki x) does not halt,
	the same as ax is used for funcs other than wiki,
	except wiki needs ax due to wiki being defined only by examples that fit together in NSAT puzzle,
	where the NSAT level is below the lambda level and may be computed in ways other than NSAT but thats the math spec.
	<br><br>
	You can effectively have unlimited number of wikis by using a linkedlist whose deepest part is the name of the wiki,
	and parts after that you use whatever you want, for example,
	and to fork one of those, use axioms (dovetailing derived in params of Op.ax, for example)
	to imply that if something exists in some parts of wiki then it exists (or translating it / migration / etc)
	in certain other parts, though convergence on a set of axioms is intentionally left nondeterministic
	so various groups of people and systems or combos of them or the whole world together,
	however they like to organize things, have not created a lambda contradiction (TruthValue.bull)
	by selecting from the space of all finite but otherwise turing complete ways to organize the wiki,
	where TruthValue.bull occurs when the same lambda call (func param) has more than 1 unique return value
	such as (wiki "hello")->"world" and (wiki "hello")->42 cant both exist,
	but (wiki ["testxyz435" "hello"])->"world" and (wiki ["monkeyspace" "hello"])->42 can exist together,
	or (wiki "testxyz435.hello") would also work, however you want to do it, just dont create lambda contradictions.
	Wiki is a pure math function and can call itself recursively, emulate itself, etc,
	such as (wiki "lazyCallWikiOnItself") -> {,wiki ,wiki} aka (s (t wiki) (t wiki))
	so if that exists (check Op.ax for any returnVal func param)
	then (wiki "lazyCallWikiOnItself" u) -> (wiki wiki),
	and there might be (wiki wiki)->"this is the return value of calling wiki on itself"
	so in that case (wiki "lazyCallWikiOnItself" u) -> "this is the return value of calling wiki on itself",
	or you could also use (s i i x) -> (x x) forall x, so (s i i wiki) -> (wiki wiki) -> whatever that returns.
	You could also make a function locally named testxyz435
	where (testxyz435 x) -> (wiki ["testxyz435" x]). Functions are useful for many possible things
	including convenient shortcuts or building virtual worlds so big and detailed
	you might mistake it for a remote interaction with the real world. You can put anything
	in the wiki as long as it doesnt create any lambda contradictions with the other contents
	and if you can get other people and computers to go along with it in them
	trying to not create lambda contradictions anywhere in the wiki considering its whole contents.
	The system will automatically look for such contradictions and automatically fork and merge
	in whatever ways people want it to as defined by functions they can create
	to help them understand other functions and create new useful functions.
	Everything can be automated and the automatic processes
	(built by people and AIs while using other automated processes)
	can choose between people andOr AIs at the time and context for whatever each does best,
	and if that doesnt work out, try other combos automatically.
	Everything gets connected to everything if thats what those parts want at the time and context.
	*/
	wiki(0b01000000,false,false,1),
	Wiki(0b01000001,false,false,1),
	
	
	/** FIXME (λ λ) is cleanLeaf and (λ (λ λ)) is dirtyLeaf, and l and r and isleaf and isclean ops must use it that way,
	such as (L (λ (λ λ))) is IdentityFunc) and (L (λ λ)) is identityFunc)
	and (R (λ (λ λ))) is (λ (λ λ)) and (R (λ λ)) is (λ λ), cuz forall x (L x (R x)) equals x,
	and this is to fix the problem of needing 2 leafs (cleanLeaf and dirtyLeaf) cuz clean cant derive dirty
	but if theres just 1 leaf (λ) then it could. 
	<br><br>
	a/isLeaf. TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	(isLeaf x) is t or f depending if x is the leaf which all paths in the binary forest of call pairs lead to
	aka the wikibinator104 universal function itself.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	isLeaf(0b01001000,false,false,1), //todo rename to isleaf and Isleaf (the l is lowercase), to match isclean and Isclean
	IsLeaf(0b01001001,false,false,1),
	
	/** FIXME (λ λ) is cleanLeaf and (λ (λ λ)) is dirtyLeaf, and l and r and isleaf and isclean ops must use it that way,
	such as (L (λ (λ λ))) is IdentityFunc) and (L (λ λ)) is identityFunc)
	and (R (λ (λ λ))) is (λ (λ λ)) and (R (λ λ)) is (λ λ), cuz forall x (L x (R x)) equals x,
	and this is to fix the problem of needing 2 leafs (cleanLeaf and dirtyLeaf) cuz clean cant derive dirty
	but if theres just 1 leaf (λ) then it could.
	<br><br>
	l/getFunc. TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	(l x) is left child of x in the binary forest of call pairs.
	Not the same as lispCar since pair is the church-pair lambda.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	getFunc(0b01010000,false,false,1),
	GetFunc(0b01010001,false,false,1),
	
	/** FIXME (λ λ) is cleanLeaf and (λ (λ λ)) is dirtyLeaf, and l and r and isleaf and isclean ops must use it that way,
	such as (L (λ (λ λ))) is IdentityFunc) and (L (λ λ)) is identityFunc)
	and (R (λ (λ λ))) is (λ (λ λ)) and (R (λ λ)) is (λ λ), cuz forall x (L x (R x)) equals x,
	and this is to fix the problem of needing 2 leafs (cleanLeaf and dirtyLeaf) cuz clean cant derive dirty
	but if theres just 1 leaf (λ) then it could. 
	<br><br>
	r/getParam. TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	(r x) is right child of x in the binary forest of call pairs.
	Not the same as lispCdr since pair is the church-pair lambda.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	getParam(0b01011000,false,false,1),
	GetParam(0b01011001,false,false,1),

	
	/** If its param is λ, its 1, vs if its param is anything except λ, its 0,
	and either way it takes an infinite number of params (like infcur) aka is halted after each next curry,
	and at and after its first param (to choose 0 vs 1) its a cbt,
	and a cbt called on anything is a cbt twice as big and 1 higher,
	and if a cbt is called on a cbt of a different height it returns (itself itself) instead,
	else just creates a halted call pair of itself and the param.
	*/
	bit(0b01100000,false,true,1),
	Bit(0b01100001,false,true,1),
	
	
	isclean(0b01110000,false,false,1),
	/** the 2 leafs are (λ (λ λ)) dirtyLeaf and (λ λ) cleanleaf, which in L, R, Isleaf, and color appear as leafs
	and theres no way to get λ which is not a function but just a data structure optimization
	or vestigial design from when there was 1 leaf instead of 2
	TODO should there just be 2 symbols for it like λ and Λ?
	<br><br>
	isclean (vs Isclean) is useless since it always returns t since it is a clean func so truncates its param to clean before using it.
	Use Isclean instead. A clean func doesnt need to check for clean cuz its params are always clean after automatic truncation to clean.
	isclean always returns t (clean true). Isclean always returns T or F (dirty true or dirty false).
	<br><br>
	To convert a func to clean, just call i (clean identityFunc, aka (f λ)) on it, for automatic truncation.
	To convert a func to dirty, that would have to be derived, using L and R recursively to rebuild a func
	matching the param, starting from cleanLeaf instead of dirtyLeaf,
	which todo put an Evaler optimization in the VM to check for that specific Asdirty func
	and just flip the isclean bit recursively, so near as fast as if there was an op to do it,
	similar to hardware optimized double multiply math will be near as fast in an Evaler optimization
	despite it being seamlessly useable as lambdas all the way down to cleanLeaf
	(cbts/blobs should be clean for blob optimizations, else they run in interpreted mode very slowly).
	*/
	Isclean(0b01110001,false,false,1),
	
	
	
	/** (curryOrInfcurOrTypeval λ) is infcur ((1) and (2)).
	(1) (curryOrInfcurOrTypeval λ type value) aka (infcur type value),
		where type != λ, is how to use it as typeval, though its still infcur as it can take infinity curries.
	(2) (curryOrInfcurOrTypeval λ λ ...) aka (infcur λ ...), is how to use it as not typeval.
	(3) (curryOrInfcurOrTypeval anythingExceptλ) is curry, and anythingExceptλ is a unarynum like (t (t (t λ))) aka ,,,λ.
	opByte can tell the difference between those 3 things since opByte knows λ vs anythingExceptλ for first 7 curries.
	Its important for that unarynum to be at curry 6 so every func's number of currys is known before
	opByte starts being copied from self.l().
	self.l().l().l()...l().opByte is always the same opByte at curry number 7 after leaf, if self is curry number 7 or higher.
	I didnt write these as 3 separate ops, cuz number of curries depends on its first param
	in a more detailed way than λ vs anythingExceptλ, cuz the anythingExceptλ is looked at in more detail
	to view it as a unarynum, therefore the first param of curryOrInfcurOrTypeval cant be used ONLY as an opbit
	to choose between these 3 op-like-things inside it.
	Using infcur as typeval vs anything_except_typeval could be an opbit, but since it comes after the unarynum
	(which is 0u aka λ to mean infinity curries, else is for example 3u aka (t (t (t λ))) to mean 3 curries
	(TODO start counting after funcbody or after the op or where?))... since it comes after the unarynum
	it cant be part of an op prefix (of n opbits), so these 3 things are joined into 1 op called curryOrInfcurOrTypeval,
	but in ImportStatic.java they will be 3 separate things,
	and ImportStatic.curry will be this curryOrInfcurOrTypeval and only acts as curry if you give it
	a unarynum thats at least 1u.
	*/
	curryOrInfcurOrTypeval(0/*"TODO"*/,false,true,2),
	CurryOrInfcurOrTypeval(0/*"TODO"*/,false,true,2),
	
	//cleancall will be derived at user level, not an op.
	

	
	/*UPDATE: getting rid of color and instead will have 2 (or maybe 3) kinds of Op.ax:
	(No, dont do 3 kinds of ax at the lambda level,
	but the third kind would be for nonhalters at nsat level below the lambda level).
	The first param of ax is λ to choose axA, and is anything except λ to choose axB,
	each of these 2 params...
	<br><br>
	(axA x) and (axB x) cant both exist.
	(axA x) is halted if (x u)->u.
	(axB x) is halted if (x u) -> anything except u.
	//Maybe, (axC x) is halted if (x u) does not halt, but I'm unsure if should have an axC.
	(axA x y) -> (x (T y))
	(axB x y) -> (x (F y))
	How would that be detected? A lambda could generate a hash thats the same for (axA x) and (axB x),
	for any x, but different for axA vs axB, or something like that.
	(details to work out on whats a normed form)
	But what to do if theres BULL in it such as (axA (pair s pair)) and (axB (pair s pair)) exist
	somewhere reachable from the same node?
	..
	The 2 ax ops need more room. make room by getting rid of isColorDisproof and 1 other 1-param op.
	*/
	ax(0/*TODO*/,false,true,3),
	
	/** (fpr func param ret λ) -> λ if (func param)->ret,
	and (fpr func param ret λ) -> (λ λ) if (func param) -> something other than ret (but it still returns),
	and (fpr func param ret λ) does not halt if (func param) does not halt.
	<br><br>
	By itself its a question, but with ax its a proof of the answer, the proof being in the color of (ax anything).
	Used with ax to store (func param)->ret cache or statements that it does not return that,
	or in VM theres normally a hashtable to store that more efficiently but could reflect it as that. 
	Normally used as (ax (fpr func param ret)) (which costs 4 callpairs to store),
	which is colorAxEven if (func param)->ret,
	and is colorAxOdd if (func param) -> something other than ret but does halt,
	and does not halt if (func param) does not halt
	(so colorAxNonhalt is only used at NSAT level, not at lambda level above it).
	Anything whose l() is not axType is colorNormal. Those are the 4 colors.
	axType can of course be used with other funcs as it only cares about
	does its param return something of even height, of odd height, or not return.
	<br><br>
	TODO what does (fpr x y z anything_except_λ) return?
	(ret λ)? (lazig x y)? {,x ,y}? (ax (fpr x y z))? (S I I (S I I))? z?
	*/
	fpr(0b00010000,false,false,4),
	Fpr(0b00010001,false,false,4);

	
	/** after (u u) aka clean or (u anything_except_u) aka dirty,
	this is "u" for leaf vs "∩" for anything except leaf, which is a binary prefix for each op.
	*
	public final String prefix;
	*/
	public final int opbits;
	
	public final boolean isAlwaysEvaling;
	
	public final boolean isStrange;
	
	/** is 0 if isStrange, cuz number of curries isnt a single number,
	may be vararg or may eval at 2 specific numbers of curries.
	*/
	public final int curriesElse0;
	
	/** generate this generated from the order of Ops in this enum and their fields,
	other than deepLazy and Deeplazy.
	0..127 is the cleans. 128..255 (negatives as byte) are the dirtys.
	*
	private static final Op[] opbyteToOp; //opbits which is 8 bits
	static{
		opbyteToOp = new Op[256];
		opbyteToOp[0] = deepLazy;
		opbyteToOp[128] = DeepLazy;
		for(int offset=0; offset<256; offset+=128){
			boolean isClean = offset==0;
			
			
			/*for(Op op : Op.values()){
				if(Character.isLowerCase(op.name().charAt(0))){
				if(op.curriesElse0 != 0) sum += 1<<op.curriesElse0;
				lg("Op."+op+"("+op.curriesElse0+")");
				} //else skip the clean/dirty mirror, and just double the number in sum *= 2
			}*
			TODO
		}
	}*/
	
	//TODO rename isStrange to isVararg, counting ax as vararg since it evals at 2 specific number of curries,
	//which is a very weak form of vararg compared to the curry ops and 0 and 1 ops.
	
	
	private Op(/*String sourceCode,*/int opbits, boolean isAlwaysEvaling, boolean isVararg, int curriesElse0){
		//this.prefix = sourceCode;
		this.opbits = opbits;
		this.isAlwaysEvaling = isAlwaysEvaling;
		this.isStrange = isVararg;
		this.curriesElse0 = curriesElse0;
	}
	
	public static void lg(String line){
		System.out.println(line);
	}
	
	/** like 00110111 */
	static String toString(byte b) {
		return Integer.toBinaryString(256+(b&0xff)).substring(1);
	}
	
	//FIXME need to get rid of some ops so it fits.
	public static void main(String[] args){
		int sum = 0;
		lg("Ops...");
		for(Op op : Op.values()){
			if(Character.isLowerCase(op.name().charAt(0))){
				if(op.curriesElse0 != 0) sum += 1<<op.curriesElse0;
				int bits = sum;
				lg(toString((byte)bits)+" Op."+op+"("+op.curriesElse0+")");
			} //else skip the clean/dirty mirror, and just double the number in sum *= 2
		}
		System.out.println("sumBeforeDoubling="+Integer.toBinaryString(sum));
		sum *= 2; //cuz first of 7 params chooses !isDirty (leaf) vs isDirty (anything except leaf)
		System.out.println("sum="+Integer.toBinaryString(sum));
		if(sum > 1<<7) throw new RuntimeException(
			"Opcodes dont fit in 7 params. 0..7 curries each being leaf vs nonleaf fits in a byte,"
			+" as a bitstring size 0..7 then a high 1 bit, aka the byte opcode"
			+" used in an earlier version of SimpleFn.interpretedMode's switch statement.");
	}

}
















