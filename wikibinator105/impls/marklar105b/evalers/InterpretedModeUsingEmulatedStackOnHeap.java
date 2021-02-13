/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impls.marklar105b.evalers;
import static wikibinator105.impls.marklar105b.ImportStatic.*;

import wikibinator105.impls.marklar105b.fn;
import wikibinator105.spec.*;

/** This doesnt overflow java stack. This happens in bigO(1) size pieces.
TODO use Op.Ax to derive debugStepOver and debugStepInto etc first, and call them from here,
which can also be called from lambdas themself such as debugging a debugger.
*/
public class InterpretedModeUsingEmulatedStackOnHeap implements Evaler<fn>{
	
	public $<fn> Eval(long gas, fn func, fn z){
		throw new RuntimeException("TODO");
	}
	
	public $<fn> Wiki(long maxSpend, fn param){
		return WikiState.bestKnownApproximationOfSparseSubsetOfWiki.apply(maxSpend,param);
	}

}
