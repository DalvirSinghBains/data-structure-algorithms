package sort;

/**
 * Elementary Selection Sort
 * Sort the given array by total order.
 * Array's keys must implement Comparable interface.
 * Selection means elect the minimum value from each iteration.
 * Algorithm:
 * 1.Scans the remaining unfixed part of given array, repeats this process N times(N is the array size).
 * 2.During each iteration mentioned in step one, find the minimum value index and swap it to first spot in remaining part and keep it fixed.
 * Selection sort uses N(2)/2 compares and N exchanges to sort any array of N items.
 * Selection sort is not stable.
 * @author Junwei,Zhao
 * @version 1.0.0
 */
public final class SelectionSort {

	private SelectionSort() {
	}
	
	/**
	 * Sort function performs elementary selection sort.
	 * @param array, wait to be sorted array which its keys are comparable.
	 */
	public static <Key extends Comparable<Key>> void sort(Key[] array){
		int N = array.length;
		for(int i=0;i<N;i++){
			int min = i;   //minimum value pointer, keeps tracking of the index of minimum value this iteration's getting so far.
			for(int j=i+1;j<N;j++){
				if(less(array[j],array[i])){
					min = j;  //if find a value which is smaller than current minimum value, refreshes minimum point to this new index.
				}
				exchange(array,i,min); //after current iteration, swap the minimum value to the front.
			}
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
