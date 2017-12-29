package resources;

import gameMechanics.Resource;

public class BasicIronOre extends Resource{

	@Override
	public boolean isContinous() {
		return true;
	}

	@Override
	public String getName() {
		return "Iron Ore";
	}

}
