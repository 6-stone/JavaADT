import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
	private class Node{
		Item item;
		Node next;
	}
    Node first;
    Node last;
    int N=0;
    public boolean isEmpty(){
    	return N==0;
    }
    public int size(){
    	return N;
    }
    public void enqueue(Item item){
    	Node oldlast;
    	oldlast=last;
    	last=new Node();
    	last.item=item;
    	last.next=null;
    	if(isEmpty()) first=last;
    	else oldlast.next=last;
    	N++;
    }
    public Item dequeue(){
    	Item item=first.item;
    	first=first.next;
    	if(isEmpty())return null;
    	N--;
    	return item;
    }
    public Iterator<Item> iterator(){
    	return new QueueIterator();
    }
    private class QueueIterator implements Iterator<Item>{
    	Node head =first;
    	public boolean hasNext(){
    		return head==null;
    	}
    	public Item next(){
    		Item item=head.item;
    		head=head.next;
    		return item;
    	}
    	public void remove(){}
    }
}
