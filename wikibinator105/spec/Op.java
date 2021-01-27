/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;


/** TODO rewrite disorganized text...
<br><br>
<br><br>

use ax as typeval where func is recog of that type and ret of leaf is true. create optimization to check for something like (igfp "double[]" (t u)) as tupe, to not have to check constraints where its certain it always returns u.
..
change ax so...
(ax ret func param x) -> (param x),
aka (ax u type instance) is a typed instance, and (ax u type instance x) -> (instance x).

put typeval as first param of infcur is "contenttype" or maybe first param is always viewed as a utf8 string bit some types are more generally datastructs?
...
or just put typeval in the space gained by 0 and 1 having longer prefix?
..
no, cuz then lose infcur.
..
i want typeval to have exactly 2 halted params so acts like pair. can do that with curry or a prefix of pair.
..
could use ax for typeval, viewing a type as a recogfunc, and a type can be designed to contain a string and recog all possible params, like type = (t u) returns u for all possible types. (ax u (t u) instance). (igfp "double[]" (t u)) also rets u for all possible params. (ax uu ) or (ax anythingexceptu) means its not that type, but never halting also means that.
<br><br>

ukΩuuul?
ukΩuuur?
ukΩuu∩a?
ukΩuu∩w?
ukΩu∩t??
ukΩu∩f??
ukΩ∩s???
ukΩ∩p???
ukƱuuu0
ukƱuuu1
ukƱuu∞
ukƱuc??
ukƱx???


---

<br><br>
<br><br>
<br><br>

Most of the opcodes in wikibinator104 will be kept but move ax and curry,
and the first 2 params choose clean/dirty and isStrange.
Strange ops are vararg andOr eval on multiple numbers of args. Nonstrange ops all have the same number of curries.
This will make it easier to dovetail and emulate itself etc.
I'm abbreving each op and some things that arent ops (param indexs in the op prefixs) with 1 char each
so I can organize them. This should be viewed in any monospaced font.

x - (ax ret func param ignore)
c - (curry ,,,u comment funcbody)
//∞ - infcur
0 - 0cbtofsamesizeonlyElseReplaceParamWithSelfAndBeHalted
1 - 1cbtofsamesizeonlyElseReplaceParamWithSelfAndBeHalted
//2 - iscbt
s
t
f - fi
l
r
a - isleaf
p - pair
v - typeval
//e - cleancall
w - wiki

u - the leaf aka the universal function.
i - identityFunc, derived as (f u).
k - clean: first bit thats leaf for clean, any nonleaf for dirty.
ʞ - dirty: first bit thats leaf for clean, any nonleaf for dirty.
Ω - not isStrange
Ʊ - isStrainge
∩ - anything except u
. - u or ∩
? - anything, param of an op
_ - ignore


ukΩ...l?
ukΩ...r?
ukΩ...a?
ukΩ...w?
ukΩ..t??
ukΩ..f??
//ukΩ..e??
ukΩ.s???
ukΩ.p???
//ukΩ.v???
ukƱ..0?......
ukƱ..1?......
ukƱ.c??......
ukƱx???.

Remove e/cleancall and derive it as some clean func of λfunc.λparam.(func param). Dirty can call that on 2 dirty params and they'll automatically become clean,
but will still have to derive func to recursively truncate its param to clean by forkEditing its first param, then of course Evaler.java optimize those. It will be near equally fast as if it was an op, so no problem.

ukΩ...l?
ukΩ...r?
ukΩ...a?
ukΩ...w?
ukΩ..t??
ukΩ..f??
ukΩ.s???
ukΩ.p???
ukƱ..0(1 0)0001(1(0 0))
ukƱ..1(1 0)0001(1(0 0))
ukƱ.c //next param is unaryNum, and any params after that are ok to hang off the end as its vararg
ukƱx???

But how to replace v/typeval? If I want to keep the Ω/normal at max 6 params when halted (eval on 7)
and the Ʊ as storing enough in them to know [their num of curries and to know ret_func_param cache] in 6 params,
then need to either accept the lack of typeval (which I'm not going to accept, it has to go somewhere)
or find a place to fit it in...

If theres a comment param, I could put typeval in pair. but comment maybe should just go in params of c/curry???

If comment is only in c/curry, then could still use the opbit just before p/pair as a semantic, that it has to be some nonleaf
to mean pair, and could use 2 different nonleafs for pair vs typeval, but I dont want to complicate it.

Could put typeval in a curry whose comment says "typeval".

Unrelated to typeval, but I might want 0 and 1 to come earlier, considering that there is ONLY a clean form of them...


Do the ops fit....

ukΩuuul?
ukΩuuur?
ukΩuu∩a?
ukΩuu∩w?
ukΩu∩t??
ukΩu∩f??
ukΩ∩s???
ukΩ∩p???
ukƱuu0(1 0)0001(1(0 0))
ukƱuu1(1 0)0001(1(0 0))
ukƱuc //next param is unaryNum, and any params after that are ok to hang off the end as its vararg
ukƱx???

Yes, the ops fit, but I want a typeval op (and am planning to derive cleancall).
Ok, derive typeval in c/curry.



ukΩuuul?
ukΩuuur?
ukΩuu∩a?
ukΩuu∩w?
ukΩu∩t??
ukΩu∩f??
ukΩ∩s???
ukΩ∩p???
ukƱuu0(1 0)0001(1(0 0))
ukƱuu1(1 0)0001(1(0 0))
ukƱuc //next param is unaryNum, and any params after that are ok to hang off the end as its vararg
ukƱx???


/ - comment param

ukΩuuul/?
ukΩuuur/?
ukΩuu∩a/?
ukΩuu∩w/?
ukΩu∩t/??
ukΩu∩f/??
ukΩ∩s/??? 
ukΩ∩p/??? //comment of pair tells if its typeval or not, by comment being a certain constant?
ukƱuu0/(1 0)0001(1(0 0))
ukƱuu1/(1 0)0001(1(0 0))
ukƱuc/??? //unaryNum funcBody, and any params after that are ok to hang off the end as its vararg
ukƱx/????

Some things naturally should not have comments, like 0 and 1 and cbts of them as its just a blob and should be optimized that way,
and x/ax is for proofs or cache about ret<-(func param),
and w/wiki is where all nondeterminism goes and should be a func of 1 param instead of 2 including comment.


ukΩuuul?
ukΩuuur?
ukΩuu∩a?
ukΩuu∩w?
ukΩu∩t??
ukΩu∩f??
ukΩ∩s???
ukΩ∩p???
ukƱuu0(1 0)0001(1(0 0))
ukƱuu1(1 0)0001(1(0 0))
ukƱuc //c itself is unaryNum at least 1 (,u), and any params after that are ok to hang off the end as its vararg
ukƱx???

If c is replaced by a unaryNumber such as (t (t (t u))) aka ,,,u for 3u, and never use u in its param (as that means 0 or 1)
then can fit 1 more param...

u k Ʊ u u 0 (1 0)0001(1(0 0))
u k Ʊ u u 1 (1 0)0001(1(0 0))
u k Ʊ u 3u "fibonacci" {funcbody} ...params...
u k Ʊ x_as_u ret func param ignore

Theres room for more ops as 0 and 1 could each lose a param, but that wouldnt fit well with Ω vs Ʊ,
and it does leave room for cbt2 in the first 6 params being halted, which might be useful for truthvalue being part of opByte.

Cleancall will be derived.

Where does typeval go?


Or maybe put typeval in ∞/infcur?

ukΩuuul?
ukΩuuur?
ukΩuu∩a?
ukΩuu∩w?
ukΩu∩t??
ukΩu∩f??
ukΩ∩s???
ukΩ∩p???
ukƱuuu0(1 0)0001(1(0 0))
ukƱuuu1(1 0)0001(1(0 0))
ukƱuu∞ //(∞ type) fits in the 6 params, but val is first to push into infcur range aka normally things halt
ukƱuc //c itself is unaryNum at least 1 (,u), and any params after that are ok to hang off the end as its vararg
ukƱx???

c/curry and ∞/infcur are both same kind of thing IF you view [0u (unaryNum0) aka u] as infinite number of curries.
So put those together???
No c lacks enuf params for that.

use ax as typeval where func is recog of that type and ret of leaf is true. create optimization to check for something like (igfp "double[]" (t u)) as tupe, to not have to check constraints where its certain it always returns u.
..
change ax so...
(ax ret func param x) -> (param x),
aka (ax u type instance) is a typed instance, and (ax u type instance x) -> (instance x).

put typeval as first param of infcur is "contenttype" or maybe first param is always viewed as a utf8 string bit some types are more generally datastructs?
...
or just put typeval in the space gained by 0 and 1 having longer prefix?
..
no, cuz then lose infcur.
..
i want typeval to have exactly 2 halted params so acts like pair. can do that with curry or a prefix of pair.
..
could use ax for typeval, viewing a type as a recogfunc, and a type can be designed to contain a string and recog all possible params, like type = (t u) returns u for all possible types. (ax u (t u) instance). (igfp "double[]" (t u)) also rets u for all possible params. (ax uu ) or (ax anythingexceptu) means its not that type, but never halting also means that.

ukΩuuul?
ukΩuuur?
ukΩuu∩a?
ukΩuu∩w?
ukΩu∩t??
ukΩu∩f??
ukΩ∩s???
ukΩ∩p???
ukƱuuu0
ukƱuuu1
ukƱuu∞
ukƱuc??
ukƱx???

FIXME i want c and ∞ to align at the same opbit prefix len.

ukΩuuul?
ukΩuuur?
ukΩuu∩a?
ukΩuu∩w?
ukΩu∩t??
ukΩu∩f??
ukΩ∩s???
ukΩ∩p???
ukƱuuu0
ukƱuuu1
ukƱu∞    FIXME this conflicts with ukƱuu since the last opbit of ∞ is u and thats a prefix of 0 and 1.
ukƱuc??
ukƱx???


ukΩuuul?
ukΩuuur?
ukΩuu∩a?
ukΩuu∩w?
ukΩu∩t??
ukΩu∩f??
ukΩ∩s???
ukΩ∩p???
ukƱuuu0
ukƱuuu1
ukƱu∩∞ 
ukƱu∩c? FIXME i want 2 curries after c to fit in the first 6 curries, those being comment and funcbody.
ukƱx???

ukƱu∩∞ 
*/
public enum Op{
	
