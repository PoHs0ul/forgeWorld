package gameMechanics;

import utilities.DoubleLinkedLockedList;

public class ElectricityNetwork {
	private DoubleLinkedLockedList<ElectricitySubNetwork> subNetworks;
	
	
	ElectricityNetwork(){
		subNetworks=new utilities.DoubleLinkedLockedList<ElectricitySubNetwork>();
	}
	
	public ElectricitySubNetwork addSubNetwork() {
		return new ElectricitySubNetwork(this, subNetworks);
	}
}
