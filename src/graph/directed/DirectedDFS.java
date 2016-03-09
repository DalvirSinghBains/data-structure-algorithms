package graph.directed;

/**
 * Depth First Search Algorithm on Directed Graph.
 * Using this algorithm to determines which vertices in graph are reachable from a given specific vertex.
 * Algorithm: similar to DFS in undirected graph.
 * DFS(to visit a vertex v):
 * 1.Mark v as visited.
 * 2.Recursively visit all unmarked vertices adj pointing from v.
 * @author Junwei,Zhao
 * @see Digraph
 * @version 1.0.0
 */
public class DirectedDFS {
	
	//Boolean array marked[] keeps tracking all visited marked vertices, marked true means that vertex is reachable from source vertex.
	private boolean[] marked;
	
	/**
	 * DirectedDFS Constructor
	 * Constructor marks all vertices reachable from given source vertex.
	 * @param G, graph object
	 * @param source, source vertex.
	 */
	public DirectedDFS(Digraph G, int source) {
		this.marked = new boolean[G.numOfVs()];
		this.dfs(G,source);
	}
	
	//Helper function dfs performs DFS algorithm does the work.
	private void dfs(Digraph G, int s){
		this.marked[s] = true;
		for(int adj : G.adjcentVs(s)){
			if(!this.marked[adj]){
				this.dfs(G, adj);
			}
		}
	}
	
	/**
	 * visited method returns whether given vertex is reachable from source vertex.
	 * @param v, any vertex from graph
	 * @return true if there is a path connecting two vertices, false if there is not.
	 */
	public boolean reachable(int v){
		return this.marked[v];
	}

}
