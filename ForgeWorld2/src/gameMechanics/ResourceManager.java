package gameMechanics;

import java.util.ArrayList;

import dimensionSystem.DimensionalQuantity;

//A class which manages the resource amounts
public class ResourceManager {
	
	ArrayList<Resource> resources;
	ResourceList resourceList;
	
	ResourceManager(){
		
	}
	
	//add a given amount of resources to the pool using an ID
	private void addRes(int resID, DimensionalQuantity amount){
		resources.get(resID).add(amount);
	}
	//add a given amount of resources to the pool using a name
	public void addRes(String resName, DimensionalQuantity amount){
		addRes(resourceList.getResourceID(resName),amount);
	}
	//attempt to remove a given amount of a resource from the pool using an ID. Returns true if it was successful and false if not (possible remove and only use the version below)
	private boolean remRes(int resID, DimensionalQuantity amount){
		return resources.get(resID).remove(amount);
	}
	//attempt to remove a given amount of a resource from the pool using a name. Returns true if it was successful and false if not (possible remove and only use the version below)
	public boolean remRes(String resName, DimensionalQuantity amount){
		return remRes(resourceList.getResourceID(resName),amount);
	}
	//check whether there are enough resources available to remove a given amount of multiple resources from the pool using IDs
	boolean possibleToRemResByIDs(ArrayList<Integer> itemIDs, ArrayList<DimensionalQuantity> amounts){
		for(int i=0;i<itemIDs.size();++i){
			if(!resources.get(i).possibleToRemove(amounts.get(i))){
				return false;
			}
		}
		return true;
	}
	//check whether there are enough resources available to remove a given amount of multiple resources from the pool using names
	public boolean possibleToRemResByNames(ArrayList<String> itemNames, ArrayList<DimensionalQuantity> amounts){
		for(int i=0;i<itemNames.size();++i){
			if(!resources.get(resourceList.getResourceID(itemNames.get(i))).possibleToRemove(amounts.get(i))){
				return false;
			}
		}
		return true;
	}
	//attempt to remove a given amount of multiple resources from the pool using IDs. Returns true if it was successful and false if not
	boolean remResByIDs(ArrayList<Integer> itemIDs, ArrayList<DimensionalQuantity> amounts){
		if(possibleToRemResByIDs(itemIDs, amounts)){
			for(int i=0;i<itemIDs.size();++i){
				resources.get(itemIDs.get(i)).remove(amounts.get(i));
			}
			return true;
		}else{
			return false;
		}
	}
	//attempt to remove a given amount of multiple resources from the pool using names. Returns true if it was successful and false if not
	public boolean remResByNames(ArrayList<String> itemNames,ArrayList<DimensionalQuantity> amounts){
		if(possibleToRemResByNames(itemNames, amounts)){
			for(int i=0;i<itemNames.size();++i){
				resources.get(resourceList.getResourceID(itemNames.get(i))).remove(amounts.get(i));
			}
			return true;
		}else{
			return false;
		}
	}
}
