package gameMechanics;

import java.util.ArrayList;

public abstract class MapObject {
	//A class for everything which can be placed on the map
	private int strPts;
	private ArrayList<Spot> occupyingSpots;
	
	public MapObject(){
		
	}
	
	public MapObject(Map map, int x, int y, int angle){
		strPts=getMaxStrPts();
		//save all the spots which are occupied by the object
		occupyingSpots=new ArrayList<Spot>();
		for(int i=0;i<getHitMap().length;++i){
			for(int j=0;j<getHitMap()[i].length;++j){
				if(getHitMap()[i][j]==1){
					Spot s=map.getSpotFromSpCoords(x-((((angle+1)/2)%2)*2-1)*(i-getHitMap().length/2), y-(((angle/2)%2)*2-1)*(j-getHitMap()[i].length/2));
					occupyingSpots.add(s);//save this spot
					updateSpot(s);//give information about this object to the spot
				}
			}
		}
		occupyingSpots.trimToSize();//use only the necessary space
	}
	
	public void remStrPts(int amount){
		strPts-=amount;
	}
	
	public int getStrPts(){
		return strPts;
	}
	
	//returns a boolean indicating whether the object can be placed at a certain position or not
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
	
	public abstract int getMaxStrPts();
	public abstract int[][] getHitMap();
	protected abstract void updateSpot(Spot spot);
	public abstract String getTextureFileName();
	public abstract int[] getAnimationArray();
	public abstract void setAnimationArray(int[] array);
}
