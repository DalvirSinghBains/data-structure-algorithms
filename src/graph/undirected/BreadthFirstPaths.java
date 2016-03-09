package graph.undirected;

import java.util.Stack;
import list.Queue;

/**
 * Breadth First Search for Paths.
 * BFS is a directed graph algorithm, its main functionality is to compute shortest path(fewest number of edges.).
 * Algorithm: BFS(from source vertex source)
 * 1.Put the source vertex onto a FIFO queue, and mark it as visited.
 * 2.Repeat following steps until the queue is empty:
 * 	 2.1 Remove the least recently added vertex v from the queue
 *   2.2 Add each of v's unvisited adjacent vertices to the queue and mark them as visited
 *   2.3 Keep tracks of paths and distance to the source 
 *  If graph is considered as a tree structure, BFS is an level-order traversal through tree's nodes.
 * @author Junwei,Zhao
 * @see Paths
 * @see Graph
 * @version 1.0.0
 */
public class BreadthFirstPaths implements Paths {
	
	//Boolean array marked[] keeps tracking all visited marked vertices, marked true means that vertex is reachable from source vertex.
	private boolean[] marked;
	
	//Integer array edgeTo[] keeps tracking which previous vertex that current vertex is connecting to.
	private int[] edgeTo;
	
	//Integer array distTo keeps tracking the number of edges need to travel from source vertex to other connected vertices in graph
	private int[] distTo;
	
	//Instance variable source keeps track of source vertex.
	private int source;
	
	
	/**
	 * BFS Paths Constructor
	 * @param G, the graph object.
	 * @param source, the source vertex.
	 */
	public BreadthFirstPaths(Graph G, int source) {
		this.source = source;
		this.marked = new boolean[G.numOfVs()];
		this.edgeTo = new int[G.numOfVs()];
		this.distTo = new int[G.numOfVs()];
		this.bfs(G, source);
	}
	
	//helper function bfs to implement Breadth First Paths Algorithm.
	private void bfs(Graph G, int s){
		Queue<Integer> queue = new Queue<>();
		queue.enquene(s);
		this.marked[s] = true;
		this.distTo[s] = 0;
		
		//loop base condition: queue is not empty.
		while(!queue.isEmpty()){
			int current = queue.dequeue();
			for(int adj : G.adjcentVs(current)){
				if(!this.marked[adj]){
					queue.enquene(adj);
					this.marked[adj] = true;
					this.edgeTo[adj] = current;
					this.distTo[adj] = this.distTo[current]+1;
				}
			}
		}
	}
	
	/**
	 * distanceToS method returns the distance from given vertex to source vertex.
	 * @param v, any vertex from graph.
	 * @return Integer value represents distance
	 */
	public int distanceToS(int v){
		return this.distTo[v];
	}
	
	@Override
	public boolean hasPathTo(int v) {
		return this.marked[v];
	}

	@Override
	public Iterable<Integer> pathTo(int v) {
		if(!this.hasPathTo(v)){return null;}
		Stack<Integer> stack = new Stack<>();
		int index = v;
		while(this.edgeTo[index]!=this.source){
			stack.push(this.edgeTo[index]);
			index = this.edgeTo[index];
		}
		stack.push(this.source);
		return stack;
	}

}
