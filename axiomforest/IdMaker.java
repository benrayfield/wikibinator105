/** Ben F Rayfield offers this software opensource MIT license */
package axiomforest;

public interface IdMaker{
	
	/** these are normally 256 bits each. The id of leaf is some constant.
	Normally will use (short)HeaderBits.headerOfLeaf(TruthValue) and 30 bytes of some constant.
	Maybe last bit is always 1 so is a bitstring size 255 with 1 bit of padding?
	*/
	public λ idOfLeaf(TruthValue tv);
	
	/** hese are normally 256 bits each. this may be all 0s. The third child of a norm is norm of the norm,
	but we cant include x in the content to hash to get x, so use idOfNull to mean "norm of norm" is itself.
	Normally will use (short)HeaderBits.headerOfNull() and 30 bytes of some constant.
	Maybe last bit is always 1 so is a bitstring size 255 with 1 bit of padding?
	*/
	public λ idOfNull();
	
	/** these are normally 256 bits each. normOfParent is idOfNorm(leftId,rightId).
	Normally will use (short)HeaderBits.header(TruthValue,short,short) and 30 bytes of some kind of hash.
	Maybe last bit is always 1 so is a bitstring size 255 with 1 bit of padding?
	*/
	public λ id(TruthValue tv, λ leftId, λ rightId, λ normOfParent);
	
	/** these are normally 256 bits each.
	Normally will use (short)HeaderBits.header(TruthValue,short,short) and 30 bytes of hash.
	Maybe last bit is always 1 so is a bitstring size 255 with 1 bit of padding?
	*/
	public λ idOfNorm(λ leftId, λ rightId);

}