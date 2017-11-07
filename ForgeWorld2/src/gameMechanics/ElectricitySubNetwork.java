package gameMechanics;

public class ElectricitySubNetwork {
	
	private utilities.DoubleLinkedLockedListNode<ElectricitySubNetwork> networkNode;
	private ElectricityNetwork network;
	
	private utilities.DoubleLinkedLockedList<ElectricityLvPole> poles;
	
	ElectricitySubNetwork(ElectricityNetwork net, utilities.DoubleLinkedLockedList<ElectricitySubNetwork> list){
		networkNode=list.addNode(this);
		network=net;
	}
	
	public utilities.DoubleLinkedLockedListNode<ElectricityLvPole> addPole(ElectricityLvPole newPole) {
		return poles.addNode(newPole);
	}
	
}
