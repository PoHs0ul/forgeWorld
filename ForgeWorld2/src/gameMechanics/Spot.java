package gameMechanics;

public class Spot {
	public static final int size=50;
	
	private Building occupyingBuilding;//A reference to the building which is on this spot, is null if there is none
	private String terrainType;//A string which represents the terrain type of this spot
	int texture;//An integer which represents the texture variation of this spot
	
	Spot(String terType, int texture){
		occupyingBuilding=null;
		terrainType=terType;
	}
	
	public Building getBuilding(){
		return occupyingBuilding;
	}
	
	public void setBuilding(Building newBuilding){
		occupyingBuilding=newBuilding;
	}
	
	public String getTerrainType() {
		return terrainType;
	}
	
	public int getTexture() {
		return texture;
	}
}
