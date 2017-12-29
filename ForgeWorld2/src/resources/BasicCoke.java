package resources;

import gameMechanics.Resource;

public class BasicCoke extends Resource{

	@Override
	public boolean isContinous() {
		return true;
	}

	@Override
	public String getName() {
		return "Coke";
	}

}