	/** not used in the wikibinator105 prototype since everything is either halted or evaling on java stack,
	but other implementations might use this for call pairs that are not halted.
	TODO copy comments from wikibinator104 and maybe modify them.
	*/
	deepLazy("",true,false,0),
	
	/** TODO copy comments from wikibinator104 and maybe modify them. */
	getFunc("uΩuuur",false,false,1),
	GetFunc("∩Ωuuur",false,false,1),
	
	/** TODO copy comments from wikibinator104 and maybe modify them. */
	getParam("uΩuu∩l",false,false,1),
	GetParam("∩Ωuu∩l",false,false,1),
	
	/** TODO copy comments from wikibinator104 and maybe modify them. */
	isLeaf("uΩuu∩a",false,false,1),
	IsLeaf("∩Ωuu∩a",false,false,1),
	
	/** TODO copy comments from wikibinator104 and maybe modify them. */
	wiki("uΩuu∩w",false,false,1),
	Wiki("∩Ωuu∩w",false,false,1),
	
	/** TODO copy comments from wikibinator104 and maybe modify them. */
	tru("uΩu∩t",false,false,2),
	Tru("∩Ωu∩t",false,false,2),
	
	/** TODO copy comments from wikibinator104 and maybe modify them. */
	fal("uΩu∩f",false,false,2),
	Fal("∩Ωu∩f",false,false,2),
	
