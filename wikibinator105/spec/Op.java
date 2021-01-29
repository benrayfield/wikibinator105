/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;


/** TODO rewrite disorganized text...
<br><br>
<br><br>

UPDATING 2021-1-29... (todo rewrite this...)
ukΩuuuw? (λ   λ     λ     λ     λ     λ     λ)    ? //wiki
ukΩuuua? (λ   λ     λ     λ     λ     λ   (λ λ))  ? //isleaf
ukΩuu∩l? (λ   λ     λ     λ     λ   (λ λ)   λ)    ? //getfunc/l
ukΩuu∩r? (λ   λ     λ     λ     λ   (λ λ) (λ λ))  ? //getparam/r
ukΩu∩t?? (λ   λ     λ     λ   (λ λ)   λ)    ?     ? //tru/t
ukΩu∩f?? (λ   λ     λ     λ   (λ λ) (λ λ))  ?     ? //fal/f/fi
ukΩ∩s??? (λ   λ     λ   (λ λ)   λ  )  ?     ?     ? //trecurse/s
ukΩ∩p??? (λ   λ     λ   (λ λ) (λ λ))  ?     ?     ? //pair/p
XXXXXXXX (λ   λ   (λ λ)   λ     λ     λ     λ)    ? //1
XXXXXXXX (λ   λ   (λ λ)   λ     λ     λ   (λ λ))  ? //0
XXXXXXXX (λ   λ   (λ λ)   λ     λ   (λ λ))  ?     ? //infcur_if_next_param_is_leaf_else_curry_if_its_unarynum
XXXXXXXX (λ   λ   (λ λ)   λ   (λ λ))  ?     ?     ? //typeval_and_the_2_get_truthval_ops
ukƱx???? (λ   λ   (λ λ) (λ λ))  ?     ?     ?     ? //ax/x/axiomOp

UPDATE 2021-1-28[
ukΩuuuw? (λ   λ     λ     λ     λ     λ     λ)    ? //wiki
ukΩuuua? (λ   λ     λ     λ     λ     λ   (λ λ))  ? //isleaf
ukΩuu∩l? (λ   λ     λ     λ     λ   (λ λ)   λ)    ? //getfunc/l
ukΩuu∩r? (λ   λ     λ     λ     λ   (λ λ) (λ λ))  ? //getparam/r
ukΩu∩t?? (λ   λ     λ     λ   (λ λ)   λ)    ?     ? //tru/t
ukΩu∩f?? (λ   λ     λ     λ   (λ λ) (λ λ))  ?     ? //fal/f/fi
ukΩ∩s??? (λ   λ     λ   (λ λ)   λ  )  ?     ?     ? //trecurse/s
ukΩ∩p??? (λ   λ     λ   (λ λ) (λ λ))  ?     ?     ? //pair/p
ukƱuu1?? (λ   λ   (λ λ)   λ     λ     λ)    ?     ? //1
ukƱuu0?? (λ   λ   (λ λ)   λ     λ   (λ λ))  ?     ? //0
ukƱu∩∞?? (λ   λ   (λ λ)   λ   (λ λ)   λ)    ?     ? //infcur/∞
ukƱu∩c?? (λ   λ   (λ λ)   λ   (λ λ) (λ λ))  ?     ? //curry/c
ukƱx???? (λ   λ   (λ λ) (λ λ))  ?     ?     ?     ? //ax/x/axiomOp
]

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

---



ukΩuuul?
ukΩuuur?
ukΩuu∩a?
ukΩuu∩w?
ukΩu∩t??
ukΩu∩f??
ukΩ∩s???
ukΩ∩p???

ukƱuu0??
ukƱuu1??
ukƱu∩∞??
ukƱu∩c??
ukƱx????

ukƱuu0??
ukƱuu1??
ukƱuc???
ukƱx????

ukƱuuu0?
ukƱuuu1?
ukƱuu∩??
ukƱu∩∞??
ukƱu∩c??
ukƱx????

ukƱuuu0?
ukƱuuu1?
ukƱuu∩∞?
ukƱuu∩@? //anothe constant can go at @?
ukƱu∩c??
ukƱx????

//opByte can tell 0 vs 00 vs 01 vs 1 vs 10 vs 11 (so can do truthvalue efficiently), other than the prefixes of 0 and 1.
ukƱuu0??
ukƱuu1??
ukƱu∩∞?? 
ukƱu∩c?? //c/curry's first param is unaryNumber of curries, and is the 6th param of u. Its important that fit in the first 6.
ukƱx???? //x/ax's first 3 params are the fourth fifth and sixth params of u. checks constraint at sixth. evals at 7.

ukΩuuuw? (λ   λ     λ     λ     λ     λ     λ)    ? //wiki
ukΩuuua? (λ   λ     λ     λ     λ     λ   (λ λ))  ? //isleaf
ukΩuu∩l? (λ   λ     λ     λ     λ   (λ λ)   λ)    ? //getfunc/l
ukΩuu∩r? (λ   λ     λ     λ     λ   (λ λ) (λ λ))  ? //getparam/r
ukΩu∩t?? (λ   λ     λ     λ   (λ λ)   λ)    ?     ? //tru/t
ukΩu∩f?? (λ   λ     λ     λ   (λ λ) (λ λ))  ?     ? //fal/f/fi
ukΩ∩s??? (λ   λ     λ   (λ λ)   λ  )  ?     ?     ? //trecurse/s
ukΩ∩p??? (λ   λ     λ   (λ λ) (λ λ))  ?     ?     ? //pair/p
ukƱuu1?? (λ   λ   (λ λ)   λ     λ     λ)    ?     ? //1
ukƱuu0?? (λ   λ   (λ λ)   λ     λ   (λ λ))  ?     ? //0
ukƱu∩∞?? (λ   λ   (λ λ)   λ   (λ λ)   λ)    ?     ? //infcur/∞
ukƱu∩c?? (λ   λ   (λ λ)   λ   (λ λ) (λ λ))  ?     ? //curry/c
ukƱx???? (λ   λ   (λ λ) (λ λ))  ?     ?     ?     ? //ax/x/axiomOp
*/
public enum Op{
	
