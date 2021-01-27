/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;

/** TODO rewrite code and comments cuz copied alot from wikibinator104 to this wikibinator105. */
public final class $λ{
	
	public final long gas;
	
	/** null if didnt have enough gas to do the requested calculation. Any nonnegative amount of gas may be left. */
	public final λ fn;
	
	public $λ(long gas, λ fn){
		this.gas = gas;
		this.fn = fn;
	}
	
	public $λ e(λ param){
		return fn.e(gas,param);
	}
	
	public $λ e(λ... params){
		$λ x = this;
		for(λ param : params){
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
	}

}
