package gameMechanics;

import java.util.ArrayList;
import java.util.Iterator;

public class TechTreeItem {
	private ArrayList<TechTreeItem> requirements;
	private ArrayList<TechTreeItem> dependencies;
	private String name;
	
	TechTreeItem(String newItemName, ArrayList<TechTreeItem> newItemRequirements){
		name=newItemName;
		
		requirements=newItemRequirements;
		requirements.trimToSize();
		
		for(Iterator<TechTreeItem> i=requirements.iterator();i.hasNext();){
			i.next().addLink(this);
		}
		
		dependencies=new ArrayList<TechTreeItem>(0);
	}
	
	void addLink(TechTreeItem dependentItem){
		dependencies.add(dependentItem);
		dependencies.trimToSize();
	}
	
	String getName(){
		return name;
	}
}
