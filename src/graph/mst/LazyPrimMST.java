package graph.mst;

import list.MinPQ;
import list.Queue;

/**
 * Lazy Implementation of Prim's MST Algorithm
 * Lazy solution for computing MST by implementing Prim's algorithm.
 * Prim's algorithm:
 * 1.Start with vertex 0 and greedily grow tree T
 * 2.Add to T the minimum weight edge with exactly one endpoint in T. 
 * 3.Repeat until V-1 edges.
 * Implementation details:
 * 1.Maintain a prior queue of edges with (at least) one endpoint in T.
 * 2.Delete-minimum to determine next edge e=v-w to add to T.
 * 3.Disregard if both endpoints v and w are marked(both in T).
 * 4.Otherwise, let w be the unmarked vertex(not in T):
 * 	 -add e to T and mark w
 * 	 -add to PQ any edge incident to w
 * @author Junwei,Zhao
 * @see WeightedEdge
 * @see EdgeWeightedGraph
 * @see MST
 * @version 1.0.0
 */
public class LazyPrimMST implements MST {
	
	//boolean array of graph vertices' number, each entry marked if vertex is already in MST(T).
	private boolean[] isInMST;
	
	//a collection of all mst's edges
	private Queue<WeightedEdge> mst;
	
	//minimum priority queue to store all possible edges, ordered by edges' weights.
	private MinPQ<WeightedEdge> pq;
	
	/**
	 * Constructor, constructs the corresponding MST by given EdgeWeightedGraph G.
	 * @param G, given EdgeWeightedGraph
	 */
	public LazyPrimMST(EdgeWeightedGraph G) {
		isInMST = new boolean[G.V()];
		mst = new Queue<>();
		pq = new MinPQ<>();
		this.visit(G,0);      //start with vertex 0 and assume G is connected.
		
		while(!pq.isEmpty()){  //loop when priority queue is not empty AND T's vertices' number is not greater than V-1.
			WeightedEdge min = pq.deleteMin();   //repeatedly delete the current minimum weight edge from PQ
			int v = min.either();
			int w = min.other(v);
			if(isInMST[v]&&isInMST[w]){continue;} //disregard the edge if both its end points in T
			mst.enquene(min);                     //add edge to MST
			if(!isInMST[v]){this.visit(G,v);}     //visit either v or w
			if(!isInMST[w]){this.visit(G,w);}
			if(mst.size()==G.V()-1){break;}
		}
	}
	
	//helper function for visiting each vertex, two steps: 1.add vertex to T(MST) 2.for each edge e=v-w,add to PQ if w not already in T
	private void visit(EdgeWeightedGraph G, int v){
		isInMST[v] = true;                         //marks vertex as it's in MST
		for(WeightedEdge we : G.adj(v)){           //add all one end point adjacent edges into priority queue
			if(!isInMST[we.other(v)]){
				pq.insert(we);
			}
		}
	}

	@Override
	public Iterable<WeightedEdge> edges() {
		return mst;
	}

	@Override
	public double weightOfMST() {
		double weight = 0.0;
		for(WeightedEdge edge : mst){
			weight += edge.weight();
		}
		return weight;
	}

}
