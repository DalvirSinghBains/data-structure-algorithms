package list;

public class MinPQ<E extends Comparable<E>> {

	//
	private E[] array;
	
	//Keep tracking how many items are stored in the priority queue. Valid array indices are from 0 to size-1.
	private int size;
	
	//Default array size, in practice, internal array should be adjusting its size automatically, in this case for simplicity, choose fixed size.
	private static final int DEFAULTSIZE = 30;
	
	@SuppressWarnings("unchecked")
	public MinPQ() {
		this.array =(E[])new Object[DEFAULTSIZE];
		size = 0;
	}
	
	/**
	 * Overloaded Constructor, which creates an empty priority queue with specified size.
	 */
	@SuppressWarnings("unchecked")
	public MinPQ(int capacity) {
		this.array =(E[])new Object[capacity];
		size=0;
	}
	
	/**
	 * Insert a key into the priority queue 
	 * @param v
	 */
	public void insert(E v){
	}
	
	/**
	 * Remove the largest key and return it.
	 * @return
	 */
	public E deleteMin(){
		E min = this.array[0];
		
		
		
		
		return min;
	}
	
	/**
	 * 	Return if the priority queue is empty or not.
	 * @return
	 */
	public boolean isEmpty(){
		return size==0;
	}
	
	/**
	 * Return the number of entries in the priority queue.
	 * @return 
	 */
	public int size(){
		return this.size;
	}
	
	//Test out if first given item is less than second given one.
	private static <E extends Comparable<E>> boolean less(E v, E w){
		return v.compareTo(w)<0;
	}
	
	//Swap two given index items stored in given array.
	private static <E extends Comparable<E>> void exchange(E[] array, int i, int j){
		E temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
