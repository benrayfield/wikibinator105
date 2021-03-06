/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impls.marklar105b.ids;
import java.security.MessageDigest;

import immutable.util.HashUtil;
import immutable.util.MathUtil;
import wikibinator105.spec.Op;

/** The default kind of id of a wikibinator105 node. More kinds of ids can be created at runtime,
as any lambda that returns a cbt512 or cbt256 or cbt1024 etc when called on another lambda to create the id of,
but this kind is just to get started. I'm a little concerned about it only having 96 bits of collision security,
(UPDATE: todo could raise that to 112 bits (2 of 224 bit hashes)
since they can share a long header, instead of 2 longs??? but might want that for extra bize precision, num of curries, etc)
and ids can be created for other purposes such as to optimize its alignment to specific other systems,
and use multiple kinds of ids together at once, matching them together.
Ids are global (whole internet) and sync with zero knowledge instantly,
and they're lazyEvaled so you dont even need to create the 512 bits just to use a node as a binary forest shape.
<br><br>
512 bit ids, which is a literal 512 bits if the first byte is NOT (byte)'\\',
so 255/256 of all literal 512 bits fit in 512 bits,
else the first half is a binary forest node with color, and second half
is that same shape of binary forest node without color aka the normed form
for matching it to compare colors of the same node and OR them together as bloomfilter.
<br><br>
Collision resistance is 96 bits of security, that if you store 2^96 (79228162514264337593543950336)
of them you will find about 1 collision,
cuz it contains 2 related 192 bit hashes, or literals that fit in id.
<br><br>
The first 256 bits are for binary forest node with color, and the second 256 bits are that same node without color,
to match it and consider different combos of color and OR them together into bloomfilter (a node is a bloomfilter),
unless they're a literal in which case the 512 bits mean something else.
<br><br>
TODO choose order of the 2 halfs...
Its better for it to be hard to find something with the same prefix as another call pair,
so put the one with color first, cuz treemaps are a little more efficient the shorter the shared prefix.
On the other hand, sorting by the one without color puts those that need to be color-ORed together right beside eachother.
<br><br>
About the name of this kind of id...
'The Marklar are an intelligent species of identical aliens who refer to all people, places,
and things as "Marklar". Despite being almost identical and all having the same name, they are still
somehow able to tell each other apart and address each other as Marklar without ambiguity.'
-- https://southpark.fandom.com/wiki/Marklar
It seems similar to how a universal function works. Theres only 1 word but you can say anything as combos of it.  
*/
public final class MarklarId105b /*implements IdMaker_old_useFuncsDirectlyAsIdmaker*/{
	
	/** If first byte is not this, its a literal cbt256 that is its own id, so most random 256 bits are their own id,
	else its a normal id.
	*/
	public static final byte noncbt256firstbyte = (byte)0xf9;
	
	public static final long noncbt256firstbyteLong = ((long)noncbt256firstbyte)<<56;
	
	public static final long mask15 = (1<<15)-1;
	
	public static final long mask45 = (1L<<45)-1;
	
	public static final long mask46 = (1L<<46)-1;
	
	/** If first byte is not 0xf9, none of the masks apply, as all 256 bits of the id are literal data */
	public static final long maskContainsAxof2params_ifFirstByteIsNotF9 = (1L<<47);
	
	/** If first byte is not 0xf9, none of the masks apply, as all 256 bits of the id are literal data */
	public static final long maskIsBitstringUpTo2tB_ifFirstByteIsNotF9 = (1L<<46);
	//FIXME 2tB not 2tB, and its cbtHeightAndBize45 not cbtHeightAndBize46.
	//FIXME reorder the 3 mask bits (that come after the first 16 bits when not literalCbt256)
	//aka containsaxof2params containsbit1 isblobupto2power44minus1bits?
	
	public static final long maskContainsBit1_ifFirstByteIsNotF9 = (1L<<45);
	
	/** height of (Op.blob u) and of (Op.blob (u u)) */
	public static final int minHeightOfCbt = 7;
	
	/* Almost done.. TODO instead of 2 of 23, have 3 of 15 (reserve 1 bit to mean "some other data format, maybe cid/ipld/ipfs/URN/contenttype/etc related?"):
		ignoreThisBit1 heightIf15 curryAllIf15 curryMoreIf15.
	This way, can efficiently have cbts up to 2^32766 bits (sparse of course) since they have to verify height of their param,
	and number of curries goes in id up to 32766 curries, and past that have to use lambdas to count curries up to unlimited.
	32767 means "doesnt fit in 15 bits".
	Know its a cbt IFF its Op.blob (which opbyte tells, which is copied from l().l().l()...l() after curry 7 forall node).
	*/
	
