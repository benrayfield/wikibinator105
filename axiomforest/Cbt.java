/** Ben F Rayfield offers this software opensource MIT license */
package axiomforest;
import java.io.InputStream;

/** complete binary tree of 1s and 0s. ((u u) u) is 1. (u (u u)) is 0. ( ((u u) u) (u (u u)) ) is 10, and so on.
If viewed as bitstring, its padded with 10000000... up to the next powOf2 size,
and you use Blob.java for that which extends (this) Cbt.java.
*/
public strictfp interface Cbt{
	
	/** 127 means higher, but wont quickly know (so not storing it here) that its a cbt if so. */
	public byte heightUpTo126();
	
	/** bit at index 0 to bize()-1 */
	public default boolean z(long bitIndex){
		return (J(bitIndex)>>>63)!=0;
	}
	
	/** float at index 0 to bize()-1 */
	public default float F(long bitIndex){
		return Float.intBitsToFloat(I(bitIndex)); 
	}
	
	/** float at index 0 to bize()/32-1 */
	public default float f(int index){
		return Float.intBitsToFloat(i(index));
	}
	
	/** double at index 0 to bize()-1 */
	public default double D(long bitIndex){
		return Double.longBitsToDouble(J(bitIndex));
	}
	
	/** double at index 0 to bize()/64-1 */
	public default double d(int index){
		return Double.longBitsToDouble(j(index));
	}
	
	/** long at index 0 to bize()-1 */
	public long J(long bitIndex);
	
	/** long at index 0 to bize()/64-1 */
	public default long j(int index){
		return J(((long)index)<<6);
	}
	
	/** int at index 0 to bize()-1 */
	public default int I(long bitIndex){
		return (int)J(bitIndex+32);
	}
	
	/** int at index 0 to bize()/32-1 */
	public default int i(int index){
		return (int)I(((long)index)<<5);
	}
	
	/** char at index 0 to bize()-1 */
	public default char C(long bitIndex){
		return (char)S(bitIndex);
	}
	
	/** char at index 0 to bize()/16-1 */
	public default char c(int index){
		return (char)s(index);
	}
	
	/** short at index 0 to bize()-1 */
	public default short S(long bitIndex){
		return (short)J(bitIndex+48);
	}
	
	/** short at index 0 to bize()/16-1 */
	public default short s(int index){
		return (short)S(((long)index)<<4);
	}
	
	/** byte at index 0 to bize()-1 */
	public default byte B(long bitIndex){
		return (byte)J(bitIndex+56);
	}
	
	/** byte at index 0 to bize()/8-1 */
	public default byte b(int index){
		return (byte)B(((long)index)<<3);
	}
	
	public default InputStream IN(long bitFrom, long bitToExcl){
		return new InputStream(){
			long pos = bitFrom;
			public int read(){
				if(pos < bitToExcl){
					int ret;
					if(pos+8 <= bitToExcl){
						ret = B(pos)&0xff;
					}else{ //last byte in this InputStream, and not byte aligned. Pad with 0s.
						int keepHighBitsOfByte = (int)(bitToExcl-pos);
						ret = ((B(pos)&0xff)>>keepHighBitsOfByte)<<keepHighBitsOfByte;
					}
					pos += 8;
					return ret;
				}else{
					return -1;
				}
			}
		};
	}

}
