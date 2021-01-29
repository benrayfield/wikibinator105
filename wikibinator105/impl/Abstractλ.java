/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl;
import static wikibinator104.impl.ImportStatic.*;
import wikibinator104.spec.*;

public abstract class Abstractλ implements λ{
	
	/** EvalerChain should start as some interpreted mode, such as InterpretedMode.evalerChain,
	even if you have a more optimized form ready to use now, use setCompiled(EvalerChain)
	so EvalerChain.prev() is InterpretedMode.evalerChain for example.
	*/
	protected EvalerChain chain = InterpretedMode.chain;
	
	public λ p(λ r){
		return cp(this,r);
	}

	public λ e(λ r){
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
