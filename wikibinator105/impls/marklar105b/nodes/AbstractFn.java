/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impls.marklar105b.nodes;
import static wikibinator105.impls.marklar105b.ImportStatic.*;
import wikibinator105.impls.marklar105b.fn;
import wikibinator105.impls.marklar105b.evalers.InterpretedModeUsingJavaStack;
import wikibinator105.impls.marklar105b.ids.MarklarId105b;
import wikibinator105.spec.*;

public abstract class AbstractFn implements fn{
	
	/** EvalerChain should start as some interpreted mode, such as InterpretedMode.evalerChain,
	even if you have a more optimized form ready to use now, use setCompiled(EvalerChain)
	so EvalerChain.prev() is InterpretedMode.evalerChain for example.
	*/
	protected EvalerChain chain = InterpretedModeUsingJavaStack.chain;
	
	public fn p(fn r){
		return cp(this,r);
	}

	public fn e(fn r){
		//FIXME what if .fn is null even with gas being the max possible?
		//It might take a long time to use that much gas, depending how the cost of compute resources is defined.
		return e(Long.MAX_VALUE, r).fn;
	}
	
	public EvalerChain compiled(){
		return chain;
	}
	
	public void setCompiled(EvalerChain c){
		chain = c;
	}
	

	public boolean containsAxOf2Params(){
		return MarklarId105b.containsAxof2params(marklar105bHeader()) ;
	}

}
