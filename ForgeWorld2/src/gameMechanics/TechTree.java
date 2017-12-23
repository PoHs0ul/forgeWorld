package gameMechanics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TechTree {
	HashMap<String,Integer> techItemIDs;
	ArrayList<TechTreeItem> techItems;
	ArrayList<Boolean> playersResearch;
	
	TechTree(){
		techItemIDs=new HashMap<String,Integer>();
		techItems=new ArrayList<TechTreeItem>();
		
		try {
			//Get the file listing all tech items
			File techTreeFile=new File(Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent()+File.separator+"files"+File.separator+"TechTreeData");
			BufferedReader buffer=new BufferedReader(new FileReader(techTreeFile));
			//Go through all lines in the file
			for(String s=buffer.readLine();s!=null;s=buffer.readLine()){
				//Only proceed with the non-empty lines
				if(!(s.isEmpty()||s.startsWith("//"))){
					//Check that the line has the correct format for a new tech item
					if(s.contains(";")&&s.contains("{")&&s.indexOf(";")<s.indexOf("{")){
						//Save the name of the new tech item to add 
						String newItemName=s.substring(s.indexOf(";")+1,s.indexOf("{"));
						//Prepare an array to save all the tech items required for the new one
						ArrayList<TechTreeItem> newItemRequirements=new ArrayList<TechTreeItem>();
						//Check if not the first line is already the end line
						if(!s.contains("}")){
							//Read the first line of the requirement list 
							s=buffer.readLine();
							//Go on until the end line is reached
							while(!s.contains("}")){
								//Check if the line contains a new requirement entry
								if(s.contains(";")){
									TechTreeItem item=getTechItem(s.substring(s.indexOf(";")+1));
									//Check if the required tech item is a tech item which is already loaded
									if(item==null){
										System.out.println("No tech item '"+s.substring(s.indexOf(";"))+"' which is required for '"+newItemName+"' found. Either it is not specified or introduced afterwards. Ignoring it.");
									}else{
										newItemRequirements.add(item);
									}
								}else{
									//Ignore lines which are not marked to specify a requirement
									System.out.println("Impossible to interpret '"+s+"' in file '"+techTreeFile+"'. Ignoring it.");
								}
								//red the next line of the requirements list
								s=buffer.readLine();
							}
						}
						//Add add new tech item with the gathered information
						techItemIDs.put(newItemName, techItems.size());
						techItems.add(techItems.size(), new TechTreeItem(newItemName, newItemRequirements));
						System.out.println("Added tech item '"+newItemName+"' with ID "+(techItems.size()-1));
					}else{
						//Line has not the correct format
						System.out.println("Impossible to interpret '"+s+"' in file '"+techTreeFile+"'. Ignoring it.");
					}
				}
			}
			buffer.close();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		techItems.trimToSize();
		
		playersResearch=new ArrayList<Boolean>(techItems.size());
		for(int i=0;i<playersResearch.size();++i) {
			playersResearch.set(i, false);
		}
	}
	
	TechTreeItem getTechItem(String name) {
		return techItems.get(techItemIDs.get(name));
	}
	
	public boolean isResearched(String name) {
		return playersResearch.get(techItemIDs.get(name));
	}
	
	public boolean requirementsFulfilled(String name) {
		for(Iterator<TechTreeItem> i=techItems.get(techItemIDs.get(name)).getDependencies().iterator();i.hasNext();) {
			if(!isResearched(i.next().getName())) {
				return false;
			}
		}
		return true;
	}
	
	//Research a tech item (if the requirements are fulfilled)
	public boolean research(String name) {
		if(requirementsFulfilled(name)) {
			forceResearch(name);
			System.out.println("Successfully researched '"+name+"'");
			return true;
		}else {
			System.out.println("Requirements for researching '"+name+"' are not fulfilled");
			return false;
		}
	}
	
	//Mark a tech item as researched regardless what the requirements are
	public void forceResearch(String name) {
		playersResearch.set(techItemIDs.get(name), true);
		System.out.println("Successfully researched '"+name+"'");
	}
}
