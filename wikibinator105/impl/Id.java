/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl;
import wikibinator105.spec.Blob;

/** 512 bit ids, which is a literal 512 bits if the first byte is NOT (byte)'\\',
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
*/
public final class Id implements Blob{
	
	TODO make this be a λ. Will λ be a Blob or have a func to return a Blob? Either way, this class can be both.
	
	private final long[] data;
	
	public Id(long... data){
		this.data = data;
		if(data.length != 8) throw new RuntimeException("Wrong size");
	}
	
	public byte firstByte(){
		return (byte)(data[0]>>>56);
	}
	
	/** If true, this is both the 512 bit content and the id of that content. The only other kinds
	of literal (that fit in an id) are cbt256 cbt128 cbt64 cbt32 cbt16 cbt8 cbt4 cbt2 cbt1 and leaf/u.
	Anything else is a call pair as usual.
	*/
	public boolean isLiteral512(){
		return firstByte()!='\\';
	}

}