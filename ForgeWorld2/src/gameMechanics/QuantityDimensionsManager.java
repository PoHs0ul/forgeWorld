package gameMechanics;

import dimensionSystem.QuantityDimension;

public class QuantityDimensionsManager {
	
	public final QuantityDimension massDimension;
	public final QuantityDimension lengthDimension;
	
	public QuantityDimensionsManager() {
		String[] massUnits =              {"t", "kg", "g" , "mg"};
		double[] massExchangeParameters = {1e3, 1   , 1e-3, 1e-6};
		massDimension = new QuantityDimension(massUnits, massExchangeParameters);
		
		String[] lengthUnits =              {"km", "m", "mm" , "um"};
		double[] lengthExchangeParameters = {1e3, 1   , 1e-3, 1e-6};
		lengthDimension = new QuantityDimension(lengthUnits, lengthExchangeParameters);
	}
}
