package resources;

import gameMechanics.QuantityDimensionsManager;
import gameMechanics.Resource;

public class BasicCoke extends Resource{

	public BasicCoke(QuantityDimensionsManager dimensionManager) {
		super(dimensionManager.massDimension, 1.0, "mg");
	}
	
	//Constructor for copying from template
	public BasicCoke(BasicCoke templateResource, double startAmount, String startAmountUnit) {
		super(templateResource, startAmount, startAmountUnit);
	}
	
	@Override
	public String getDefaultUnit() {
		return "t";
	}
	
	@Override
	public String getName() {
		return "Coke";
	}

}
