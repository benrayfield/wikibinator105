/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;
import java.util.function.BinaryOperator;

/** TODO rewrite code and comments cuz copied alot from wikibinator104 to this wikibinator105.
TODO rewrite the below text for wikibinator104 cuz was copied from wikibinator101...
<br><br>
Renamed Compiled to EvalerChain.
<br><br>
For any 2 fns x and y, an optimization of call x on y and return what that would return,
but potentially in a more optimized way, and in the worst case just do it in the slow interpreted way.
Every fn has a Compiled and that Compiled can be replaced by another Compiled
that remembers it in a linkedlist (Compiled.prev()), and when you use a Compiled,
you use Compiled.get() which gives you the nearest Compiled that is Compiled.on()
which you can choose to disable certain Compileds (which may be shared by multiple fns
such as a fn that does plus with a curried param of 3 vs a fn that does plus with a
curried param of 5, both might use a Compiled optimization of the plus function,
and if you want to compare the earlier Compileds or down to pure interpreted mode
to see if that optimization gives the exact same answer (a fn being a binary forest node)
then aFn.compiled().setOn(false) then aFn.e(20) -> 23, and anotherFn.e(20) -> 25,
and both aFn and anotherFn, if their .compiled() is that Compiled, have that optimization
turned on or off together at runtime.
<br><br>
This Compiled stuff is already working in some fork of occamsfuncer.
<br><br>
TODO this will be similar to Compiled.java in occamsfuncer,
which is a mutable linkedlist of Compiled instances which is called in fn.e(fn)
and is likely to use LazyBlob (of lazycl) and other optimizations,
else run in interpreted mode (the default Compiled) when no optimization is known.
<br><br>
As BinaryOperator<fn> its the same as UnaryOperator<fn> on cp(its 2 params),
without needing to create that object. Its call x on y -> z.
*/
public interface EvalerChain extends Evaler{
		
	/** If prev()==null then this must be on */
	public void setOn(boolean on);
	
	public boolean on();
	
	/** use this (linkedlists of Compiled each replacing the last) if this Compiled is not on(),
	which you can do to test it with and without various optimizations,
	which must all compute exactly the same thing.
	*/
	public EvalerChain prev();
	
	public default EvalerChain get(){
		return on() ? this : prev().get();
	}

}



