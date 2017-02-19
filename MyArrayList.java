import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {
	private static final int DEFAULT_CAPACITY=10;
	private int theSize;
	private T[] theItems;
	
	public void MyArrayList(){
		clear();
	}
	
	public void clear(){
		theSize=0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	public void ensureCapacity(int newCapacity){
		if(theSize>newCapacity)
			return;
		T[] old =theItems;
		theItems=(T[])new Object[newCapacity];
		for(int i=0;i<theSize;i++)
			theItems[i]=old[i];
	}
	
	public void trimToSize(){
		ensureCapacity(theSize);
	}
	
	public int size(){
		return theSize;
	}
	
	public boolean isEmpty(){
		return theSize==0;
	}
	
	public T get(int index){
		if(index<0||index>=theSize)
			throw new ArrayIndexOutOfBoundsException();
		return theItems[index];
	}
	
	public T set(int index,T t){
		if(index<0||index>=theSize)
			throw new ArrayIndexOutOfBoundsException();
		T old=theItems[index];
		theItems[index]=t;
		return old;
	}
	
	public void add(int index,T t){
		if(theSize==theItems.length)
			ensureCapacity(theItems.length*2+1);
		for(int i=theSize;i>index;i--)
			theItems[i]=theItems[i-1];
		theItems[index]=t;
		theSize++;
	}

	public boolean add(T t){
		add(theSize,t);
		return true;
	}
	
	public T remove(int index){
		T old=theItems[index];
		for(int i=size()-2;i>=index;i--)
			theItems[i]=theItems[i+1];
		theSize--;
		return old;
	}
	
	public Iterator<T> iterator(){
		return new ArrayListIterator();
	}
	
	public class ArrayListIterator implements Iterator<T>{
		private int current=0;
		public boolean hasNext(){
			return current<size();
		}
		public T next(){
			if(!hasNext())
				throw new java.util.NoSuchElementException();
			return theItems[current++];
		}
		public void remove(){
			MyArrayList.this.remove(--current);
		}
	}
	
}
