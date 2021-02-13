/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impls.marklar105a.ids;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Predicate;

import wikibinator105.spec.*;

/** This will be the simplest s
<br><br>
This kind of id is a very inefficient but still secure prototype,
and you probably want to use MarklarId105b or some other kind of id instead.
You can use multiple id types at once or make more types.
<br><br>
OLD...
<br><br>
A MarklarId105a is 2565 0s if its leaf (MarklarId105a(leaf) = 256 0s),
else is of 2 childs x and y (each 256 bits), where x.l is its first 256 bits, and x.r is its second 256 bits:
MarklarId105a(x,y) = concat(
	sha3_256(concat( //without color
		sha3_256(MarklarId105a(x.l),MarklarId105a(y.l))
	))
	sha3_256(concat( //with color
		sha3_256(MarklarId105a(x),MarklarId105a(y))
	))
)
*/
public final class MarklarId105a /*implements IdMaker_old_useFuncsDirectlyAsIdmaker*/{
	
	public static final MarklarId105a instance = new MarklarId105a();

	/*public Blob idOfLeaf(){
		throw new RuntimeException("TODO");
	}

	public Blob idOfBits(Blob bits){
		throw new RuntimeException("TODO");
	}
	
	public Blob idOfCallPair(Blob funcId, Blob paramId){
		throw new RuntimeException("TODO");
	}

	/*public Blob idOfCallPair(EnumSet<λColor> colors, Blob funcId, Blob paramId){
		throw new RuntimeException("TODO");
	}*

	public λ fn(){
		throw new RuntimeException("TODO");
	}*/

}