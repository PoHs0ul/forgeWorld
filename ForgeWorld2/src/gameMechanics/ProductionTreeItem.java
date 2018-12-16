package gameMechanics;

public class ProductionTreeItem {
	private Building productionBuilding;
	private int[] requiredItemIDs;
	private double[] requiredItemAmmounts;
	
	ProductionTreeItem(){
		
	}
	
	Building getProductionBuilding() {
		return productionBuilding;
	}
}