	/*
	//Here's the datastruct for MarklarId105b:
	if(first byte is not 0xf9){
		//is literal cbt256 thats its own id.
	}else{
		//bit 16 is containsAx. bit 17 is isBitstringUpTo32Terabits
		0xf9
		op8 //see Op enum
		containsAx1 //contains Op.axA or Op.axB deeply in l() and r() recursively?
		isBitstringUpTo32Terabits1 //is bitstring up to 2^45-1 bits aka 4 terabytes, else slower
			//but unlimited size using normal call pairs.
		if(isBitstringUpTo32Terabits1){
			//is cbt of powOf2 number of bits from 1..2^46 bits, and knows the index of the last 1 bit if exists.
			cbtHeightAndBize46 //high 1 bit tells which powOf2. Bits below that tell wheres the last 1 bit (if it exists).
		}else{
			curriesAllIf23 //is 2^23-1 if bigger. number of this.l.l.l.l...l until get to u/leaf plus curriesMoreIf23.
			curriesMoreIf23 //curries until eval. is 2^23-1 if bigger. is 0 if op8 is Op.deepLazy aka is (a snapshot of) evaling.
		}
	}
	*/
	
	
	
	/** This is the most general, though slower, and usually you dont need to do it this slow way.
	This does not compute hash bits and only needs to see the non-header bits in case theres literal data in them
	thats so big it replaces the header (which starts with any byte other than 0xf9 to say so).
	You can avoid this slowness by first calling isLiteralCbt256(leftHeader), and if false,
	call parentHeaderIfLeftIsNotALiteralCbt256(leftHeader,rightHeader),
	else you can use the bize you already know it is (may be in Simpleλ.java etc?)
	to call parentHeaderIfLeftIsALiteralCbt256(long,long,short),
	or maybe a subclass of λ that has 4 longs and a boolean to say if the hash is computed yet
	and maybe an extra few fields for caches.
	*
	public static long parentHeader(
			long leftHeader, long leftB, long leftC, long leftD,
			long rightHeader, long rightB, long rightC, long rightD){
		TODO call parentHeaderIfLeftIsALiteralCbt256(long,long,short) or parentHeaderIfLeftIsALiteralCbt256(long,long)
		depending if left is a literal cbt256.
	}
	
	public static long parentHeaderIfLeftIsNotALiteralCbt256(long leftHeader, long rightHeader){
		
		if(isLiteralCbt256(leftHeader)){
			FIXME also need cbtHeightAndBize46 (of left andOr right?), so parentHeader of 2 longs and 2 ubytes in that case?
			TODO
		}else{
			TODO cbt1..128. In that case its leafHeader with its curriesAllIf23++
			cuz a cbt called on anything is a cbt twice as big
			and moving up the high 1 bit in cbtHeightAndBize46 (like adding 1 to curriesAllIf23 doubles the cbt size,
			if its a bitstring bigger than fits in cbtHeightAndBize46).
			But if leftHeader is already a literal cbt256 thats a problem cuz dont know its bize just from that header
			but parent of those 2 do need to know its bize,
			and rightHeader often knows its bize but may also be a literal cbt256
			or may be a bitstring bigger than fits in cbtHeightAndBize46
			(in which case its a normal funcall with curriesAllIf23 and curriesMoreIf23).
			This wouldnt be a problem if 2 child id256 are known, instead of just the first 64 bits of each.
			Maybe this function should take 8 longs?
			
			
			TODO
		}
	}
	
	/** resultingBize is range 0..511 and must be given since its not derivable from the 2 headers
	in all possible cases, since its a compression for 256 bits to be their own id
	in most cases (when doesnt start with 0xf9).
	<br><br>
	Bize is always the number of bits before the last 1 in cbt/blob content, or 0 if its all 0s,
	and ranges 0(inclusive)..infinity(exclusive) which must be supported in all Wikibinator105 VMs
	as it is just a thing that lambdas can ask about lambdas that dont know about internal optimizations.
	<br><br>
	That simple definition gets complex depending on optimizations such as trying to compute it
	using only the first 64 bits of each of 2 id256s that are lazyevaled ids
	and considering that if 256 bits dont start with the byte 0xf9 then they are their own id,
	so in that case you need all 256 bits to look for that last 1 bit, or to know the bize which is the short param here.
	<br><br>
	To know the difference between all 0s and the first bit being 1 and all the others 0s
	(which are both bize 0), those have 2 different opbytes
	cuz in a blob the opbyte (Op.blob u) aka 1 vs (Op.blob (u u)) aka 0
	knows the first bit and is a binary tree of bits after that.
	<br><br>
	Bize ranges 0(inclusive)..infinity(exclusive) regardless of how its stored in what kinds of ids,
	and if a certain data type cant hold enough bits you can still use interpreted mode of lambdas for unlimited bize,
	which it will retreat to in cases thats asked but the local optimizations dont have enough bits,
	because otherwise it would not be a correct implementation of the wikibinator105 universal function.
	<br><br>
	If right is a cbt256 of 256 0s, then resultingBize is bize of left.
	If left is not all 0s and right is not a cbt256 then resultingBize is 256 + bize of left,
	cuz its (leftCbt leftCbt).
	If right is a cbt256 that is not all 0s, then bize is 256 + bize of right.
	If both are all 0s, then bize is 0.
	*
	public static long parentHeaderIfLeftIsALiteralCbt256(long leftHeader, long rightHeader, short resultingBize){
		TODO
	}*/
	public static long parentHeader(
			long leftHeader, byte leftLizif, long leftCMayBeReturnedAsHeaderIfReturnLiteralCbt256_ignoredIfLeftIsNotACbt128,
			long rightHeader, byte rightLizif){
		
		consider Op.deeplazy
		
		boolean leftIsLiteralCbt128 = isLiteralCbt128(leftHeader);
		boolean rightIsLiteralCbt128 = isLiteralCbt128(rightHeader);
		//Cbt called on anything is cbt twice as big, by if its param is anything except a cbt of same size
		//its return value is instead itself called on itself, but thats an eval, and before that it would be Op._deeplazy.
		//So here, its only a cbt256 if both its childs are cbt128 and if the first byte of left's content is not 0xf9.
		boolean parentIsLiteralCbt256 = leftIsLiteralCbt128 & rightIsLiteralCbt128
			& isLiteralCbt256(leftCMayBeReturnedAsHeaderIfReturnLiteralCbt256_ignoredIfLeftIsNotACbt128); //check for 0xf9
		if(parentIsLiteralCbt256) return leftCMayBeReturnedAsHeaderIfReturnLiteralCbt256_ignoredIfLeftIsNotACbt128;
		
		TODO next opbyte or copy opbyte.
		
		Compute cbtheightandbize45 (in last 45 bits of header)
		
		compute containsBit1 by oring that from 2 childs and with self's opbyte being the opbyte of bit1.
		compute similar for containsaxof2params.
		
		compute the 3 short15s (heightif and 2 curriesif)
		
		
		
		
		
		
		//boolean rightIsLiteralCbt128 = isLiteralCbt128(rightHeader);
		//boolean leftIsLiteralCbt256 = isLiteralCbt256(leftHeader);
		//boolean rightIsLiteralCbt256 = isLiteralCbt256(rightHeader);
		//if()
			
		TODO
	}
	