	/** aka the S lambda of SKI calculus. "tree recurse".
	TODO copy comments from wikibinator104 and maybe modify them.
	*/
	trecurse("uΩ∩s",false,false,3),
	Trecurse("∩Ω∩s",false,false,3),
	
	/** TODO copy comments from wikibinator104 and maybe modify them. */
	pair("uΩ∩p",false,false,3),
	Pair("∩Ω∩p",false,false,3),
	
	/** is a cbt. can only be part of cbt. if its param is not a cbt of same size, then calls itself on itself instead,
	so a cbt called on anything is always a cbt twice as big. Avoids the need for pairs in cbts so is more efficient.
	TODO copy comments from wikibinator104 and maybe modify them.
	*/
	zero("uƱuuu0",false,true,0),
	
	/** is a cbt. can only be part of cbt. if its param is not a cbt of same size, then calls itself on itself instead,
	so a cbt called on anything is always a cbt twice as big. Avoids the need for pairs in cbts so is more efficient.
	TODO copy comments from wikibinator104 and maybe modify them.
	*/
	one("uƱuuu1",false,true,0),
	
	/** waiting for infinity curries, never evals just keeps accumulating params. A list of anything you want,
	without the inefficiency of using ((pair x) y). Just call it on y without the pair.
	No funcbody or unaryNumber of curries left to cache. Just create a halted callpair for each next curry.
	TODO copy comments from wikibinator104 and maybe modify them.
	*/
	infcur("uƱuu∞",false,true,0),
	Infcur("∩Ʊuu∞",false,true,0),
	
