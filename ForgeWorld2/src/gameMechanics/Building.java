package gameMechanics;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class Building extends MapObject{
	//A class for all buildings on the map which the player controls
	private utilities.DoubleLinkedLockedListNode<Building> node;//node which corresponds to the position in the list of all buildings where this building is saved
	
	public Building(){
		
	}
	
	protected Building(Map map, int x, int y, int angle, utilities.DoubleLinkedLockedList<Building> list) {
		super(map, x, y, angle);
		node=list.addNode(this);//Add the building to the list and remember where it is saved
	}

	//What happens each time a production tick occurs
	public void onProductionTick(){
		
	}
	
	//called to delete the building
	public void delete(){
		node.remove();
	}
	
	protected void updateSpot(Spot spot){
		spot.setBuilding(this);
	}
	
	public boolean areResourcesAvailable(Map map){
		return map.resources.possibleToRemResByNames(getCostItemNames(), getCostItemAmounts());
	}
	
	public boolean isBuildable(Map map, int x, int y, int angle){
		return map.resources.possibleToRemResByNames(getCostItemNames(), getCostItemAmounts()) && isPlacable(map, x, y, angle);
	}
	
	public int build(Map map, int x, int y, int angle, utilities.DoubleLinkedLockedList<Building> list){
		if(!areResourcesAvailable(map)){
			return 2;//Code for not enough resources
		}else if(!isPlacable(map, x, y, angle)){
			return 3;//Code for unsuitable terrain
		}else{
			try {
				System.out.println(this.getClass().getConstructors()[1]);
				this.getClass().getConstructor(Map.class, int.class, int.class, int.class, utilities.DoubleLinkedLockedList.class).newInstance(map, x, y, angle, list);
			} catch (InstantiationException e) {
				e.printStackTrace();
				return 1;//Code for unknown error
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return 1;//Code for unknown error
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return 1;//Code for unknown error
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				return 1;//Code for unknown error
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				return 1;//Code for unknown error
			} catch (SecurityException e) {
				e.printStackTrace();
				return 1;//Code for unknown error
			}
			return 0;//Code for successful placement
		}
	}
	
	//Getter methods
	
	//Abstract methods
	
	
	//Abstract getter methods
	public abstract ArrayList<String> getCostItemNames();
	public abstract ArrayList<Integer> getCostItemAmounts();
	
	//Abstract setter methods
	
	
}
