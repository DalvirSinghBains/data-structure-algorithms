package sort;

/**
 * Heap Sort
 * Algorithm:
 * 1.Heap Construction: Construct input array into a max(min)-heap with all N keys.
 * 2.Sort Down: Repeatedly remove the maximum(minimum) key.
 * Bottom-up heap construction method:
 * Find the last internal node, iterate backwards until it hits root, for each node, perform sink operation on it.
 * *********************************************************
 * Heap Sort Analysis and Properties:
 * Heap Construction uses <=2N compares and <=N exchanges.
 * Heap Sort uses <=2NlgN compares and exchanges.
 * In-place srting algorithm with NlogN worst-case:
 * Mergesort: no, not in place, liner extra space.
 * Quicksort: no, quadratic time in worst time.
 * Heapsort: yes!
 * Heap sort is optimal for both time and space, BUT:
 * Heap sort is not stable.
 * Inner loop longer than quicksort's.
 * Makes poor use of cache.
 * @author Junwei,Zhao
 * @see MaxPQ
 * @version 1.0.0
 */
public final class HeapSort {

	private HeapSort() {
	}
	
	/**
	 * Heap sort, which is a two steps process.
	 * @param array
	 */
	public static <Key extends Comparable<Key>> void sort(Key[] array){
		int N = array.length;
		for(int i=N-1/2;i>=0;i--){        //bottom-up heap construction method.
			sink(array,i,N);
		}
		while(N>1){                       //remove the maximum from heap, one at a time until there is only one left.
			exchange(array,0,--N);
			sink(array,0,N);
		}
	}
	
	//sink helper function, sink any given position key to its proper place, maintain heap-order all the time.
	private static <Key extends Comparable<Key>> void sink(Key[] array,int k,int N){
		while(k<N/2){
			int j = 2*k+1;
			if(j<N-1&&less(array[j],array[j+1])){j++;}  //decide which child is larger
			if(less(array[k],array[j])){
				exchange(array,k,j);
				k=j;
			}else {return;}
		}	
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
