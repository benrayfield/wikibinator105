/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl.nodes;
import static wikibinator105.impl.ImportStatic.*;

import wikibinator105.impl.Λ;
import wikibinator105.impl.evalers.InterpretedMode;
import wikibinator105.spec.*;

public abstract class AbstractΛ implements Λ{
	
	/** EvalerChain should start as some interpreted mode, such as InterpretedMode.evalerChain,
	even if you have a more optimized form ready to use now, use setCompiled(EvalerChain)
	so EvalerChain.prev() is InterpretedMode.evalerChain for example.
	*/
	protected EvalerChain chain = InterpretedMode.chain;
	
	public Λ p(Λ r){
		return cp(this,r);
	}

	public Λ e(Λ r){
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

}
