package stringsort;

/**
 * Least-Significant-Digit-First String Radix Sort
 * Algorithm:
 * 1.Consider characters from right to left.
 * 2.Stably sort using dth character as the key and using key-indexed counting.
 * LSD sorts fixed-length strings in ascending order in linear time.
 * LSD sort is stable.
 * @author Junwei,Zhao
 * @see KeyIndexedCounting
 * @version 1.0.0
 */
public final class LSD {
	
	//cache radix
	private final static int R = 256;

	private LSD() {
	}
	
	/**
	 * Sort given array of strings whose lengths are fixed stably
	 * @param array, string array waiting to be sorted
	 * @param W, strings' length
	 */
	public static void sort(String[] array, int W){
		int N = array.length;
		String[] aux = new String[N];
		
		for(int d=W-1;d>=0;d--){  //use each string's dth character as key, d decreasing
			int[] count = new int[R+1]; //Attention: count array needs to be reinitialized after dth key is finished.
			
			for(int i=0;i<N;i++){     //count frequencies
				count[array[i].charAt(d)+1]++;
			}
			for(int r=1;r<R+1;r++){   //compute cumulates
				count[r] += count[r-1];
			}
			for(int i=0;i<N;i++){     //move items
				aux[count[array[i].charAt(d)]++] = array[i];
			}
			for(int i=0;i<N;i++){     //copy back
				array[i] = aux[i];
			}
		}
	}
	
}
