/**
 * 
 */
package symboltable;

/**
 * Symbol Table Elementary Implementation
 * Parallel Array Implementation and perform Binary Search
 * Data Structure: Maintain parallel arrays for keys and values, keys are comparable, sorted by keys.
 * Search: Use binary search to find key.
 * Insert: Use binary search to find place to insert; shift right all larger keys over one place. 
 * @author Junwei
 * @see ST
 * @version 1.0.0
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> implements ST<Key, Value>{
	
	//Keys Array
	private Key[] keys;
	
	//Values Array
	private Value[] values;
	
	private int size;
	
	/**
	 * 
	 */
	public BinarySearchST() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void put(Key key, Value value) {
		// TODO Auto-generated method stub
		
	}
	
	//Binary Search
	@Override
	public Value get(Key key) {
		int lo = 0, hi = this.size - 1;
		while(lo<=hi){   // loop terminated when lower bound grows larger than higher bound(lo>hi)
			int mid = lo + (hi - lo)/2;
			int cmp = key.compareTo(this.keys[mid]);
			if(cmp<0){hi = mid - 1;}
			else if(cmp>0){lo = mid +1;}
			else {return this.values[mid];}
		}
		return null; //no matching key found in keys collection
	}

	@Override
	public boolean contains(Key key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Value delete(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Key> allKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
