package graph.mst;

/**
 * Weighted Edge Class
 * Instance of this class represents a weighted edge connecting two vertices v and w.
 * An immutable data type.
 * @author Junwei,Zhao
 * @see EdgeWeightedGraph
 * @version 1.0.0
 */
public final class WeightedEdge implements Comparable<WeightedEdge> {
	
	//First end point for this edge
	private final int v;
	//Second end point for this edge
	private final int w;
	//Weight value for this edge
	private final double weight;
	
	/**
	 * Constructor
	 * @param v, end point
	 * @param w, end point
	 * @param weight, edge's weight
	 */
	public WeightedEdge(int v, int w,double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object vertex) {
		//reference check
		if(this==vertex){return true;}
		//null reference check and type check
		if(!(vertex instanceof WeightedEdge)){return false;}
		//cast object into class type 
		WeightedEdge that = (WeightedEdge)vertex;
		//compare significant fields
		if(this.v==that.v&&this.w==that.w&&Double.compare(this.weight, that.weight)==0){return true;}
		else {return false;}
	}

	@Override
	public String toString() {
		return "Weighted Edge connected vertex "+v+" and vertex "+w+" ,its weight is "+weight+".";
	}

	@Override
	public int compareTo(WeightedEdge that) {
		if(this.weight>that.weight){return 1;}
		else if(this.weight<that.weight){return -1;}
		else {return 0;}	
	}
	
	/**
	 * Returns current weightedEdge object's weight value 
	 * @return A double value represents the weight of current edge.
	 */
	public double weight(){
		return weight;
	}
	
	/**
	 * Returns one of this edge's end points.
	 * @return An int value represents one end point.
	 */
	public int either(){
		return v;
	}
	
	/**
	 * Returns another end point that is not vertex from this edge
	 * @param vertex, given end point 
	 * @return An int value represents another end point.
	 */
	public int other(int vertex){
		if(vertex==v){return w;}
		else {return v;}
	}

}
