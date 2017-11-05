package gameMechanics;

import java.util.ArrayList;

public class ResourceManager {
	
	private ArrayList<Integer> resourceAmount;
	private GameMechanics gameMec;
	
	ResourceManager(GameMechanics mec){
		gameMec=mec;
		resourceAmount= new ArrayList<Integer>(gameMec.getResourceNameList().size());
		for(int i=0;i<gameMec.getResourceNameList().size();++i){
			resourceAmount.add(i, 0);
		}
	}
	
	//add a given amount of resources to the pool using an ID
	public void addRes(int resID,int amount){
		resourceAmount.set(resID, resourceAmount.get(resID)+amount);
	}
	//add a given amount of resources to the pool using a name
	public void addRes(String resName, int amount){
		addRes(gameMec.getResourceID(resName),amount);
	}
	//attempt to remove a given amount of a resource from the pool using an ID. Returns true if it was successful and false if not (possible remove and only use the version below)
	public boolean remRes(int resID,int amount){
		int cacheNewAmount=resourceAmount.get(resID)-amount;
		if(cacheNewAmount<0){
			return false;
		}else{
			resourceAmount.set(resID,cacheNewAmount);
			return true;
		}
	}
	//attempt to remove a given amount of a resource from the pool using a name. Returns true if it was successful and false if not (possible remove and only use the version below)
	public boolean remRes(String resName,int amount){
		return remRes(gameMec.getResourceID(resName),amount);
	}
	//check whether there are enough resources available to remove a given amount of multiple resources from the pool using IDs
	public boolean possibleToRemResByIDs(ArrayList<Integer> itemIDs,ArrayList<Integer> amounts){
		for(int i=0;i<itemIDs.size();++i){
			if(resourceAmount.get(itemIDs.get(i))<amounts.get(i)){
				return false;
			}
		}
		return true;
	}
	//check whether there are enough resources available to remove a given amount of multiple resources from the pool using names
	public boolean possibleToRemResByNames(ArrayList<String> itemNames,ArrayList<Integer> amounts){
		for(int i=0;i<itemNames.size();++i){
			if(resourceAmount.get(gameMec.getResourceID(itemNames.get(i)))<amounts.get(i)){
				return false;
			}
		}
		return true;
	}
	//attempt to remove a given amount of multiple resources from the pool using IDs. Returns true if it was successful and false if not
	public boolean remResByIDs(ArrayList<Integer> itemIDs,ArrayList<Integer> amounts){
		if(possibleToRemResByIDs(itemIDs, amounts)){
			for(int i=0;i<itemIDs.size();++i){
				resourceAmount.set(itemIDs.get(i), resourceAmount.get(itemIDs.get(i))-amounts.get(i));
			}
			return true;
		}else{
			return false;
		}
	}
	//attempt to remove a given amount of multiple resources from the pool using names. Returns true if it was successful and false if not
	public boolean remResByNames(ArrayList<String> itemNames,ArrayList<Integer> amounts){
		if(possibleToRemResByNames(itemNames, amounts)){
			for(int i=0;i<itemNames.size();++i){
				resourceAmount.set(gameMec.getResourceID(itemNames.get(i)), resourceAmount.get(gameMec.getResourceID(itemNames.get(i)))-amounts.get(i));
			}
			return true;
		}else{
			return false;
		}
	}
}
