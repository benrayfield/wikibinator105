/** Ben F Rayfield offers this software opensource MIT license */
package axiomforest;
import java.lang.reflect.Array;

/** knows which primitive type its a 1d array/list/Buffer/sparseView/etc of,
defined in terms of boolean flo() is it a floating point type or not (float, double, etc),
and piz() which means each primitive is 1<<piz() bits such as piz of float is 5.
<br><br>
axiomforest.λ knows if its a bitstring and its size, but anything more complex than that
needs to go in how various systems use axiomforest.
((u u) u) is 1. (u (u u)) is 0.
<br><br>
Rewrite this disorganized text[
FIXME if this is part of axiomforest, where ((u u) u) is 1 and (u (u u)) is 0,
	then it cant put in the extra bits of "boolean flo" and "byte piz" (such as float is a 1<<5 bit flo and piz is 5).
	Axiomforest can know something is or is not a bitstring and its length, and thats it.
	Also, axiomforest supports unlimited size bitstrings, so there needs to be a way to say
	that even though the interface only has longs and is bit aligned, that its bigger.
	Axiomforest's header knows log2 of bitstring size up to about cbt size 2^124 bits,
	but it also has a way to say its either bigger or not a bitstring.
]
*/
public interface TypedBlob extends Blob{
	
	/* Id like to put this in Lazycl interface but I dont want Blob to need a pointer to a Lazycl,
	so I'm moving the implementation of "public default <T> T arr(Class<T> type, long bitFrom, long bitToExcl)" into impl package. 
	public T newMutableMemToWriteThenWrapInImmutable(Class type, long bize){
		toArray
	}*/
	
	public <T> T arr(Class<T> type, long bitFrom, long bitToExcl);

	/** Example float[] f = blob.arr(float[].class) copies to a new float[] */
	public default <T> T arr(Class<T> type){
		return arr(type, 0L, bize());
	}
	
	public default Object arr(){
		return arr(arrayClassOf(prim()));
	}
	
	public static Class arrayClassOf(Class innerType){
		return Array.newInstance(innerType,0).getClass();
	}
	
	/** 2^primitiveSize. bit is 0. byte 3. short and char 4. int and float 5. long and double 6.
	float256 and int256 8.
	Regardless of piz, pize, and isFloat, can read raw bits as any type, but some are more efficient.
	*/
	public byte piz();
	
	/** primitiveSize. Regardless of piz, pize, and isFloat, can read raw bits as any type, but some are more efficient. */
	public default int pize(){
		return 1<<piz();
	}
	
	/** true if known to be stored as float or double etc.
	false if known to be stored as bit byte short char int long etc, or if unknown.
	Regardless of piz, pize, and isFloat, can read raw bits as any type, but some are more efficient.
	Even if its not isFloat() you can still use it as any type, so if unknown, then return false.
	Example: if(pize()==64 && flo()) then use d(int) or D(long).
	*/
	public boolean flo();
	
	/** derived from piz() and flo() */
	public default Class prim(){
		if(flo()){
			switch(piz()){
			case 5: return float.class;
			case 6: return double.class;
			}
		}else{
			switch(piz()){
			case 0: return boolean.class;
			case 3: return byte.class;
			case 4: return short.class;
			case 5: return int.class;
			case 6: return long.class;
			}
		}
		throw new RuntimeException("Has another primitive type been added?");
	}

}
