package gameMechanics;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import dimensionSystem.DimensionalQuantity;

public abstract class Building extends MapObject{
	//A class for all buildings on the map which the player controls
	private Map map;//saves a reference to the map the building is in
	private ArrayList<DimensionalQuantity> costItemAmounts;//only initialize for placeholder which are copied to produce actual buildings
	private int locationInProductionBuildingList = -1;//saves the index in the production building list at which this building is saved, -1 if it is not saved in one.
	
	static int i=1;
	
	public Building(ResourceList resourceList){
		//Initialize all the costItemAmounts
		costItemAmounts = new ArrayList<>(getCostItemNames().size());
		for(int i = 0; i<getCostItemNames().size(); ++i) {
			costItemAmounts.add(new DimensionalQuantity(resourceList.getResource(getCostItemNames().get(i)).getAmount(), getCostItemDoubleAmounts().get(i), getCostItemDoubleAmountUnits().get(i)));
		}
	}
	
	protected Building(Map map, int x, int y, int angle, utilities.DoubleLinkedLockedList<Building> list) {
		super(map, x, y, angle);
		this.map = map;
		this.setNode(list.addNode(this));//Add the building to the list and remember where it is saved
		if(isProductionBuilding()) {
			locationInProductionBuildingList = map.addToProductionBuildingList(this);//Add the building to the list and remember where it is saved
		}
	}

	//What happens each time a production tick occurs
	public void onProductionTick(){
		
	}
	
	//called to delete the building
	@Override
	public void delete(){
		super.delete();
		if(locationInProductionBuildingList != -1) {
			map.removeFromProductionBuildingList(locationInProductionBuildingList);
		}
	}
	
	@Override
	protected void occupySpot(Spot spot){
		spot.setBuilding(this);
	}
	
	@Override
	protected void freeSpot(Spot spot) {
		spot.setBuilding(null);
	}
	
	public boolean areResourcesAvailable(Map map){
		return map.resources.possibleToRemResByNames(getCostItemNames(), costItemAmounts);
	}
	
	public boolean isBuildable(Map map, int x, int y, int angle){
		return map.resources.possibleToRemResByNames(getCostItemNames(), costItemAmounts) && isPlacable(map, x, y, angle);
	}
	
	public int build(Map map, int x, int y, int angle, utilities.DoubleLinkedLockedList<Building> list){
		if(!areResourcesAvailable(map)){
			return 2;//Code for not enough resources
		}else if(!isPlacable(map, x, y, angle)){
			return 3;//Code for unsuitable terrain
		}else{
			try {
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
	
	//Returns whether this building is relevant for production calculations
	public boolean isProductionBuilding() {
		return getProduction().length != 0;
	}
	
	//Getter methods
	public DimensionalQuantity[] getProduction() {
		return new DimensionalQuantity[0];//TODO: Actual implementation
	}
	
	
	//Abstract methods
	
	
	//Abstract getter methods
	public abstract ArrayList<String> getCostItemNames();
	public abstract ArrayList<Double> getCostItemDoubleAmounts();
	public abstract ArrayList<String> getCostItemDoubleAmountUnits();
	
	//Abstract setter methods
	
	
}
