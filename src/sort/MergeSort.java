package sort;

/**
 * Merge Sort
 * Optimal sorting algorithm relying on compares regards to comparing number.
 * **************************************************************************
 * Algorithm:
 * Two versions: Top-down merge sort and bottom-up merge sort.
 * Top-down version: Basic Plan
 * 1.Divide array into two halves.
 * 2.Recursively sort each half, until it hits the base case which is subarray size is 1.
 * 3.Merge two halves.
 * Bottom-up version: Basic plan
 * 1.Pass through array, merging subarrays of size 1.
 * 2.Repeat for subarrays of double size like 2,4,8...
 * ***********************************************************
 * Quick Sort Analysis and Properties:
 * Merge sort takes NlogN compares to sort an array of size N, it guarantees around NlogN running time.
 * Merge sort typically uses extra space proportional to N, it's hard but viable to implement in-place merge.
 * Merge sort is stable. Merge operation is stable.
 * ***********************************************************
 * Practical Improvements: 
 * A: Use insertion sort for small subarrays.
 * A1.Merge sort has too much overhead for tiny subarrays.
 * A2.Cutoff to insertion sort for around 10 items.
 * B: Stop if already sorted
 * B1.Is largest item in first half <= smallest item in second half.
 * B2.Helps for partially-ordered arrays.
 * C: Eliminate the copy to the auxiliary array by switching the role of input and auxiliary arrays.
 * @author Junwei,Zhao
 * @version 1.0.0
 */
public final class MergeSort {
	
	//Cut off threshold, lower than this value, switch to insertion sort for sorting small array.
	@SuppressWarnings("unused")
	private static final int CUTOFF = 12;

	private MergeSort() {
	}
	
	/**
	 * Merge sort(Top-down recursive merge sort)
	 * @param array, wait to be sorted array.
	 */
	@SuppressWarnings("unchecked")
	public static <Key extends Comparable<Key>> void sort(Key[] array){
		int N = array.length;
		Key[] aux = (Key[])(new Comparable[N]);  //watch out, compiler's complaints
		sort(array,aux,0,N-1);
	}
	
	//Recursively process: sort left part, sort right part, merge them together. The base case is intend-to-be sorted array only has one entry.
	private static <Key extends Comparable<Key>> void sort(Key[] array,Key[] aux,int lo,int hi){
		
     /* if(hi<=lo+CUTOFF-1){            //practical improvement version, part A.
			InsertionSort.sort(array,lo,hi);
			return;
		}*/
		
		if(hi<=lo){return;}      //check if this is base case, lo == hi means array only has one entry.
		int mid = lo + (hi - lo)/2;
		sort(array,aux,lo,mid);       //recursive sorting process
		sort(array,aux,mid+1,hi);
		if(!less(array[mid+1],array[mid])){return;}   //practical improvement version, part B.
		merge(array,aux,lo,mid,hi);
	}
	
	/**
	 * Merge sort(Bottom-up non-recursive merge sort)
	 * @param array, wait to be sorted array.
	 */
	@SuppressWarnings("unchecked")
	public static <Key extends Comparable<Key>> void sortBU(Key[] array){
		int N = array.length;
		Key[] aux = (Key[])(new Comparable[N]);
		for(int sz=1;sz<N;sz=sz+sz){   //Wait-to-be merged subarrays's size are starting off from 1,double their size when one iteration is finished
			for(int lo=0;lo<N-sz;lo+=sz+sz){  //During each iteration,get started from index 0,continuously merge two subarrays of current size and move on to next pair.
				merge(array,aux,lo,lo+sz-1,Math.min(lo+sz+sz-1, N-1)); //consider the case that array total size is odd.
			}														   //merge operation doesn't necessarily need to take two subarrays with same size.
		}	
	}
	
	//Merge two subarray portions into one, one's from index lo to mid, another's from index mid+1 to hi.
	private static <Key extends Comparable<Key>> void merge(Key[] array, Key[] aux,int lo,int mid,int hi){
		for(int k=lo;k<=hi;k++){       //copy entire part of array into auxiliary array.
			aux[k] = array[k];
		}
		
		int i = lo, j = mid + 1;      //set two index pointers for two already sorted subarrays.
		for(int k=lo;k<=hi;k++){      //merge operation, there are four situations.
			if(i>mid){array[k]=aux[j++];}
			else if(j>hi){array[k]=aux[i++];}
			else if(less(aux[j],aux[i])){array[k]=aux[j++];}
			else {array[k]=aux[i++];}   //take from left subarray if equal keys.
		}
	}
	
	//Test out if first given item is less than second given one.
	private static <Key extends Comparable<Key>> boolean less(Key v, Key w){
		return v.compareTo(w)<0;
	}

}
