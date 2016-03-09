/**
 * 
 */
package symboltable;

/**
 * Ordered Symbol Table Ordered Operation APIs 
 * Keys in OST are comparable, implement Comparable interface, keys have order.
 * Besides normal ST APIs, OST will be some ordered operations APIs.
 * @author Junwei,Zhao
 * @see ST
 * @version 1.0.0
 */
public interface OST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {
	
	/**
	 * Returns the largest key existing in the key collection.
	 * @return Largest key.
	 */
	public Key max();
	
	/**
	 * Returns the smallest key existing in the key collection.
	 * @return Smallest key.
	 */
	public Key min();
	
	/**
	 * Returns the largest key less than or equal to key.
	 * @param key
	 * @return
	 */
	public Key floor(Key key);
	
	/**
	 * Returns the smallest key greater than or equal to key
	 * @param key
	 * @return
	 */
	public Key ceiling(Key key);
	
	/**
	 * Returns the number of keys less than given key
	 * @param key
	 * @return Rank of given key
	 */
	public int rank(Key key);
	
	/**
	 * Returns selected key by given rank number
	 * @param rank
	 * @return Key at given rank
	 */
	public Key select(int rank);

}
