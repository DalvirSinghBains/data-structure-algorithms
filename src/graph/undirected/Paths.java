package graph.undirected;

/**
 * Paths Interface
 * Decouple graph data type from graph processing.
 * Paths represents graph processing.
 * @author Junwei,Zhao
 * @version 1.0.0
 */
public interface Paths {
	
	/**
	 * hasPathTo method returns if given vertex is connected to source vertex.
	 * @param v, any vertex from graph.
	 * @return true if there is a path, false if not.
	 */
	public boolean hasPathTo(int v);
	
	/**
	 * pathTo method returns the path from given vertex to source vertex, which is represented by a collection of edges.
	 * @param v, any vertex from graph except for source vertex.
	 * @return a collection of edges represents the path from vertex v to source vertex.
	 */
	public Iterable<Integer> pathTo(int v);
	
}
