package sort;

/**
 * Quick Sort
 * Optimal sorting algorithm relying on compares regards to space and fastest one in practice.
 * ********************************************************************************************
 * Algorithm:
 * 1.Shuffle the array.
 * 2.Partition so that, for some k,
 * 	2.1:Entry array[j] is in place.
 * 	2.2:No larger entry to the left of k.
 * 	2.3:No smaller entry to the right of k.
 * 3.Sort each subarray recursively
 * Partition part in details: 
 * For a subarray waiting to be partitioned starting from index lo and ending in index hi,
 * 1.Set two index pointers i,j separately for left section and right section. Pointer i starts from lo and j starts from hi. array[lo] is j.
 * Repeat until i and j pointer cross:
 * 2.Scan i from left to right so long as a[i]<a[lo].
 * 3.Scan j from right to left so long as a[j]>a[lo].
 * 4.Exchange a[i] with a[j].
 * When pointers cross, exchange array[lo] with a[j].
 * Attention:
 * Remember to terminate the loops all the time.
 * For equal keys, when duplicate keys are present, it is better to stop scans on keys equal to the partition key.
 * Preserving randomness to every partition key, that's why the starting shuffle is important to performance guarantee.
 * ****************************************************
 * Quick Sort Analysis and Properties:
 * Average case: Expected number of compares is around 1.39NlgN
 * Best case: Number of compares is around NlgN
 * Worst case: Number of compares is around N(2)/2
 * Quick Sort is an in-place sorting algorithm.
 * Quick Sort is not a stable sorting algorithm.
 * ****************************************************
 * Practical Improvements: 
 * 1.Insertion sort for small array, cutoff to insertion sort for around 10 items.
 * 2.Best choice of pivot item is median, using median as partition item.
 * 3.3-way quick sort for multiple duplicate keys.
 * 	3.1:Let b be partition item a[lo].
 * 	3.2:Scan i from left to right
 *      1.if a[i]<v,exchange a[lt] with a[i];increment both lt and i;
 *      2.if a[i]>v,exchange a[gt] with a[i];decrement gt
 *      3.if a[i]==v,increment i.
 * 4.Dual-pivot quick sort for better performance.
 * @author Junwei,Zhao
 * @version 1.0.0
 */
public final class QuickSort {

	private QuickSort() {
	}
	
	/**
	 * Quick Sort public API
	 * @param array,wait-to-be sorted array.
	 */
	public static <Key extends Comparable<Key>> void sort(Key[] array){
		KnuthShuffle.shuffle(array);     //shuffle needed for performance guarantee.
		sort(array,0,array.length-1);
	}
	
	//Recursive process, during each recursion, partition current array portion and sort two remaining parts.
	private static <Key extends Comparable<Key>> void sort(Key[] array,int lo,int hi){
		if(hi<=lo){return;} //first check recursion base case
		int j = partition(array,lo,hi); //partition current array portion and return the already in place sorted item's index.
		sort(array,lo,j-1);             //recursively sort the rest two parts.
		sort(array,j+1,hi);
	}

	//performs partition algorithm for some k, in this case k is array[lo].
	private static <Key extends Comparable<Key>> int partition(Key[] array,int lo,int hi){
		int i = lo, j = hi+1;     //set two pointers starting from out of bound because we are using ++i and ++j.Legit area should range from lo+1 to hi.
		while(true){
			while(less(array[++i],array[lo])){  //find item on left to swap,increment the pointer i until it encounters a[i] >= partition item a[lo].
				if(i==hi){break;}				//be prepared for special case, what if partition item is the largest one among these array entries.
			}
			while(less(array[lo],array[--j])){ //find item on right to swap,decrement the pointer j until it encounters a[j] <= partition item a[lo].
				if(j==lo){break;}				////be prepared for special case, what if partition item is the smallest one among these array entries.
			}
			if(i>=j){break;}       //check if pointer cross each other
			exchange(array,i,j);   //swap current a[i] and a[j] to keep invariant, and move on to next recurrent processes until pointers cross.
		}
		exchange(array,lo,j);      //swap partition item into final fixed position
		return j;				   //return index of item now known to be in place
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
	
	/**
	 * 3-way quick sort for sorting array containing many duplicate keys
	 * @param array,wait-to-be sorted array, it contains many duplicate keys.
	 */
	public static <Key extends Comparable<Key>> void threeWaySort(Key[] array){
		KnuthShuffle.shuffle(array); 
		threeWaySort(array,0,array.length-1);
	}
	
	//Recursively perform 3-way partition until it hits the base case.
	private static <Key extends Comparable<Key>> void threeWaySort(Key[] array, int lo, int hi){
		if(hi<=lo){return;}      //recursion base case check
		int lt = lo, gt = hi;	 //set lower and upper bound pointers for middle section which its items are equal to partition item v.
		int i = lo;				 //set iteration pointer is
		Key v = array[lo];       //cache partition item as v, because after partition begins, a[lo] is no longer the partition key.
		
		//During the partition, entire array is divided into four parts:
		//1.from [lo,lt), keys < v
		//2.from [lt,i), keys == v
		//3.from [i,gt], keys undecided
		//4.from (gt,hi], keys > v
		//After the partition, array is divided into three parts:
		//1.from [lo,lt),keys < v
		//2.from [lt,gt],keys == v
		//3.from (gt,hi],keys > v
		while(i<=gt){
			int cmp = array[i].compareTo(v); //compare current iteration item with partition item v
			if(cmp<0){
				exchange(array,i++,lt++);   
			}else if(cmp>0){
				exchange(array,i,gt--);
			}else {
				i++;
			}	
		}
		threeWaySort(array,lo,lt-1);  //recursively sort two subarrays
		threeWaySort(array,gt+1,hi);
	}
	
}
