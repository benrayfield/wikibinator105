/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;

/** TODO rewrite code and comments cuz copied alot from wikibinator104 to this wikibinator105. */
public class $λ<T extends λ>{
	
	public final long gas;
	
	/** null if didnt have enough gas to do the requested calculation. Any nonnegative amount of gas may be left. */
	public final T fn;
	
	public $λ(long gas, T fn){
		this.gas = gas;
		this.fn = fn;
	}
	
	public $λ e(T param){
		return fn.e(gas,param);
	}
	
	public $λ e(T... params){
		$λ x = this;
		for(T param : params){
			if(x.fn == null) return x;
			x = x.e(param);
		}
		return x;
	}
	
	/** keeps this.fn. Ignores myGas.fn. Adds both gas into ret.gas.
	These are immutable objects, so caller should stop using param after it being eaten.
	*/
	public $λ eat($λ myGas){
		return new $λ(gas+myGas.gas, fn);
		
		/*
		//FIXME if its a subclass, such as just a wrapper to avoid writing the generic, for a subinterface of λ,
		//then this cast might break. TODO override this func if so??
		return (T) new $λ(gas+myGas.gas, fn);
		*/
	}

}
