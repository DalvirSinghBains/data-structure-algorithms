package symboltable;

import list.Queue;

/**
 * Separate-chaining Hash Table
 * Deal with collisions efficiently: two distinct keys hashing to same index.
 * Separate-chaining Algorithm(H.P.Luhn,IBM 1953):
 * 1.Hash: map keys to integer i between 0 and M-1.
 * 2.Insert: put at front of Nth chain(if not already in the chain)
 * 3.Search: sequential search in Nth chain
 * 4.Delete: if key in the chain, exchange it with the first node in chain, remove first node from chain.
 * Efficiency of Hash Table is determined by N/M ratio:
 * N(total number of key-value pair)
 * M(number of entries in that array)
 * Typical choice: N/M = 4 makes operations finished in constant time.
 * To make sure that hash table keeps itself at its best. Resizing container array is necessary:
 * Double size of array M when N/M>=8;
 * Halve size of array M when N/M<=2;
 * Note:need to rehash all keys when resizing.
 * @author Junwei,Zhao
 * @param <Key>
 * @param <Value>
 * @see ST
 * @version 1.0.0
 */
public class SCHashTable<Key, Value> implements ST<Key, Value> {
	
	//number of chains
	private int M;
	
	//array of chains
	private Node[] st;
	
	//number of nodes
	private int size;
	
	//Node class
	private class Node{
		private Key key;
		private Value value;
		private Node next;
		
		public Node(Key key, Value value, Node next){
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	/**
	 * Hash Table Constructor
	 */
	@SuppressWarnings("unchecked")
	public SCHashTable() {
		this.M = 97;
		this.st = (Node[])new Object[this.M];
		this.size = 0;
	}
	
	//no-bug hash function to convert hash code to an int index between 0 and M-1.
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff)%M;
	}
	
	@Override
	public void put(Key key, Value value) {
		int index = hash(key);
		Node current = this.st[index];
		while(current!=null){
			if(key.equals(current.key)){current.value = value;}
			current = current.next;
		}
		this.st[index] = new Node(key,value,this.st[index]);
		size++;
	}

	@Override
	public Value get(Key key) {
		int index = hash(key);
		Node current = this.st[index];
		while(current!=null){
			if(key.equals(current.key)){return current.value;}
			current = current.next;
		}
		return null;
	}

	@Override
	public boolean contains(Key key) {
		if(this.get(key)==null){return false;}
		else {return true;}
	}

	@Override
	public Value delete(Key key) {
		int index = hash(key);
		Node current = this.st[index];
		while(current!=null){
			if(key.equals(current.key)){
				Value result = current.value;
				current.key = this.st[index].key;
				current.value = this.st[index].value;
				this.st[index] = this.st[index].next;
				size--;
				return result;
			}
			current = current.next;
		}
		return null;
	}

	@Override
	public Iterable<Key> allKeys() {
		Queue<Key> queue = new Queue<>();
		for(int i=0;i<M;i++){
			if(this.st[i]==null){continue;}
			Node current = this.st[i];
			while(current!=null){
				queue.enquene(current.key);
				current = current.next;
			}
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
