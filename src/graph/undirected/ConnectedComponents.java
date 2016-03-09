/**
 * 
 */
package graph.undirected;

/**
 * Connected Components
 * Identify every connected components.
 * Def. Vertices v and w are connected if there is a path between them.
 * Def. A connected component is a maximal set of connected vertices.
 * Algorithm:
 * 1. Initialize all vertices v as unmarked.
 * 2. For each unmarked vertex v, run DFS to identify all vertices discovered as part of the same component.
 * @author Junwei,Zhao
 * @see Graph
 * @see DepthFirstPaths
 * @version 1.0.0
 */
public class ConnectedComponents {
	
	//Boolean array marked[] keeps tracking all visited marked vertices.
	private boolean[] marked;
	
	//Integer array id[] gives each vertices in the graph a associated id related to their belonging to certain CC.
	private int[] id;
	
	//Instance variable count tracks the total number of connected components in entire graph.
	private int countCC;

	/**
	 * Connected Components Constructor
	 * G is a graph object.
	 */
	public ConnectedComponents(Graph G) {
		this.marked = new boolean[G.numOfVs()];
		this.id = new int[G.numOfVs()];
		this.countCC = 1;  //id starts at 1
		
		for(int i=0;i<G.numOfVs();i++){
			if(!this.marked[i]){
				this.dfs(G, i);
				this.countCC++;
			}
		}
	}
	
	//helper function dfs performs depth first search algorithm and writes appropriate id to each vertex in that connected components.
	private void dfs(Graph G, int v){
		this.marked[v] = true;
		this.id[v] = this.countCC;  // vertices discovered in the same recursive dfs call stack will have same id.
		for(int adj : G.adjcentVs(v)){
			if(!this.marked[adj]){
				this.dfs(G, adj);
			}
		}
	}
	
	/**
	 * connected method tests out if two given vertices are connected or not.
	 * @param v, given vertex one
	 * @param w, given vertex two
	 * @return True if they are connected, False if they are not.
	 */
	public boolean connected(int v, int w){
		return this.id[v] == this.id(w);
	}
	
	/**
	 * number of connected components
	 * @return Integer value, the total number of connected components existing in the graph.
	 */
	public int numOfCCs(){
		return this.countCC;
	}
	
	/**
	 * Component identifier for given vertex
	 * @param v
	 * @return Integer value, represents the CC identifier to that given vertex.
	 */
	public int id(int v){
		return this.id(v);
	}
	
}
