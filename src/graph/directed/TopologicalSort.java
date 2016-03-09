/**
 * 
 */
package graph.directed;

import java.util.Stack;

/**
 * Topological Sort
 * Acyclic graph: there is no circle in graph.
 * Redraw Directed acyclic graph(DAR) so all edges point upwards.
 * Reverse DFS postorder of a DAG is a topological order.
 * Algorithm:
 * 1.Run depth-first search and get vertices in postorder
 * 2.Return vertices in reverse postorder.
 * @author Junwei,Zhao
 * @see Digraph
 * @see DirectedDFS
 * @version 1.0.0
 */
public class TopologicalSort {

	private boolean[] marked;

	private Stack<Integer> reversePostOrder;

	/**
	 * TopologocalSort Constructor
	 * @param G, directed graph object
	 */
	public TopologicalSort(Digraph G) {
		this.marked = new boolean[G.numOfVs()];
		this.reversePostOrder = new Stack<>();
		
		for(int i=0;i<G.numOfVs();i++){
			if(!this.marked[i]){
				this.dfs(G, i);
			}
		}
	}
	
	//helper function
	private void dfs(Digraph G, int s){
		this.marked[s] = true;
		for(int adj : G.adjcentVs(s)){
			if(!this.marked[adj]){
				this.dfs(G, adj);
			}
		}
		this.reversePostOrder.push(s);
	}
	
	/**
	 * Returns all vertices in "reverse DFS postorder".
	 * @return A collection of vertices which have reversed post order.
	 */
	public Iterable<Integer> reversePostOrder(){
		return this.reversePostOrder;
	}

}
