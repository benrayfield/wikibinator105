# wikibinator105
(TODO) A deterministic way for millions of people and AIs to build and play together in p2p, safely sandboxed but not dumbed-down,
a very simple kind of self-aware living number, where 2 numbers combine to create or find another number, and so on,
and a number can be anything such a word, video, game, simulation, publicKey, GPU optimization, music tools,
way to use multiple clouds together, etc.

Evolvable musical instruments will work like this, using this plugin:
https://github.com/benrayfield/wikibinator105/blob/main/wikibinator105/plugins/jsoundcard/Play.java
```
/** soundstream.e(double[1]).e(Tru).d(0) and .d(1) are next 2 speaker outputs,
and the double[1] is microphone input, all in range -1 to 1 (else truncates into that range),
and soundstream.e(double[1]).e(Fal) is next soundstream (a stateless snapshot of its next state),
though it can have a tiny amount of state in the Op.Wiki function,
in the way that (Wiki x)->y and (Wiki x)->z cant both be true unless y==z
aka the infinite space inside Wiki can each part only be written once ever,
and maybe (Wiki [timeUtcNanoseconds "user3345346547453e45345345.microphoneX"])->float64Amplitude
which soundstream might know to look in that, and externally you create
(AxA (Fpr Wiki [1612969561952000000L "user3345346547453e45345345.microphoneX"] -.563345634)),
though that might need some adjustments to this play_float64_2Speakers_1microphone_44100fps
function to sync the time better, such as maybe timeUtcNanoseconds should be 1 of the params
or in the cbt as the first double (can only have microsecond precision if double, or still use it as long?)
as 1 of the sound channels of the λ soundstream??? or just another param of the soundstream?
<br><br>
TODO test this. The code isnt even compiling as of 2021-2-10 but occamsfuncer works
and this is a variant of occamsfuncer. Will get it working soon, and this will fit in,
other than it might be too slow and need to be called in blocks
of 16 frames (wave amplitudes per speaker/microphone) or 256 of them like WebAudioAPI does etc,
but since its only called 44100 times per second, that shouldnt be the bottleneck.
*/
public static void play_float64_2Speakers_1Microphone_44100Fps(λ soundstream){
	if(soundstream == null) play(null);
	else play(new SoundStreamFloat64() {
		λ state = soundstream;
		final λ T = soundstream.op(Op.Tru); //can get these from any λ
		final λ F = soundstream.op(Op.Fal);
		public double[] apply(double[] ins){
			//TODO use e(maxSpend,param) to avoid infiniteloops etc, considering its limited to 0.9/fps seconds.
			λ pairOfOutsAndNextState = state.e(ins);
			λ outs = pairOfOutsAndNextState.e(T); //same as lispCar if its a pair
			double[] ret = new double[]{
				outs.d(0), //speaker
				outs.d(1)  //speaker
			};
			state = pairOfOutsAndNextState.e(F); //same as lispCdr if its a pair
			return ret;
		}
		public int outs(){
			return 2;
		}
		public int ins(){
			return 1;
		}
		public double fps(){
			return 44100;
		}
	});
}
```

Since all possible state is in Wiki, and a wiki is naturally public, such a sound stream is potentially a competition for VoIP, Skype, etc,
though things will only be broadcast if someone wants to receive it and if you want to send it as there should (TODO) be
some kind of settings in Wikibinator105 VM to specify which things to share and searching and caching in p2p network etc.
(AxA (Fpr Wiki [1612969561952000000L "user3345346547453e45345345.microphoneX"] -.563345634)) is not specific to any one computer.
Its a statement that at time 1612969561952000000L (nanoseconds utc) that microphone had that wave amplitude,
and could just as easily been userSSDFADSFG45 or not have the word "user" in the parameter of Wiki at all,
as its generally a system of lambdas, and float64 or int16 wave amplitudes (or compressions of them) are just kinds of lambdas.

The MarklarId105b kind of 256 bit ids will uniquely identify a lambda function anywhere in the peer to peer network
and will be created in 1 millionth of a second each and have 49% binary storage efficiency
in their simplest form and 99% if you dont store some of the middle nodes,
and they are the id of a binary forest shape where all paths lead to u/leaf/theUniversalFunction,
and the axA and axB ops are replacing colors, as (axA statementX) and (axB statementX)
cant both exist, for any statementX,
and (axA statementX) is halted if (statementX u) -> u,
and (axB statementX) is halted if (statementX u) -> anything except u,
and if (statementX u) does not halt then neither of those things can exist,
and typed lambda functions (typed return value, untyped param can be anything,
of an infinite number of turing complete types such as a list of prime size or opencl code
that doesnt try to read or write outside its allowed memory)...
Typed lambda functions are done by (axA statementX param)->(statementX (T param))
and (axB statementX param)->(statementX (F param)),
both of which often return another call of axA or axB aka another typed function, and so on,
and efficiently supports bitstrings up to 4 terabytes, and slower for unlimited size bitstrings,
and bitstrings can be recursed into powOf2 aligned ranges efficiently
or copied in other alignments slower, and bitstrings may be memory mapped into GPU mem, harddrive,
float[], long[], java.nio.Buffer, String, remote memory mapping across the internet,
keeping in mind that everything is immutable, including functions, bitstrings, ax statements,
so if you want a mutable bitstring you have to allocate a much larger one that
has a range for each microsecond or nanosecond or use that as a param in a func
such as (wiki [aUtcTimeInNanoseconds "hello"])->"world" may be true
(always, about a specific time, but always true if its true ever),
and to write the wiki you just pretend that you are reading it and it has always been that way
using (Op.axA (Op.fpr wiki anyParam anyReturnValue)) as long as it doesnt cause
any 2 functions (which may call (wiki anyParam) and already be known to have a different return value
that depended on that wiki call returning anyReturn value)... dont create contradictions
and it wont have to fork the bloom filter
which is (axA x) vs (axB x) vs neither observed yet but never both.
The prefix 0xf9 is not a valid utf8 byte so every possible utf8 string of 32 bytes
is its own id, as are most bitstrings of 32 bytes (any that dont start with 0xf9)...

```
//Here's the datastruct for MarklarId105b:
if(first byte is not 0xf9){
	//is literal cbt256 thats its own id.
}else{
	//bit 16 is containsAx. bit 17 is isBitstringUpTo32Terabits
	0xf9
	op8 //see Op enum
	containsAx1 //contains Op.axA or Op.axB deeply in l() and r() recursively?
	isBitstringUpTo32Terabits1 //is bitstring up to 2^45-1 bits aka 4 terabytes, else slower
		//but unlimited size using normal call pairs.
	if(isBitstringUpTo32Terabits1){
		//is cbt of powOf2 number of bits from 1..2^46 bits, and knows the index of the last 1 bit if exists.
		cbtHeightAndBize46 //high 1 bit tells which powOf2. Bits below that tell wheres the last 1 bit (if it exists).
	}else{
		curriesAll23 //is 2^23-1 if bigger. number of this.l.l.l.l...l until get to u/leaf plus curriesMoreIf23.
		curriesMoreIf23 //curries until eval. is 2^23-1 if bigger. is 0 if op8 is Op.deepLazy aka is (a snapshot of) evaling.
	}
}
```

