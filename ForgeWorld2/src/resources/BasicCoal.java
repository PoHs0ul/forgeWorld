package resources;

import gameMechanics.QuantityDimensionsManager;
import gameMechanics.Resource;

public class BasicCoal extends Resource{
	
	public BasicCoal(QuantityDimensionsManager dimensionManager) {
		super(dimensionManager.massDimension, 1.0, "mg");
	}
	
	//Constructor for copying from template
	public BasicCoal(BasicCoal templateResource, double startAmount, String startAmountUnit) {
		super(templateResource, startAmount, startAmountUnit);
	}
	
	@Override
	public String getDefaultUnit() {
		return "t";
	}
	
	@Override
	public String getName() {
		return "Coal";
	}

	
}
