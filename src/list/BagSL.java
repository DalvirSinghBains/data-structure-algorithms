package list;

import java.util.Iterator;

/**
 * 
 * @author Junwei
 *
 * @param <E>
 */
public class BagSL<E> implements Iterable<E> {
	
	private int size;
	private Node first;
	
	public BagSL(){
	}
	
	class Node{
		 E element;
		 Node next;
		
		Node(E e,Node n){
			element = e;
			next = n;
		}
	}
	
	public int size(){
		return this.size;
	}
	
	public boolean add(E element){
		first = new Node(element,first);
		size++;
		return true;
	}
	
	public boolean remove(E item){
		//empty bag
		if(size==0){return false;}
		//remove first match
		Node previous = first;
		Node index = first;
		while(index!=null){
		//if the first element in the bag matches
			if(first.element.equals(item)){
				first = first.next;
				size--;
				return true;
			}
		//if element on other position matches
			if(index.element.equals(item)){	
				previous.next = index.next;
				size--;
				return true;
			}
			previous = index;
			index = index.next;
		}
		//no match in the bag
		return false;
	}
	
	public boolean contain(E item){
		for(Node i=first;i!=null;i=i.next){
			if(i.element.equals(item)){
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new BagIterator();
	}
	
	private class BagIterator implements Iterator<E>{
		Node node = first;

		@Override
		public boolean hasNext() {
			return node!=null?true:false;
		}

		@Override
		public E next() {
			Node temp = node;
			node = node.next;
			return temp.element;
		}
		
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		Iterator<E> it = this.iterator();
		while(it.hasNext()){
			sb.append(it.next()+" ");
		}
		sb.append("}");
		return sb.toString();
	}
	
//end of class
}
