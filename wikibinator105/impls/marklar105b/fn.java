/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl;
import wikibinator105.spec.λ;

/** The main object type in wikibinator105.impl.
The 2 longs of header and bize are caches of what can be derived from forest shape and color,
and different implementations of wikibinator105 might want to use different kinds and sizes of cache,
so the core interface λ does not include these.
*/
public interface fn extends λ<fn>{
	
	public long header();
	
	/** low 63 bits of bitstring size, and sign bit tells if theres more digits as it supports unlimited size bitstrings,
	so if its nonnegative then thats the number of bits.
	FIXME what if its not a cbt or is a cbt thats all 0s? bizeplusone? bizeplus2?
	Might want the constant 0 to mean an all 0s cbt.
	*/
	public long bize();

}