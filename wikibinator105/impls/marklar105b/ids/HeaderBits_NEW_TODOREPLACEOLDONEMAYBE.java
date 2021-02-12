package wikibinator105.impls.marklar105b.ids;

public enum HeaderBits_NEW_TODOREPLACEOLDONEMAYBE{ //todo rename to HeaderBits_NEW_TODOREPLACEOLDONEMAYBE_256
	
	/*magic8 op8 curryAllIf24 curryMoreIf24 - magic8 op8 cbtHeightAndBizeIf48_butNotIfAll0s - magic8 31OtherLiteralBytes
	
	FIXME cbtHeightAndBizeIf48_butNotIfAll0s handles bitstrings size 0 to 2^47-1 bits, but does not handle cbts of all 0s.

	FIXME it needs a representation of cbts of all 0s.
	FIXME it needs a representation of unary. Do that as cbt of all 0s? Do that as infcur?

	FIXME it might need to know 1 bit for does it contain axA andOr axB.

	FIXME need to remove 1 1-param op so axB can fit.

	FIXME do I want a comment param everywhere vs just in curry?



	literalCbt256
	call
	cbtOfAllZerosUpToTerabytes
	cbtBitstringUpToTerabytes
	//want to know containsAxAAndorAxB?
	//want comment?


	move 0 and 1 to be 1 deeper, and remove isColorDisproof, to make room for Op.axB
	(so axA and axB do what color did, other than the nonhalters which lambdas cant see.
			The prefix of 0 is the prefix of 1, is at param 6, so the number of curries is
			always known by param 6.

	If its 0 or 1 then the next 4 bits choose between: cbtBitstringUpToTerabytes
	cbtOfAllZerosUpToTerabytes call_ofBigCbt cbt1..cbt128.


	
	(op.curry anotherThingThatHasEquallyManyCurriesAsThisOneShouldEvalAt_suchAsAnInfcurOrWhatever comment funcBody)
	
	
	
	make sure S and Pair's fourth last param is (u u) opbit so they can replace that with a comment if they want
	and its still a nonleaf so is the same op.
	
	
	IF the 48 bits (after opbyte saying its the 1 op or 0 op) are all 0, then it means its a CALL thats also a cbt
	aka a cbt thats too big to fit in that.
	
	If its a literal cbt1..cbt128 then it will say so in those 48 bits as usual, such as 11101 means its height is 4
	cuz theres 4 digits below the highest 1 bit, and the last 1 in the content is at positiion 1101
	aka is a bitstring size 1100.
	
	opbyte tells the first bit if its a cbt.
	so to represent all 0s, use Op.zero with 1 or 10 or 100 or 1000000 etc,
	which would normally mean the last content 1 bit is at index 0 in bitstring,
	but since we know its Op.zero, we know the first bit is 0, so AND it with that first bit.
	
	define bize==0 if the last 1 bit is at index 0 or if there is no 1 bit.
	bize has a powOf2 number of possible values. solved.
	
	TODO choose the magic8 to be not (byte)'\\' but choose something
	thats not valid utf8 such as 11111001 (aka 249 aka -7 aka f9) (also what are utf16 prefixes? want to avoid those.)
	Also want it to occur infrequently in the high byte of float32 and high byte of float64,
	which that does appear to as its a very big negative number either way.
	It can reprsent all possible floats doubles ints arrays etc,
	but if a 256 bits starts with that byte it gets stored as 2 of 128 bits and a hashed id of that call pair,
	instead of a literal 256 bits (which occurs 255/256 of the time, so 49% binary storage efficiency
	considering the internal nodes, or ~99.5% (todo exact number) binary storage efficiency if you
	dont store the middle nodes just the top node and its binary tree leafs each an id256
	such as an id256 and 4096 child id256s most of which do not start with the magic8 byte
	but those few which do have to be stored as their hash and its 2 128 bit childs so 3 id256s instead of 1.
	
	efficiently supports bitstrings up to 2^47-1 bits and cbt up to 2^47 bits aka 128 terabits aka 16 terabytes,
	and less efficiently bitstrings of unlimited size using normal call pairs instead of the 48 bit bize counter.
	
	A cbt called on anything returns a cbt of twice its size and 1 deeper,
	and if its param is not a cbt of its same size, it duplicates itself as the new left and right childs.
	
	If curriesMoreIf24 is 0 then opbyte is 0 aka Op.deepLazy_isNotCleanOrDirtyCuzIsNotHalted.
	If curriesMoreIf24 is 2^24-1 then it means the number of curries remaining doesnt fit in that number.
	
	do I want a bit to cache containsAxAOrAxB? It would help compute SyncLevel.
	That would only go in normal call datastruct (id256 which has a curriesMoreIf24 and curryAllIf24).
	Make those 23 bits each?
	
	
			
	//Here's the datastruct for MarklarId105b:
	if(first byte is not 0xf9){
		//is literal cbt256 thats its own id.
	}else{
		//bit 16 is containsAx. bit 17 is isBitstringUpTo4Terabytes
		0xf9
		op8 //is Op.zero or Op.one, the first bit
		containsAx1 //contains Op.axA or Op.axB deeply in l() and r() recursively?
		isBitstringUpTo4Terabytes1 //is bitstring up to 2^45-1 bits aka 4 terabytes, else slower but unlimited size using normal call pairs.
		if(isBitstringUpTo4Terabytes1){
			//is cbt of powOf2 number of bits from 1..2^47 bits, and knows the index of the last 1 bit if exists.
			cbtHeightAndBize46 //high 1 bit tells which powOf2. Bits below that tell wheres the last 1 bit.
		}else{
			curriesAllIf23 //is 2^24-1 if bigger. number of this.l.l.l.l...l until get to u/leaf plus curriesMoreIf16.
			curriesMoreIf23 //is 2^24-1 if bigger. is 0 if op8 is Op.deepLazy aka is (a snapshot of) evaling.
		}
	}
	
	
	/*else if(second byte is Op.zero or Op.one and next uint48 (cbtHeightAndBize48) != 0){
		
	}else{ //is normal call pair
		0xf9
		op8
		//cacheBits16 //First of these bits is containsAx (axA andOr axB). Others are not used (yet?),
		//	//which maybe useful for derived lambdas to be mostly compatible with this
		//	//while they might use these bits for something else.
		curriesAll16 //is 2^16-1 if bigger. number of this.l.l.l.l...l until get to u/leaf plus curriesMoreIf16.
		curriesMoreIf16 //is 2^16-1 if bigger. is 0 if op8 is Op.deepLazy aka is (a snapshot of) evaling.
	}*
	
	
	
	
	
	
	
	
	
	
	
	TODO I want the first n bits of an id to fit in a single switch statement,
	which gets expensive the bigger a magic it is,
	such as if its magic(8) and opbitsandaxavsaxb(12) thats 2^20 switch cases
	which would make java bytecode far too big and probably slow.
	An efficient switch statement should probably have at most 4096 cases (generated),
	or could use Evaler[1<<20] that would actually work but wouldnt fit in L1 cpu cache.
	It would be faster to check for the magic byte first THEN do the switch.
	
	TODO magic8 op8 curAllIf8 curMoreIf8 bize32 hashOrContainsLiteral192 ???
	
	if(literalCbt256){
		anything except (byte)'\\',
		other 31 bytes
	}else if(isCbtAtLeastSize512){
		curheightOrConstantToSayIsCurhigher(7),
		48 bits of bize
	}else{
		opbitsandaxavsaxb(12),
		22 bits of currriesAllIf
		22 bits of currriesMoreIf
	}
	
	bit for contains axA andOr axB, a cache of syncLevel
	
	/*
	//256 bit id, with axA and axB and no color, still a bloomfilter as axA and axB but purely made of 2-way forest shape.
	
	magic(8),
	
	/** If the first of these bits is 0 then the rest of the bits in it,
	and the bits after it
	*
	opbitsandaxavsaxb(12),
	*/

}
