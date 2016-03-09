package list;

import java.util.Iterator;

/**
 * 
 * @author Junwei
 *
 * @param <E>
 */
public class Queue<E> implements Iterable<E> {
	
	private int size;

	public Queue() {
		
	}
	
	public int size(){
		return this.size;
	}
	
	public boolean isEmpty(){
		return this.size == 0;
	}
	
	public boolean enquene(E item){
		return true;
	}
	
	public E dequeue(){
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		
		return null;
	}

}
