package gameMechanics;


public class Map {
	private Chunk[][] chunks;//a two dimensional array of chunks
	
	public ResourceManager resources;//An object which keeps track of the resources of the player
	
	public utilities.DoubleLinkedLockedList<Building> buildingList;//A list which contains all the buildings the player has under his control
	public utilities.DoubleLinkedLockedList<UncontrolledObject> mapObjectList;//A list of all objects on the map which the player does not control
	
	public ElectricityNetwork electricityNet;//A Class which handles the electricity network
	
	public Map(GameMechanics mec, int x, int y){
		resources=mec.getResourceList().createNewResourceManager();
		buildingList=new utilities.DoubleLinkedLockedList<Building>();
		mapObjectList=new utilities.DoubleLinkedLockedList<UncontrolledObject>();
		
		//create the required number of chunks in x and y direction
		chunks=new Chunk[x][y];
		
		for(int i=0;i<x;++i){
			for(int j=0;j<y;++j){
				chunks[i][j]=new Chunk();//create a new chunk at the position
			}
		}
	}
	
	//returns a boolean, indicating if a given point (in pixels) is on the map, returns false if the map is not fully created
	public boolean containsPxPoint(int x,int y){
		try{
			return x>=0&&y>=0&&x<chunks.length*Chunk.size*Spot.size&&y<chunks[0].length*Chunk.size*Spot.size;
		}catch(IndexOutOfBoundsException e){
			return false;
		}
	}
	//returns a reference to the chunk a given point (in pixels) is in. Returns a null reference if the point is outside the map
	public Chunk getChunkFromPxCoords(int x,int y){
		if(containsPxPoint(x, y)){
			return chunks[x/(Spot.size*Chunk.size)][y/(Spot.size*Chunk.size)];
		}else{
			return null;
		}
	}
	//returns a reference to the spot a given point (in pixels) is in. Returns a null reference if the point is outside the map
	public Spot getSpotFromPxCoords(int x,int y){
		try{
			return getChunkFromPxCoords(x,y).getSpot((x/Spot.size)%Chunk.size,(y/Spot.size)%Chunk.size);
		}catch(NullPointerException e){
			return null;
		}
	}
	//returns a reference to the chunk a given spot (by spots) is in.
		public Chunk getChunkFromSpCoords(int x, int y){
			return chunks[x/Chunk.size][y/Chunk.size];
		}
	//returns a reference to a spot by its coordinates (in spots).
	public Spot getSpotFromSpCoords(int x, int y){
		return getChunkFromSpCoords(x,y).getSpot(x%Chunk.size, y%Chunk.size);
	}
	
	
	
}