	/** see fn.lizif() */
	public static byte parentLizif(long parentHeader, byte leftChildLizif, byte rightChildLizif, boolean rightContainsBit1){
		if(isLiteralCbt256(parentHeader)){
			return rightContainsBit1 ? leftChildLizif : rightChildLizif;
					
			/*if(isRightChildAll0s){
				return leftChildLizif;
			}else{
				ret
			}
			
			//is the index 0..255 of the last 1 bit in the 256 literal bits that is its own id256, or 0 if its all 0s.
			if(!isRightChildAll0s || rightChildLizif != 0){ //right child is not all 0s cuz know it has a last 1 bithas a last 1 bit somewhere
				return (byte)(128+rightChildLizif);
			}else if(isRightChildAll0s){
				//lizif 0 means either the content is all 0s or the content is a 1 then the rest are all 0s,
				//and to know the difference you check opbyte, as theres 2 different opbytes for blobs that
				//start with 0 vs blobs that start with 1.
			}else{
				
			}
			TODO
			*/
		}else if((parentHeader&maskIsBitstringUpTo2tB_ifFirstByteIsNotF9) != 0){
			return (byte)bizeUpTo2tBElseNegOneIfBiggerCbtOrCantKnowFromOnlyHeaderCuzIsLiteralCbt256(parentHeader);
		}else{
			return 0;
		}
	}
	
	static final long containsBit1_optimization = (1L<<63)|maskContainsBit1_ifFirstByteIsNotF9;
	
