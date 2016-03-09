package selection;

import sort.KnuthShuffle;

/**
 * Quick Selection
 * Goal:Given an array of N items, find the kth smallest item.
 * ************************************************************
 * Algorithm:
 * 1.Partition array based on some j.
 * 2.Repeat in one subarray, depending on j; finished when j equals k.
 * ************************************************************
 * Analysis and properties:
 * Quick-select takes linear time on average.
 * Theoretically, Compare-based selection algorithm whose worst-case running time is linear.
 * But constants are high, not used in practice.
 * @author Junwei,Zhao
 * @see 1.0.0
 */
public final class QuickSelect {

	private QuickSelect() {
	}
	
	/**
	 * Quick Selection of kth smallest item in given array.
	 * @param array, given array.
	 * @param k, search index.
	 * @return item in given k position.
	 */
	public static <Key extends Comparable<Key>> Key select(Key[] array,int k){
		KnuthShuffle.shuffle(array);
		int lo = 0, hi = array.length-1;   //set two pointers
		while(hi>lo){                      //when two pointers lo and hi have not crossed.
			int j = partition(array,lo,hi);//partition array and return already in place index
			if(j<k){lo = j+1;}             //if k is larger than j, set pointer lo to j+1 and repeat entire process.
			else if(j>k){hi = j-1;}		   //if k is smaller than j, set pointer hi to j-1 and repeat entire process.
			else {return array[k];}        //if k equals to j, just return a[k]
		}
		return array[k];
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

}
