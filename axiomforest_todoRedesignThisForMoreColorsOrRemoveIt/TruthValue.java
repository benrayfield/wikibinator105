/** Ben F Rayfield offers this software opensource MIT license */
package axiomforest_todoRedesignThisForMoreColorsOrRemoveIt;
//import wikibinator100.impl.Bull;
//import wikibinator100.impl.Unknown;

/** UPDATE: 2-way forest instead of 3-way, cuz I thought of a way
to put the bloom filter inside the 6 param wikibinator function,
so any other systems can still go in the same bloomfilter.
Will still sometimes include 3 childs (instead of 4) cuz of including superposition()
which is a cache and superposition().allUnknown() is true. superposition() can be derived from binary forest.
<br><br>
Unknown is the default answer for every question,
which may become yes or no but if it becomes both at once
(2 different paths of figuring things out, one leads to yes and one leads to no),
it is therefore proven that something in that process is bull.
Anything derived from bull is bull, like a "loaded question"
is a bull statement followed by a question whose answer
does not depend on that statement, so in that case any answer
to the "loaded question" implies the bull statement is non-bull,
therefore the set of those things is bull even if not everything in the set is bull.
<br><br>
If you want more digits of precision than just these 2 bits,
such as OpenCOG's TruthValue has 2 fractions of CHANCE and CERTAINTY... Dont do that,
but use 2 numbers that are CertaintyOfYes and CertaintyOfNo,
where CertaintyOfYes+CertaintyOfNo<=1 and each are in range 0 to 1,
and BULL happens when their sum > 1,
or beyond that you could use Bayes Rule andOr other statistics on combos of many possible statements,
but I'm guessing that the Verse datastruct will handle that most efficiently,
maybe with a little neuralnets, bayesnets, and other optimizations used where they help.
*/
public enum TruthValue{
	
	/** 10 */
	yes(true,false),
	
	/** 01 */
	no(false,true),
	
	/** 00, usually sparsely stored by not storing it at all, the default value for anything no statement is claimed about. */
	unknown(false,false),
	
	/** 11 (simultaneous yes and no) disproofByContradiction.
	There are no set of non-bull statements which together generate bull.
	<br><br>
	Superposition is not bull since a nondeterministic turing machine can be emulated by a deterministic turing machine, for example.
	*/
	bull(true,true);

	public TruthValue join(TruthValue x){
		switch(x){
		case yes: case no: return (this==unknown || this==x) ? x : bull;
		case unknown: return this;
		case bull: return bull;
		default: throw new RuntimeException("This cant happen. Why is eclipse making me put this here?");
		}
	}
	
	public TruthValue not(){
		switch(this){
		case yes: return no;
		case no: return yes;
		default: return this;
		}
	}
	
	/** observe yes or no, else throw Unknown or Bull *
	public boolean z() throws Bull, Unknown{
		switch(this){
		case unknown: throw Unknown.instance;
		case no: return false;
		case yes: return true;
		case bull: throw Bull.instance;
		default: throw new RuntimeException("This cant happen. Why is eclipse making me put this here?"); 
		}
	}*/
	
	//public final double certaintyOfYes, certaintyOfNo;
	public final boolean y, n;
	
	private TruthValue(boolean certaintyOfYes, boolean certaintyOfNo){
		this.y = certaintyOfYes;
		this.n = certaintyOfNo;
	}

}
