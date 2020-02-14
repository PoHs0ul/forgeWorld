package gameMechanics;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import dimensionSystem.DimensionalQuantity;

public class BuildingUniversalData extends MapObjectUniversalData{
	
	private ArrayList<Integer> costItemIDs;//Saves the IDs of the building cost resource types
	private ArrayList<DimensionalQuantity> costItemAmounts;//Saves the building cost in a format which is consistent with the internally used format
	
	public BuildingUniversalData(String name, Class<? extends Building<?>> associatedBuildingClass, ResourceList resourceList, String[] costItemNames, double[] costItemDoubleAmounts, String[] costItemDoubleAmountUnits, int[][] hitMap) {
		super(associatedBuildingClass, name, hitMap);
		
		//calculate cost in a format which is consistent with the rest of the game
		costItemAmounts = new ArrayList<>(costItemNames.length);
		costItemIDs = new ArrayList<>(costItemNames.length);
		for(int i = 0; i<costItemNames.length; ++i) {
			costItemIDs.add(resourceList.getResourceID(costItemNames[i]));
			costItemAmounts.add(new DimensionalQuantity(resourceList.getResource(costItemIDs.get(i)).getAmount(), costItemDoubleAmounts[i], costItemDoubleAmountUnits[i]));
		}
	}
	
	public ArrayList<Integer> getCostItemIDs(){
		return costItemIDs;
	}
	
	public ArrayList<DimensionalQuantity> getCostItemAmounts(){
		return costItemAmounts;
	}
	
	public boolean areResourcesAvailable(Map map){
		return map.resources.possibleToRemResByIDs(getCostItemIDs(), getCostItemAmounts());
	}
	
	public boolean isBuildable(Map map, int x, int y, int angle){
		return map.resources.possibleToRemResByIDs(getCostItemIDs(), getCostItemAmounts()) && isPlacable(map, x, y, angle);
	}
	
	public int buildBuilding(Map map, int x, int y, int angle, utilities.DoubleLinkedLockedList<Building<?>> list) {
		if(!areResourcesAvailable(map)){
			return 2;//Code for not enough resources
		}else if(!isPlacable(map, x, y, angle)){
			return 3;//Code for unsuitable terrain
		}else{
			try {
				associatedMapObjectClass.getConstructor(BuildingUniversalData.class, Map.class, int.class, int.class, int.class, utilities.DoubleLinkedLockedList.class).newInstance(this, map, x, y, angle, list);
				return 0;//Code for succesfull placement
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
				System.out.println("Required constructor for instantiation of class "+associatedMapObjectClass+" not found!");
				e.printStackTrace();
				return 1;//Code for unknown error
			} catch (SecurityException e) {
				e.printStackTrace();
				return 1;//Code for unknown error
			}
		}
	}
	
	//Get the production of the associated building
	public DimensionalQuantity[] getProduction() {
		return new DimensionalQuantity[0];//TODO: Actual implementation
	}
}
