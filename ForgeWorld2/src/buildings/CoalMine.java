package buildings;

import gameMechanics.Building;
import gameMechanics.BuildingUniversalData;

public class CoalMine extends Building<BuildingUniversalData>{

	private static int [] animationArray;
	
	public CoalMine(BuildingUniversalData universalDataObject, gameMechanics.Map map, int x, int y, int angle, utilities.DoubleLinkedLockedList<Building<?>> list) {
		super(universalDataObject, map, x, y, angle, list);
	}
	
	public static BuildingUniversalData createUniversalDataObject(gameMechanics.ResourceList resourceList) {
		
		String name = "Coal Mine";
		
		String [] costItemNames = {"Steel","Wood"};
		double [] costItemDoubleAmounts = {0.0, 0.0};
		String [] costItemDoubleAmountUnits = {"t", "t"};
		int[][] hitMap = {{1,1},{1,1},{1,1}};
		
		return new BuildingUniversalData(name, CoalMine.class, resourceList, costItemNames, costItemDoubleAmounts, costItemDoubleAmountUnits, hitMap);
	}

	public int getMaxStrPts() {
		return 1000;
	}

	public String getTextureFileName() {
		return "basic.coalMine";
	}

	public int[] getAnimationArray() {
		return animationArray;
	}

	public void setAnimationArray(int[] array) {
		animationArray=array.clone();
	}
}
