package dimensionSystem;

public class DimensionalQuantity implements Cloneable{
	
	protected long amount;
	protected QuantityDimension dimension;
	protected double dimensionBaseFactor;
	
	public DimensionalQuantity(QuantityDimension dimension, long startAmount, double dimensionBaseFactor){
		this.amount = startAmount;
		this.dimension = dimension;
		this.dimensionBaseFactor = dimensionBaseFactor;
	}
	
	private DimensionalQuantity(QuantityDimension dimension, double startAmountInDimStdUnit, double dimensionBaseFactor) {
		this(dimension, (long)(Math.round(startAmountInDimStdUnit/dimensionBaseFactor)), dimensionBaseFactor);
	}
	
	public DimensionalQuantity(QuantityDimension dimension, double startAmount, String startAmountUnit, double dimensionBaseFactor) {
		this(dimension, dimension.getInStdUnit(startAmount, startAmountUnit), dimensionBaseFactor);
	}
	
	public DimensionalQuantity(QuantityDimension dimension, double startAmount, String startAmountUnit, double smallestAmount, String smallestAmountUnit) {
		this(dimension, startAmount, startAmountUnit, dimension.getInStdUnit(smallestAmount, smallestAmountUnit));
	}
	
	public DimensionalQuantity(QuantityDimension dimension, long startAmount, double smallestAmount, String smallestAmountUnit) {
		this(dimension, startAmount, dimension.getInStdUnit(smallestAmount, smallestAmountUnit));
	}
	
	//Constructor for creating a new Instance based on another instance as a template
	public DimensionalQuantity(DimensionalQuantity templateDimensionalQuantity, double startAmount, String startAmountUnit) {
		this(templateDimensionalQuantity.dimension, startAmount, startAmountUnit, templateDimensionalQuantity.dimensionBaseFactor);
	}
	
	private void setDimensionBaseFactor(double dimensionBaseFactor) {
		this.multiply(this.dimensionBaseFactor/dimensionBaseFactor);
		this.dimensionBaseFactor =dimensionBaseFactor;
	}
	
	public String getValue(String unit) {
		return amount*dimensionBaseFactor/dimension.getInStdUnit(1, unit)+" "+unit;//TODO: Implement some way of circumventing the hash map call when one unit is used fequently
	}
	
	@Override
	public DimensionalQuantity clone() {
		return new DimensionalQuantity(dimension, amount, dimensionBaseFactor);
	}
	
	//public boolean equals(DimensionalQuantity q) {
	//	assert dimension == q.dimension;
	//	return dimensionBaseFactor==q.dimensionBaseFactor && amount==q.amount;
	//}
	
	//Math operations
	public DimensionalQuantity add(DimensionalQuantity quantityToAdd) {
		assert dimension == quantityToAdd.dimension;
		if(dimensionBaseFactor == quantityToAdd.dimensionBaseFactor) {
			amount += quantityToAdd.amount;
		}else {
			DimensionalQuantity intermQuantity = quantityToAdd.clone();
			intermQuantity.setDimensionBaseFactor(this.dimensionBaseFactor);
			amount += intermQuantity.amount;
		}
		return this;
	}
	
	public DimensionalQuantity multiply(double factor) {
		this.amount = (long)Math.round(amount*factor); 
		return this;
	}
	
	public DimensionalQuantity multiply(long factor) {
		this.amount *= factor; 
		return this;
	}
	
	public static DimensionalQuantity multiplication(DimensionalQuantity a, double factor) {
		DimensionalQuantity newQuantity = a.clone();
		newQuantity.multiply(factor);
		return newQuantity;
	}
	
	public static DimensionalQuantity multiplication(DimensionalQuantity a, long factor) {
		DimensionalQuantity newQuantity = a.clone();
		newQuantity.multiply(factor);
		return newQuantity;
	}
	
	public static DimensionalQuantity addition(DimensionalQuantity a, DimensionalQuantity b) {
		DimensionalQuantity newQuantity = a.clone();
		a.add(b);
		return newQuantity;
	}
	
	public static boolean smallerThan(DimensionalQuantity a, DimensionalQuantity b) {
		assert a.dimension == b.dimension;
		
		if(a.dimensionBaseFactor == b.dimensionBaseFactor) {
			return a.amount < b.amount;
		}else {
			return a.amount < multiplication(b, b.dimensionBaseFactor/a.dimensionBaseFactor).amount;
		}
	}
	
	public static boolean largerThan(DimensionalQuantity a, DimensionalQuantity b) {
		assert a.dimension == b.dimension;
		
		if(a.dimensionBaseFactor == b.dimensionBaseFactor) {
			return a.amount < b.amount;
		}else {
			return a.amount > multiplication(b, b.dimensionBaseFactor/a.dimensionBaseFactor).amount;
		}
	}
	
	public boolean isNegative() {
		return amount<0;
	}
}