	/*FIXME change all strings to only use those 2 chars instead of the more chars I used while designing it,
	or actually write it like "(λ λ (λ λ) λ)" etc of how to write the normed form of the op in the default syntax,
	then rename (String)Op.prefix to Op.sourceCode,
	therefore it will need twice as many ops (except Op.deepLazy and maybe Op.one and Op.zero).
	FIXME choose between Op.curry being a unaryNumber (other than u aka 0u)
	vs being a specific op whose next param is a unaryNumber.
	*/
	
	
	
	/** not used in the wikibinator105 prototype since everything is either halted or evaling on java stack,
	but other implementations might use this for call pairs that are not halted.
	TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	Anything thats not halted is deepLazy,
	other than if its l() and r() are halted but it is not cuz r() is its
	7th param (FIXME its vararg, though !isStrange ops all take 7 params, other than deepLazy
	which isnt really an op or unsure how to categorize it),
	that uses the same op as r()'s op.
	This is a low level of lazy that does not exist in the wikibinator104 prototype
	but may in other implementations of wikibinator105.
	<br><br>
	Wikibinator105 is (TODO after work out details) a specific pure math function not a specific system.
	<br><br>
	Wikibinator105 can (in theory) be mounted into (axiomforestLeaf "wikibinator105"),
	using axiomforest's kind of strings for that,
	where wikibinator104Leaf is (axiomforestLeaf "wikibinator104") and wikibinator104
	cant see anything outside of (axiomforestLeaf "wikibinator104"),
	and calling it on anything outside that is axiomforest.TruthValue.no,
	so for it to see other systems it would have to emulate axiomforest such as using
	(pair cbt16_axiomforestHeader (pair axiomforestLeftChild axiomforestRightChild)) for example.
	*/
	deepLazy(null,true,false,0),
	
