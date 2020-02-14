package gameMechanics;

//A class which saves data universally common to all map objects of a certain type and whose functionality is shared by all map object types
public class MapObjectUniversalData {
	
	protected Class<? extends MapObject<?>> associatedMapObjectClass;//saves the class of the associated map object type
	
	//Data about the map object type
	protected String name;
	protected int[][] hitMap;
	
	public MapObjectUniversalData(Class<? extends MapObject<?>> associatedMapObjectClass, String name, int[][] hitMap) {
		this.associatedMapObjectClass = associatedMapObjectClass;
		
		this.name = name;
		this.hitMap = hitMap;
	}
	
	public int[][] getHitMap(){
		return hitMap;
	}
	
	//returns a boolean indicating whether an instance of the associated class can be placed at a certain position or not
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
