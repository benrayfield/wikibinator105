/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl.ids;
import java.util.function.Predicate;

import wikibinator105.spec.*;

/** The default kind of id of a wikibinator105 node. More kinds of ids can be created at runtime,
as any lambda that returns a cbt512 or cbt256 or cbt1024 etc when called on another lambda to create the id of,
but this kind is just to get started. I'm a little concerned about it only having 96 bits of collision security,
(UPDATE: todo could raise that to 112 bits (2 of 224 bit hashes)
since they can share a long header, instead of 2 longs??? but might want that for extra bize precision, num of curries, etc)
and ids can be created for other purposes such as to optimize its alignment to specific other systems,
and use multiple kinds of ids together at once, matching them together.
Ids are global (whole internet) and sync with zero knowledge instantly,
and they're lazyEvaled so you dont even need to create the 512 bits just to use a node as a binary forest shape.
<br><br>
512 bit ids, which is a literal 512 bits if the first byte is NOT (byte)'\\',
so 255/256 of all literal 512 bits fit in 512 bits,
else the first half is a binary forest node with color, and second half
is that same shape of binary forest node without color aka the normed form
for matching it to compare colors of the same node and OR them together as bloomfilter.
<br><br>
Collision resistance is 96 bits of security, that if you store 2^96 (79228162514264337593543950336)
of them you will find about 1 collision,
cuz it contains 2 related 192 bit hashes, or literals that fit in id.
<br><br>
The first 256 bits are for binary forest node with color, and the second 256 bits are that same node without color,
to match it and consider different combos of color and OR them together into bloomfilter (a node is a bloomfilter),
unless they're a literal in which case the 512 bits mean something else.
<br><br>
TODO choose order of the 2 halfs...
Its better for it to be hard to find something with the same prefix as another call pair,
so put the one with color first, cuz treemaps are a little more efficient the shorter the shared prefix.
On the other hand, sorting by the one without color puts those that need to be color-ORed together right beside eachother.
<br><br>
About the name of this kind of id...
'The Marklar are an intelligent species of identical aliens who refer to all people, places,
and things as "Marklar". Despite being almost identical and all having the same name, they are still
somehow able to tell each other apart and address each other as Marklar without ambiguity.'
-- https://southpark.fandom.com/wiki/Marklar
It seems similar to how a universal function works. Theres only 1 word but you can say anything as combos of it.  
*/
public final class MarklarId105b implements IdMaker{
	
	public static final MarklarId105b instance = new MarklarId105b();
	
	public static byte firstByte(Blob id){
		throw new RuntimeException("TODO");
		//return (byte)(data[0]>>>56);
	}
	
	/** If true, this is both the 512 bit content and the id of that content. The only other kinds
	of literal (that fit in an id) are cbt256 cbt128 cbt64 cbt32 cbt16 cbt8 cbt4 cbt2 cbt1 and leaf/u.
	Anything else is a call pair as usual.
	*/
	public static boolean isLiteral512(Blob id){
		return firstByte(id)!='\\';
	}

	public Blob idOfLeaf(){
		throw new RuntimeException("TODO");
	}

	public Blob idOfBits(Blob bits){
		throw new RuntimeException("TODO");
	}

	/** boolean[λColor.values().length] and id512 and id512 -> id512 of the parent of those */
	public Blob idOfCallPair(Predicate<λColor> mapOfColorToBit, Blob funcId, Blob paramId){
		throw new RuntimeException("TODO");
	}

}