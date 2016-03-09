package sort;

/**
 * Insertion Sort
 * Sort the given array by total order.
 * Array's keys must implement Comparable interface.
 * Algorithm:
 * 1.Scans through the array from left to right.
 * 2.During the scan,consider each entry is a new insertion to previous fixed portion,locate the proper spot in that portion for new insertion.
 * To sort a randomly-ordered array with distinct keys
 * Insertion sort uses around N(2)/4 compares and around N(2)/4 exchanges on average.
 * Worst case:if the array is in descending order, it would take N(2)/2 compares and around N(2)/2 exchanges.
 * Best case:if the array is in ascending order, it would take N-1 compares and 0 exchanges.
 * For partially-sorted arrays,insertion sort runs in linear time.
 * Insertion sort is stable.
 * @author Junwei,Zhao
 * @version 1.0.0
 */
public final class InsertionSort {

	private InsertionSort() {
	}
	
	/**
	 * Sort function performs elementary insertion sort.
	 * @param array,wait to be sorted array which its keys are comparable.
	 */
	public static <Key extends Comparable<Key>> void sort(Key[] array){
		int N = array.length;
		for(int i=0;i<N;i++){
			for(int j=i;j>0;j--){  //for each new insertion, go through previous portion and search for appropriate place to insert.
				if(less(array[j],array[j-1])){
					exchange(array,j,j-1);
				}else {break;}
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
