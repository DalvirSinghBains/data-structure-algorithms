package stringsort;

/**
 * 
 * @author Junwei,Zhao
 * @see KeyIndexedCounting
 * @version 1.0.0
 */
public final class MSD {
	
	//cache radix
	private final static int R = 256;

	private MSD() {
	}
	
	/**
	 * 
	 * @param array
	 */
	public static void sort(String[] array){
		String[] aux = new String[array.length];
		sort(array,aux,0,array.length-1,0);
	}
	
	//
	private static void sort(String[] array, String[] aux, int lo, int hi, int d){
		if(lo>=hi){return;}    //recursion's base case check
		//key-indexed counting process
		int[] count = new int[R+2];
		for(int i=lo;i<=hi;i++){
			count[charAt(array[i],d)+2]++;
		}
		for(int r=1;r<R+2;r++){
			count[r] += count[r-1];
		}
		for(int i=lo;i<=hi;i++){
			aux[count[charAt(array[i],d)+1]++] = array[i];
		}
		for(int i=lo;i<=hi;i++){
			array[i] = aux[i-lo];
		}
		//recursively sort R subarrays
		for(int r=0;r<R;r++){
			sort(array,aux,lo+count[r],lo+count[r+1]-1,d+1);
		}
	}
	
	//treate strings as if they had an extra char at the end(smaller than any char, which has value of -1)
	private static int charAt(String s, int d){
		if(d<s.length()){return s.charAt(d);}
		else {return -1;}
	}

}
