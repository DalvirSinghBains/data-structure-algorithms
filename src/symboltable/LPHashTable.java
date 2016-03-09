package symboltable;

import list.Queue;

/**
 * Linear-Probing Hash Table
 * Open Addressing Algorithm:
 * 1.Maintain keys and values in two parallel arrays.
 * 2.When a new key collides, find next empty slot, and put it there.
 * Hash:Map key to integer i between 0 and M-1.
 * Insert:Put at table index i if free;if not try i+1, i+2,etc.
 * Search:Search table index i;if occupied but no match,try i+1,i+2,etc,if it hits an empty slot, which means key is not in the table.
 * Note: Array size M must be greater than number of key-value pairs.
 * Efficiency of Hash Table is determined by N/M ratio:
 * N(total number of key-value pair)
 * M(number of entries in that array)
 * Typical choice: N/M = 1/2 makes operations finished in constant time.
 * To make sure that hash table keeps itself at its best. Resizing container array is necessary:
 * Double size of array M when N/M>=1/2;
 * Halve size of array M when N/M<=1/8;
 * Note:need to rehash all keys when resizing.
 * @author Junwei,Zhao
 * @see ST
 * @version 1.0.0
 * @param <Key>
 * @param <Value>
 */
public class LPHashTable<Key,Value> implements ST<Key,Value> {
	
	//number of entries that container arrays have
	private int M;
	
	//array stores keys
	private Key[] keys;
	
	//array stores values
	private Value[] vals;
	
	//track the number of key-value pairs stored in table
	private int size;

	/**
	 * Hash Table Constructor
	 */
	@SuppressWarnings("unchecked")
	public LPHashTable() {
		this.M = 30001;
		this.keys = (Key[])new Object[M];
		this.vals = (Value[])new Object[M];
		this.size = 0;
	}
	
	//hash function converts key's hash code into container array index between 0 and M-1.
	private int hash(Key key){
		return (key.hashCode()&0x7fffffff)%M;
	}
	
	@Override
	public void put(Key key, Value value) {
		int i;
		for(i=hash(key);this.keys[i]!=null;i=(i+1)%M){
			if(key.equals(keys[i])){
				break;
			}
		}
		this.keys[i] = key;
		this.vals[i] = value;
	}

	@Override
	public Value get(Key key) {
		for(int i=hash(key);this.keys[i]!=null;i=(i+1)%M){
			if(key.equals(this.keys[i])){
				return this.vals[i];
			}
		}
		return null;
	}

	@Override
	public boolean contains(Key key) {
		if(get(key)==null){return false;}
		else {return true;}
	}

	//Deletion:remove given key and its associated value, then move all key-value pairs one entry ahead until it hits an empty one.
	@Override
	public Value delete(Key key) {
		for(int i=hash(key);this.keys[i]!=null;i=(i+1)%M){
			if(key.equals(this.keys[i])){
				Value result = this.vals[i];
				for(int k=i;keys[k]!=null;k=(k+1)%M){  
					keys[k] = keys[k+1];
					vals[k] = vals[k+1];
				}
				return result;
			}
		}
		return null;
	}

	@Override
	public Iterable<Key> allKeys() {
		Queue<Key> queue = new Queue<>();
		for(int i=0;i<M;i++){
			if(keys[i]==null){continue;}
			queue.enquene(keys[i]);
		}
		return queue;
	}

	@Override
	public boolean isEmpty() {
		return this.size==0;
	}

	@Override
	public int size() {
		return this.size;
	}

}
