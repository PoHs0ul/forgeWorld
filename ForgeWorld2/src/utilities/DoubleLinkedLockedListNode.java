package utilities;

public class DoubleLinkedLockedListNode<Type> {
	DoubleLinkedLockedList<Type> parentList;
	
	private Type content;
	DoubleLinkedLockedListNode<Type> nextNode;
	DoubleLinkedLockedListNode<Type> previousNode;
	
	DoubleLinkedLockedListNode(DoubleLinkedLockedList<Type> parent, DoubleLinkedLockedListNode<Type> previous, DoubleLinkedLockedListNode<Type> next, Type nodeContent){
		parentList=parent;
		nextNode=next;
		previousNode=previous;
		content=nodeContent;
	}
	
	public DoubleLinkedLockedListNode<Type> getNextNode(){
		return nextNode;
	}
	public DoubleLinkedLockedListNode<Type> getPreviousNode(){
		return previousNode;
	}
	public Type getContent(){
		return content;
	}
	
	//removes this node from the list
	public void remove(){
		synchronized(this){
			if(previousNode==null){
				parentList.head=nextNode;
			}else{
				previousNode.nextNode=nextNode;
			}
			if(nextNode==null){
				parentList.tail=previousNode;
			}else{
				nextNode.previousNode=previousNode;
			}
		}	
	}
}
