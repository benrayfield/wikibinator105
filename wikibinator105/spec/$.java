/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;

/** TODO rewrite code and comments cuz copied alot from wikibinator104 to this wikibinator105. */
public class $<T extends λ>{
	
	/** FIXME choose a design. If gas (remaining) is 0, does fn have to be null?
	If not, then could an infloop happen? Or would the loop body check if gas is at least 1
	before using fn? Everything must have a positive cost to avoid infloops. 
	*/
	public final long gas;
	
	/** null if didnt have enough gas to do the requested calculation. Any nonnegative amount of gas may be left. */
	public final T fn;
	
	//public static final $ empty = new $(0,null);
	
	public $(long gas, T fn){
		this.gas = gas;
		this.fn = fn;
	}
	
	public $ e(T param){
		return fn.e(gas,param);
	}
	
	public $ e(T... params){
		$ x = this;
		for(T param : params){
			if(x.fn == null) return x;
			x = x.e(param);
		}
		return x;
	}
	
	/** keeps this.fn. Ignores myGas.fn. Adds both gas into ret.gas.
	These are immutable objects, so caller should stop using param after it being eaten.
	*/
	public $ eat($ myGas){
		return new $(gas+myGas.gas, fn);
		
		/*
		//FIXME if its a subclass, such as just a wrapper to avoid writing the generic, for a subinterface of λ,
		//then this cast might break. TODO override this func if so??
		return (T) new $λ(gas+myGas.gas, fn);
		*/
	}

}

