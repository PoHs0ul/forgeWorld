package utilities;

public class DoubleLinkedLockedList<Type> {
	DoubleLinkedLockedListNode<Type> head;//reference to the first element of the list
	DoubleLinkedLockedListNode<Type> tail;//reference to the last element of the list
	
	public DoubleLinkedLockedList(){
		head=null;
		tail=null;
	}
	
	public DoubleLinkedLockedListNode<Type> getHead(){
		return head;
	}
	
	//Add a node to the end of the list
	public DoubleLinkedLockedListNode<Type> addNode(Type newContent){
		DoubleLinkedLockedListNode<Type> newNode=new DoubleLinkedLockedListNode<Type>(this, tail, null, newContent);
		synchronized(newNode){
			if(head==null){
				tail=newNode;
				head=tail;
			}else{
				tail.nextNode=newNode;
				tail=tail.nextNode;
			}
		}
		return tail;
	}
}
