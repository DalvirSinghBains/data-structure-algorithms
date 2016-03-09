package sort;

/**
 * Knuth Shuffle
 * Rearrange array so that result is uniformly random permutation.
 * Shuffle elementary implementation: shuffle sort
 * 1.Generate a random real number for each array entry.
 * 2.Sort the array.
 * Knuth shuffle implementation:
 * 1.In iteration i, pick integer r between 0 and i uniformly at random.
 * 2.Swap a[i] and a[r].
 * Attention: pick random integer r between 0 and i.(including 0 and i)
 * Knuth shuffle will take linear time to shuffle an given array.
 * @author Junwei,Zhao
 * @version 1.0.0
 */
public final class KnuthShuffle {

	private KnuthShuffle() {	
	}
	
	/**
	 * Shuffles given array.
	 * @param array, wait to be shuffled array.
	 */
	public static void shuffle(Object[] array){
		int N = array.length;
		for(int i=0;i<N;i++){
			int r = (int)(Math.random()*(i+1)); //Uniformly generates a random number between 0 and i.
			exchange(array,i,r);
		}
	}
	
	//Swap two given index items stored in given array.
	private static void exchange(Object[] array, int i, int j){
		Object temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