	/** does it deeply anywhere contain (Op.blob u) aka clean bit 1, even if its not a cbt it may still contain that */
	public static boolean containsBit1(long header, byte lizif){
		return lizif != 0 || (header&containsBit1_optimization) != 0; //optimization of the commentedout code below
		/*if(lizif != 0){
			return true;
		}else if(isLiteralCbt256(header)){ //lizif==0 so its either 256 0s or 1 then 255 0s
			return header!=0;
		}else{
			return (header&maskContainsBit1_ifFirstByteIsNotF9) != 0;
			//FIXME go down from 4tB to 2tB so these 3 bits are part of all non-isLiteralCbt256 headers,???
			//instead of having to check if it uses the cbtHeightAndBize46???
		}*/
	}
	//FIXME TODO wherever theres 15 15 15 is (parentHeader, etc) put the containsBit1 cache in it,
	//and other times derive it from opbyte and lizif.
	
	/** or more generally use parentHeader(...) */
	public static long parentHeaderIfLeftIsNotACbt128(long leftHeader, byte leftLizif, long rightHeader, byte rightLizif){
		return parentHeader(leftHeader, leftLizif, 0L, rightHeader, rightLizif);
	}
	
	/** or more generally use parentHeader(...) */
	public static long parentHeaderIfLeftOpIsNotBlob(long leftHeader, long rightHeader){
		if(op(leftHeader)==Op.blob) throw new RuntimeException("Op.blob cant be used in this simplified function thats here for convenience");
		return parentHeader(leftHeader, (byte)0, 0L, rightHeader, (byte)0);
	}
	
	public static long[] parentId(
			long leftHeader, long leftB, long leftC, long leftD,
			long rightHeader, long rightB, long rightC, long rightD) {
		return parentId(defaultHashAlgorithm, leftHeader, leftB, leftC, leftD, rightHeader, rightB, rightC, rightD);
	}
			
	
	/** Eager eval of id256. Usually lazyeval is best.
	Returns long[4], a marklar105b id deterministicly generated from 2 child marklar105b ids.
	Should take a few microseconds for one of these, or a few nanoseconds in GPU for many (id,id)->id in parallel,
	so when GPU gives you a large primitive array,
	it can also give you the id of that array (in log number of kernel calls) or not,
	which should be fast enough to, if you wanted (not necessary),
	generate global id of every frame of video (1/60 second) in a FPS game and screen blits etc,
	or at least it could at low resolution. Will have to experiment with it to know exactly how fast.
	Default hashAlgorithm is "SHA3-256", and whatever it is, only the last 192 bits are used.
	TODO would it be more secure to XOR in the other 64 bits into all 3 of the other 64 64 64 bits?
	*/
	public static long[] parentId(
			String hashAlgorithm,
			long leftHeader, long leftB, long leftC, long leftD,
			long rightHeader, long rightB, long rightC, long rightD){
		long header = parentHeader(
			leftHeader, lizif(leftHeader,leftB,leftC,leftD), leftC,
			rightHeader, lizif(rightHeader,rightB,rightC,rightD)
		);
		if(isLiteralCbt256(header)){ //(cbt128,cbt128)->cbt256 by concat. header==leftC. Most random 256 bits are their own id.
			assert header==leftC; //FIXME when are JVM assert turned on?
			return new long[]{ leftC, leftD, rightC, rightD };
		}else{
			int leftCbtHeight = heightIf15(leftHeader)-minHeightOfCbt; //number of bits in cbt is 2^cbtHeight, including padding.
			int rightCbtHeight = heightIf15(rightHeader)-minHeightOfCbt;
			//cbt called on anything is cbt twice as big, so if they're different heights it has to eval that first,
			//which is a bigO(1) eval to just call left on left, but this parentId function was given the 2 childs,
			//and if those 2 childs cant be together in a halted state as they are, it has to be marked as Op._deeplazy.
			if(isLiteralCbt1To128(header) && leftCbtHeight == rightCbtHeight){
				//TODO optimize by not using switch here, but the switch might be useful for debugging for a while.
				long c = 0, d = 0;
				//cbt1 to cbt128 literals have all 0s between end of header and start of the content which is at end of id256.
				switch(leftCbtHeight){
				case 1: //(cbt1,cbt1)->cbt2 by concat
					d = (leftD<<1)|rightD;
				break;
				case 2: //(cbt2,cbt2)->cbt4
					d = (leftD<<2)|rightD;
				break;
				case 3: //(cbt4,cbt4)->cbt8
					d = (leftD<<4)|rightD;
				break;
				case 4: //(cbt8,cbt8)->cbt16
					d = (leftD<<8)|rightD;
				break;
				case 5: //(cbt16,cbt16)->cbt32
					d = (leftD<<16)|rightD;
				break;
				case 6: //(cbt32,cbt32)->cbt64
					d = (leftD<<32)|rightD;
				break;
				case 7: //(cbt64,cbt64)->cbt128
					c = leftD;
					d = rightD;
				}
				return new long[]{ header, 0, c, d };
			}else{
				return id256FromParentHeaderConcatHash192_onlyUseIfNotLiteralCbt(header, hashAlgorithm,
					leftHeader, leftB, leftC, leftD,
					rightHeader, rightB, rightC, rightD);
			}
		}
	}
	
