
public class MyLinkedList<T> implements Iterable<T> {
	
	private int theSize;
	private int modCount=0;
	private Node<T> beginMarker;
	private Node<T> endMarker;
	
	private static class Node<T>{
		public T data;
		public Node<T> prev;
		public Node<T> next;
		public Node(T d,Node<T> p,Node<T> n){
			data=d;
			prev=p;
			next=n;
		}
	}
	public MyLinkedList(){
		clear();
	}
	public void clear(){
		beginMarker=new Node<T>(null, null, endMarker);
		endMarker=new Node<T>(null, beginMarker, null);
		theSize=0;
		modCount++;
	}
	public int size(){
		return theSize;
	}
	public boolean isEmpty(){
		return size()==0;
	}
	private void addBefore(Node<T> p,T x){
		Node<T> newNode=new Node<T>(x,p.prev,p);
		p.prev.next=newNode;//newNode.prev.next=newNode;
		p.prev=newNode;
		theSize++;
		modCount++;
	}
	private T remove(Node<T> p){
		p.prev.next=p.next;
		p.next.prev=p.prev;
		theSize--;
		modCount++;
		return p.data;
	}
	private Node<T> getNode(int index){
	    Node<T> p;
		if(index<0||index>size())
			throw new IndexOutOfBoundsException();
		if(index<size()/2)
		{
			p=beginMarker;
			for(int i=0;i<index;i++)
				p=p.next;
		}
		else{
			p=endMarker;
			for(int i=size();i>index;i--)
				p=p.prev;	
		}
		return p;
	}
	public void add(int index, T x){
		addBefore(getNode(index), x);
	}
	public boolean add(T x){
		add(size(),x);
		return true;
	}
	public T remove(int index){
		return remove(getNode(index));
	}
	public T get(int index){
		return getNode(index).data;
	}
	public T set(int index,T x){
		Node<T> p =getNode(index);
		T oldVal=p.data;
		p.data=x;
		return oldVal;
	}
	public java.util.Iterator<T> iterator(){
		return new LinkedListIterator();
	}
	private class LinkedListIterator implements java.util.Iterator<T>{
		private Node<T> current=beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove =false;
		public boolean hasNext(){
			return current!=endMarker;
		}
		public T next(){
			if(modCount!=expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if(!hasNext())
				throw new java.util.NoSuchElementException();
			T nextItem=current.data;
			current=current.next;
			okToRemove=true;
			return nextItem;
		}
		public void remove(){
			if(modCount!=expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if(!okToRemove)
				throw new IllegalStateException();
			MyLinkedList.this.remove(current.prev);
			okToRemove=false;
			expectedModCount++;
		}
		
	}

}
