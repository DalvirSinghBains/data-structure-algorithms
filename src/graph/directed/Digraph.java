package graph.directed;

/**
 * Dependency is the Singled-list Bag class.
 */
import list.BagSL;

/**
 * Directed Graph
 * Every graph consists of V vertices and a number of edges which have directions.
 * @author Junwei
 * @version 1.0.0
 */
public class Digraph {
	
	
 /* Bag<Integer> array, vertices, represents graph's V vertices, ranging from 0 to V-1.
	For each bag, it stores a collection of all adjacent vertices whose edges are pointing from particular vertex v.*/
	private BagSL<Integer>[] vertices; 
	
	
	//numOfVertices, final instance variable stores the number of vertices existing in graph.
	private final int numOfVertices;
	
	
	//numOfEdges, instance variable tracks the total number of edges in graph.
	private int numOfEdges;
	
	/**
	 * Graph Constructor, 
	 * V is the graph's vertices number.
	 */
	@SuppressWarnings("unchecked")
	public Digraph(int V) {
		this.numOfVertices = V;
		this.vertices =(BagSL<Integer>[])new BagSL[V];
		for(int i=0;i<V;i++){
			this.vertices[i] = new BagSL<>();
		}
		this.numOfEdges = 0;
	}
	
	/**
	 * addEdge method adds the directional edge between two given vertices into graph.
	 * @param v, vertex v from graph.
	 * @param w, vertex w from graph.
	 */
	public void addEdge(int v, int w){
		this.vertices[v].add(w);	//each edge has direction, in this case, edge is pointing from v to w, edge v --> w
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
