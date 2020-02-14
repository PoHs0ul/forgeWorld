package gameMechanics;

import buildings.ElectricityLvPole;
import utilities.DoubleLinkedLockedList;

public class ElectricitySubNetwork {
	
	private utilities.DoubleLinkedLockedListNode<ElectricitySubNetwork> networkNode;
	private ElectricityNetwork network;
	
	private DoubleLinkedLockedList<ElectricityLvPole> poles;
	
	ElectricitySubNetwork(ElectricityNetwork net, DoubleLinkedLockedList<ElectricitySubNetwork> list){
		networkNode=list.addNode(this);
		network=net;
		
		poles = new DoubleLinkedLockedList<>();
	}
	
	public utilities.DoubleLinkedLockedListNode<ElectricityLvPole> addPole(ElectricityLvPole newPole) {
		return poles.addNode(newPole);
	}
	
	public ElectricityNetwork getNetwork() {
		return network;
	}
	
	public DoubleLinkedLockedList<ElectricityLvPole> getPoles() {
		return poles;
	}
	
	public void delete() {
		networkNode.remove();
	}
}
