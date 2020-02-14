package gameMechanics;

import dimensionSystem.DimensionalQuantity;

public abstract class Building<T extends BuildingUniversalData> extends MapObject<T>{
	//A class for all buildings on the map which the player controls
	private Map map;//saves a reference to the map the building is in
	
	private int locationInProductionBuildingList = -1;//saves the index in the production building list at which this building is saved, -1 if it is not saved in one.
	
	protected Building(T universalDataObject, Map map, int x, int y, int angle, utilities.DoubleLinkedLockedList<Building<?>> list) {
		super(universalDataObject, map, x, y, angle);
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
	
	//Returns whether this building is relevant for production calculations
	public boolean isProductionBuilding() {
		return getProduction().length != 0;
	}
	
	//Getter methods
	public DimensionalQuantity[] getProduction() {
		return universalDataObject.getProduction();
	}
	
	//Abstract methods
	
	//Abstract getter methods
	
	//Abstract setter methods
	
	
}
