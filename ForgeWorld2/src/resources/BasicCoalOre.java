package resources;

import gameMechanics.QuantityDimensionsManager;
import gameMechanics.Resource;

public class BasicCoalOre extends Resource{

	public BasicCoalOre(QuantityDimensionsManager dimensionManager) {
		super(dimensionManager.massDimension, 1.0, "mg");
	}
	
	//Constructor for copying from template
	public BasicCoalOre(BasicCoalOre templateResource, double startAmount, String startAmountUnit) {
		super(templateResource, startAmount, startAmountUnit);
	}
	
	@Override
	public String getDefaultUnit() {
		return "t";
	}
	
	@Override
	public String getName() {
		return "Coal Ore";
	}

}
