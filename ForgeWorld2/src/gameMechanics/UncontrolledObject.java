package gameMechanics;

public abstract class UncontrolledObject extends MapObject{
	//A class for all objects on the map which the player does not control
	private utilities.DoubleLinkedLockedListNode<UncontrolledObject> node;
	
	public UncontrolledObject(Map map, int x, int y, int angle, utilities.DoubleLinkedLockedList<UncontrolledObject> list) {
		super(map, x, y, angle);
		node=list.addNode(this);
	}
	
	//called to delete the object
	public void delete(){
		node.remove();
	}

}
