package gameMechanics;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

//A class which stores all general information about the different resources available in the game
public class ResourceList {
	private ArrayList<Resource> resources;//A list which contains the resources. Their position in the array is the ID.
	private HashMap<String,Integer> resourceIDs;//A hash map which relates item names to their IDs
	private QuantityDimensionsManager dimensionManager;//A reference to the dimension manager which is used
	
	ResourceList(QuantityDimensionsManager dimensionManager){
		this.dimensionManager = dimensionManager;
		
		resources=new ArrayList<Resource>();
		resourceIDs=new HashMap<>();
		try {
			File[] classFilesArray=new File(Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().toURI())+File.separator+"resources").listFiles();
			//File resourceDir=new File(System.getProperty("user.dir")+File.separator+"resources");
			//String[] resourceDirContent=resourceDir.list();
			Class [] classesForConstructor = {QuantityDimensionsManager.class};
			for(int i=0;i<classFilesArray.length;++i){
				System.out.println(Class.forName(classFilesArray[i].getParentFile().getName()+"."+classFilesArray[i].getName().split("\\.")[0]).getConstructor(classesForConstructor));
				Resource newResource=(Resource)Class.forName(classFilesArray[i].getParentFile().getName()+"."+classFilesArray[i].getName().split("\\.")[0]).getConstructor(classesForConstructor).newInstance(dimensionManager);
				resources.add(newResource);
				resourceIDs.put(newResource.getName(), resources.size()-1);
				System.out.println("Added resource "+newResource.getName());
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resources.trimToSize();
	}
	
	ResourceManager createNewResourceManager() {
		ResourceManager newResourceManager=new ResourceManager();
		
		newResourceManager.resources=new ArrayList<Resource>(resources.size());
		for(int i=0;i<resources.size();++i) {
			try {
				Class[] classesForConstructor = {resources.get(i).getClass(), double.class, String.class };
				Object[] objectsForConstructor = {resources.get(i), 0.0, resources.get(i).getDefaultUnit()};
				newResourceManager.resources.add(resources.get(i).getClass().getConstructor(classesForConstructor).newInstance(objectsForConstructor));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		newResourceManager.resources.trimToSize();
		
		newResourceManager.resourceList=this;
		return newResourceManager;
	}
	
	//Get the ID of a resource using its name
	public int getResourceID(String resName){
		return resourceIDs.get(resName);
	}
	//Get the name of a resource using its ID
	public String getResourceName(int resID){
		return getResource(resID).getName();
	}
	
	public Resource getResource(int resID) {
		return resources.get(resID);
	}
	
	public Resource getResource(String resName) {
		return getResource(getResourceID(resName));
	}
	
	//Get a list of all resource names, the position in the list is the id of the resource
	//public ArrayList<String> getResourceNameList(){
	//	return resourceNames;
	//}
}
