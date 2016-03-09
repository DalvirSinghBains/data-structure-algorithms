package stringsort;

/**
 * Key-Indexed Counting 
 * Assumption: Keys are integers between 0 and R-1, a set of size R.
 * Algorithm: Four Steps:
 * 1.Count frequencies of each key using key as index.
 * 2.Compute frequency accumulates which specify destinations.
 * 3.Access accumlates using key as index to move items.
 * 4.Copy back into original array.
 * It runs in linear time with extra space proportional to N+R, and stable.
 * @author Junwei,Zhao
 * @version 1.0.0
 */
public final class KeyIndexedCounting {
	
	//how many kinds of values keys could have 
	private final static int R = 256;

	private KeyIndexedCounting() {
	}
	
	/**
	 * Sort a given array using key-indexed counting algorithm.
	 * @param array, array waiting to be sorted.
	 */
	public static void sort(int[] array){
		int N = array.length;
		int[] count = new int[R+1];//count needs extra one entry for 
		
		//step1: count frequencies
		for(int i=0;i<N;i++){
			count[array[i]+1]++;
		}
		//step2: compute accumulates
		for(int r=1;r<R+1;r++){
			count[r] += count[r-1];
		}
		//step3: move items
		int[] aux = new int[N];
		for(int i=0;i<N;i++){
			aux[count[array[i]]++] = array[i];
		}
		//step4: copy back
		for(int i=0;i<N;i++){
			array[i] = aux[i];
		}
	}

}
