/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl;
import static wikibinator104.impl.ImportStatic.*;
import wikibinator104.spec.*;

public class Leaf extends Abstractλ{
	
	/** leaf.l is identityFunc. leaf.r is leaf. Derive leafsByte from that.
	TODO choose design of bigEndian of littleEndian.
	*/
	public static final byte isLeafsByte = 0; //FIXME
	
	public static final λ instance = new Leaf();

	/** true, this is the leaf */
	public boolean a(){ return true; }

	public λ l(){ return i; }

	public λ r(){ return this; }

}
