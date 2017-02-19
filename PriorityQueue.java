public class PriorityQueue<Item extends Comparable<Item>> {
	private Item[] a;
	private int N=0;
	private static void swap(Comparable[] a,int i ,int j){
		Comparable tmp=a[i];
		a[i]=a[j];
		a[j]=tmp;
	}
	private  void swim(int i){
		while(i>1){
			if(a[i].compareTo(a[i/2])>0)
				swap(a,i,i/2);
			i/=2;
		}
	}
	private void sink(int i){
		while(2*i<=N){
			int j=2*i;
			if(j+1<=N && a[j].compareTo(a[j+1])<0)
				j++;
			if(a[i].compareTo(a[j])>=0)
				break;
			swap(a,i,j);
			i=j;
			
		}
	}
	public void insert(Item item){
		a[++N]=item;
		swim(N);
	}
	public Item delMax(){
        Item max=a[1];
		swap(a,1,N--);
		a[N+1]=null;
		sink(1);
		return max;
	}
	public int size(){
		return N;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public Item max(){
		return a[1];
	}

}
