package wikibinator105.plugins.sql_todoShouldThisBePartOfAnImplInsteadOfAPlugin;

/** TODO_sqlFileToCreateTableWith3Id256Cols1IsPrimaryKeyAnd2AreForeignkeyOfSameTablesPrimarykeyToPreventGarbcolWhileReachable
<br><br>
The most basic use of this is to store a 2-way forest of id256s in a sql database
instead of each being a file on harddrive which is wasteful cuz block sizes are much bigger than 256*3 bits.
Could also put in some stuff for getting many in parallel n levels deep from some starting set of them,
to reduce number of sql calls.
<br><br>
An alternative to this is just to store blocks of 256*3 bits in a file,
and keep appending them to that file, and copy to a new file when want to garbcol/garbageCollect,
and could sort such a file by the first of those id256 in each 3: parentId leftChildId rightChildId,
so could binarySearch it, or theres various kinds of hashtables could use in a RandomAccessFile.
TODO also create that in a different plugin than wikibinator105.plugins.sql.
<br><br>
Technically a sql query could do 1 compute step of all nodes in the system at once called on all of eachother,
and generate every possible function if did that in a loop, but it expands exponentially so is impractical,
also would have to define the hash function (such as including the last 192 bits of sha3_256) as part of the query,
but could make an Evaler instance that uses sql, however slowly.
*/
public class TODO{

}
