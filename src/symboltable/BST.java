/**
 * 
 */
package symboltable;

import list.Queue;

/**
 * Binary Search Trees
 * Def:A BST is a binary tree in symmetric order.
 * A binary tree is either:
 * 1.Empty
 * 2.Two disjoint binary trees(left and right)
 * Symmetric Order:
 * Each node has a key, and every node's key is either:
 * 1.Larger than all keys in its left subtree
 * 2.Smaller than all keys in its right subtree
 * Tree shape depends on order of insertion. 
 * For N distinct keys, in average case the costs of searching and insertion are logN, but in worst case, they are N.
 * In this BST implementation, keys and values are generic types; key is Comparable.
 * There are two set of insertion operation and its associated methods. One is using recursion, marked with trailing R, others is not.
 * @author Junwei,Zhao
 * @see OST
 * @see ST
 * @version 1.0.0
 */
public class BST<Key extends Comparable<Key>, Value> implements OST<Key, Value> {
	
	//Root of BST
	private Node root;
	
	//track how many key-value pairs stored in the tree.
	private int size;
	
	/**
	 * BST Constructor
	 */
	public BST() {
	}
	
	//Private class for Node
	//A node is composed of four fields:
	//A key and a value.
	//A reference to the left and right subtree.
	//There is one optional field count used for recursive put and rank operations.
	//A count stores the number of nodes in its subtrees and itself.
	private class Node {
		private Key key;
		private Value val;
		private Node left;
		private Node right;
		private int count;
		
		public Node(Key key, Value val){
			this.key = key;
			this.val = val;
		}
		
		public Node(Key key, Value val, int count){
			this.key = key;
			this.val = val;
			this.count = count;
		}
	}
	
	//Search for key, then two cases:
	//Key in the tree ==> reset value
	//Key not in tree ==> add new node associated with key and value and add node to proper spot in the tree.
	//Two different implementations: recursive and non-recursive
	
	//recursive implementation
	public void putR(Key key, Value value) {
		this.root = this.putRecursive(root, key, value);
	}
	
	//concise, awesome but tricky code, careful here.
	//this helper method can return two kinds of node: new created node with key and value or modified node which its old value is replaced by new one..
	private Node putRecursive(Node x, Key key, Value value){
		if(x==null){return new Node(key,value,1);}
		int cmp = key.compareTo(x.key);
		if(cmp<0){
			x.left = this.putRecursive(x.left, key, value);
		}else if(cmp>0){
			x.right = this.putRecursive(x.right, key, value);
		}else if(cmp==0){
			x.val = value;
		}
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	//non-recursive implementation
	@Override
	public void put(Key key, Value value) {
		if(this.root==null){this.root = new Node(key,value); size++;}
		else {
			Node current = this.root;
			while(true){
				int cmp = key.compareTo(current.key);
				if(cmp<0){
					if(current.left==null){
						current.left = new Node(key,value);
						size++;
						return;
					}else {current = current.left;}
				}else if(cmp>0){
					if(current.right==null){
						current.right = new Node(key,value);
						size++;
						return;
					}else {current = current.right;}
				}else if(cmp==0){
					current.val = value;
					return;
				}
			}
		}
	}
	
	@Override
	public Value get(Key key) {
		Node current = this.root; //start off searching from root
		while(current!=null){   //searching keeps going until it hits null, which means there is no matching in the tree.
			int cmp = key.compareTo(current.key);
			if(cmp<0){
				current = current.left;
			}else if(cmp>0){
				current = current.right;
			}else if(cmp==0){
				return current.val;
			}
		}
		return null;
	}

	@Override
	public boolean contains(Key key) {
		if(this.get(key)==null){return false;}
		else {return true;}
	}
	
	//Deletion can be divided into three situations, matched node has:
	//1.No children: directly remove node from tree
	//2.One child: exchange node with its parent node, and remove it.
	//3.Two children: find the node with largest key from left subtree or with the smallest key from right subtree based on current matched node.
	//Exchange that node with current node, and remove already exchanged "that" node.
	@Override
	public Value delete(Key key) {
		return null;
		
	}
	
	//Because keys are comparable, traversal order should be inorder.
	//In-order traversal algorithm:
	//1.Traverse left subtree
	//2.Enqueue key
	//3.Traverse right subtree
	@Override
	public Iterable<Key> allKeys() {
		Queue<Key> queue = new Queue<>();
		this.inorder(this.root, queue);
		return queue;
	}
	
	//In-order traverse through all keys in tree
	private void inorder(Node x, Queue<Key> q){
		if(x==null){return;}
		this.inorder(x.left, q);
		q.enquene(x.key);
		this.inorder(x.right, q);
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	/**
	 * Returns if tree is empty, used with recursion version of put method.
	 * @return True if it is empty, false if not
	 */
	public boolean isEmptyR(){
		return this.size(this.root) == 0;
	}

	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Returns how many key-value pairs stored in tree, used with recursive put method and rank operation.
	 * @return Size of tree
	 */
	public int sizeR(){
		return size(this.root);
	}
	
	private int size(Node x){
		if(x==null){return 0;} //okay to call when it is null.
		return x.count;
	}

	@Override
	public Key max() {
		if(this.root==null){return null;}
		else {
			Node current = this.root;
			while(current.right!=null){
				current = current.right;
			}
			return current.key;
		}
	}

	@Override
	public Key min() {
		if(this.root==null){return null;}
		else {
			Node current = this.root;
			while(current.left!=null){
				current = current.left;
			}
			return current.key;
		}
	}
	
	//Every time it turns out to go to right subtree, updates the floor reference.
	@Override
	public Key floor(Key key) {
		if(this.root==null){return null;}
		Node current = this.root;
		Node floor = null;
		while(current!=null){
			int cmd = key.compareTo(current.key);
			if(cmd==0){
				return current.key;
			}else if(cmd<0){
				current = current.left;
			}else if(cmd>0){
				floor = current;
				current = current.right;
			}
		}
		if(floor==null){return null;}
		else {return floor.key;}
	}

	//Every time it turns out to go to left subtree, updates the ceiling reference.
	@Override
	public Key ceiling(Key key) {
		if(this.root==null){return null;}
		Node current = this.root;
		Node ceiling = null;
		while(current!=null){
			int cmd = key.compareTo(current.key);
			if(cmd==0){
				return current.key;
			}else if(cmd<0){
				ceiling = current;
				current = current.left;
			}else if(cmd>0){
				current = current.right;
			}	
		}
		if(ceiling==null){return null;}
		else {return ceiling.key;}
	}

	@Override
	public int rank(Key key) {
		return rank(key,this.root);
	}
	
	private int rank(Key key, Node x){
		if(x==null){return 0;}
		int cmp = key.compareTo(x.key);
		if(cmp<0){
			return rank(key,x.left);
		}else if(cmp>0){
			return 1 + size(x.left) + rank(key, x.right);
		}else{
			return size(x.left);
		}
	}

	@Override
	public Key select(int rank) {
		
		return null;
	}

}
