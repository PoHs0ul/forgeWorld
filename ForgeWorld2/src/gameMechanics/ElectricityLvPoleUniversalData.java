package gameMechanics;

public class ElectricityLvPoleUniversalData extends BuildingUniversalData{

	public ElectricityLvPoleUniversalData(String name, Class<? extends Building<?>> associatedBuildingClass, ResourceList resourceList, String[] costItemNames, double[] costItemDoubleAmounts, String[] costItemDoubleAmountUnits, int[][] hitMap) {
		super(name, associatedBuildingClass, resourceList, costItemNames, costItemDoubleAmounts, costItemDoubleAmountUnits, hitMap);
	}
	
	@Override
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
