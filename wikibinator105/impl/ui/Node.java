/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl.ui;
import java.util.*;

import wikibinator105.spec.λ;

public class Node{
	
	//public final long localId;
	
	/** display position */
	public double y, x;
	
	public Set<Edge> ins = new HashSet();
	
	public Set<Edge> outs = new HashSet();
	
	/** the function being displayed here, with colored edges between it and others such as a "left child" edge
	or a "what does it return when called on leaf" edge used with (lazig x y) and (ax (fpr edgeType from to)),
	and the kind of edge used with that is {I ,u} aka a func that calls its param on u
	such as (ax (fpr {I ,u} (lazig x y) whateverCallingXOnYReturns)).
	*/
	public final λ fn;
	
	public Node(λ fn){
		this.fn = fn;
	}

}
