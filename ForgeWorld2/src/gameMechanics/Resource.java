package gameMechanics;

import dimensionSystem.DimensionalQuantity;
import dimensionSystem.QuantityDimension;

public abstract class Resource {
	protected DimensionalQuantity amount;
	
	public Resource(Resource templateResource, double startAmount, String startAmountUnit) {
		amount = new DimensionalQuantity(templateResource.amount, startAmount, startAmountUnit);
	}
	
	private Resource(QuantityDimension dimension, long startAmount, double smallestAmount, String smallestAmountUnit){
		amount=new DimensionalQuantity(dimension, startAmount, smallestAmount, smallestAmountUnit);
	}
	
	protected Resource(QuantityDimension dimension, double smallestAmount, String smallestAmountUnit){
		this(dimension, 0l, smallestAmount, smallestAmountUnit);
	}
	
	public void add(DimensionalQuantity toAdd) {
		amount.add(toAdd);
	}
	
	public boolean remove(DimensionalQuantity toRemove) {
		DimensionalQuantity newAmount = DimensionalQuantity.addition(amount, DimensionalQuantity.multiplication(toRemove, -1l));
		if(newAmount.isNegative()) {
			return false;
		}else {
			amount=newAmount;
			return true;
		}
	}
	
	public boolean possibleToRemove(DimensionalQuantity toRemove) {
		return !DimensionalQuantity.addition(amount, DimensionalQuantity.multiplication(toRemove, -1l)).isNegative();
	}
	
	protected DimensionalQuantity getAmount() {
		return amount;
	}
	
	public abstract String getName();
	public abstract String getDefaultUnit();//TODO: Replace with a system which assigns units automatically (possibly implement in DimensionalQuantity Class)
	
	//Whether the resource is production/consumption is calculated by all buildings together or by individual building
	public boolean getUseGlobalCalculation() {
		return true;//Default is calculation by all buildings together; Overwrite to change
	}
	
	@Override
	public String toString() {
		return super.toString()+": "+getName()+": "+getAmount().getValue(getDefaultUnit());
	}
}