	/** returns id256 */
	public static long[] id256FromParentHeaderConcatHash192_onlyUseIfNotLiteralCbt(
			long header, String hashAlgorithm, long... in){
		if(isLiteralCbt(header)) throw new RuntimeException("isLiteralCbt "+header);
		long[] ret = new long[4];
		ret[0] = header;
		hash192(hashAlgorithm,ret,1,in);
		return ret;
	}
	
	/** last 192 bits of hashAlgorithm. returns long[3] */
	public static long[] hash192(String hashAlgorithm, long... in){
		long[] ret = new long[3];
		hash192(hashAlgorithm,ret,0,in);
		return ret;
	}
	
	/** literal cbt1..256 that fits in an implies its 2 child id256s so dont need to store them */
	public static boolean isLiteralCbt(long header){
		//return isLiteralCbt256(header) || isLiteralCbt1To128(header);
		return isCbt(header) && heightIf15(header)<=minHeightOfCbt+8;
	}
	
	public static boolean isLiteralCbt1To128(long header){
		return isCbt(header) && heightIf15(header)<minHeightOfCbt+8;
	}
	
	/** change this, for example to "SHA256", and it will work in CPU,
	but any GPU optimizations of it would not change algorithm with it automatically unless TODO those
	are generated from lambdas at runtime in custom JIT compiler I'm making for wikibinator105.
	*/
	public static final String defaultHashAlgorithm = "SHA3-256";
	
	/** last 192 bits of sha3_256. writes writeHere[offset..(offset+2)]. normally this is long[8] of 2 id256s.
	*/
	public static void hash192(String hashAlgorithm, long[] writeHere, int offset, long... in){
		byte[] hash = HashUtil.hash(hashAlgorithm,MathUtil.longsToBytes(in));
		writeHere[offset] = MathUtil.readLongFromByteArray(hash, hash.length-24);
		writeHere[offset+1] = MathUtil.readLongFromByteArray(hash, hash.length-16);
		writeHere[offset+2] = MathUtil.readLongFromByteArray(hash, hash.length-8);
	}
	
	
	
	/** casts the ints to shorts */
	public static long headerOfFuncall(byte opbyte, boolean containsAxOf2Params, boolean containsBit1,
			int heightIf15, int curriesAllIf15, int curriesMoreIf15){
		return headerOfFuncall(opbyte, containsAxOf2Params, containsBit1,
			(short)heightIf15, (short)curriesAllIf15, (short)curriesMoreIf15);
	}
	
	public static long headerOfFuncall(byte opbyte, boolean containsAxOf2Params, boolean containsBit1,
			short heightIf15, short curriesAllIf15, short curriesMoreIf15){
		//TODO verify the 3 shorts are all positive else throw? or drop the sign bit (using mask15)?
		return noncbt256firstbyteLong | ((opbyte&0xffL)<<48)
			| (containsAxOf2Params?maskContainsAxof2params_ifFirstByteIsNotF9:0L)
			| (containsBit1?maskContainsBit1_ifFirstByteIsNotF9:0L)
			| (((long)heightIf15)<<30) | ((long)curriesMoreIf15<<15) | (long)curriesMoreIf15;
			//| (((long)(curriesAllIf23&mask23))<<23) | (curriesMoreIf23&mask23);
	}
	
	/** cbtHeight is (TODO) height+heightOfCbt1 */
	public static long cbtHeightAndBize46(int cbtHeight, long bize){
		if(cbtHeight > 45 || (bize&mask45) != bize) throw new RuntimeException(
			"Too big to fit in cbtHeightAndBize46 cbtHeight="+cbtHeight+" bize="+bize);
		return (1L<<cbtHeight)|bize;
	}
	//FIXME 2tB not 2tB, and its cbtHeightAndBize45 not cbtHeightAndBize46.
	//FIXME reorder the 3 mask bits (that come after the first 16 bits when not literalCbt256)
	//aka containsaxof2params containsbit1 isblobupto2power44minus1bits?
	
	public static long headerOfBlobUpTo2tBThatsNotALiteralCbt256(byte opbyte, long cbtHeightAndBize46){
		return noncbt256firstbyteLong | maskIsBitstringUpTo2tB_ifFirstByteIsNotF9
			| ((opbyte&0xffL)<<48) | (cbtHeightAndBize46&mask46);
	}
	
