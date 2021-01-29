/** Ben F Rayfield offers this software opensource MIT license */
package axiomforest;
import java.io.InputStream;

/** Immutable. bitstring of size 0 to 2^63-1 bits.
Can wrap a variety of data sources such as FloatBuffer, CLMem, long[], String,
lazy or eager evaled.
To represent tensor, use 2 Blob together, 1 being the dimension sizes as longs,
where lowest dimension is the primitive size in bits.
To represent sparse tensor, interpret the bitstrings to have pointers in them somehow,
maybe similar to how in Tensorflow they use multiple dense tensors to represent sparse tensors.
Interpret the bitstrings however you like. They are all 1d here, as a lower level of optimization,
but even though blobs are 1d (like memory ranges) you can still use 1-3d opencl globalSize and localSize.
*/
public strictfp interface Blob extends Cbt{
	
	/** bitstring size. bize() < (1L<<Cbt.heightUpTo126()),
	and if its all 0s then its not a bitstring/Blob since it lacks padding (100000... until next powOf2).
	*/
	public long bize();
	
	/** Cbt has InputStream too but you have to say which range. This needs to know bize(). */
	public default InputStream IN(){
		return IN(0L, bize());
	}

}
