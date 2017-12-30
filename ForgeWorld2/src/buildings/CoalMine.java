package buildings;

import java.util.ArrayList;
import java.util.Arrays;

import gameMechanics.Building;

public class CoalMine extends gameMechanics.Building{

	private static int [] animationArray;
	
	public CoalMine(){
		
	}
	
	public CoalMine(gameMechanics.Map map, int x, int y, int angle, utilities.DoubleLinkedLockedList<Building> list) {
		super(map, x, y, angle, list);
	}
	
	public ArrayList<String> getCostItemNames() {
		return new ArrayList<String>(Arrays.asList("Steel","Wood"));
	}

	public ArrayList<Double> getCostItemAmounts() {
		return new ArrayList<Double>(Arrays.asList(0.0,0.0));
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
