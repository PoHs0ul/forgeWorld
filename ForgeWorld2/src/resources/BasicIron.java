package resources;

import gameMechanics.Resource;

public class BasicIron extends Resource{

	@Override
	public boolean isContinous() {
		return true;
	}

	@Override
	public String getName() {
		return "Iron";
	}

}