	//First param is λ (aka u aka leaf) for clean, or anything else (such as (λ λ)) for dirty.
	//
	//OLD...
	//
	//First param chooses isClean vs !isClean. In choosing opcodes, leaf is 0, and anything else is 1.
	//isClean = !isDirty.
	//Wiki is the first opcode, so dirtyWiki = (λ λ λ λ λ λ λ),
	//and (dirtyWiki x) = (λ λ λ λ λ λ λ x) which has 7 params so must eval,
	//such as (λ λ λ λ λ λ λ "hello")->"world" might be in the dirtyWiki.
	//A cleanWiki is (λ (λ λ) λ λ λ λ λ), but cleanWiki is practically unuseable
	//cuz clean means deterministic, so no wiki edits are allowed,
	//so the wiki is the function (S (T (S I I)) (T (S I I))) which infinite loops for all possible params,
	//so (λ (λ λ) λ λ λ λ λ "hello") -> (S I I (S I I)) aka never responds to the statement "hello".
	//
	//Similarly, if cleanS or cleanPair etc tries to create a dirty, it evals to (S I I (S I I)).
	//Dirty can create clean, but clean cant create dirty. If a call starts as clean, it ends as clean,
	//but if that was started by a dirty then when the clean is returned to the dirty,
	//it can continue as dirty which contains some clean parts (still dirty as a whole).
	//
	//Theres a clean/deterministic vs dirty/nondeterministic form of everything.
	//(aClean bClean) -> thing_that_clean.
	//(aDirty bDirty) -> thing_that_clean_or_dirty.
	//(aDirty bClean) -> thing_that_clean_or_dirty.
	//(aClean bDirty) -> (aClean (recursivelyChangeFirstParamsToClean bDirty)) -> (aClean bClean) -> thing_that_clean.
	
	
	/*
	UPDATING 2021-1-29... (todo rewrite this...)
	ukΩuuuw? (λ   λ     λ     λ     λ     λ     λ)    ? //wiki
	ukΩuuua? (λ   λ     λ     λ     λ     λ   (λ λ))  ? //isleaf
	ukΩuu∩l? (λ   λ     λ     λ     λ   (λ λ)   λ)    ? //getfunc/l
	ukΩuu∩r? (λ   λ     λ     λ     λ   (λ λ) (λ λ))  ? //getparam/r
	ukΩu∩t?? (λ   λ     λ     λ   (λ λ)   λ)    ?     ? //tru/t
	ukΩu∩f?? (λ   λ     λ     λ   (λ λ) (λ λ))  ?     ? //fal/f/fi
	ukΩ∩s??? (λ   λ     λ   (λ λ)   λ  )  ?     ?     ? //trecurse/s
	ukΩ∩p??? (λ   λ     λ   (λ λ) (λ λ))  ?     ?     ? //pair/p
	XXXXXXXX (λ   λ   (λ λ)   λ     λ     λ     λ)    ? //1
	XXXXXXXX (λ   λ   (λ λ)   λ     λ     λ   (λ λ))  ? //0
	XXXXXXXX (λ   λ   (λ λ)   λ     λ   (λ λ))  ?     ? //infcur_if_next_param_is_leaf_else_curry_if_its_unarynum
	XXXXXXXX (λ   λ   (λ λ)   λ   (λ λ))  ?     ?     ? //typeval_and_the_2_get_truthval_ops
	ukƱx???? (λ   λ   (λ λ) (λ λ))  ?     ?     ?     ? //ax/x/axiomOp
	
	
	OLD:
	ukΩuuuw? (λ   λ     λ     λ     λ     λ     λ)    ? //wiki
	ukΩuuua? (λ   λ     λ     λ     λ     λ   (λ λ))  ? //isleaf
	ukΩuu∩l? (λ   λ     λ     λ     λ   (λ λ)   λ)    ? //getfunc/l
	ukΩuu∩r? (λ   λ     λ     λ     λ   (λ λ) (λ λ))  ? //getparam/r
	ukΩu∩t?? (λ   λ     λ     λ   (λ λ)   λ)    ?     ? //tru/t
	ukΩu∩f?? (λ   λ     λ     λ   (λ λ) (λ λ))  ?     ? //fal/f/fi
	ukΩ∩s??? (λ   λ     λ   (λ λ)   λ  )  ?     ?     ? //trecurse/s
	ukΩ∩p??? (λ   λ     λ   (λ λ) (λ λ))  ?     ?     ? //pair/p
	ukƱuu1?? (λ   λ   (λ λ)   λ     λ     λ)    ?     ? //1
	ukƱuu0?? (λ   λ   (λ λ)   λ     λ   (λ λ))  ?     ? //0
	ukƱu∩∞?? (λ   λ   (λ λ)   λ   (λ λ)   λ)    ?     ? //infcur/∞
	ukƱu∩c?? (λ   λ   (λ λ)   λ   (λ λ) (λ λ))  ?     ? //curry/c
	ukƱx???? (λ   λ   (λ λ) (λ λ))  ?     ?     ?     ? //ax/x/axiomOp
	*/
	
