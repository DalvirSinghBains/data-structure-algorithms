package graph.undirected;

//import java.util.ArrayList;
import java.util.Stack;

/**
 * Depth First Search for Paths.
 * DFS is a directed graph algorithm, its main functionality is to find solution in Reachability.
 * Using this algorithm to determines which vertices in graph are reachable from a given specific vertex.
 * Algorithm: DFS(to visit a vertex v)
 * 1. Mark v as visited
 * 2. Recursively visit all unmarked vertices w adjacent to v and keep the tracks of path.
 * @author Junwei,Zhao
 * @see Paths
 * @see Graph
 * @version 1.0.0
 */
public class DepthFirstPaths implements Paths {
	
	//Boolean array marked[] keeps tracking all visited marked vertices, marked true means that vertex is reachable from source vertex.
	private boolean[] marked;

	//Integer array edgeTo[] keeps tracking which previous vertex that current vertex is connecting to.
	private int[] edgeTo;
	
	//Instance variable source keeps track of source vertex.
	private int source;

	/**
	 * DFS Paths Constructor
	 * @param G, the graph object.
	 * @param source, the source vertex.
	 */
	public DepthFirstPaths(Graph G, int source) {
		this.marked = new boolean[G.numOfVs()];
		this.edgeTo = new int[G.numOfVs()];
		this.source = source;
		this.dfs(G, source);
	}
	
	//Recursive called helper function visits all unmarked adjacent vertices and keeps tracking the path.
	private void dfs(Graph G, int v){
		this.marked[v] = true;
		for(int adj : G.adjcentVs(v)){
			if(!this.marked[adj]){
				//recursion base condition: no unmarked adjacent vertices or no adjacent vertices at all.
				dfs(G, adj);
				edgeTo[adj] = v; //edge to the Destination(adj) is from the Source(v)
			}
		}
		
	}

	@Override
	public boolean hasPathTo(int v) {
		return this.marked[v];
	}

	@Override
	public Iterable<Integer> pathTo(int v) {
		if(!this.hasPathTo(v)){return null;}
		Stack<Integer> stack = new Stack<>();
		int index = v;
		while(this.edgeTo[index]!=this.source){
			stack.push(this.edgeTo[index]);
			index = this.edgeTo[index];
		}
		stack.push(this.source);
		return stack;
		
      /*ArrayList<Integer> path = new ArrayList<>();
		for(int i=0;i<stack.size();i++ ){
			path.add(stack.pop());
		}
		return path;*/
	}

}