	/** Nonnegative short. 0x7fff (Short.MAX_VALUE) means higher.
	If first byte is noncbt256firstbyte, height is minHeightOfCbt+8.
	Height of a cbt of size 2^n bits is minHeightOfCbt+n.
	Cbt of size 1 2 4 8 16 32 64 128 and usually 256 (depending on first byte) fits in an id256.
	Cbt<2^45> aka 2tB, has cbtHeightAndBize46 in its header
	instead of [ignoreThisBit1, short heightIf15, short curriesAllIf15, short curriesMoreIf15] in normal call pair.
	Cbt<2^46> and bigger use normal call pairs. See maskIsBitstringUpTo2tB_ifFirstByteIsNotF9.
	Normal call pairs know their specific height if its 0..0x7ffe (Short.MAX_VALUE-1). 0x7fff means higher.
	*/
	public static short heightIf15(long header){
		if(isLiteralCbt256(header)) return minHeightOfCbt+8;
		if((header&maskIsBitstringUpTo2tB_ifFirstByteIsNotF9) != 0)
			return (short)(minHeightOfCbt+45-Long.numberOfLeadingZeros(header&mask46));
		return (short)((header>>>30)&mask15);
	}
	
	/** Nonnegative short. 0x7fff (Short.MAX_VALUE) means more than 0x7ffe total curries (so far + more) before eval */
	public static short curriesAllIf15(long header){
		if(isCbt(header)){
			return Short.MAX_VALUE; //cbt (like infcur) has infinity curries left, just keeps appending curries
			//return heightIf15(header); //FIXME is this right?
		}else{
			return (short)((header>>>15)&mask15);
		}
	}
	
	
	/** Nonnegative short. 0x7fff (Short.MAX_VALUE) means more than 0x7ffe curries before eval */
	public static short curriesMoreIf15(long header){
		if(isCbt(header)){
			return Short.MAX_VALUE; //cbt (like infcur) has infinity curries left, just keeps appending curries
		}else{
			return (short)(header&mask15);
		}
	}
	
	/** If true, contains any (Op.ax x y) for any x and y, which affect SyncLevel
	(makes it harder to sync cuz it claims things about what calls return what else vs return something other than that,
	which is necessary for caching func_param_return but you dont have to (or can) share those caches in p2p network
	which accumulates them as bloomFilter of you cant have (Op.ax u y) and (Op.ax (u u) y) for any y,
	aka you cant have (AxA y) and (axB y) for any y, cuz (axA y) means (y u)->u and (axB y) means (y u)-> anything_except_u.
	Also, Op.ax is how to do typed lambdas (param can be anything, and return is a constraint system with turingComplete types),
	such as the constraint (ax u (fpr func param ret)) is a claim that (func param)->ret.
	Its most reliable to only share things that are CLEAN (vs DIRTY) and !containsAxof2params(its header),
	and explore into DIRTY andOr containsAxof2params as a research path.
	*/
	public static boolean containsAxof2params(long header){
		return isLiteralCbt256(header) ? false : ((header&maskContainsAxof2params_ifFirstByteIsNotF9) != 0);
	}
	
	public static boolean isHalted(long header){
		return curriesMoreIf15(header)>0;
	}
	
	public static final byte opByteOfBlobStartingWith1 = Op.opbyteConcatCleanleaf(Op.opbyte(Op.blob));
	
	public static final byte opByteOfBlobStartingWith0 = Op.opbyteConcatAnythingButCleanleaf(Op.opbyte(Op.blob));
	
	public static boolean isCbt(long header){
		byte opbyte = opbyte(header);
		//TODO optimize those 2 opbytes differ by only the low bit of opbyte. use a mask and 1 ==? 
		return opbyte==opByteOfBlobStartingWith1 || opbyte==opByteOfBlobStartingWith0;
	}
	
	/** If !isLiteralCbt256(header) then use headerOfFuncall of 2 cbt128. Most random 256 bits are their own id,
	so only in 1/256 of cases would you need to use 2 cbt128 like that (other than is implied by the literal cbt256
	those are its childs, and cbt64s are thoses childs and so on down to (Op.blob u) being 1 and (Op.blob (u u)) being 0,
	and past that all the way down to cleanLeaf/u.
	*/
	public static long headerOfLiteralCbt256(long header, long b, long c, long d){
		if(!isLiteralCbt256(header)) throw new RuntimeException("Not a literal cbt256");
		return header;
	}
	
	/** Op._deeplazy. This isnt a constant. It needs to know heightIf15 *
	public static final long headerOfDeeplazy = headerOfFuncall((byte)0, false, 0, 0);
	*/
	
