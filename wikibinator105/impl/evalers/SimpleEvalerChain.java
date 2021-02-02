/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impl.evalers;
import wikibinator105.spec.*;

public class SimpleEvalerChain implements EvalerChain{
	
	protected boolean on = true;
	
	public final EvalerChain prevOrNull;
	
	public final Evaler wrapMe;
	
	public SimpleEvalerChain(Evaler wrapMe){
		this(wrapMe, null);
	}
	
	public SimpleEvalerChain(Evaler wrapMe, EvalerChain prevOrNull){
		this.wrapMe = wrapMe;
		this.prevOrNull = prevOrNull;
	}

	/** todo optimize by extending SimpleCompiled directly and overriding this apply func, consistent with setOn and prev etc? */
	public $λ eval(long maxSpend, λ func, λ param){
		return (on?wrapMe:prevOrNull).eval(maxSpend, func, param);
	}

	public void setOn(boolean on){
		if(!on && prevOrNull == null) throw new RuntimeException(
			"Cant turn off the deepest Compiled whose prev()==null cuz that would change its behaviors to have no implementation of lambda math at all. this="+this);
		this.on = on;
	}

	public boolean on(){
		return on;
	}

	public EvalerChain prev(){
		return prevOrNull;
	}

}
