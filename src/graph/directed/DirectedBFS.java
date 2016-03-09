package graph.directed;

import list.Queue;

/**
 * Breadth First Search Algorithm on Directed Graph.
 * BFS is a directed graph algorithm, its main functionality is to compute shortest path(fewest number of edges.).
 * Algorithm: similar to BFS in undirected graph.
 * BFS(from source vertex source)
 * 1.Put the source vertex onto a FIFO queue, and mark it as visited.
 * 2.Repeat following steps until the queue is empty:
 * 	 2.1 Remove the least recently added vertex v from the queue
 *   2.2 Add each of v's unvisited adjacent vertices pointing from v to the queue and mark them as visited
 * @author Junwei,Zhao
 * @see Digraph
 * @version 1.0.0
 */
public class DirectedBFS {
	
	//
	private boolean[] marked;
	
	/**
	 * 
	 * @param G
	 * @param source
	 */
	public DirectedBFS(Digraph G, int source) {
		this.marked = new boolean[G.numOfVs()];
		this.bfs(G, source);
	}
	
	//
	private void bfs(Digraph G, int s){
		this.marked[s] = true;
		Queue<Integer> queue = new Queue<>();
		queue.enquene(s);
		
		while(!queue.isEmpty()){
			int current = queue.dequeue();
			for(int adj : G.adjcentVs(current)){
				if(!this.marked[adj]){
					this.marked[adj] = true;
					queue.enquene(adj);
				}
			}
		}
	}	

}