	/** Op._root. This never occurs, at least in this prototype,
	but may occur when mounting wikibinator105 in other systems like axiomforest.
	*/
	public static final long headerOfRoot = headerOfFuncall((byte)1, false, false, 0, 0, 7);
	
	/** FIXME
	??? (Op._root Op._root). FIXME _root isnt really a node in the system, so to make this consistent
	I might need to define dirtyLeaf as (_root cleanLeaf).
	Originally it was (leaf leaf) as the prefix of CLEAN and (leaf (leaf leaf)) as the prefix of DIRTY.
	These kind of things wont break anything at the opbyte level, but the redesign
	for the reflect ops (l r isleaf isclean L R Isleaf Isclean) to not be able to return _root
	and instead (L cleanLeaf)->identityFunc and (R cleanLeaf)->cleanLeaf
	and (L DirtyLeaf)->IdentityFunc and (R DirtyLeaf)->DirtyLeaf, for the purpose of (L x (R x)) equals x forall x...
	That redesign will have to involve opbyte not always being made of isleaf of right child
	since 1 of the bits in opbyte is for each of the first 0..7 curries,
	and the first such curry was originally _root but _root cant be a node in the system (except for mounting it etc),
	so that bit in opbyte (which slides to higher bit with each next curry) will mean clean vs dirty,
	and all those opbits after it will mean isleaf vs is something other than leaf. 
	*/
	public static final long headerOfCleanLeaf = headerOfFuncall((byte)2, false, false, 1, 1, 6);
	
	/** FIXME see comment of headerOfCleanLeaf.
	(Op._root anything_except_ROOT) such as (Op._root cleanLeaf). ???
	*/
	public static final long headerOfDirtyLeaf = headerOfFuncall((byte)3, false, false, 2, 1, 6);
	
	/** bize is bitstring size, which is stored in header if its up to 2^45-1 bits (4 terabytes).
	TODO return what if its not a blob/cbt or is bigger than that? For now at least, returns -1 for that. 
	*/
	public static long bizeUpTo2tBElseNegOneIfBiggerCbtOrCantKnowFromOnlyHeaderCuzIsLiteralCbt256(long header){
		if(isLiteralCbt256(header)) throw new RuntimeException("need the whole 256 bits to know where last 1 bit (if exists) is");
		if((header&(1L<<46))!=0){ //the isBitstringUpTo4Terabytes bit. Will be 0 if is not a blob or if too big for header.
			long cbtHeightAndBize46 = header&((1L<<46)-1);
			//If cbtHeightAndBize46 is 0 then then it may or may not lack the 1 0000... padding.
			//If bize > 0 then that padding exists, and the bitstring is whatevers before that last 1 in the content.
			//The following code works either way, and is about the header not the content it describes size of:
			int leadingZeros = Long.numberOfLeadingZeros(cbtHeightAndBize46);
			return cbtHeightAndBize46^(1L<<(63-leadingZeros)); //remove high 1 bit (which tells cbt height)
		}
		return -1;
	}
	
	/** see parentHeader(long leftHeader, byte leftLizif, long rightHeader, byte rightLizif).
	Returns null if header is of a cbt256, which is the only case when it cant be known from header.
	<br><br>
	OLD...
	if cant be known just from that header, such as if its a literal cbt256
	in which case you need to know all 256 bits to know where the last 1 bit is (or if its all 0s),
	or if its a cbt/blob bigger than 2^45-1 bits in which case the header knows about curries but not bitstrings
	and would have to be computed by lambdas recursively
	(average of constant cost if AxfprCache remembers such calls on childs).
	*/
	public static Byte lizif(long header){
		if(isLiteralCbt256(header)) return null;
		//max changes -1 to 0
		return (byte)Math.max(0,bizeUpTo2tBElseNegOneIfBiggerCbtOrCantKnowFromOnlyHeaderCuzIsLiteralCbt256(header));
	}
	
	/** Always works, unlike lizif(long header)==null if isLiteralCbt256(long header) cuz need more info.
	A faster way is to cache the lizif byte in each node, and compute it upward from each 2 childs,
	and only call this when loading a node from an id256, or when when wrapping
	an int[] or double[] etc (childs are aligned on powOf2 size ranges) and generating their ids
	as most blocks of 256 bits in those will be their own id but some will
	be 2 of cbt128 (cuz starts with a certain byte).
	*/
	public static byte lizif(long header, long b, long c, long d){
		Byte lizif = lizif(header);
		return lizif!=null ? lizif : (byte)Math.max(0,255-numberOfTrailingZeros(header, b, c, d));
	}
	
