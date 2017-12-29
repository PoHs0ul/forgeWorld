package resources;

import gameMechanics.Resource;

public class BasicCoalOre extends Resource{

	@Override
	public boolean isContinous() {
		return true;
	}

	@Override
	public String getName() {
		return "Coal Ore";
	}

}
