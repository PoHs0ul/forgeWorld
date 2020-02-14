package gameMechanics;

import java.util.ArrayList;
import java.util.Iterator;

import utilities.DoubleLinkedLockedListNode;

public abstract class MapObject<T extends MapObjectUniversalData> {
	//A class for everything which can be placed on the map
	protected T universalDataObject;
	
	protected int strPts;
	protected ArrayList<Spot> occupyingSpots;
	protected DoubleLinkedLockedListNode<?> node;
	
	public MapObject(){
		
	}
	
	public MapObject(T universalDataObject, Map map, int x, int y, int angle){
		this.universalDataObject = universalDataObject;
		
		strPts=getMaxStrPts();
		//save all the spots which are occupied by the object
		int[][] hitMap = universalDataObject.getHitMap();
		occupyingSpots=new ArrayList<Spot>();
		for(int i=0;i<hitMap.length;++i){
			for(int j=0;j<hitMap[i].length;++j){
				if(hitMap[i][j]==1){
					Spot s=map.getSpotFromSpCoords(x-((((angle+1)/2)%2)*2-1)*(i-hitMap.length/2), y-(((angle/2)%2)*2-1)*(j-hitMap[i].length/2));
					occupyingSpots.add(s);//save this spot
					occupySpot(s);//give information about this object to the spot
				}
			}
		}
		occupyingSpots.trimToSize();//use only the necessary space
	}
	
	//Delete this map object
	public void delete() {
		//Remove from list if necessary
		if(!(node == null)) {
			node.remove();
			node = null;
		}
		
		for(Iterator<Spot> it = occupyingSpots.iterator(); it.hasNext();) {
			freeSpot(it.next());
		}
	}
	
	public void setNode(DoubleLinkedLockedListNode<?> node) {
		this.node=node;
	}
	
	public void remStrPts(int amount){
		strPts-=amount;
	}
	
	public int getStrPts(){
		return strPts;
	}
	
	public abstract int getMaxStrPts();
	protected abstract void occupySpot(Spot spot);
	protected abstract void freeSpot(Spot spot);
	public abstract String getTextureFileName();
	public abstract int[] getAnimationArray();
	public abstract void setAnimationArray(int[] array);
}
