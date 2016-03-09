/**
 * 
 */
package symboltable;

/**
 * Symbol Table Elementary Implementation
 * Linked List Implementation and perform Sequential Search 
 * Data Structure: Maintain an unordered linked list of key-value pairs.
 * Search: Scan through all keys until find a match.
 * Insert: Scan through all keys until find a match and replace its original value; if no match add to front.
 * @author Junwei,Zhao
 * @see ST
 * @version 1.0.0
 */
public class SequentialSearchST<Key, Value> implements ST<Key, Value> {
	
	//private LinkedList list;
	
	@SuppressWarnings("unused")
	private int size;
	
	/**
	 * 
	 */
	public SequentialSearchST() {
	}

	@Override
	public void put(Key key, Value value) {
		
	}

	@Override
	public Value get(Key key) {
		return null;
	}

	@Override
	public boolean contains(Key key) {

		return false;
	}

	@Override
	public Value delete(Key key) {
		return null;
	}

	@Override
	public Iterable<Key> allKeys() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int size() {
		return 0;
	}

}
