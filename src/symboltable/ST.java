/**
 * 
 */
package symboltable;

/**
 * Symbol Table Simplified APIs Interface
 * Key-Value Pair Abstraction, also known as maps, dictionaries and associative arrays.
 * Conventions:
 * 1.Values are not null.
 * 2.Method get() returns null if given key not present.
 * 3.Method put() overwrites old value with new value. 
 * Key type: several natural assumptions.
 * Assume keys are Comparable, use compareTo(), for sorted purpose.
 * Assume keys are any generic type, use equals() to test equality, for unordered usage.
 * Best practices:
 * Key Type: immutable types
 * Value Type: any generic types 
 * Goal: Efficient implementation of both search and insert operation.
 * @author Junwei,Zhao
 * @param <Key>
 * @param <Value>
 * @version 1.0.0
 */
public interface ST<Key,Value> {
	
	/**
	 * Put a given key-value pair into the table
	 * @param key
	 * @param value
	 */
	public void put(Key key, Value value);
	
	/**
	 * Returns the value paired with the given key
	 * @param key
	 * @return Associated value to given key. Return null if given key is not presented in table.
	 */
	public Value get(Key key);
	
	/**
	 * Is given key stored in the table
	 * @param key, given key to search through table
	 * @return True, if it is, false if not
	 */
	public boolean contains(Key key);
	
	/**
	 * Removes given key and its value from table
	 * @param key, given key is intended to be removed from table.
	 * @return Associated value with that deleted key
	 */
	public Value delete(Key key);
	
	/**
	 * Returns a collection of all keys in the table.
	 * @return Keys collection which can be iterated through.
	 */
	public Iterable<Key> allKeys();
	
	/**
	 * Returns whether the table is empty.
	 * @return True is table is empty, false if not.
	 */
	public boolean isEmpty();
	
	/**
	 * Returns number of key-value pairs in the table.
	 * @return Integer value represents the number of key-value pairs
	 */
	public int size();

}
