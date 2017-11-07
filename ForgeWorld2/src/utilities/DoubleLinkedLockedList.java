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
	
	public DoubleLinkedLockedListNode<Type> getTail(){
		return tail;
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
	
	//Adds the content from another list to this one. Leaves the other list empty
	public void addList(DoubleLinkedLockedList<Type> listToAdd) {
		//update content in the individual nodes
		for(DoubleLinkedLockedListNode<Type> i=listToAdd.head;i!=listToAdd.tail;i=i.getNextNode()) {
			i.parentList=this;
		}
		//update heads and tails
		if(head==null) {
			head=listToAdd.head;
			tail=listToAdd.tail;
		}else {
			tail.nextNode=listToAdd.head;
			tail=listToAdd.tail;
		}
		listToAdd.head=null;
		listToAdd.tail=null;
	}
}
