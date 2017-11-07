package gameMechanics;

import java.util.Vector;

public class ElectricityLvPole extends MapObject{
	//Variables required by superclass
	private static int [] animationArray;
	
	//Constants
	private final static double cableRange=10;
	
	private Vector<ElectricityLvPole> connectedPoles;
	private ElectricitySubNetwork subNetwork;
	private utilities.DoubleLinkedLockedListNode<ElectricityLvPole> associatedNode;
	
	//private constructor to sum up the common elements of the two main constructors
	private ElectricityLvPole(Map map, int x, int y, int angle){
		super(map, x, y, angle);
	}
	//Public constructor to create a representative without content
	public ElectricityLvPole() {
		
	}
	//Public constructor for the case that no automatic connections should be made
	public ElectricityLvPole(Map map, int x, int y, int angle, ElectricitySubNetwork subNet){
		this(map, x, y, angle);
		connectedPoles=new Vector<ElectricityLvPole>(0,1);
		subNetwork=subNet;
	}
	//Public constructor for the case that automatic connections should be made
	public ElectricityLvPole(Map map, int x, int y, int angle, ElectricityNetwork net){
		this(map, x, y, angle);
		
		Vector<ElectricityLvPole> connectedPoleCandidates=new Vector<ElectricityLvPole>(0,1);
		
		Vector<Double> connectedPoleDistances=new Vector<Double>(0,1);
		for(int i=x-(int)cableRange;i<=x+(int)cableRange;++i) {
			for(int j=y-(int)Math.sqrt(cableRange*cableRange-i*i);j<=y+(int)Math.sqrt(cableRange*cableRange-i*i);++j) {
				if(map.getSpotFromSpCoords(i, j).getElectricityPole()!=null) {
					boolean add=true;
					//Go through all subnetworks wich have been discovered
					for(int k=0;k<connectedPoleCandidates.size();++k) {
						//check if the new pole belongs to one of the discovered networks
						if(map.getSpotFromSpCoords(i, j).getElectricityPole().getSubNetwork() == connectedPoleCandidates.get(k).getSubNetwork()) {
							add=false;
							//check if the new pole is closer tha the other discovered poles from this network
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
		//Seting the new subnetwork memberships
		if(connectedPoles.size() == 0) {
			setSubnetwork(net.addSubNetwork());
		}else {
			setSubnetwork(connectedPoleCandidates.get(1).getSubNetwork());
		}
				
		//connect the chosen poles
		connectedPoles=new Vector<ElectricityLvPole>(0,1);
		for(int i=0;i<connectedPoleCandidates.size();++i) {
			addConnection(connectedPoleCandidates.get(i));
		}
		
		
	}
	
	//public method to create a new connection from this pole to another one
	public void addConnection(ElectricityLvPole poleToAdd) {
		connectedPoles.add(poleToAdd);
		addConnectionForSecondPole(poleToAdd);
	}
	//private method to update the information on the second pole if a new connection is added
	private void addConnectionForSecondPole(ElectricityLvPole poleToAdd) {
		connectedPoles.add(poleToAdd);
	}
	//method which is called to embed a new pole in the electricity network
	private void setSubnetwork(ElectricitySubNetwork subNet) {
		subNetwork=subNet;
		associatedNode=subNet.addPole(this);
	}
	
	public ElectricitySubNetwork getSubNetwork() {
		return subNetwork;
	}
	
	@Override
	public int getMaxStrPts() {
		return 100;//random value (for now)
	}

	@Override
	public int[][] getHitMap() {
		return new int[][] {{1}};
	}

	@Override
	protected void updateSpot(Spot spot) {
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
}
