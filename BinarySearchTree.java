
public class BinarySearchTree<T extends Comparable<? super T>> {
	
	private class BinaryNode<T>{
		BinaryNode(T element){
			this(element,null,null);
		}
		BinaryNode(T element,BinaryNode<T>lt,BinaryNode<T> rt){
			data=element;
			left=lt;
			right=rt;
		}
		private T data;
		BinaryNode<T> left;
		BinaryNode<T> right;
		
	}
	
	BinaryNode<T> root;
	public BinarySearchTree() {
		root=null;
	}
	public void makeEmpty(){
		root=null;
	}
	public boolean isEmpty(){
		return root==null;
	}
	public boolean contains(T x){
		return contains(x,root);
	}
	public T findMin(){
		return findMin(root).data;
	}
	public T findMax(){
		return findMax(root).data;
		
	}
	public void insert(T x){
		root=insert(x,root);
		
	}
	public void remove(T x){
		root=remove(x,root);
	}
	public void printTree(){
		if(isEmpty())
			System.out.println("Tree is empty");
		else
		    printTree(root);
	}
	private void printTree(BinaryNode<T> node){
		if(node!=null){
			printTree(node.left);
			System.out.println(node.data);
			printTree(node.right);
		}
	}
	private boolean contains(T x,BinaryNode<T> node){
		if (node==null)
			return false;
		int compareResult=x.compareTo(node.data);
		if(compareResult==0)
			return true;
		else if(compareResult<0)
			return contains(x,node.left);
		else
			return contains(x,node.right);
		
	}
	private BinaryNode<T> findMin(BinaryNode<T> node){
		if(node==null)
			return null;
		if(node.left==null)
			return node;
		return findMin(node.left);
		
	}
	private BinaryNode<T> findMax(BinaryNode<T> node){
		if(node==null)
			return null;
		while(node.right!=null)
			node=node.right;
		return node;
	}
	private BinaryNode<T> insert(T x,BinaryNode<T> node){
		if(node==null)
			return new BinaryNode<T>(x,null,null);
		int compareResult=x.compareTo(node.data);
		if (compareResult<0)
			node.left=insert(x,node.left);
		else if (compareResult>0)
			node.right=insert(x,node.right);
		else
			;
			return node;	
	}
	private BinaryNode<T> remove(T x,BinaryNode<T> node){
		if(node==null)
			return null;
		int compareResult=x.compareTo(node.data);
		if(compareResult<0)
			node.left=remove(x,node.left);
		else if (compareResult>0)
			node.right=remove(x,node.right);
		else if(node.left!=null&&node.right!=null){  //要删除节点有两个儿子，用右子树最小数据代替该节点数据
			node.data=findMin(node.right).data;
			node.right=remove(node.data,node.right); //删除替换的最小数据
		}
		else 
		    node=(node.left!=null)?node.left:node.right;
		return node;
	}
}
