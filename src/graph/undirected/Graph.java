package graph.undirected;

/**
 * Dependency is the Singled-list Bag class.
 */
import list.BagSL;

/**
 * Undirected Graph
 * Every graph consists of V vertices and a number of edges, which don't have directions.
 * Every undirected graph is a digraph(with edges in both directions).
 * @author Junwei,Zhao
 * @see BagSL
 * @version 1.0.0
 */

public class Graph {
	
	/**
	 * Bag<Integer> array, vertices, represents graph's V vertices, ranging from 0 to V-1.
	 * For each bag, it stores a collection of all adjacent vertices associated with that particular vertex.
	 */
	
	private BagSL<Integer>[] vertices; 
	
	/**
	 * numOfVertices, final instance variable stores the number of vertices existing in graph.
	 */
	private final int numOfVertices;
	
	/**
	 * numOfEdges, instance variable tracks the total number of edges in graph.
	 */
	private int numOfEdges;
	
	/**
	 * Graph Constructor, 
	 * V is the graph's vertices number.
	 */
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.numOfVertices = V;
		this.vertices =(BagSL<Integer>[])new BagSL[V];
		for(int i=0;i<V;i++){
			this.vertices[i] = new BagSL<>();
		}
		this.numOfEdges = 0;
	}
	
	/**
	 * addEdge method adds the edge between two given vertices into graph.
	 * @param v, vertex v from graph.
	 * @param w, vertex w from graph.
	 */
	public void addEdge(int v, int w){
		this.vertices[v].add(w);
		this.vertices[w].add(v);	
		this.numOfEdges++;
	}
	
	/**
	 * adjacentVs method returns a collection of given vertex's adjacent vertices.
	 * @param v, any vertex from graph.
	 * @return A collection of adjacent vertices.
	 */
	public Iterable<Integer> adjcentVs(int v){
		return this.vertices[v];
	}
	
	/**
	 * numOfVs method returns the number of graph vertices.
	 * @return number of vertices.
	 */
	public int numOfVs(){
		return this.numOfVertices;
	}
	
	/**
	 * numOfEs method returns the number of graph edges.
	 * @return number of edges.
	 */
	public int numOfEs(){
		return this.numOfEdges;
	}
	
	/**
	 * degrees method returns the number of edges that a given vertex has.
	 * @param v, any vertex from graph.
	 * @return number of edges attached from the given vertex.
	 */
	@SuppressWarnings("unused")
	public int degrees(int v){
		int degree = 0;
		for(int adjs : this.adjcentVs(v)){
			degree++;
		}
		return degree;
	}

}
