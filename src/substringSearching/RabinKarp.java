package substringSearching;

/**
 * Rabin-Karp Substring Search Algorithm
 * Basic Idea:
 * 1. Compute a hash of pat[0..M-1]
 * 2. For each i, compute a hash of text[i>>M+i-1]
 * 3. If pattern hash = text substring hash, check for a match.
 * @author Junwei,Zhao
 */

public class RabinKarp {
	
	//cache precomputed hash for pattern
	private long patHash;
	
	//cache pattern string's length
	private int M;
	
	//cache modulus
	private int Q;
	
	//cache radix
	private int R;
	
	//precompute and cache the result of (R over M-1) mod Q 
	private long RM1;
	
	//cache pattern string
	private String pat;
	
	//constructor,initialize all fields
	public RabinKarp(String pattern){
		
		M = pattern.length();
		Q = 997;    //normally use a large prime but avoid overflow, in theory, Q = M*N2, which'll give the probability of collision about 1/N.
		R = 256;
		pat = pattern;
		
		RM1 = 1;                     //compute (R over M-1) mod Q 
		for(int i=0;i<M;i++){
			RM1 = (RM1*R)%Q;
		}
		
		patHash = hash(pattern,M);
	}
	
	//Horner's method: linear-time method to evaluate degree-M polynomial
	private long hash(String key, int length){
		
		long h = 0;
		
		for(int j=0;j<length;j++){
			h = ((h*R) + key.charAt(j))%Q;
		}
		
		return h;
	}
	
	//if pattern is in text, return the index of first occurrence, if not, return -1.
	//Randomized result, if finding a potential match, we assume that potential match is the correct result.
	public int searchRdm(String text){
		
		int N = text.length();
		long txtHash = hash(text,M);  //compute the hash for first M characters.
		if(txtHash==patHash){return 0;} //if first M characters match the pattern, return index 0, else keep going
		for(int i=M;i<N;i++){           //Go through entire text and keep computing the hash of text[i..M+i-1].
			txtHash = (txtHash + Q - RM1*(text.charAt(i-M)%Q))%Q; //add Q to make sure that entire term is non-negative.
			txtHash = (txtHash*R + text.charAt(i))%Q;
			if(txtHash==patHash){return i-M;}         //Randomized Algorithm, probability of collision is about 1/Q. linear time guaranteed.
		} 											  //Extremely likely to return correct answer(but not always)
		return -1;
	}
	
	//if pattern is in text, return the index of first occurrence, if not, return -1.
	//precise result, if finding a potential match, compare potential match to pattern by going through every characters.
	public int searchPce(String text){
		
		int N = text.length();
		long txtHash = hash(text,M);  //compute the hash for first M characters.
		if(txtHash==patHash){return 0;}
		outer:
			for(int i=M;i<N;i++){
				txtHash = (txtHash + Q - RM1*(text.charAt(i-M)%Q))%Q; //add Q to make sure that entire term is non-negative.
				txtHash = (txtHash*R + text.charAt(i))%Q;
				if(txtHash==patHash){
					for(int k=0;k<M;k++){                             //check every character
						if(pat.charAt(k)!=text.charAt(i-M+k)){continue outer;}
					}
					return i-M;
				}        
			} 										
		return -1;
	}
}