the hash192 is the last 192 bits of sha3_256 and comes after the 64 bit header,
and its either 192 0s for leaf (op9 is (byte)1, so even if theres a hash collision its still a different id)
(and of course the normal 64 bits of header before that) or is last192Bits(sha3_256(concat(leftId,rightId))).

Also some Op enum changes etc described in HeaderBits_NEW_TODOREPLACEOLDONEMAYBE
but most of that is what led to this and just comment it out or remove those words.


Planned UI, which will have drag-and-droppable functions (colored circles) with colored edges between them showing left child, right child, return value (when called on leaf, especially if its a (lazy x y) call, and various edges to skip over internal tree nodes to make it look like linkedlists, sCurryLists, and human readable, might look something like this, and it will be the game-tree of all possible turing-completeness, at first appearing very basic but as you navigate deeper into the tree you might find a chess board with the chess game tree in it, derived at runtime (including its graphics, just another lambda, and all lambdas are in the game-tree already), the same way you would find pacman, FPS games, GPU optimizations, new kinds of web browsers, music tools. This game-tree is constant, never changes, at least in the CLEAN parts, while the DIRTY parts are for convergence on the agreement of some CONSTANT wiki function (that we agree on more and more parameters what (wiki parameter) returns, such as some parameters might include a time so mutability and immutability dont conflict as long as (wiki [theTime someParam]) never has more than 1 unique return value, but the point is, this is a constant game-tree, like a chess game tree is constant, and millions of people will be able to navigate it together in realtime. Building things is the same as finding them in the space of all possibilities, and I plan to literally display and let you explore the space of all possibilities... I believe the internet can become something similar to https://en.wikipedia.org/wiki/Ralph_Breaks_the_Internet and https://en.wikipedia.org/wiki/The_Emoji_Movie and https://en.wikipedia.org/wiki/Minecraft and jsfiddle such as https://jsfiddle.net/greggman/8djzyjL3/ and wikipedia, among all other possible things that occur in https://en.wikipedia.org/wiki/Mathematical_universe_hypothesis specificly the way apps cross borders into eachother in unexpected openended ways at runtime, but I've worked out the security to do it safely in a p2p network andOr existing clouds andOr GPUs andOr whatever you have.

<img src=https://github.com/benrayfield/wikibinator105/raw/main/data/wikibinator105/pics/todoEdgesUi.png>

```
Incomplete example code...

λ#u
(u u)#uu
(u u u u u u u)#wiki
(u uu u u u u u)#Wiki
(...)#x
(...)#a
(...)#t
(...)#f
(f u)#i
(...)#ifButNotInS
(...)#if
(...)#paramN
(...)#comment
(...)#recur
(...)#p0
(...)#p2
(...)#p3
(...)#p4
(...)#p5
(...)#and
(...)#and3
(...)#c
(c ,u)#c1
(c ,,u)#c2
(c ,,,u)#c3
(c ,,,,u)#c4
(c ,,,,,u)#c5
(...)#isColorProof
(...)#isColorDisproof
(...)#nor

/* TODO where to write comments like this? Maybe curry isnt the only place comments should
 have a place in? I might want them in {...} which always contains an S op,
and maybe also in [] and <> which are made of pairs aka (pair x y) aka ((pair x) y){
	,c2
	"its colorNormal if its halted and neither of colorProof or colorDisproof, since
	 colorWordsalad is (ax anything) where (anything u) does not halt so that (ax anything)
	  does not halt. colorNormal is anything whose left child is not ax."
	{,nor {,isColorDisproof p0
}#isColorNormal
*/
{,nor isColorProof isColorDisproof}#isColorNormal

{
	,c2
	"todo write a comment here"
	{
		,if
		{,a p0}
		{,a p1}
		{
			,if
			{,a p1}
			,,t
			{
				,and
				{recur {,l p0} {,l p1}}
				{recur {,r p0} {,r p1}}
			}
		}
	}
}#equals

{,equals ,equals ,equals}#lazy_eqeqeq_callMeOnLeafAndShouldGetT
{,equals ,"hello" ,"hi"}#lazy_eqhellohi_callMeOnLeafAndShouldGetF
{,equals ,"hello" {,L ,"hello" {,R ,"hello"}}}#lazy_eqhellolhellorhello_callMeOnLeafAndShouldGetT

(ax (fpr lazy_eqeqeq_callMeOnLeafAndShouldGetT u t))#test_lazy_eqeqeq_callMeOnLeafAndShouldGetT_todoVerifyColorIsProof
(ax (fpr lazy_eqeqeq_callMeOnLeafAndShouldGetT u f))#testOpp_lazy_eqeqeq_callMeOnLeafAndShouldGetT_todoVerifyColorIsDisproof
{,isColorProof ,test_lazy_eqeqeq_callMeOnLeafAndShouldGetT_todoVerifyColorIsProof}#testColorProof_callOnLeafShouldBeT
```

===Planned FEATURES===
* After the prototype is finished and hooks into some other systems, will be for ages 0 to expert, that anyone of any age and skill, maybe even some of the smarter mice or cats or monkeys, will near instantly find it intuitive on a mulitouchscreen or with camera watching their movements and responding with sound or a video projector or whatever kind of UI they are able to use, but for experts will connect many things across the internet through a universal pure math function to make things simpler, more efficient, more scaleable, compatible, secure, low lag, and deploy a global system in a tiny fraction of a second in tiny pieces similar to how a cellular automata spreads but in sparse turing complete dimensions and safely sandboxed and with formal verification.
* Human readable programming language and interactive visual and sound and game controller mouse keyboard webcam multitouchscreen etc forms, where you may drag-and-drop function onto function to find or create function or use it like normal systems, such as {I I} is a function that calls its parameter on itself if I is identityFunc, and {I ,"hello"} aka (S I (T "hello")) is a function that calls its parameter on "hello", and there will be drag-and-droppable functions for as advanced of things that any programming expert does, like a whole game, simulation, picture, video, sound, word, sentence, pdf file, way of using multiple compute clouds together, social network, digital signature algorithm, musical instruments, photoshop-like tools, browser plugins to view the web new ways and share tools that build tools when they act on combos of other tools to measure or improve tools, etc... all those things might be built from calling λ on itself in various combos.
* Everything is immutable and efficiently forkEditable and comes in tiny 256 bit pieces that are lazyEvaled global id of every possible tiny piece of computing, so its not a blockchain but has in common with those that its a merkle forest, but the merkle is lazyEvaled and does not need to generate global id256 (which everyone generates the same id256 for the same function deterministicly even if they do so separately and when they finally share things built on those together they fit like clockwork)... ids are lazyevaled and instead can use wrappers of primitive arrays (such as float[] or opencl gpu memory) and callpair nodes with EvalerChain.java as where compiled optimizations go in them.
* can be used on pen-and-paper or shared by USB sticks or email in small amounts, on a single computer, peer to peer, in near any existing cloud system, in experimental kinds of computers such as cellular automata processors maybe (may be the most efficient way) or in a black-hole-computer, andOr any combos of that. 
* whole system is a simple pure math function, which is a combinator, universal lambda, and pattern calculus function, of an infinite number of parameters to support vararg but all interesting logic happens in the first 7 parameters.
* A node is either λ or a callpair of nodes such as (λ λ) or (λ (λ λ)) or (λ λ ((λ (λ (λ (λ λ)))) λ) λ λ (λ λ)), where (a b c d) means (((a b) c) d).
* Data integrity and formal verification is strengthened by the sparse bloom filter of map of binary forest node to 1 of 4 colors (colorAxEven colorAxOdd colorAxNonhalt colorNormal) where every node observed must be a specific color the first time its used, though for more speculate statements such as how Transfinite-turing-complete-type-system can do an if/else of if p equals np or not (may take infinite time to eval that condition) that statement would have to be the bloom filter there being 0000 (unknown color) instead of 1000 or 0100 or 0010 or 0001 (the 4 colors), and things like 1010 or 1111 are an error code that ORing the bloom filters together (on key of the form where its all 0000 in all nodes below to identify the binary forest shape only) generate another map of binary forest node to color and the error bit (like axiomforest Truthvalue.bull) propogates up so every node which can reach an error in its l() and r() childs deeply knows it in its id256. This would be id512 as you would share the id where everything has a color along with the id of all 0000 (unknown color the normed form) so can use that to find where to OR the sparse pieces of bloomfilter together to derive what node is what color.
* The SAT (such as 3SAT or NSAT or neuralnet or bayesnet or other kinds of logic programming) level is below the lambda level, and is on the bit vars of 2 bits per node which choose 1 of 4 colors, in conditional probability or hard logic with other relevant bit vars / colors in other nodes depending on binary forest shape, and this can select from all possible states of the wiki function which is the only function in the system which is not defined as a forest of call pairs but is defined by example of many (func param)->return where the turing complete type system can represent a (func param)->returnX or that it returns anything thats not returnX or that it does not halt (colorAxNonhalt) (not always proveable but can use it if the statement is already in the system) or that its left child is not the axType op (colorNormal).
* All calculations are repeatable to produce the exact same bits, by any computer anywhere or many computers doing parts of the calculation using tiny pieces of it each as id256.
* Every node has an id thats an infinite number of bits without collisions as its id is the set of all possible idMaker functions which are themselves any lambda you create at runtime, and a default idMaker will be provided that always returns 256 bits for any given node, including that it generates its own id, and that every part of every idMaker function has an id from every other idMaker function so this web of redundantly cross referencing eachothers parts gives it a security level of needing a halting-oracle to crack it unless people get lazy and tell it not to redundantly cross-reference ids much, as you can trade between security and efficiency.
* creates a new lambda function every 1 millionth of a second, and can safely throw lambdas across the internet and run sandboxed on peer's computers at 10% lightspeed meaning that the time from starting a new lambda call on one gamer's computer to another gamer's computer running that lambda safely in sandbox will be around the time it takes light to go back and forth between them 10 times (so gaming-low-lag without needing "lag hiding"), and will compute matrix multiply of 2 float[1000][1000] in .01 second by using opencl.
* a multiverse of all possibilities, where disagreements may fork and continue separately or merge and continue together, each an immutable/stateless efficiently forkEditable tiny (1-256 bits) self contained piece of computing which can form together, still dividable into such tiny pieces for other combos... form together into things as big as the whole internet.
* unlimited depth of emulation of emulation of emulation in a sparse way where you only emulate the tiny parts you're interested in, and self reference to derive new kinds of debuggers and opcodes such as a debugStepOver and debugStepInto op, which are themselves lambdas built at runtime.
* derive and formalverify IEEE754 float32 and float64 math and optimize to use CPUs and GPUs and compute clouds, whatever you have, seamlessly still being lambdas all the way down to the universal function.
* ability to turn on and off each optimization per object at runtime, such as to test if the optimization computes the exact same bits as the universal function in interpreted mode does, and optimizations made of multiple other optimizations such as using a float32 optimization (instead of computing all its bits as lambdas) in a LWJGL OpenCL (GPU) optimization which uses float32s.
* Turing-complete-type-system (UPDATE: Still be a turing complete type system, but not transfinite. This is cuz colorAxNonhalt only happens when (axType x) where (x u) does not halt, and in that case lets just have (axType x) not halt and leave axColorNonhalt as a math abstraction, cuz otherwise might make it ambiguous what halts that checks for colorAxNonhalt in other things and the VM is trying to figure out if it should halt or not based on what optimizations are in the VM, so dont do it that way), OLD but the parts other than about P vs NP are still true... Transfinite-turing-complete-type-system implemented as each possible state of the system is a map of node to color, in which a function can be derived that evals to T/true vs F/false depending if P equals NP vs P not equals NP and this statement evals in bigO(1)/constant time IF you have an infinite number of threads each with a constant amount of memory to verify the forest of callpairs and their 1 of 4 colors each all in parallel looking only constant depth outward from each and selecting from all possible maps of node to color (which in practice means its a calculation that will never finish unless a proof is found that P equals NP or proof that P not equals NP, but if such a proof is found and somehow written into the VM as an optimization then the opcodes were already supporting the, if(does_p_equal_np) then else, and can work by just claiming one way or the other as long as no statement contradicts any other statement which would cause forking if ever observed... or you could have a turing complete type of IEEE754 normed form of float64 (a cbt of 64 bits) or a type of a list whose size is a prime number or a type of a string of opencl code that does not try to read outside its allowed memory ranges or a type of jpg image bytes that are a picture of a cat thats standing on buttons on a keyboard that is typing the code of a quine of the turing complete type that recognizes such pictures of that, where (axType typeandinstance) calls (typeandinstance λ) to get an even height or an odd height return value (so (axType typeandinstance) is colorAxEven or colorAxOdd) or if somehow its known that it doesnt halt (mostly its there just for abstract math completeness) then colorAxNonhalt, or if the node's left is not axType then its color is colorNormal. The system approaches the line of nearly containing a halting-oracle which would be impossible EXCEPT that the color of (axType questionOfDoesAThingHaltOrNot), can answer in some cases that a thing does or does not halt (and if it does halt then is its return value even or odd height in binary forest of call pairs, is colorAxEven or colorAxOdd, or does not halt is colorAxNonhalt, or if is not an ax statement is colorNormal)... comes very close BUT if a function asks will (itself λ) halt then chooses to do the opposite (if self halts, then infinite loop, else halt instantly), then it never gets the answer, never knows the color, because the nearHaltingOracle does not always halt. This needs further research to know for sure how consistent it is combined with all these other parts of the math that are hard to imagine all at once.


```
You start with the universal function: λ
(x y) is calling the function x on y,
which returns some function or stays as itself if waiting on more functions like (x y z).
(λ λ) is calling λ on λ.
Here are the (TODO)drag-and-droppable opcodes and how to get them using only λ...

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

(these opcodes are near finished but there will be a few changes to them before
committing to a constant set of opcodes for wikibinator105 forever,
and to fork a different name andOr number to represent any other changes).

Theres twice that many opcodes
by starting with (λ (λ λ)) aka dirty instead of (λ λ) aka clean.

From these, anything can be built. This will be demonstrated soon in a basic form,
but it will take a long time to optimize it for GPU, graphics, sound, networking, etc
(JIT compiler designed for this kind of lambdas).
```

The following disorganized page of text explains part of a theory how to guarantee at least approx convergence in the p2p network based on a turing complete energy function which includes all possible wiki states, in the form of a SAT Solver https://en.wikipedia.org/wiki/Boolean_satisfiability_problem where there is exactly 1 solution for every possible wiki state and astronomically many ways to find that solution redundantly cross-referenced, and sparse, where every possible binary forest node (where all paths lead to the 1 leaf aka the universal function is the leaf) is a bit var of is it included or not, and the nsat constraints could be computed procedurally forward as usual for optimizations, or by a bayesnets or by neuralnets, or by SAT Solver, or by guessing then seeing if it breaks anything then backing out, and so on, or any combo of those, for the purpose of sync of the space of all possible lambda functions called on eachother in a p2p network at gaming low lag. The energy function prefers a clique of overlapping sets of binary forest nodes INCLUDED which none of disagree on the truthvalue being yes or no of anything but still can be unknown but cant be bull, and none of the energy is counted where such a map of node to truthvalue conflicts with any nsat exclusion, and such exclusions only exclude what is not possible in any wiki state, of all possible wiki states (selected from all possible turing complete behaviors) and their interactions with eachother at gaming low lag and number crunching optimizations etc. This space of all turing complete possibilities, and the energy function (TODO), describes all possible pasts, present, and futures, in all possible physics simulations (as it is turing complete), in a single energy function and therefore explains time-symmetry-breaking https://en.wikipedia.org/wiki/T-symmetry aka if you play a video backward, other than friction, it appears to still obey the laws of physics, so why does reality tend to go epsilon time forward slightly more often than it goes epsilon time backward, and this model of computing and energy function's answer will be something like... As you accumulate proof-of-work or position in the weights of observing things within the nsat space, fewer possibilities remain, but since there are an infinite number of possibilities, half of infinity is still infinity, so heat-death may be like a horizon that you never reach. This, at first in very simple forms, but growing in dimensions and contexts, will someday be available (TODO) in this system and a web of compatible systems working together.

TODO implement ids like that page of text near the top of readme as of 2021-1-29
	about nsat, axiomforest header 16 bits, merging (something like?) axiomforest node with wikibinator node,
	and having 2 or 3 childs like axiomforestnode depending if all are yes or all unknown or if its mixed,
	the third child being deriveable from binary forest shape plus the truthvalue per node,
	and it being a stateless model of all possible wiki states etc in a 3^numberOfPossibleBinaryForestNodes bayes node way. Its this[[[
wikibinator id256...
if first byte not \ then its cbt256 and is its own id.
if next bit is 1 then the next 55 bits are height and bize etc, not storing whole opbyte, just store enuf to know opbyte. height is by highest 1 bit in those 55-few etc, bize just under that.
make 0 and 1 be at param 6 so less to remember.
put typeval somewhere also other than ax and in ax, for nonenforced loose typevals, hard typevals still go in ax. maybe want second ax, or derive it, as normed form of how to ask what does (x y) return in rfpd cache, but in fp order, so maybe use that in the java map of rfpd.
opbyte is all 255 values of first 7 curries being leaf bs nonleaf.
a bit for saying its a cbt and a long long range of it comes after the 256 bit header, for efficient binary storage, and store no padding or only padding to next block of 256 bits as it goes in array of ids but just dont point at the subrange of blob. or a bit that means store the whole blob and can use a rfpd to say one thing is which subrange of which other thing.
if its not cbt or is very big cbt then opbyte is stored and num of curries is stored in those 47-few bits.
also similar to cbt but for whatever kind of unary counting curry op uses, same depth in id as num of curries, so to get that unary node its just flip a bit in those 47 bits etc.
do dovetailing debugstepover asap.
opbyte must be knowable from every id256.
ishalted or islazy bit not always derived from forest shape? or how about truthvalue? could use opbyte0 for that, and use that in java map key? (ax ret func param) of opbyte 0 vs the halted opbyte for that?
wikibinator see into axiomforest? probably not all of it but just its own namespace and opbyte 0 is unknown and all else is yes and ax ret func param vs ax otherret func param if one is yes the other is no.
push 0 and 1 deeper 1. they were both at param 5, move to 6 so  an have another op  at param 5. if also do that for curry and infcur can have a new op at param4 which can be typeval. but problem currys first param is unary and would be at param7. unless that unary is itself the curry op and if its leaf its infcur, so can curry 1u to any finite unary and in ids cache that unary etc up to around 2^45 curries and past that have to interpret lambdas.
the isstrange opbit is no longer descriptive of vararg vs nonvararg but is just another prefix opbit, cuz typeval is in its (u u) side.
Generalize ax to all 6 permutations of rfp rpf fpr etc by it being ax of 4 things and the extra is in ax's opbit being anynonleaf that axkind x y z???? probably not cuz that makes ax not knowable just from opbyte or makes you have to store 4 things instead of 3.
i want to think of the system as circles with colored arrows between them, green for left child, blue for right child, red for what (self u) returns (useful with lazig), maybe white for what (wiki self) returns. wiki is the only nondeterminism so the dirtynode edges are conditionalprobability with eachother. every edge has a binary forest form using ax. so the whole system is conditionalprobability/satlogicetc of set of binary forest nodes. maybe use the bayeslambda kind of 3^n bayesnode as each dim has 3 positions -1 0 1, and along any dim the midpoint has bayes weight of the sum of the 2 ends, and the center of it all has bayes weight of 1. this is a constant trinarybayesnode, and its dims/bayesvars are every unique binary forest node like in axiomforest. can use separate axiomforest id256 for that as its more general and needs a cached norm third child, so together a wikibinator105 id with an axiomforest id is 512 bits, and remember axiomforest can have yes no for any set of nodes while all others are unknown and efficiently cache that. its a bayesaddr. so map of axiomforestnode to chance, or ratio of chances maybe dince its an infinite space.
use axiomforest a different way... accumulate nsat exclusions, each an axiomforest node, that are proven can never happen in wikibinator105. exclusions dont have to agree with eachother the way possibleinclusions do. also have an evolving set of possibleinclusions like how evolving subsets of observed bit vars can evolve in nsat. each exclusion must be self proving by its forest shape and truthvalues alone. add another bit to axiomforestnodeheader maybe, to say its an inclusion vs exclusion, or maybe do that at a higher level since its 1 bit per whole forest below. this will maybe help p2p sync especially about wiki op and ax op. the set of exclusions are facts including the space of all possible wiki functions/states, so at this level the whole system is truly stateless and multiverselike. but im worried that it might take very large number of bayesvars/binforestnodes to describe wikibinator105. still im happy to have a stateless theory of it at all. given this nsat forest sync ability in theory, its now ok to define the energy function of the whole system as weighted sum of proofofwork attached to each inclusionaxiomnode in a clique aka none of them overlap to bull eachother AND none of them conflict with any exclusionnsatforestnode since exclusions are only the things that cant happen in wikibinator105 etc. proofofwork may be also on exclusions but does not affect the energy function but may affect peoples and computers attention to look at them. share sets containing multiple inclusions and multiple exclusions, or maybe just pairs of relevant things to look at, and put proofofwork also on that small set maybe. use the dovetailing debugsyepover etc in the nsat view (where  every binaryforestnode is a dim/bayesvar/nsatvar). maybe include the 16bit axiomforestnodeheader in wikibinator id so theres only 22 bytes left for hash and  needs 3 childs...  but if all are yes or all unknown then only need 2 childs. the third child is derivable from binaryforest shape and is only a cache.
cbts are always included, especially cbt256.
there will be a bit in ids (other than cbt256) that tells if its include or exclude, and it doesnt affect hashid except at a higher level of sets of includes and excludes. proofofwork comes in 2 forms, in the hash bits of nonliteralcbt256 ids, and as an extra 256 bits of nonce thats only valid if its lessthan the proofofworkamount it generates, so its a cache, and maybe that same thing but for sets of includes and excludes.
keep the 192 bit hash and shrink the bize to fit in 32-few bits? could also have use 40 byte ids and 64 byte ids etc later if needed. i want the main stuff in the long header.
fixme, for ids to be makeable by custom idmakers created at runtime, wikibinator105 needs at least ability to read cbt2 truthvalue from param, but they all have to be yes if they halt especially ax 6th param, so im unsure what the behaviors are for unknowns nos etc. since only a dirty can see all yes nodes since some yes nodes are dirty, could kind of put that func in wiki but paradox everything in wiki must also be yes. could define another kind of idmaker as func of 256+256+2 bits to 256 bits aka 2 child ids and a truthvalue to parent id, but it still couldnt see the truthvalue of ots param... unless, take that op space made room for typeval and put the getyespart and getnopart, and derive typeval using curry.
]]]


TODO rewrite disorganized text...

A redesign of https://github.com/benrayfield/wikibinator104/ to directly allow vararg, instead of putting it in linkedlists, for efficiency. This is a big change to the universal function so is its own project. TODO copy parts from wikibinator104's readme etc. See Op.java

and pattern calculus functionfunction thats a lambda, combinator, and pattern ca thats also a pattern calculus function.

What makes the internet work, this is its purest simplest form, a kind of number that any 2 of them create or find 1 more number, and so on,

(TODO) A kind of number that any 2 of them create or find another number and so on, that millions of 
Scalable gaming-low-lag p2p wiki of 1 editable universal function for millions of people and AIs to build and play together, safely sandboxed but not dumbed down, 
a universal lambda and pattern calculus function of infinity/vararg params with opcode in the first few, GPU optimizable, turing-complete-challenge-response, godel-quality-self-reference (wiki can call itself recursively, emulate itself, etc), safely sandboxed across millions of untrusted computers. (TODO)

For ages 0 to expert. It will teach you how itself and every known kind of math works and to build fun games and useful tools and play and work together, from the basics of math counting on your fingers up to the most advanced AI and number crunching and scaleable systems deployed in realtime, and for millions of people to be able to do that together in p2p without anyone being above or below anyone else, just merges and forks in binary forest of immutable objects viewed any way you like as functions can be built to view functions in new ways. (TODO).

Later it will create its own graphics per pixel (around 60 times per second) and sounds per wave amplitude (around 20000 times per second), with drag-and-drop function onto function to find/create function, and other intuitive ways of using it. Here's how it might look when used as a programming language...

```
λ //is the universal function, also often named u or leaf or Λ. In java λ.java is the main object type.

//pair, typeval, l, r, t, f, i, isleaf, curry, wiki, ax are functions you can make with λ.

(x y) //is x called on y.

(x y z) //is ((x y) z)

{x y} //is (s x y)

{x y z} //is (s {x y} z)

[x y] //is (pair x y)

[x y z] //is [[x y] z]. If you want a linkedlist you have to write the λ like in [λ x y z].

<x y> //is (pair x y), but [x y] is how its normally displayed if theres only 2.

<w x y z> is [w <x y z>] is [w [x [y z]]].
If you want a linkedlist you have to write the λ like in <w x y z λ>.

,x means (t x). ,,,x means (t (t (t x))).

*x means (curry x), such as in (*[λ "add 3 doubles" {...func body...} ,,λ] 5 10 100.3) -> 115.3

+ddd = *[λ "add 3 doubles" {...func body...} ,,λ]
(+ddd 5) -> *[λ "add 3 doubles" {...func body...} 5 ,λ]
(+ddd 5 10) -> *[λ "add 3 doubles" {...func body...} 5 10 λ]
(+ddd 5 10 100.3) -> 115.3
(+ddd x y z) -> ({...func body...} [λ "add 3 doubles" {...func body...} x y z])
(l +ddd) -> curry
(r +ddd) -> [λ "add 3 doubles" {...func body...} ,,λ]
(r (r +ddd)) -> ,,λ
//FIXME some of these skipped the (pair x) in (pair x y), so you could use car and cdr.
(r (r (r +ddd))) -> ,λ
(l (r (r +ddd))) -> t
(l (r +ddd)) -> [λ "add 3 doubles" {...func body...}]
(l +ddd (r +ddd)) -> +ddd //(l x (r x)) -> x forall x,
	if x is clean, else you have to use L and R which handle clean and dirty.
	
There is no built in double or string math. Everything has to be derived
from the universal function called on itself in various combos,
such as emulating the bits of IEEE754 double multiply math and utf8/unicode, then optimizing
it in an Evaler.java (to use double hardware ops or LWJGL OpenCL GPU (in lazycl)) which is where
compiled functions of (long maxSpend, function func, function param)->longRemaining_and_function go.

(ax  115.3 (*[λ "add 3 doubles" {...func body...} ,,λ] 5 10) 100.3) //means ret<-(func param) is true.

//(wiki x) is defined only by the statements made by ax such as (ax "world" wiki "hello")
causes "world"<-(wiki "hello") to be true, but in theory it can also pattern match or have
things built into some of the VMs based on belief in those patterns (will be tested automatically
in realtime, that it doesnt have more than 1 unique returnVal for same function call anywhere in
the network) such as a "spend" call to limit compute resources nondeterministicly and recursively
or a "solve" call where (solve x) -> y where (x y)->λ for any y thats a solution of x
(though that will often not find solutions, so you'll probably want to use it inside a spend call
to limit how long it will look and how much memory it might use while looking etc.

"add 3 doubles" is (typeval "text/plain" bytes_of_the_string_add_3_doubles), except first param
of typeval can be just the bits without having to wrap it in a typeval.

115.3 is (typeval "double" those_64_bits), compared to if you just want those_64_bits for
efficiency it would be displayed some other way and without implying its a double.

//TODO some prefix that means clean vs dirty? Which should be the one that doesnt need a prefix?
//Maybe ?x means dirty x and /x means clean x
//and x by itself means whatever clean vs dirty its written inside
//such as ?[/+ddd (b c) /(y z) /200] is dirty but contains whatever clean /(y z) returns
//and clean /200 and clean /+ddd and whatever the ?(b c) dirty call returns.
//Inside a clean call, its either halted and clean or not halted
//and may have dirty things to truncate to clean before using them.
//λ takes 7 params. ax evals at 6 (verifies constraint) and 7 params, everything else just at 7.
//Clean calls start with (λ λ). Dirty calls start with (λ anything_except_λ) such as (λ (λ λ)).
//Theres 6 params after that,
//which choose between pair, typeval, l, r, t, f, i, isleaf, curry, wiki, ax etc,
//then the params of those opcodes, so the prefix and opcode params always sum to 7 params
//when its time to eval (or 6 params for ax to verify constraint), and less params is waiting.
//For example, s takes 3 params, and i takes 1 param, and (s i i) calls a param on itself,
//and (s i i) is waiting on 1 param, and (s i i wiki) -> (wiki wiki),
//and (s i i (s i i)) -> (s i i (s i i)) which is an infinite loop,
//but if you call some lazy form of (s i i (s i i)) inside a spend call it will
//limit the amount of compute resources (time, memory, num of compiles, network, etc)
//before giving up on (s i i (s i i)) or may know its not going to end within that limit
//so not even spend the compute resources on it as soon as it knows it cant do the requested work.
```

------

TODO rewrite confusing text below...

Dovetailing is needed cuz (L x (R x)) equals x, forall x, and L and R are its childs in the binary forest of call pairs which is how all possible functions are made. Somewhere inside that is a bloom-filter, which is one of the ways of using the axiomOp (Op.ax), which accumulates true statements (and statements which can contradict them, but shouldnt ever generate contradictions (then have to fix them) if everyone follows the protocol), so cant let the lambdas just generate any statements they want in axiomOp, only true statements. But for every statement, you can call (L statement) to get its left child (a function), and call (R statement) to get its right child (a function), and can call any 2 functions on eachother to create (a or find an existing) function. If you take such a true statement twoPlusTwoEqualsFour then (L twoPlusTwoEqualsFour (R twoPlusTwoEqualsFour)) equals twoPlusTwoEqualsFour. Or similarly (L L (R L)) equals L. But what if you try to create a false statement by taking 2 things that dont seem to fit together? Dovetailing is a way to loop through all possible true statements which can be proven in any finite time and memory. An example of a statement which may take infinite time and memory to prove is does P equal NP or not. An example of a statement that takes finite time and memory to prove is what would the weather do next week based on its current movements and a given lambda based model of physics, or is this or that a picture of a cat, or whats the 1 trillionth prime number, or basically anything any computer can do in practice but only including parts of the deep abstractions that mathematicians get into. Wikibinator will actually do dovetailing, but it can only do a small amount of it cuz its like a chess game tree of the space of all possibilities, of lambda functions. The more important use of dovetailing in this case is to define, in abstract math at least, what calculation to do when there are many possible ways to generate the same true statement and you dont know which one the VM is optimized to nondeterministicly use, so that can be used as an id in the system for (eventually automatic but for now manual) formal-verification purposes to create a Compiled.java instance to look in the VM's hashtables for does it have the relevant objects needed to do the next debugStepInto (calculate recursively into detail) or debugStepOver (needs a cache of [func param return] for a given [func param] call, which many of those accumulate and get uncached in order of least recently used) or other proof based data structures where theres many possible ways to prove new true statements using combos of existing true statements (such as (paintMustacheOn picOfCat)->aPicOfACatWithAMustache) would be a [func param return] cache, similar to (paintMustacheOn 42)->someConstantMeaningItDoesntKnowWhatToDo is a [func param return] cache, and is a statement in axiomOp. So if you call (L aPicOfACatWithAMustache (R aPicOfACatWithAMustache)) it equals aPicOfACatWithAMustache, nomatter what kind of thing the aPicOfACatWithAMustache is, but if you try to fake the creation of true statements from other true statements, the dovetail proof wont be able to match the Compiled.java instance to it so would have to actually do the dovetailing or whatever lesser optimizations it can come up with, and would one way or another do the exact calculation that the universal function says to, normally by looking in VM's hashtables for the relevant other true statements or more often just simple halted lambdas derived from combos of other halted lambdas.


/** l/getFunc. TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	(l x) is left child of x in the binary forest of call pairs.
	Not the same as lispCar since pair is the church-pair lambda.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	getFunc("(λ λ λ λ λ λ λ) FIXME uΩuuul",false,false,1),
	GetFunc("∩Ωuuul",false,false,1),
	
	/** r/getParam. TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	(r x) is right child of x in the binary forest of call pairs.
	Not the same as lispCdr since pair is the church-pair lambda.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	getParam("(λ λ λ λ λ λ (λ λ)) FIXME uΩuu∩r",false,false,1),
	GetParam("∩Ωuu∩r",false,false,1),
	
	//FIXME TODO: l/getFunc and r/getParam differ by only 1 opcode bit (being leaf vs anything_except_leaf*)
	
	/** a/isLeaf. TODO copy comments from wikibinator104 and maybe modify them...
	<br><br>
	(isLeaf x) is t or f depending if x is the leaf which all paths in the binary forest of call pairs lead to
	aka the wikibinator104 universal function itself.
	isLeaf, l, and r make this a "pattern calculus function".
	*/
	isLeaf("(λ λ λ λ λ (λ λ) λ) FIXME uΩuu∩a",false,false,1),
	IsLeaf("∩Ωuu∩a",false,false,1),
	
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
	wiki("(λ λ λ λ λ (λ λ) (λ λ)) FIXME uΩuu∩w",false,false,1),
	Wiki("∩Ωuu∩w",false,false,1),
	
	/** λy.λz.y aka true. (pair b c tru) is b. Is the K lambda of https://en.wikipedia.org/wiki/SKI_combinator_calculus */
	tru("(λ λ λ (λ λ) λ) FIXME uΩu∩t",false,false,2),
	Tru("∩Ωu∩t",false,false,2),
	
	/** λy.λz.z aka false aka f. (fal λ) is identityFunc aka λz.z. (pair b c fal) is c. */
	fal("(λ λ λ (λ λ) (λ λ)) FIXME uΩu∩f",false,false,2),
	Fal("∩Ωu∩f",false,false,2),
	
	/** λx.λy.λz.xz(yz) aka ((xz)(yz)). Is the S lambda of https://en.wikipedia.org/wiki/SKI_combinator_calculus */
	trecurse("uΩ∩s",false,false,3),
	Trecurse("∩Ω∩s",false,false,3),
	
	/** λx.λy.λz.zxy. Is the church-pair lambda and lispCons. */
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
	
	
	


/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator104.spec;
import java.util.function.UnaryOperator;

/** immutable. binary forest node, defined ONLY by its forest shape, with no data in each node
except caches which can be derived from that shape.
A 7 param universal pattern combinator
(aka combinator, universal lambda function, and pattern calculus function)
whose first param is isDirty (leaf is clean, any nonleaf means dirty, which will be cached to
swap between clean vs dirty in bigo1), and leaf itself is clean.
The other 6 params are mostly the same as in earlier wikibinator versions
but will reorder some opcodes and put in
(axiomop ret param func) being halted if (func param)->ret
and (axiomop ret param func ignore) does infloop and that "ignore" param is the 7th of 7 params,
so unlike earlier wikibinator versions, it can infloop at the second last param,
which is similar to occamsfuncerV2's curry op which used its second last param for types that way.
As of 2021-1-21 github.com/benrayfield/occamsfuncer is version 3 but doesnt say what its version is,
and it doesnt have that infloop in curry behavior cuz its an axiom related thing thats hard to prove things about. 
*/
public interface λ extends UnaryOperator<λ>{
	
	//"wikibinatorDovetailForDebugstepoverAndDebugstepintoCuzThoseAreManyPathsToTheSameFuncparamreturnAndNeedDovetailingSo(L x (R x))EqualsXForXIs(axiomOp ... stepstuff)"
	
	//TODO copy designs from "wikibinator102Designing2021-1-21+"
	//	wikibinator104_usesTheDesignsIn(wikibinator102Designing2021-1-21+)WhichIsBadlyNamed
	
	/** isLeaf */
	public boolean a();
	
	/** func/L child, of the 2 childs in binary forest */
	public λ l();
	
	/** param/R child, of the 2 childs in binary forest */
	public λ r();
	
	/** callpair of this and param, without checking if thats a valid thing to do,
	since (todo choose a design?) only halted nodes are allowed.
	*/
	public λ p(λ r);
	
	/** lambda call (eval this on) by eager eval. For lazy eval (todo choose a design?) use this.p(λ)??? */
	public λ e(λ r);
	
	/** Returns the same return as e(λ),
	if it has enough gas (compute resources such as time and memory and num of compiles),
	and the $λ.gas is how much of that maxSpend remains (was not spent).
	FIXME how should it say theres lots of gas left but it didnt spend that part cuz it knew there
	wasnt enough to finisht he calculation? Should $λ.fn be null?
	<br><br>
	Nondeterministic, normally called thru wiki opcode, which will
	have various nondeterministic opcodes inside it such as spend and (solve salt x) -> any y where (x y)->leaf,
	and will deterministicly fork 1 of 2 ways recursively a salt param (128 or 256 bits?)
	in most nondeterministic calls so it becomes very unlikely to ever repeat those calls
	since the same func and param must always give the same return or its return may not be known,
	and it needs to do those calls multiple times in the forest, temporarily (such as in .00001 second),
	cuz the S lambda (Op.s) is the main kind of controlFlow and (s x y z)->((x z)(y z)) aka (x z (y z)),
	and those (x z) and (y z) often do the same call multiple times using the <func param return> caches
	which will be in (Op.ax ret param func) or in optimizations of that in hashtables in the VM.
	*/
	public $λ e(long maxSpend, λ r);
	
	/** lambda call (eval this on) by eager eval. For lazy eval (todo choose a design?) use this.p(λ)??? 
	UnaryOperator<λ>, but I want the func names used very often to have short names like e(λ) instead of apply(λ).
	*/
	public default λ apply(λ r){
		return e(r);
	}
	
	/** cache of 8 of (isLeaf this) .. (isLeaf this.l.l.l.l.l.l.l)
	or FIXME is it (isLeaf this.r) .. (isLeaf this.l.l.l.l.l.l.l.r)
	since a currylist is for example (u u (u u) u (u u) ...params...),
	and want to know which things before ...params... are` u vs anything other than u.
	Might need to pad a 1 bit to mark a bitstring size of 0..7 bits like did in an earlier wikibinator version
	called the "op byte"??? If so, rename this to opByte.
	*/
	public byte isLeafsByte();
	
	public default λ clean(){
		throw new RuntimeException("TODO returns a forkEdit of this where first param after leaf is leaf, unless it already is. TODO optimize this in Simpleλ by each keeping a ptr to the opposite clean/dirty.");
	}
	
	public default λ dirty(){
		throw new RuntimeException("TODO returns a forkEdit of this where first param after leaf is (leaf leaf), unless it already is something other than leaf. TODO optimize this in Simpleλ by each keeping a ptr to the opposite clean/dirty.");
	}
	
	public EvalerChain compiled();
	
	public void setCompiled(EvalerChain c);
	
	/** If this is a cbt (complete binary tree of pair of pair... of t vs f),
	either as pure interpreted lambdas or a wrapper of a byte, long, float, array, nio Buffer, etc,
	then view it as Blob (immutable).
	TODO can this be null?
	*/
	public Blob blob();

}



/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator104.impl.optimize;

/** opcode is double. all possible opcodes are valid for all possible double[] mem states.
This would only be an optimization of some combo of calls of the universal func which emulates this in a cbt,
and this is not part of the spec, nor does it contradict the spec to optimize what the spec does
only when it happens to match this, which it may often happen to match it when
user level code is designed knowing that this optimization is in some VMs and maybe not in other VMs.
Eventually, optimizations will be compiled automatically instead of manually written,
though this can optimize a huge variety of possible things as it is turingComplete,
just not nearly as sparse as the usual function pointers.
*/
public strictfp class SomeKindOfAssemblyLikeLanguageInDoubleArrayWith2Registers {
	
	/** ((int)double[IP])&mask is instructinPointer */
	public static final int IP = 0;
	
	/** ((int)double[SP])&mask is stackPointer */
	public static final int SP = 1;
	
	/** you may want to use double[HALT] as 0 to continue and any nonzero value (or cast to int is nonzero?) to continue,
	of at most maxSteps number of steps in param of steps func???
	*/
	public static final int HALT = 2;
	
	public static double get(double ptr, double[] mem){
		return mem[ptr(ptr,mem)];
	}
	
	public static double get(int ptr, double[] mem){
		return mem[ptr(ptr,mem)];
	}
	
	public static void put(double ptr, double val, double[] mem){
		mem[ptr(ptr,mem)] = val;
	}
	
	public static void put(int ptr, double val, double[] mem){
		mem[ptr(ptr,mem)] = val;
	}
	
	public static double opcode(double[] mem){
		return get(mem[IP],mem);
	}
	
	public static int ip(double[] mem){
		return ptr(mem[IP],mem);
	}
	
	public static int sp(double[] mem){
		return ptr(mem[SP],mem);
	}
	
	public static void jump(double ptr, double[] mem){
		mem[IP] = ptr;
	}
	
	/** masked safe ptr, IF mem.length is powOf2.
	<br><br>
	System.out.println("nan"+(0./0.)+" asint:"+(int)(0./0.));
	System.out.println("inf"+(1./0.)+" asint:"+(int)(1./0.));
	System.out.println("-inf"+(-1./0.)+" asint:"+(int)(-1./0.));
	System.out.println("ok");
	nanNaN asint:0
	infInfinity asint:2147483647
	-inf-Infinity asint:-2147483648
	ok        
	*/
	public static int ptr(double ptr, double[] mem){
		return ((int)ptr)&(mem.length-1);
	}
	
	/** masked safe ptr, IF mem.length is powOf2 */
	public static int ptr(int ptr, double[] mem){
		return ptr&(mem.length-1);
	}
	
	public static void push(double val, double[] mem){
		int sp = ptr(mem[SP],mem);
		int nextSp = ptr(++sp,mem);
		mem[SP] = nextSp;
		mem[nextSp] = val;
	}
	
	public static int popInt(double[] mem){
		return (int)pop(mem);
	}
	
	public static double pop(double[] mem){
		int sp = ptr(mem[SP],mem);
		double ret = mem[sp];
		mem[SP] = ptr(--sp,mem);
		return ret;
	}
	
	public static void nextIp(double[] mem){
		mem[IP] = ptr(mem[IP]+1,mem); //have to mask it so it doesnt overflow eventually, even though its masked on every read
	}
	
	public static final byte
		memMask = 'm',
		memSize = 'M',
		mul = '*',
		neg = '-',
		add = '+',
		intAnd = '&',
		intOr = '|',
		intXor = '^',
		intNot = '~',
		intNand = 'n',
		intNor = 'N',
		intMinorityOf3 = '3', //like nand and nor, this is an NP op
		intShift = 'TODO choose a symbol for shift, and does it need upshift and downshift op or 1 op for all of it, and should it have a rotate op vs just do it with shifts ands and ors etc',
		write = 'w',
		read = 'r',
		jump = 'j';
	
	/** a little slower than step(double[]) which doesnt check isHalted(double[]) */
	public static void stepIfNotHalted(double[] mem){
		if(!isHalted(mem)) step(mem);
	}
	
	/** Modifies double[] mem. Does a bigO(1) amount of work.
	mem.length must be a powOf2 and must be at least 2, normally much bigger.
	TODO check for that vs let it be caller's responsibility?
	*/
	public static void step(double[] mem){
		double opcode = opcode(mem);
		if(opcode <= 0){
			//If opcode is nonpositive then push (neg)it as literal on stack. If want to push the opposite literal,
			//do that then use NEG opcode, so 2 opcodes.
			push(-opcode, mem); //push positive
			//TODO should opcodes be neg and this be positive? I expect to push positives more often.
			//TODO should int ops have a signed and unsigned version?
		}else{
			switch((byte)opcode){
			case memMask:
				push(mem.length-1,mem);
				nextIp(mem);
			break;
			case memSize:
				push(mem.length,mem);
				nextIp(mem);
			break;
			case mul:
				push(pop(mem)*pop(mem),mem);
				nextIp(mem);
			break;
			case neg:
				push(-pop(mem),mem);
				nextIp(mem);
			break;
			case add:
				push(pop(mem)+pop(mem),mem);
				nextIp(mem);
			break;
			case intAnd:
				push(popInt(mem)&popInt(mem), mem);
				nextIp(mem);
			break;
			case intOr:
				push(popInt(mem)|popInt(mem), mem);
				nextIp(mem);
			break;
			case intXor:
				push(popInt(mem)|popInt(mem), mem);
				nextIp(mem);
			break;
			case intNot:
				push(~popInt(mem), mem);
				nextIp(mem);
			break;
			case intNand:
				push(~(popInt(mem)&popInt(mem)), mem);
				nextIp(mem);
			break;
			case intNor:
				push(~(popInt(mem)|popInt(mem)), mem);
				nextIp(mem);
			break;
			case intMinorityOf3:
				int x = popInt(mem), y = popInt(mem), z = popInt(mem);
				push(~(x&y)^(y&z)^(z&x), mem);
				//This is an NP op, similar to NAND and NOR. Its output is 1 vs 0 equally often, given random inputs.
				//NOT OF: "maj := (a and b) xor (a and c) xor (b and c)" -- https://en.wikipedia.org/wiki/SHA-2
				nextIp(mem);
			break;
			case write:
				put(pop(mem), pop(mem), mem); //FIXME reverse order of those 2 pops? Is address on top of stack or second from top?
				nextIp(mem);
			break;
			case read:
				push(get(pop(mem),mem),mem); //FIXME order of these ops?
				nextIp(mem);
			break;
			case jump:
				jump(pop(mem),mem);
			break;
			TODO other basic math ops etc.
			TODO ops (in range 1..127) for around 32 local memory slots to read and write into
				from stack andOr to copy andOr swap between
			the top n parts of stack (all masked so all ops are valid for all possible double[] mem states)
			TODO a form of this that works with undomem and concat of cbts during it
			andOr just a form of it that runs n steps, or runs until a certain index contains value 0 (halt condition),
			then do concat and subrange as cbts then do more of this on such concats/subranges etc,
			and use that to build some simple graphics and game demos, proofOfconcepts that
			the lambdas can be optimized, even though there are far better optimizations to do later.
			}
		}
	}
	
	public static boolean isHalted(double[] mem){
		return mem[HALT] != 0;
	}
	
	public static void steps(long steps, double[] mem){
		while(steps-- > 0) step(mem);
	}
	
	public static void stepsOrChoosesToHalt(long maxSteps, double[] mem){
		while(!isHalted(mem) && maxSteps-- > 0) step(mem);
	}
	
	/** its recommended to use stepsOrChoosesToHalt instead cuz this might never halt,
	but stepsOrChoosesToHalt(...) and steps(...) always halt.
	*/
	public static void stepsUntilChoosesToHalt(double[] mem){
		while(!isHalted(mem)) step(mem);
	}
	
	public static void main(String[] args){
		TODO test this by using it to compute low resolution mandelbrot, and display it in a window
		(use ScreenUtil andOr BufferedImage andOr StretchVideo I have that code somewhere)
	}

}
	
