package wikibinator105.impls.marklar105b.evalers;
import java.util.function.BiFunction;
import wikibinator105.impls.marklar105b.*;
import wikibinator105.spec.*;

/** TODO lazy loads parts of Wiki as needed, and TODO writes to Wiki
using (Op.Ax u (Op.Fpr Op.Wiki param ret)) which implies (Wiki param)->ret everywhere in p2p network
or just local to this computer or wherever sparse parts of it are shared and forked and merged blockchain-like
but a web not a blockchain as theres an infinite number of paths from and to each possible 2 states,
unlike a blockchain where theres only 1 main branch or sidechains where theres n main branches,
every id256 is a sidechain so wikibinator105 creates about 1 million sidechains per computer per second,
or many billions or low trillions of sidechains per second per computer if counting GPU calculations
but you might say it doesnt count as a sidechain until the id256 is created
or until another computer across network sees the same id256, in which case
it would be only around 10k-1million new sidechains per second per computer in p2p network.
Also, you dont have to create id of everything to tell other computers how to generate those ids,
as it can be described by any cross-section of nodes and telling which to callpair on eachother,
like (idC ((idA (idC idB)) idA)) doesnt have to give the id of (idC idB)
or the id of (idA (idC idB)) if it just gives the text "(idC ((idA (idC idB)) idA))"
and gives those 3 ids, or some compressed form of it similar to how how s-expressions
might normally be shared efficiently, but optimized for forest of callpairs.
*/
public class WikiState{
	
	/** you can replace this with anything you believe is a better approximation of the Wiki.
	This will include "wallet" and "spend" and "solve" functions, and maybe halting-vs-nonhalting related functions
	related to the "NSAT layer" which is below the "lambda layer" and uses λColor... a few things like that,
	and "mutableWrapperLambda" such as using ed25519 or ed448 publicKeys (generated by password automatically)
	to digitallySign (digsig) things, or like normal pages on wikipedia, or like the basics of math that
	actually work to compute things and build up from there, or whatever people and AIs might find useful
	to put inside a turingComplete wiki,
	like (ed25519Sign "my password..." ["the wiki is" Wiki]) -> (AxA (myPublicKey sig ["the wiki is" Wiki]))
	aka a proof that (myPublicKey sig ["the wiki is" Wiki] u)->u
	and myPublicKey is a function that anyone can verify that it is a verifier of an ed25519 sig and message...
	So if you write (ed25519Sign "my password has cat hair inside a solid block of wood how did it get there")#aliceSays
	and (getPubKeyOutOf (aliceSays "just getting my pubkey"))#alice
	and write (aliceSays ["hi " bob]) then bob might write (bobSays ["got your message " alice]),
	of course after both those publickeys are published, and the passwords are not published,
	and the privateKeys are generated every time (using AxfprCache to not repeat the calculation) they're used,
	and if you want a key store you derive it using whatever kind of math you trust
	or something built by others as lambda functions that you trust to generate a key store,
	or whatever tools we need we build by calling the universal function on itself in various combos.
	Since aliceSays, bobSays, alice, and bob are just local names and dont affect id256 of those nodes,
	Anyone may name anything whatever they like, and if they want to share names then use a treemap (todo derive it)
	which is efficiently forkEditable and trie-like (skips levels where theres only 1 path instead of 2)
	and has an id256 (lazyevaled of course) and generates the same id256 for the same set of key/value mappings
	regardless of order of gets and puts,
	<br><br>
	but other than those, things in Wiki will in general be any (Op.Ax u (Op.Fpr Op.Wiki param ret)),
	which is more general than Wiki such as (Op.Ax u (Op.Fpr L ["abc" "def"] (pair "abc")).
	*/
	public static BiFunction<Long,fn,$<fn>> bestKnownApproximationOfSparseSubsetOfWiki = (Long gas, fn param)->{
		return new $(gas,null);
	};

}
