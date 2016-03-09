package graph.mst;

import list.BagSL;

/**
 * Edge-Weighted Graph API
 * Using adjacency lists representation.
 * @author Junwei,zhao
 * @see WeightedEdge
 * @version 1.0.0
 */
public final class EdgeWeightedGraph {
	
	//store the number of vertices, index ranges from 0 to V-1.
	private final int V;
	
	//An array of bags, each bag contains all weighted edges connected to vertex represented by index.
	private final BagSL<WeightedEdge>[] vertices;
	
	/**
	 * Constructor, create an empty graph with V vertices.
	 * @param V, vertices' number
	 */
	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int V) {
		this.V = V;
		vertices = (BagSL<WeightedEdge>[])new BagSL[V];  //create an array of size V.
		for(int i=0;i<V;i++){
			vertices[i] = new BagSL<>();        //for each index, create a bag which will hold weighted edges.
		}
	}
	
	/**
	 * Add edge to both adjacency lists
	 * @param e, given weighted edge
	 */
	public void addEdge(WeightedEdge e){
		int v = e.either();
		int w = e.other(v);
		vertices[v].add(e);
		vertices[w].add(e);
	}
	
	/**
	 * Returns given vertex's adjacency list.
	 * @param v, given vertex 
	 * @return An iterable collection contains all edges connected given vertex and its adjacent vertex.
	 */
	public Iterable<WeightedEdge> adj(int v){
		return vertices[v];
	}
	
	/**
	 * Returns a collection of all edges belonging to this graph, duplicates eliminated..
	 * @return A collection is full of distinct edges.
	 */
	public Iterable<WeightedEdge> edges(){
		BagSL<WeightedEdge> edges = new BagSL<>();
		for(BagSL<WeightedEdge> bag : vertices){
			for(WeightedEdge edge : bag){
				if(!edges.contain(edge)){edges.add(edge);}
			}
		}
		return edges;
	}
	
	/**
	 * Returns the number of graph's vertices
	 * @return An int value represents vertices' number
	 */
	public int V(){
		return V;
	}

}
