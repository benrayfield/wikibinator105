/** Ben F Rayfield offers this software opensource MIT license */
package wikibinator105.spec;

/** sync levels in order of increasing difficulty of sync,
slCleanAxless being easiest and most reliable, then slCleanWithAx,
then slDirty is hardest to sync but also needed for wiki to do anything nontrivial.
Work up the levels gradually, in experiments, both in single computer and sync in p2p network,
and if theres a sync problem, retreat to lower levels and explore from there
to find what went wrong.
In slCleanAxless and slCleanWithAx, every node has a specific correct color,
known before you even run the system, all infinity possible nodes in the space of all
possible turing completeness. Its deterministic. Thats what makes it clean.
slDirty allows the colors to depend on eachother in an NSAT way,
and the correct color per node depends on the wiki state,
but the wiki state is so big it cant be stored
so is only stored in pieces by example such as (ax (fpr wiki "hello" "world"))
would mean (wiki "hello")->"world", or (ax (fpr wiki [wiki "is itself and that is" wiki] "anything"))
would mean (wiki [wiki "is itself and that is" wiki])->"anything".
These func param return statements can be checked in many combos,
using the design that (L x (R x)) equals x forall x,
such as (L [wiki "is itself and that is" wiki] (R [wiki "is itself and that is" wiki]))
equals [wiki "is itself and that is" wiki], and (L L (R L)) equals L, and (L ax (R ax)) equals ax,
and various kinds of emulators could be built inside the system (as user level code, not in VM)
to further verify from many redundantly cross referenced contexts
that the func param return statements are true,
though this can only converge statistically and is not guaranteed to find the exact solution,
so keep it denser of many overlapping explorations of the functions
to verify stronger or more sparse for more efficiency.
*/
public enum SyncLevel{
	
	UPDATE: getting rid of color and instead will have 2 (or maybe 3) kinds of Op.ax:
	(axA x) and (axB x) cant both exist.
	(axA x) is halted if (x u)->u.
	(axB x) is halted if (x u) -> anything except u.
	//Maybe, (axC x) is halted if (x u) does not halt, but I'm unsure if should have an axC.
	(axA x y) -> (x (T y))
	(axB x y) -> (x (F y))
	How would that be detected? A lambda could generate a hash thats the same for (axA x) and (axB x),
	for any x, but different for axA vs axB, or something like that.
	(details to work out on whats a normed form)
	But what to do if theres BULL in it such as (axA (pair s pair)) and (axB (pair s pair)) exist
	somewhere reachable from the same node?
	
	/** forest shape seen deeply thru l() and r() does not contain a call of ax,
	but can contain ax itself (of 0 params after ax).
	This is only to measure does a certain node contain any (ax anything) or not.
	A call of this function, or another function called on this,
	may lead to slCleanWithAx, but cant lead to slDirty cuz clean cant lead to dirty.
	Only dirty can lead to dirty. Dirty can also lead to clean.
	If clean is called on dirty, its truncated to clean before the clean func uses it.
	Such truncating is by changing the first param of leaf/u to leaf/u
	which is the prefix of clean, compared to (u (u u)) or (u "hello")
	aka (u anything_except_u) is a prefix of dirty.
	*/
	slCleanAxless,
	
	/** Clean but allows (ax param) seen deeply thru l() and r(),
	such as (ax (fpr (pair x y) T x)) meaning (pair x y T)->x
	In clean, all calls of wiki eval to (S I I (S I I)) which is an infinite loop.
	*/
	slCleanWithAx,
	
	/** wiki behaviors are nondeterministicly chosen by (ax (fpr wiki x y)) to mean (wiki x)->y */ 
	slDirty,
	
	/** if λ.color().size()!=1 (> 1 is Bull, ==0 is Unknown) then it cant be seen at lambda level, only at NSAT level below it.
	OLD...
	if it contains any λColorTruthValue.bull, λColorTruthValue.unknown, or λColorTruthValue.wordsalad,
	then it cant be seen at the lambda level but can be seen at nsat level.
	Things that contain λColorTruthValue.unknown can be a part of something lambda can see,
	but lambda can only see things that in l() and r() deeply all the way down to leaf
	everything is λColor.proof, λColor.disproof, or λColor.normal,
	and nothing is λColor.wordsalad since thats only if it doesnt halt,
	and nothing is λColorTruthValue.bull cuz that only happens if a node has more than 1 color,
	and nothing is λColorTruthValue.unknown cuz whatever it can see thru l() and r() have a specific λColor.
	The nodes above it (of bigger height, which there are infinity of them) are all unknown from here
	since they're not reachable thru l() and r() deeply.
	*/
	slLambdaCantSee;
	
	/** todo need more synclevels for things that cant be seen at lambda level but can at nsat level and bloom syncing???...
	Here's some things from wikibinator105.impl.ids.HeaderBits 2021-2-4 but I'm still designing it,
	(and λColorTruthValue has unknown and bull)...

	bloomProof
	
	bloomDisproof
	
	bloomWordsalad
	
	bloomNormal
	
	anyBull(1)
	
	anyAxCall(1)
	
	anyWordsalad(1)
	
	anyUnknown
	
	allUnknown
	
	allUnknownBelow
	*/

}
