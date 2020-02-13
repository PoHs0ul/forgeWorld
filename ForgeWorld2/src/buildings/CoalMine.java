package buildings;

import java.util.ArrayList;
import java.util.Arrays;

import gameMechanics.Building;
import gameMechanics.ResourceList;

public class CoalMine extends gameMechanics.Building{

	private static int [] animationArray;
	
	public CoalMine(ResourceList resourceList){
		super(resourceList);
	}
	
	public CoalMine(gameMechanics.Map map, int x, int y, int angle, utilities.DoubleLinkedLockedList<Building> list) {
		super(map, x, y, angle, list);
	}
	
	@Override
	public ArrayList<String> getCostItemNames() {
		return new ArrayList<>(Arrays.asList("Steel","Wood"));
	}

	@Override
	public ArrayList<Double> getCostItemDoubleAmounts() {
		return new ArrayList<>(Arrays.asList(0.0, 0.0));
	}
	
	@Override
	public ArrayList<String> getCostItemDoubleAmountUnits(){
		return new ArrayList<>(Arrays.asList("t", "t"));
	}

	public int getMaxStrPts() {
		return 1000;
	}

	public int[][] getHitMap() {
		return new int[][] {{1,1},{1,1},{1,1}};
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