	/** all nondeterminism goes here. TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	if strict/clean this is (S I I (S I I)), else/dirty is loose and (wiki x) == ret in (ax ret wiki x).
	<br><br>
	You can effectively have unlimited number of wikis by using a linkedlist whose deepest part is the name of the wiki,
	and parts after that you use whatever you want, for example,
	and to fork one of those, use axioms (dovetailing derived in params of Op.ax, for example)
	to imply that if something exists in some parts of wiki then it exists (or translating it / migration / etc)
	in certain other parts, though convergence on a set of axioms is intentionally left nondeterministic
	so various groups of people and systems or combos of them or the whole world together,
	however they like to organize things, have not created a lambda contradiction (TruthValue.bull)
	by selecting from the space of all finite but otherwise turing complete ways to organize the wiki,
	where TruthValue.bull occurs when the same lambda call (func param) has more than 1 unique return value
	such as (wiki "hello")->"world" and (wiki "hello")->42 cant both exist,
	but (wiki ["testxyz435" "hello"])->"world" and (wiki ["monkeyspace" "hello"])->42 can exist together,
	or (wiki "testxyz435.hello") would also work, however you want to do it, just dont create lambda contradictions.
	Wiki is a pure math function and can call itself recursively, emulate itself, etc,
	such as (wiki "lazyCallWikiOnItself") -> {,wiki ,wiki} aka (s (t wiki) (t wiki))
	so if that exists (check Op.ax for any returnVal func param)
	then (wiki "lazyCallWikiOnItself" u) -> (wiki wiki),
	and there might be (wiki wiki)->"this is the return value of calling wiki on itself"
	so in that case (wiki "lazyCallWikiOnItself" u) -> "this is the return value of calling wiki on itself",
	or you could also use (s i i x) -> (x x) forall x, so (s i i wiki) -> (wiki wiki) -> whatever that returns.
	You could also make a function locally named testxyz435
	where (testxyz435 x) -> (wiki ["testxyz435" x]). Functions are useful for many possible things
	including convenient shortcuts or building virtual worlds so big and detailed
	you might mistake it for a remote interaction with the real world. You can put anything
	in the wiki as long as it doesnt create any lambda contradictions with the other contents
	and if you can get other people and computers to go along with it in them
	trying to not create lambda contradictions anywhere in the wiki considering its whole contents.
	The system will automatically look for such contradictions and automatically fork and merge
	in whatever ways people want it to as defined by functions they can create
	to help them understand other functions and create new useful functions.
	Everything can be automated and the automatic processes
	(built by people and AIs while using other automated processes)
	can choose between people andOr AIs at the time and context for whatever each does best,
	and if that doesnt work out, try other combos automatically.
	Everything gets connected to everything if thats what those parts want at the time and context.
	*/
	wiki("(λ λ λ λ λ λ λ)",false,false,1),
	Wiki("(λ (λ λ) λ λ λ λ λ)",false,false,1),
	
	/** a/isLeaf. TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	(isLeaf x) is t or f depending if x is the leaf which all paths in the binary forest of call pairs lead to
	aka the wikibinator104 universal function itself.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	isLeaf("(λ λ λ λ λ λ (λ λ))",false,false,1),
	IsLeaf("(λ (λ λ) λ λ λ λ (λ λ))",false,false,1),
	
	/*FIXME??? move the wiki and ax ops so that theres only 1 possible isdirty form of them,
	so that they use u instead of (u u) in their opcodes, or at least that way for wiki
	since they cant both have all u's as their prefix. the wiki is the most important
	to be that way cuz its the only nondeterministic thing in the system.
	*/
	
	/** l/getFunc. TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	(l x) is left child of x in the binary forest of call pairs.
	Not the same as lispCar since pair is the church-pair lambda.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	getFunc("(λ λ λ λ λ (λ λ) λ)",false,false,1),
	GetFunc("(λ (λ λ) λ λ λ (λ λ) λ)",false,false,1),
	
	/** r/getParam. TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	(r x) is right child of x in the binary forest of call pairs.
	Not the same as lispCdr since pair is the church-pair lambda.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	getParam("(λ λ λ λ λ (λ λ) (λ λ))",false,false,1),
	GetParam("(λ (λ λ) λ λ λ (λ λ) (λ λ))",false,false,1),
	
	//l/getFunc and r/getParam differ by only 1 opcode bit (being leaf vs anything_except_leaf*)
	
	/** λy.λz.y aka true. (pair b c tru) is b. Is the K lambda of https://en.wikipedia.org/wiki/SKI_combinator_calculus */
	tru("(λ λ λ λ (λ λ) λ)",false,false,2),
	Tru("(λ (λ λ) λ λ (λ λ) λ)",false,false,2),
	
