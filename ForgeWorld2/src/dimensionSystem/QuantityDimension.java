package dimensionSystem;

import java.text.DecimalFormat;
import java.util.HashMap;

public class QuantityDimension {
	
	protected HashMap<String, Double> unitExchangeParameters;
	
	public QuantityDimension(String[] units, double[] unitExchangeParameters) {
		assert units.length == unitExchangeParameters.length;//Same length required
		
		//Prepare hash map
		float hashMapLoadFactor = 0.75f;
		int hashMapCapacity = (int) (Math.ceil(units.length/hashMapLoadFactor));
		this.unitExchangeParameters = new HashMap<>(hashMapCapacity, hashMapLoadFactor);
		
		//Fill hash map
		for(int i = 0; i < units.length; ++i) {
			this.unitExchangeParameters.put(units[i], unitExchangeParameters[i]);
		}
	}
	
	//Returns a given quantity in the internal standard unit of this dimension
	public double getInStdUnit(double inputAmount, String inputUnit) {
		return inputAmount*this.unitExchangeParameters.get(inputUnit);
	}
	
	//Returns a given quantity in the requested unit
	public double getInDifferentUnit(double inputAmount, String inputUnit, String outputUnit) {
		return this.getInStdUnit(inputAmount, inputUnit)/this.getInStdUnit(1.0, outputUnit);
	}
	
	public String getAsString(double inputAmount, String inputUnit, String outputUnit) {
		return Double.toString(this.getInDifferentUnit(inputAmount, inputUnit, outputUnit))+' '+outputUnit;
	}
	
}
