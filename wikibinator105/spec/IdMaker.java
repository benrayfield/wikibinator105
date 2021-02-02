package wikibinator105.spec;
import java.util.function.Predicate;

public interface IdMaker{
	
	//TODO is id always a powOf2 size without header
	//such as cbt512 as a bitstring is 513 bits so pads to 1024 (in abstract math but could still be stored in 512 bits)???
	//or should id be a bitstring (include padding)?
	
	public Blob idOfLeaf();
	
	public Blob idOfBits(Blob bits);
	
	public Blob idOfCallPair(Predicate<λColor> mapOfColorToBit, Blob funcId, Blob paramId);

}
