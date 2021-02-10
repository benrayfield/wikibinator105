/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.impls.marklar105b;
import wikibinator105.spec.*;

/** The main object type in wikibinator105.impls.marklar105b. The long header is
a cache of what can be derived only from forest shape (L and R and Isleaf recursively),
and different implementations of wikibinator105 might want to use different kinds and sizes of cache,
so the core interface λ does not include that.
<br><br>
Since its deterministicly derived from forest shape, other id types could also include
a marklar105bHeader and just change the hash algorithm, for example.
<br><br>
For example, given a forest of marklar105a, which has no header and is just 256 bits hash
of its 2 childs (or all 0s for λ/leaf), a marklar105bHeader could be computed from
any such marklar105a id and all marklar105a ids below it down to leaf,
for average constant cost per node using a hashtable to map ids 1 to 1.
It could be mapped both directions.
It should work that way between any 2 kinds of ids of wikibinator105.
<br><br>
Ids should be wrapped in an Evaler.java attached to (by setCompiled(EvalerChain))
the derived lambda form (build it by calling λ on λ in various combos, then in VM setCompiled),
of the idMaker algorithms, such as marklarId105b and marklarId105a and
any other kind of merkle based ids people and AIs might create and find useful
in the same p2p network at runtime.
<br><br>
(idMaker x)->the_id_of_x,
forall function idMaker and forall function x to deterministicly create an id for.
*/
public interface fn extends λ<fn>{
	
	public long marklar105bHeader();

}