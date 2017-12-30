package gameMechanics;

public abstract class Resource {
	private double amount;
	
	protected Resource(){
		amount=0.0;
	}
	
	public void add(double toAdd) {
		amount=amount+toAdd;
	}
	
	public boolean remove(double toRemove) {
		double newAmount=amount-toRemove;
		if(newAmount<0) {
			return false;
		}else {
			amount=newAmount;
			return true;
		}
	}
	
	public boolean possibleToRemove(double toRemove) {
		return amount-toRemove>=0;
	}
	
	public int getIntegerAmount() {
		if(this.isContinous()) {
			System.out.println("Integer ammount of a non-integer resource requested");
		}
		return (int)(amount);
	}
	
	public double getDoubleAmmount() {
		if(!this.isContinous()) {
			System.out.println("Non-integer ammount of a integer resource requested");
		}
		return amount;
	}
	
	public abstract boolean isContinous();
	
	public abstract String getName();
}
