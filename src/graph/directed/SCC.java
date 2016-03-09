package graph.directed;

/**
 * Strongly-Connected Components(SCC)
 * Def:A strongly-connected component is a maximal subset of strongly-connected vertices.
 * Def:Vertices v and w are strongly connected if there is both a directed path from v to w and a directed path from w to v.
 * Kosaraju-Sharir Algorithm:
 * Reverse graph: Strong components in G are same a s in G reverse.
 * Kernel DAG: Contract each strong component into a single vertex.
 * Algorithm's phases:
 * 1.Compute reverse postorder in G reverse.
 * 2.Run DFS in G, visiting unmarked vertices in reverse postorder of G reverse.
 * @author Junwei,Zhao
 * @see Digraph
 * @version 1.0.0
 */
public class SCC {
	
	//
	private boolean marked[];
	
	//
	private int[] id;
	
	//
	private int count;
	
	/**
	 * 
	 * @param G
	 */
	public SCC(Digraph G) {
		this.marked = new boolean[G.numOfVs()];
		this.id = new int[G.numOfVs()];
		
	}
	

}
