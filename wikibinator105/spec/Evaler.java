/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;

public interface Evaler<T extends λ>{
	
	public $<T> Eval(long maxSpend, T func, T param);
	
	/** same as these 3 evals, ((funcA param) (funcB param)) aka (S funcA funcB param), aka Op.Trecurse,
	but may differ in number of threads or which gets how much gas,
	such as in some implementations it might allow up to half the gas to
	each of (funcA param) and up to half the gas to (funcB param)
	and if both of those finish and if there is any gas left then
	evals what one returns on what the other returns.
	<br><br>
	The default implementation (in Evaler interface) just does it single threaded.
	<br><br>
	If you want more threads, use Op.Trecurse and Op.Pair together in various combos,
	like if you want the 4 calls (a b) and (c d) and (e f) and (g h),
	then you might want (pair (pair (a b) (c d)) (pair (e f) (g h))) aka [[(a b) (c d)][(e f) (g h)]],
	so you could eval ({,pair {,pair {,a ,b} {,c ,d}} {,pair {,e ,f} {,g ,h}}} u)
	which evals to that, but before that last u param
	its halted (a derived kind of lazyeval, not lazy at VM level).
	If you eval that, then in some kinds of Evaler
	(TODO create InterpretedModeUsingJavaStack.multiThreadedInstance?),
	when it gets to Op.treecurse or Op.Treecurse in eval(long,λ,λ), it would call fork(long,λ,λ,λ),
	which would recursively call fork, and so on,
	BUT if it just divides the gas in half each time then that would likely run out of gas in some parts
	and have more than enough in other parts but since its done in parallel
	some parts would fail and some would succeed so the whole calculation in total would fail
	even though if it had been done sequentially it could share gas between those calls
	so if some use less and some use more but in total theres enough, in any combo of that, it would work.
	FIXME design some way for a parallel calculation to wait on other parallel calculations
	to finish with gas left over, instead of failing, wait for the more gas to come in,
	or something like that.
	*/
	public default $<T> Fork(long maxSpend, T funcA, T funcB, T param){
		//single threaded
		$<T> ap = funcA.e(maxSpend,param);
		if(ap.fn == null) return ap;
		$<T> bp = funcB.e(ap.gas,param);
		if(bp.fn == null) return bp;
		return ap.fn.e(bp.gas,bp.fn);
	}
	
	/** See Op.Wiki. All nondeterminism goes here, and in dirty ops (start with capital letter like Op.Trecurse vs Op.trecurse)
	which can call this Op.Wiki without truncateToClean happening automatically (cuz clean can only see and create clean).
	*/
	public $<T> Wiki(long maxSpend, T param);

}