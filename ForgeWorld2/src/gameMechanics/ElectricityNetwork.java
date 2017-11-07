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
	
	public void mergeSubNetworks(ElectricitySubNetwork subNet1, ElectricitySubNetwork subNet2) {
		//set the individual poles to reference to the new subnetwork
		for(utilities.DoubleLinkedLockedListNode<ElectricityLvPole> i=subNet2.getPoles().getHead();i!=subNet2.getPoles().getTail();i=i.getNextNode()) {
			i.getContent().associateToNewSubNetwork(subNet1);
		}
		//merge the two linked lists
		subNet1.getPoles().addList(subNet2.getPoles());
		//get rid of the now unused subnetwork
		subNet2.delete();
	}
}
