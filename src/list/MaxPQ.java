package list;

/**
 * Maximum Priority Queue
 * The first item is either the smallest or the largest items in entire collection depending on which priority queue it is, minimum or maximum?
 * Items are generic but they must be Comparable and immutable.
 * ********************************************************************
 * Binary tree: Empty or node with links to left and right binary tree.
 * Complete tree: Perfectly balanced, except for bottom level.
 * Height of complete binary tree with N nodes is lgN.
 * ***********************************************************
 * Binary Heap: Array representation of a heap-ordered complete binary tree.
 * Heap-Ordered: Parent's key no smaller than children keys.
 * Array representation: Indices start at 0 or 1 and take nodes in level order.
 * In case 0: if parent is k, left child is 2k+1, right child is 2k+2; if child is k, parent is k-1/2. Array[0] is the largest/smallest key.
 * in case 1: if parent is k, left child is 2k, right child is 2k; if child is k, parent is k/2.  Array[1] is the largest/smallest key.
 * *************************************************************************************************
 * Algorithm:
 * Insert: ADD node at end, then swim it up.
 * Remove: Exchange root with node at end, then sink it down.
 * Two essential operations: swim and sink(using maximum for example)
 * Swim Up: When a key becomes larger than its parent's key, exchange key in child with key in parent and repeat until heap order restored.
 * Sink Down: When a key becomes smaller than one(or both)of its children's, exchange key in parent with key in large child and repeat until heap order restored.
 * @author Junwei,Zhao
 * @see HeapSort
 * @version 1.0.0 
 * @param <Key>
 */
public class MaxPQ<Key extends Comparable<Key>>{
	
	//
	private Key[] array;
	
	//Keep tracking how many items are stored in the priority queue. Valid array indices are from 0 to size-1.
	private int size;
	
	//Default array size, in practice, internal array should be adjusting its size automatically, in this case for simplicity, choose fixed size.
	private static final int DEFAULTSIZE = 30;
	
	//Create an empty priority queue.
	@SuppressWarnings("unchecked")
	public MaxPQ(){
		this.array =(Key[])new Object[DEFAULTSIZE];
		size = 0;
	}
	
	/**
	 * Create an empty priority queue with specified size.
	 */
	@SuppressWarnings("unchecked")
	public MaxPQ(int capacity) {
		this.array =(Key[])new Object[capacity];
		size=0;
	}
	
	/**
	 * Create a priority queue with given keys.
	 * @param array
	 */
	public MaxPQ(Key[] array){
		this.array = array;
		this.size = array.length;
		
		//unfinished, bottom-up heap construction
	}
	
	/**
	 * Insert a key into the priority queue 
	 * @param v
	 */
	public void insert(Key v){
		this.array[size++] = v;
		swim(size-1);
	}
	
	//swim up operation
	private void swim(int k){
		while(k>=1){
			if(less(array[(k-1)/2],array[k])){
				exchange(this.array,(k-1)/2,k);
				k = (k-1)/2;
			}else {return;}
		}
	}

	/**
	 * Remove the largest key and return it.
	 * @return
	 */
	public Key deleteMax(){
		Key max = this.array[0];
		this.array[0] = this.array[--size];
		this.array[size] = null;
		sink(0);
		return max;
	}
	
	//sink down operation
	private void sink(int k){
		while(k<size/2){
			int j = 2*k+1;
			if(j<size-1&&less(array[j],array[j+1])){j++;}
			if(less(array[k],array[j])){
				exchange(array,k,j);
				k=j;
			}else {return;}
		}		
	}
	
	/**
	 * 	Return if the priority queue is empty or not.
	 * @return
	 */
	public boolean isEmpty(){
		return size==0;
	}
	
	/**
	 * Return the largest key.
	 * @return
	 */
	public Key Max(){
		return this.array[0];
	}
	
	/**
	 * Return the number of entries in the priority queue.
	 * @return 
	 */
	public int size(){
		return this.size;
	}
	
	//Test out if first given item is less than second given one.
	private static <Key extends Comparable<Key>> boolean less(Key v, Key w){
		return v.compareTo(w)<0;
	}
	
	//Swap two given index items stored in given array.
	private static <Key extends Comparable<Key>> void exchange(Key[] array, int i, int j){
		Key temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
