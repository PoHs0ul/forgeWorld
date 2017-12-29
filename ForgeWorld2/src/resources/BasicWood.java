package resources;

import gameMechanics.Resource;

public class BasicWood extends Resource{

	@Override
	public boolean isContinous() {
		return true;
	}

	@Override
	public String getName() {
		return "Wood";
	}
	
}
