package symboltable;

/**
 * Left-leaning Red-Black BST(Guibas-Sedgewick 1979 and Sedgewick 2007)
 * Common way to represent a 2-3 tree and its 3-node by using a regular BST with "internal" left-leaning red "glue" link.
 * Maintains 2-3 tree invariants:
 * 1.Symmetric order: same as BST.
 * 2.Perfect balance: every path from root to null link has same length.
 * The key property is to have 1-1 correspondence between 2-3 and LLRB.
 * A BST such that:
 * 1.No node has two red links connected to it.
 * 2.Every path from root to null link has the same number of black links.(perfect black balance
 * 3.Red links lean left.
 * @author Junwei,Zhao
 * @see ST
 * @see OST
 * @version 1.0.0
 * @param <Key>
 * @param <Value>
 */
public class LLRBBST<Key extends Comparable<Key>,Value> implements OST<Key, Value> {
	
	//
	private static final boolean RED = true;
	
	//
	private static final boolean BLACK = false;
	
	//
	private Node root;
		
	/**
	 * 
	 */
	public LLRBBST() {	
	}
	
	//Each node is pointed to by precisely one link from its parent and links can encode color.
	private class Node{
		Key key;
		Value val;
		Node left, right;
		boolean color;  //color of parent link
		Node(Key k,Value v,boolean cl){
			key = k ;
			val = v;
			color = cl;
		}
	}
	
	//Returns if color of parent link is red?
	private boolean isRed(Node x){
		if(x==null){return BLACK;} //null links are black
		return x.color;
	}

	@Override
	public void put(Key key, Value value) {
		this.root = put(root,key,value);
	}
	
	//Recursive function findding proper position in the tree to insert pair and maintain symmetric order and perfect balance(black links)
	private Node put(Node h,Key key,Value value){
		if(h==null){return new Node(key,value,RED);} //when it hits an null, insert at bottom and color it RED. 
		int cmp = key.compareTo(h.key);
		if(cmp<0){h.left = put(h.left, key, value);}
		else if(cmp>0){h.right = put(h.right,key,value);}
		else {h.val = value;}
		
		//strategy for maintaining invariants: same code for all case
		//1.Right child red, left child black: rotate left.
		//2.Left child, left-left grandchild red: rotate right.
		//3.Both children red: flip colors.
		if(isRed(h.right)&&!isRed(h.left)){h=rotateLeft(h);}  //1.lean left
		if(isRed(h.left)&&isRed(h.left.left)){h=rotateRight(h);} //2.build balance 4-node
		if(isRed(h.right)&&isRed(h.left)){flipColor(h);}   //3.split 4-node
		
		return h;
	}
	
	//Orient a temporarily right-leaning red link to lean left
	private Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	//Orient a left-leaning red link to temporarily lean right
	private Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	//Rocolor to split a temporary 4-node
	private void flipColor(Node h){
		h.left.color = BLACK;
		h.right.color = BLACK;
		h.color = RED;
	}

	//Search is the same as for elementary BST(ignore color), but runs faster because of better balance.
	@Override
	public Value get(Key key) {
		Node current = this.root;
		while(current!=null){
			int cmp = key.compareTo(current.key);
			if(cmp<0){current = current.left;}
			else if(cmp>0){current = current.right;}
			else {return current.val;}
		}
		return null;
	}

	@Override
	public boolean contains(Key key) {
		if(this.get(key)==null){return false;}
		else {return true;}
	}

	@Override
	public Value delete(Key key) {

		return null;
	}

	@Override
	public Iterable<Key> allKeys() {

		return null;
	}

	@Override
	public boolean isEmpty() {

		return false;
	}

	@Override
	public int size() {

		return 0;
	}

	@Override
	public Key max() {

		return null;
	}

	@Override
	public Key min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key floor(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key ceiling(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int rank(Key key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Key select(int rank) {
		// TODO Auto-generated method stub
		return null;
	}

}