	/** λy.λz.z aka false aka f. (fal λ) is identityFunc aka λz.z. (pair b c fal) is c. */
	fal("(λ λ λ λ (λ λ) (λ λ))",false,false,2),
	Fal("(λ (λ λ) λ λ (λ λ) (λ λ))",false,false,2),
	
	/** λx.λy.λz.xz(yz) aka ((xz)(yz)). Is the S lambda of https://en.wikipedia.org/wiki/SKI_combinator_calculus */
	trecurse("(λ λ λ (λ λ) λ)",false,false,3),
	Trecurse("(λ (λ λ) λ (λ λ) λ)",false,false,3),
	
	/** λx.λy.λz.zxy. Is the church-pair lambda and lispCons. */
	pair("(λ λ λ (λ λ) (λ λ))",false,false,3),
	Pair("(λ (λ λ) λ (λ λ) (λ λ))",false,false,3),
	
	/** is a cbt. can only be part of cbt. if its param is not a cbt of same size, then calls itself on itself instead,
	so a cbt called on anything is always a cbt twice as big. Avoids the need for pairs in cbts so is more efficient.
	TODO copy comments from wikibinator104 and maybe modify them.
	*/
	one("(λ   λ   (λ λ)   λ     λ     λ     λ)",false,true,0), //one and One do the same thing except viewed thru reflect, and only one is optimized
	One("(λ (λ λ) (λ λ)   λ     λ     λ     λ)",false,true,0), //one and One do the same thing except viewed thru reflect, and only one is optimized
	
	/** is a cbt. can only be part of cbt. if its param is not a cbt of same size, then calls itself on itself instead,
	so a cbt called on anything is always a cbt twice as big. Avoids the need for pairs in cbts so is more efficient.
	TODO copy comments from wikibinator104 and maybe modify them.
	*/
	zero("(λ   λ   (λ λ)   λ     λ     λ   (λ λ))",false,true,0), //zero and Zero do the same thing except viewed thru reflect, and only zero is optimized
	Zero("(λ (λ λ) (λ λ)   λ     λ     λ   (λ λ))",false,true,0), //zero and Zero do the same thing except viewed thru reflect, and only zero is optimized
	
	/** waiting for infinity curries, never evals just keeps accumulating params. A list of anything you want,
	without the inefficiency of using ((pair x) y). Just call it on y without the pair.
	No funcbody or unaryNumber of curries left to cache. Just create a halted callpair for each next curry.
	TODO copy comments from wikibinator104 and maybe modify them.
	*
	infcur("(λ   λ   (λ λ) λ (λ λ) (λ λ) λ)",false,true,0),
	Infcur("(λ (λ λ) (λ λ) λ (λ λ) (λ λ) λ)",false,true,0),
	
	/** (curry unaryNum comment funcbody ...params...)
	or TODO choose a design, maybe unaryNum other than 0 (u) is the curry op itself?
	Avoids the need for pairs, except just 1 pair when it evals,
	compared to wikibinator104 uses a pair for every curry, and calls curry and a unary number on it,
	and this will instead cache the unaryNumber (thats deep inside, near funcBody) similar to caching funcBody.
	TODO copy comments from wikibinator104 and maybe modify them.
	*
	curry("(λ   λ   (λ λ) λ (λ λ) (λ λ) (λ λ))",false,true,0),
	Curry("(λ (λ λ) (λ λ) λ (λ λ) (λ λ) (λ λ))",false,true,0),
	*/
	infcur_if_next_param_is_leaf_else_curry_if_its_unarynum("(λ   λ   (λ λ)   λ     λ   (λ λ))",false,true,0),
	Infcur_if_next_param_is_leaf_else_curry_if_its_unarynum("(λ (λ λ) (λ λ)   λ     λ   (λ λ))",false,true,0),
	
