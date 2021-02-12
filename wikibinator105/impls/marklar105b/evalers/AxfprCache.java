package wikibinator105.impls.marklar105b.evalers;
import java.util.HashMap;
import java.util.Map;
import wikibinator105.impls.marklar105b.fn;

public class AxfprCache{
	
	public static int cacheEntries(){
		return callToRet.size();
	}
	
	public static void garbcol(int numCacheEntries){
		int garbcolDownToNumOfCacheEntries = Math.max(0,cacheEntries()-numCacheEntries);
		while(cacheEntries() > garbcolDownToNumOfCacheEntries){
			//loop over the map of Call to Ret in order of Ret.garbcolOrder,
			//and garbcol enough of them that maybe around 1/3 of the available memory
			throw new RuntimeException("TODO");
		}
		//System.gc();
	}
	
	public static fn getOrNull(fn func, fn param){
		Call call = new Call(func,param);
		Ret r = callToRet.get(call);
		if(r == null) return null;
		touch(r);
		return r.ret;
	}
	
	public static void put(fn func, fn param, fn ret){
		callToRet.put(new Call(func,param), new Ret(ret));
	}
	
	/*
	//TODO garbcol in order of Ret.garbcolOrder but only of those not reachable,
	//so might need to use WeakReference or Runtime get amount of memory or something.
	Maybe use a SortedMap and touch(Ret) removes from the map, changes Ret.garbcolOrder, then adds to map,
	where map is sorted by Ret.garbcolOrder.
	That would solve the order problem, but not the problem of
	how to know which are reachable at JVM level.
	Might need to create a Pointer class thats has a long localId and a WeakReference (or is a WeakReference?)
	to the λ which also knows its long localId, and use localId instead of == to compare and hashcode them.
	Or maybe this cache should prevent garbcol (not use any weakreference) and just watch the amount of memory
	and when it gets low garbcol from the low end of Ret.garbcolOrder.
	Also, must consider NIO memory (such as lwjgl opencl uses),
	but that can be isolated in MultiPool (or what was the "pool" class name in lazycl?)
	and in stack during OpenclUtil.callOpencl and .callOpenclDependnet and maybe in Mem.java instances.
	*/
	
	private static Map<Call,Ret> callToRet = new HashMap();
	//private static SortedMap<Call,Ret> callToRet = new TreeMap();
	//FIXME SortedMap is sorted by key, but want it sorted by Ret.garbcolOrder
	
	private static final class Call{
		
		public final fn func, param;
		
		public final int hash;
		
		public Call(fn func, fn param){
			this.func = func;
			this.param = param;
			hash = System.identityHashCode(func)-System.identityHashCode(param);
		}
		
		public int hashCode(){ return hash; }
		
		public boolean equals(Object o){
			if(!(o instanceof Call)) return false;
			Call c = (Call)o;
			return c.func == func && c.param == param;
		}
		
		/*TODO??? hashcode and equals, and make sure the hashcode
		uses strongrand salts generated once per JVM run so if something causes hash collisions (32 bit) in one computer
		it will very probably not in another computer, and same for every time you restart the wikibinator105 VM
		copy some code from occamsfuncer's CacheFuncParamRet.java.
		Actually, that only matters for id256s, as this cache works even before those are generated (if they ever are)
		so uses System.identityHashCode...
		*/
		
	}
	
	private static long max;
	
	private static void touch(Ret r){
		r.garbcolOrder = ++max;
	}
	
	private static final class Ret{
		
		/** garbcol (garbage collect) in order of least recently used, of those which have no incoming pointers.
		this might be utc nanoseconds, or just a counter. When use this in a Map.Entry<Call,Ret>,
		update this long to be bigger than all other such longs. 
		*/
		public long garbcolOrder;
		
		public final fn ret;
		
		public Ret(fn ret){
			this.ret = ret;
			touch(this);
		}
	}

}