/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;
import java.util.EnumSet;
import java.util.Set;

public interface IdMaker_old_useFuncsDirectlyAsIdmaker{
	
	/** The first headerBize() of bize() bits are header, such as in the default kind of id512 its 128
	which is a HeaderBits long then a bize (of cbt) long.
	*
	public short headerBize();
	*/
	
	/** the func which makes the ids.
	TODO just call λ idMaker on λ whatsMyId to get λ theId,
	and use Evaler as compiled optimization such as derive sha3_256 or a 192 bit form of it
	and use that as part of an idMaker func.
	*/
	public λ fn();
	
	/** bize of Blobs returned */
	public default short bize(){
		return (short)idOfLeaf().bize();
	}
	
	//TODO is id always a powOf2 size without header
	//such as cbt512 as a bitstring is 513 bits so pads to 1024 (in abstract math but could still be stored in 512 bits)???
	//or should id be a bitstring (include padding)?
	
	public Blob idOfLeaf();
	
	public Blob idOfBits(Blob bits);
	
	/** Its an error for there to be more than 1 color, but there can be more to detect those errors aka BULL.
	If theres 0 colors, its UNKNOWN. At the lambda level theres always exactly 1 color.
	*/
	public Blob idOfCallPair(EnumSet<λColor> colors, Blob funcId, Blob paramId);
	
	public default Blob idOfCallPair(λColor color, Blob funcId, Blob paramId) {
		return idOfCallPair(color.set, funcId, paramId);
	}
	
	//public Blob idOfCallPair(Predicate<λColor> mapOfColorToBit, Blob funcId, Blob paramId);

}
