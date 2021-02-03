/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl.ids;
import java.util.function.Predicate;

import wikibinator105.spec.*;

/** This kind of id is a very inefficient but still secure prototype,
and you probably want to use MarklarId105b or some other kind of id instead.
You can use multiple id types at once or make more types.
<br><br>
A MarklarId105a is 512 0s if its leaf (MarklarId105a(leaf) = 512 0s),
else is of 2 childs x and y (each 512 bits), where x.l is its first 256 bits, and x.r is its second 256 bits:
MarklarId105a(x,y) = concat(
	sha3_256(concat( //without color
		sha3_256(MarklarId105a(x.l),MarklarId105a(y.l))
	))
	sha3_256(concat( //with color
		sha3_256(MarklarId105a(x),MarklarId105a(y))
	))
)
FIXME need to put in check for BULL and place to write color (1 of 4 colors, need 4 bits each for bloomfilter).
Remember, this is not mean to be efficient, just to show the simplest kind of id thats also secure.
*/
public final class MarklarId105a implements IdMaker{
	
	public static final MarklarId105a instance = new MarklarId105a();

	public Blob idOfLeaf(){
		throw new RuntimeException("TODO");
	}

	public Blob idOfBits(Blob bits){
		throw new RuntimeException("TODO");
	}

	public Blob idOfCallPair(Predicate<λColor> mapOfColorToBit, Blob funcId, Blob paramId){
		throw new RuntimeException("TODO");
	}

}