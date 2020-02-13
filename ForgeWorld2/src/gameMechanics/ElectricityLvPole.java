package gameMechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ElectricityLvPole extends Building{
	//Variables required by superclass
	private static int [] animationArray;
	
	//Constants
	private final static double cableRange=10;
	
	//Control the behavior of all electricity lv poles
	private static boolean addAutomatedWiresOnBuild = true;//Whether to add automated wires to all poles which are newly built
	
	private Vector<ElectricityLvPole> connectedPoles;
	private ElectricitySubNetwork subNetwork;
	private utilities.DoubleLinkedLockedListNode<ElectricityLvPole> associatedNode;
	
	//private constructor to sum up the common elements of the two main constructors
	//private ElectricityLvPole(Map map, int x, int y, int angle){
	//	super(map, x, y, angle);
	//}
	
	public ElectricityLvPole(Map map, int x, int y, int angle, utilities.DoubleLinkedLockedList<Building> list, ElectricityNetwork net, boolean addAutomatedWires){
		super(map, x, y, angle, list);
		if(addAutomatedWires) {
			Vector<ElectricityLvPole> connectedPoleCandidates=new Vector<ElectricityLvPole>(0,1);
			
			Vector<Double> connectedPoleDistances=new Vector<Double>(0,1);
			for(int i=x-(int)cableRange;i<=x+(int)cableRange;++i) {
				for(int j=y-(int)Math.sqrt(cableRange*cableRange-i*i);j<=y+(int)Math.sqrt(cableRange*cableRange-i*i);++j) {
					if(map.getSpotFromSpCoords(i, j).getElectricityPole()!=null) {
						boolean add=true;
						//Go through all subnetworks which have been discovered
						for(int k=0;k<connectedPoleCandidates.size();++k) {
							//check if the new pole belongs to one of the discovered networks
							if(map.getSpotFromSpCoords(i, j).getElectricityPole().getSubNetwork() == connectedPoleCandidates.get(k).getSubNetwork()) {
								add=false;
								//check if the new pole is closer than the other discovered poles from this network
								if(Math.sqrt((i-x)*(i-x)+(j-y)*(j-y)) < connectedPoleDistances.get(k)) {
									//Set this as the new connect pole for this network
									connectedPoleCandidates.set(k, map.getSpotFromSpCoords(i, j).getElectricityPole());
									connectedPoleDistances.set(k, Math.sqrt((i-x)*(i-x)+(j-y)*(j-y)));
								}
							}
						}
						//Add entry for new connected Network
						if(add) {
							connectedPoleCandidates.add(map.getSpotFromSpCoords(i, j).getElectricityPole());
							connectedPoleDistances.add(Math.sqrt((i-x)*(i-x)+(j-y)*(j-y)));
						}
					}
				}
			}
			//Setting the new subnetwork memberships
			if(connectedPoleCandidates.size() == 0) {
				setSubNetwork(net.addSubNetwork());
			}else {
				setSubNetwork(connectedPoleCandidates.get(1).getSubNetwork());
			}
					
			//connect the chosen poles
			connectedPoles=new Vector<ElectricityLvPole>(0,1);
			for(int i=0;i<connectedPoleCandidates.size();++i) {
				addConnection(connectedPoleCandidates.get(i));
			}
		}else {
			connectedPoles=new Vector<ElectricityLvPole>(0,1);
			setSubNetwork(net.addSubNetwork());
		}
	}
	
	//Constructor for the "build" function
	public ElectricityLvPole(Map map, int x, int y, int angle, utilities.DoubleLinkedLockedList<Building> list) {
		this(map, x, y, angle, list, map.electricityNet, addAutomatedWiresOnBuild);
	}
	
	//public method to create a new connection from this pole to another one
	public void addConnection(ElectricityLvPole poleToAdd) {
		connectedPoles.add(poleToAdd);
		addConnectionForSecondPole(poleToAdd);
		if(poleToAdd.getSubNetwork()!=getSubNetwork()) {
			subNetwork.getNetwork().mergeSubNetworks(getSubNetwork(), poleToAdd.getSubNetwork());
		}
	}
	//private method to update the information on the second pole if a new connection is added
	private void addConnectionForSecondPole(ElectricityLvPole poleToAdd) {
		connectedPoles.add(poleToAdd);
	}
	//method which is called to embed a new pole in the electricity network
	private void setSubNetwork(ElectricitySubNetwork subNet) {
		subNetwork=subNet;
		associatedNode=subNet.addPole(this);
	}
	
	void associateToNewSubNetwork(ElectricitySubNetwork subNet) {
		subNetwork=subNet;
	}
	
	public ElectricitySubNetwork getSubNetwork() {
		return subNetwork;
	}
	
	@Override
	public int getMaxStrPts() {
		return 100;//TODO:Change; random value for now
	}

	@Override
	public int[][] getHitMap() {
		return new int[][] {{1}};
	}

	@Override
	protected void occupySpot(Spot spot) {
		spot.setElectricityPole(this);
	}

	@Override
	public String getTextureFileName() {
		return "basic.electricityLvPole";
	}

	@Override
	public int[] getAnimationArray() {
		return animationArray;
	}

	@Override
	public void setAnimationArray(int[] array) {
		animationArray=array.clone();
	}
	
	@Override
	public boolean isPlacable(Map map, int x, int y, int angle){
		for(int i=0;i<getHitMap().length;++i){
			for(int j=0;j<getHitMap()[i].length;++j){
				if(getHitMap()[i][j]==1){
					Spot s=map.getSpotFromSpCoords(x-((((angle+1)/2)%2)*2-1)*(i-getHitMap().length/2), y-(((angle/2)%2)*2-1)*(j-getHitMap()[i].length/2));
					if(s==null||s.getBuilding()!=null||s.getElectricityPole()!=null){
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public ArrayList<String> getCostItemNames() {
		return new ArrayList<>(Arrays.asList("Steel","Wood"));
	}

	@Override
	public ArrayList<Double> getCostItemDoubleAmounts() {
		return new ArrayList<>(Arrays.asList(0.0, 0.0));
	}
	
	@Override
	public ArrayList<String> getCostItemDoubleAmountUnits(){
		return new ArrayList<>(Arrays.asList("t", "t"));
	}
}
