package resources;

import gameMechanics.Resource;

public class BasicSteel extends Resource{

	@Override
	public boolean isContinous() {
		return true;
	}

	@Override
	public String getName() {
		return "Steel";
	}

}