	/** (curry unaryNum comment funcbody ...params...)
	or TODO choose a design, maybe unaryNum other than 0 (u) is the curry op itself?
	Avoids the need for pairs, except just 1 pair when it evals,
	compared to wikibinator104 uses a pair for every curry, and calls curry and a unary number on it,
	and this will instead cache the unaryNumber (thats deep inside, near funcBody) similar to caching funcBody.
	TODO copy comments from wikibinator104 and maybe modify them.
	*/
	curry("uƱuc",false,true,0),
	Curry("∩Ʊuc",false,true,0),
	
	/** (ax ret func param) is halted IFF ret<-(func param),
	and (ax ret func param x) -> (param x),
	which will be used as a replacement for TYPEVAL as in
	(ax u type instance) is a typed instance,
	and (ax u type instance x) -> (instance x) aka do the same thing the instance would do
	other than reflection ops (getFunc/l getParam/r isLeaf/a) can still see inside it.
	TODO copy comments from wikibinator104 and maybe modify them.
	<br><br>
	This partly explains it, but needs rewriting and was before i decided what to do...
	<br><br>
	use ax as typeval where func is recog of that type and ret of leaf is true.
	create optimization to check for something like (igfp "double[]" (t u)) as tupe,
	to not have to check constraints where its certain it always returns u.
	..
	change ax so...
	(ax ret func param x) -> (param x),
	aka (ax u type instance) is a typed instance, and (ax u type instance x) -> (instance x).
	
	put typeval as first param of infcur is "contenttype" or maybe first param is always
	viewed as a utf8 string bit some types are more generally datastructs?
	...
	or just put typeval in the space gained by 0 and 1 having longer prefix?
	..
	no, cuz then lose infcur.
	..
	i want typeval to have exactly 2 halted params so acts like pair. can do that
	with curry or a prefix of pair.
	..
	could use ax for typeval, viewing a type as a recogfunc, and a type can be designed to contain
	a string and recog all possible params, like type = (t u) returns u for all possible types.
	(ax u (t u) instance). (igfp "double[]" (t u)) also rets u for all possible params. (ax uu )
	or (ax anythingexceptu) means its not that type, but never halting also means that.
	*/
	ax("uƱx",false,true,4),
	Ax("∩Ʊx",false,true,4);
	
	/** after (u u) aka clean or (u anything_except_u) aka dirty,
	this is "u" for leaf vs "∩" for anything except leaf, which is a binary prefix for each op.
	*/
	public final String prefix;
	
	public final boolean isAlwaysEvaling;
	
	public final boolean isStrange;
	
	/** is 0 if isStrange, cuz number of curries isnt a single number,
	may be vararg or may eval at 2 specific numbers of curries.
	*/
	public final int curriesElse0;
	
	private Op(String prefix, boolean isAlwaysEvaling, boolean isStrange, int curriesElse0){
		this.isAlwaysEvaling = isAlwaysEvaling;
		this.isStrange = isStrange;
		this.curriesElse0 = curriesElse0;
	}
	
	FIXME change all strings to only use those 2 chars instead of the more chars I used while designing it,
	or actually write it like "(λ λ (λ λ) λ)" etc of how to write the normed form of the op in the default syntax,
	therefore it will need twice as many ops (except Op.deepLazy and maybe Op.one and Op.zero).
	FIXME choose between Op.curry being a unaryNumber (other than u aka 0u)
	vs being a specific op whose next param is a unaryNumber.

}
