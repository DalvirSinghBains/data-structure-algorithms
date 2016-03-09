package substringSearching;

/**
 * BruteForce Algorithm for substring searching
 * Complexity: O(M*N)
 * @author Junwei,Zhao
 */
public class BruteForce {
	
	public static int search(String pat, String txt){
				
		int M = pat.length();
		int N = txt.length();
		
		for(int i=0;i<N-M;i++){
			int j;
			for(j=0;j<M;j++){
				if(pat.charAt(j)!=txt.charAt(i+j)){
					break;
				}
			}
			if(j==M){return i;} //found, return starting index
		}
		
		return -1; //not found
	}
}