	/** same as Long.numberOfTrailingZeros(int256) would be. Ranges 0 to 256 */
	public static int numberOfTrailingZeros(long a, long b, long c, long d){
		if(d != 0) return (byte)(255-Long.numberOfTrailingZeros(d));
		if(c != 0) return (byte)(191-Long.numberOfTrailingZeros(c));
		if(b != 0) return (byte)(127-Long.numberOfTrailingZeros(b));
		if(a != 0) return (byte)(63-Long.numberOfTrailingZeros(a));
		return 256;
	}
	
	/** is 256 bits that is its own id if its first byte is not 0xf9. Most possible 256 bits are. */
	public static boolean isLiteralCbt256(long header){
		return header>>>56 != 0xf9;
	}
	
	public static boolean isLiteralCbt128(long header){
		return isCbt(header) && heightIf15(header)==minHeightOfCbt+7;
	}
	
	public static byte opbyte(long header){
		return isLiteralCbt256(header)
			? (header<0 ? opByteOfBlobStartingWith1 : opByteOfBlobStartingWith0)
			: (byte)(header>>>48);
	}
	
	public static Op op(long header){
		return Op.atOpbyte(opbyte(header));
	}
	
	/** this might be slower than just calling opbyte(long)
	cuz it fits in CPU L2 cache but maybe not L1 cache. TODO experiment.
	Also, its important things can be computed in very little memory to GPU optimize,
	but this optimization is for CPUs.
	*/
	public static byte opbyte_using64kBCache(long header){
		return opbyte_using64kBCache((int)(header>>>48));
	}
	
	private static final byte[] opbyte_using64kBCache;
	static{
		opbyte_using64kBCache = new byte[1<<16];
		for(int i=0; i<opbyte_using64kBCache.length; i++) {
			//not always valid header but opbyte(long) doesnt look at the low 48 bits
			opbyte_using64kBCache[i] = opbyte(((long)i)<<48);
		}
	}
	
	/*public static long bizeIsKnown(){
		
		return (1L<<(63-17))
	}*/
	
	/*TODO...
	
	//Here's the datastruct for MarklarId105b:`
	if(first byte is not 0xf9){
		//is literal cbt256 thats its own id.
	}else{
		//bit 16 is containsAx. bit 17 is isBitstringUpTo4Terabytes
		0xf9
		op8 //see Op enum
		containsAx1 //contains Op.axA or Op.axB deeply in l() and r() recursively?
		isBitstringUpTo32Terabits1 //is bitstring up to 2^45-1 bits aka 4 terabytes, else slower
			//but unlimited size using normal call pairs.
		if(isBitstringUpTo32Terabits1){
			//is cbt of powOf2 number of bits from 1..2^46 bits, and knows the index of the last 1 bit if exists.
			cbtHeightAndBize46 //high 1 bit tells which powOf2. Bits below that tell wheres the last 1 bit (if it exists).
		}else{
			curriesAll23 //is 2^23-1 if bigger. number of this.l.l.l.l...l until get to u/leaf plus curriesMoreIf23.
			curriesMoreIf23 //is 2^23-1 if bigger. is 0 if op8 is Op.deepLazy aka is (a snapshot of) evaling.
		}
	}
	
	/*the hash192 is the last 192 bits of sha3_256 and comes after the 64 bit header,
	and its either 192 0s for leaf (op9 is (byte)1, so even if theres a hash collision its still a different id)
	(and of course the normal 64 bits of header before that) or is last192Bits(sha3_256(concat(leftId,rightId))).
	
	Also some Op enum changes etc described in HeaderBits_NEW_TODOREPLACEOLDONEMAYBE
	but most of that is what led to this and just comment it out or remove those words.
	*
	
	public static final MarklarId105b instance = new MarklarId105b();
	
	public static byte firstByte(Blob id){
		throw new RuntimeException("TODO");
		//return (byte)(data[0]>>>56);
	}
	
	/** If true, this is both the 512 bit content and the id of that content. The only other kinds
	of literal (that fit in an id) are cbt256 cbt128 cbt64 cbt32 cbt16 cbt8 cbt4 cbt2 cbt1 and leaf/u.
	Anything else is a call pair as usual.
	*
	public static boolean isLiteral512(Blob id){
		return firstByte(id)!='\\';
	}

	public Blob idOfLeaf(){
		throw new RuntimeException("TODO");
	}

	public Blob idOfBits(Blob bits){
		throw new RuntimeException("TODO");
	}

	/*public Blob idOfCallPair(EnumSet<λColor> colors, Blob funcId, Blob paramId){
		throw new RuntimeException("TODO");
	}*

	public λ fn(){
		throw new RuntimeException("TODO");
	}*/

}