	/** λtype.λval.λparam.(val param), like (typeval "image/jpeg" bytesOfJpg),
	except type cant be leaf cuz then it means getTruthvalueYesPart or getTruthvalueNoPart.
	Similar to (ax ret func param x)->(param x) and (ax ret func param) is halted only if (func param)->ret,
	except typeval is an informal way of saying a type, more of a semantic,
	which you could do the same thing in (ax λ) if its next param is a func that returns λ for all possible params.
	*/
	typeval_butTypeIsNotLeaf("(λ   λ   (λ λ) λ (λ λ))",false,true,3),
	Typeval_butTypeIsNotLeaf("(λ (λ λ) (λ λ) λ (λ λ))",false,true,3),
	
	/** returns t or f. Overlaps (typeval λ λ), which is ok since typeval's first param never needs to be λ. */
	getTruthvalueYesPart("(λ   λ   (λ λ) λ (λ λ) λ λ)",false,false,1),
	GetTruthvalueYesPart("(λ (λ λ) (λ λ) λ (λ λ) λ λ)",false,false,1),
	
	/** returns t or f. Overlaps (typeval λ (λ λ)), which is ok since typeval's first param never needs to be λ. */
	getTruthvalueNoPart("(λ   λ   (λ λ) λ (λ λ) λ (λ λ))",false,false,1),
	GetTruthvalueNoPart("(λ (λ λ) (λ λ) λ (λ λ) λ (λ λ))",false,false,1),
	
	//cleancall will be derived at user level, not an op.
	
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
	<br><br>
	OLD, before merged typeval with ax[
	λx.λy.λz.zxy, same as pair except with the semantic like (typeval "image/jpeg" ...bytesOfJpg...).
	Other funcs can see the difference between typeval and pair using isLeaf, l, r, and a derived equals function.
	]
	<br><br>
	OLD, from wikibinator104 which has the same behaviors of the first 3 params of ax but not its 4th/last param[
		λret.λfunc.λparam.(λret.λfunc.λparam.(λret.λfunc.λparam.(...))) is halted if (func param)->ret,
		else evals to (S I I (S I I)) aka an infinite loop. 1 more param and it does...
		λret.λfunc.λparam.λignore.(S I I (S I I)) aka an infinite loop, to complete the 7 params of the universal func,
		but we dont normally call it all the way to λignore, just use the first 3.
		TODO explain λret.λfunc.λparam.(λret.λfunc.λparam.(λret.λfunc.λparam.(...))) differently
		cuz that way of writing it makes it appear that it takes 3 more params, instead of the 3 params already given.
		<br><br> 
		Theres a !isDirty and isDirty form of this. Theres nothing dirty about it, other than possibly its params.
		(ax ret param func) will eventually halt (and cache that) if (func param)->ret (use derived equals func).
		(ax ret param func ignore) does infloop, which completes the 7 params of the universal func.
		<br><br>
		Axiom op, such as (ax (leaf leaf) leaf) can only halt on params that (ax leaf leaf) cant halt on,
		(cuz same func and param can have at most 1 returnVal, but theres 2 in that case: leaf and (leaf leaf)),
		which together are similar to axiomforest's TruthValue.yes and TruthValue.no and together would TruthValue.bull,
		and if neither are observed then thats TruthValue.unknown. But in this software, theres only binary forest shape
		with no TruthValue or other data at nodes, except caches of what can be derived only from binary forest shape,
		but "can be mounted into axiomforest" (search for that) as explained in comment near end of this class.
		<br><br>
		This will normally be used with dovetailing of fntape to search all possible axioms provable in finite steps,
		and if that does not find the axiom you're requesting (by calling it on ret param func)
		then it infloops by not finding that or appears to infloop cuz it takes longer than the universe --> heatdeath,
		aka it calls (func param) then checks if what it returns (if it ever returns) equals ret,
		and the dovetailing would be implemented inside that func, in some cases,
		as axiomforest-like bloom-filter of accumulating true statements in (ax (leaf leaf) leaf)
		and accumulating false statements in (ax leaf leaf), and checking for overlap between those 2.
		That dovetailing, which will be derived at user level (see "fntape" in occamsfuncer readme),
		is just an abstract math description of what it must do and will actually be
		computed exponentially faster in many cases using Compiled.java manually written optimizations,
		and later deriving some of those optimizations automatically (to gpu, javassist, etc).
		Its easy to make something exponentially faster if you already know what you want it to do
		but it just happens to take exponential time and memory to write it in the form of dovetailing
		as a recognizer function of the the thing which a Compiled.java would compute procedurally forward,
		those things being the various axiom-like statements or their outputs like in earlier wikibinator versions
		where that got too complex and I redesigned it to only need this one ax op with exponential optimizations.
		<br><br>
		OLD...
		Ok, updated comments (changed from λret.λparam.λfunc) for...
		should it be (ax func ret param) is halted if (func param)->ret?
			cuz that way ret could be leaf and func is like a type,
			such as (ax ifItsACatPicThenReturnLeaf leaf aPossibleCatPic) would be halted
			only if (ifItsACatPicThenReturnLeaf aPossibleCatPic)->leaf,
			and it could hook into axiomforest similar as the other way like
			(ifItsACatPicThenReturnLeafElseLeafofleaf aPossibleCatPic)->(leaf leaf)
				if its certainly not a cat pic, and any return other than leaf or (leaf leaf) could mean unknown
				and if it never halts thats also unknown.
			Its still the same 3 params as (ax func ret param), or (ax ret func param) would also work,
			but func cant be the last one cuz func has control.
		Similarly func could be something that calls its param on another func, if you want it to do some other order.
	]
	*/
	ax("(λ λ (λ λ) (λ λ))",false,true,4),
	Ax("(λ (λ λ) (λ λ) (λ λ))",false,true,4);
	
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
	
