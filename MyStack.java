
public class MyStack {
	int[] array;
	int max;
	int top;
	public MyStack(int max){
		this.max=max;
		top=0;
		array=new int[max];
	}
	public void push(int value){
		if(top==max){
			System.out.println("the stack is full,can not push");
			return;}
		array[top++]=value;
	}
	
	public int pop(){
		if(top==0){
			System.out.println("the stack is empty,can not pop");
			return -1;
		}
		return array[--top];
	}
	public boolean isEmpty(){
		return (top==0);
	}
	public boolean isFull(){
		return (top==max);
	}
    public void display(){
    	while(!isEmpty()){
    		System.out.println(pop());
    	}
    }
	
}
