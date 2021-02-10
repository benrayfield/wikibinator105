package wikibinator105.plugins.spreadsheet;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import javax.swing.JTable;

import wikibinator105.spec.λ;

/** Each cell is proven by (Op.ax λ (Op.fpr columnX rowY valueZ)) aka (axA (fpr func param return)),
or disproven by Op.axB similarly, but if it doesnt halt then it can not be proven nor disproven
at the lambda level but possibly could at the nsat level (if you have infinite time and memory)
or in some cases proven not to halt in finite time and memory such as (S I I (S I I)) doesnt halt.
<br><br>
columnX.node.e(maxSpend,rowY.node).fn returns value at columnX and rowY,
or returns null if maxSpend is not enough to call the column on the row,
and func_param_return cache entries only pay that cost the first time
and are nearly free to get from cache after that UNLESS its uncached later
to make room for other cache entries (so deterministicly compute it again, and cache it),
and cache entries will normally be removed in order of least recently used.
<br><br>
Display will be by typeval (see comments in Op.java)
such as "text/plain", "image/jpeg", "double[]", "double", etc,
for both writing display pixels and editing of function outputs
such as if the identityFunc column of it is a linkedlist of 5 things
and 5 other columns are to get each of those 5 things
(if it happens to be such a list, else to get some constant),
then maybe you could edit those 5 columns which causes editing of the identityFunc column,
which is not actually editing the contents of that row
but instead is navigating, in the space of all possible rows,
to a row whose identityFunc column says it is already that value,
as the space of all possibilities is constant (other than wiki function,
but its literally true in the CLEAN space, which the DIRTY space is layered on top of).
<br><br>
TODO custom cell editors viewers
https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
https://docs.oracle.com/javase/8/docs/api/javax/swing/JTable.html
<br><br>
TODO reorder columns and rows, as theres an infinite number of each.
<br><br>
TODO 4 columns are required and you start with them: L R Isleaf IdentityFunc.
*/
public class SpreadsheetJTable extends JTable{
	
	protected List<Column> cols = new ArrayList();
	
	protected List<Row> rows = new ArrayList();
	
	protected WeakHashMap<λ,Val> cacheFuncToVal = new WeakHashMap();
	
	public Val val(Column col, Row row){
		throw new RuntimeException("TODO use columnX.node.e(maxSpend,rowY.node).fn returns value at columnX and rowY, and maxSpend");
	}

}