	private Op(String sourceCode, boolean isAlwaysEvaling, boolean isStrange, int curriesElse0){
		this.prefix = sourceCode;
		this.isAlwaysEvaling = isAlwaysEvaling;
		this.isStrange = isStrange;
		this.curriesElse0 = curriesElse0;
	}
	
	/*
	Can be mounted into axiomforest.
	
	FIXME rewrite the below 2 paragraphs cuz i just replaced "wikibinator104" with "wikibinator105"
	without checking if theres anything different cuz of the vararg redesign,
	which would only affect how to explain it here cuz its still a 1-to-1 mapping
	between axiomforest (4-way-forest if you count the 2 bits of TruthValue as 2 childs, and 2 pointers)
	node and wikibinator105 node (2-way-forest, both are pointers).
	
	TODO hook into axiomforest at (a/axiomforestLeaf "wikibinator105"),
	of course using ((a a) a) as 1 and (a (a a)) as 0 from which bytes and bytestrings can be made
	like (((0 1)(1 1))((1 0)(0 0))) is the byte 01111000, and ((a a)(a a)) as identityFunc,
	and (a identityFunc) as axiomforestTypeval and (a 1) as Yes and (a 0) as No
	and (a "wikibinator105") would be (a (axiomforestTypeval "text/plain" "wikibinator105"))
	while the first param of typeval is either not a cbt or is interpreted as utf8 string,
	but other strings or pics videos pdfs or content of any type uses axiomforestTypeval,
	which is the standard way to use axiomforest between multiple systems, in theory, BUT
	wikibinator will not be able to see anything outside that namespace
	and will use (axiomforestLeaf "wikibinator105") as u/wikibinator105Leaf.
	Other systems built in axiomforest, such as maybe a wikibinator105 someday,
	or conwaysgameoflife experiments, etc, or tools built specificly for combining things
	inside the axiomforest, might be able to combine these parts that otherwise
	cant see outside themself, though you could emulate axiomforest in wikibinator105
	using pairs etc.
	
	A wikibinatorNode is a binary forest node without any data at it
	(other than caches derivable from that binary forest shape),
	but not all possible binary forest shapes are allowed cuz only those which are halted are allowed,
	and when viewed in axiomforest, TruthValue.yes means is halted, TruthValue.no means is not halted,
	and TruthValue.unknown means dont know if its halted or not (which may take infinite time/memory to know),
	and TruthValue.bull works as usual. They all work as usual.
	There will be some duplication of the YES and NO in
	(wikibinatorOp.ax (wikibinatorLeaf wikibinatorLeaf) wikibinatorLeaf x)
	and (wikibinatorOp.ax wikibinatorLeaf wikibinatorLeaf x) will never both exist for any x.
	Other first params of wikibinatorOp are allowed too, but those 2 can emulate all the others in axiomforest way.
	*/

}
