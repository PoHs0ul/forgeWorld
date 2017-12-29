package resources;

import gameMechanics.Resource;

public class BasicCoal extends Resource{

	@Override
	public boolean isContinous() {
		return true;
	}

	@Override
	public String getName() {
		return "Coal";
	}

}
