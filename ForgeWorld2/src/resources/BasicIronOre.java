package resources;

import gameMechanics.QuantityDimensionsManager;
import gameMechanics.Resource;

public class BasicIronOre extends Resource{

	public BasicIronOre(QuantityDimensionsManager dimensionManager) {
		super(dimensionManager.massDimension, 1.0, "mg");
	}
	
	//Constructor for copying from template
	public BasicIronOre(BasicIronOre templateResource, double startAmount, String startAmountUnit) {
		super(templateResource, startAmount, startAmountUnit);
	}
	
	@Override
	public String getDefaultUnit() {
		return "t";
	}
	
	@Override
	public String getName() {
		return "Iron Ore";
	}

}
