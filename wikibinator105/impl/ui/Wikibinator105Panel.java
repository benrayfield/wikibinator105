/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl.ui;
import wikibinator105.spec.*;
import java.awt.Graphics;
import java.util.*;
import javax.swing.JPanel;

public class Wikibinator105Panel extends JPanel{
	
	protected Map<λ,Integer> edgetypeToColorARGB = new HashMap();
	
	/** Each edge is a call of ax such as (ax (fpr {I ,u} (lazig x y) whateverCallingXOnYReturns)) which must be λColor.proof. 
	Only display these edges, which are already proven to exist, even if we know many other edges which are proven too,
	since theres only so much room on the screen and the space being viewed is infinite
	as it contains all possible turingComplete statements.
	For example, you might want to skip viewing the L childs in (S x y) aka ((S x) y) and instead
	display another edge type from (S x y) to x, without displaying the S, similar to the {x y} syntax means (S x y),
	and similar to [x y] means (pair x y).
	*
	protected Set<λ> edges = new HashSet();
	*/
	
	protected Set<Node> nodes = new HashSet();
	
	public void paint(Graphics g){
		throw new RuntimeException("TODO");
	}

}
