package graph.mst;

/**
 * Interface for MST API
 * 
 * @author Junwei,Zhao
 * @see WeightedEdge
 * @see EdgeWeightedGraph
 * @see LazyPrimMST
 * @version 1.0.0
 */
public interface MST {
	
	/**
	 * Returns an iterable collection of edges consist of MST
	 * @return A collection contains all MST's edges which can be iterated through.
	 */
	public Iterable<WeightedEdge> edges();
	
	/**
	 * Returns the total weight of this MST.
	 * @return A double value represents the total weight.
	 */
	public double weightOfMST();
	
}
