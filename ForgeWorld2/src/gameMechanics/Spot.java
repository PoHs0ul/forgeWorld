package gameMechanics;

public class Spot {
	public static final int size=50;
	
	private Building occupyingBuilding;//A reference to the building which is on this spot, is null if there is none
	
	Spot(){
		occupyingBuilding=null;
	}
	
	public Building getBuilding(){
		return occupyingBuilding;
	}
	
	public void setBuilding(Building newBuilding){
		occupyingBuilding=newBuilding;
	}
}
