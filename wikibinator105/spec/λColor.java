package wikibinator105.spec;

/** (axType typeandinstance) calls (typeandinstance u) to get an even height or an odd height
return value (so (axType typeandinstance) is colorAxEven or colorAxOdd) or if somehow its known
that it doesnt halt (mostly its there just for abstract math completeness) then colorAxNonhalt,
or if the node's left is not axType then its color is colorNormal.

"Create typeOfListOfPrimeSize and typeOfListOfNonprimeSize with it, given a func isPrimeSizeList
and debugStepOver etc" will be done by (axType typeandinstance) that contain typeOfListOfPrimeSize
or typeOfListOfNonprimeSize and
(axType typeandinstance x)->(typeandinstance (t x)) which is a normal call
since (axType typeandinstance) already has its 1 of 4 colors and if it wants to reproduce that
it just calls axType on itself again and can see itself by being in an op curry.
*/
public enum λColor{
	
	colorAxEven,
	
	colorAxOdd,
	
	colorAxNonhalt,
	
	colorNormal;

}
