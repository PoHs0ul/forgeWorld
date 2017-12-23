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
import java.util.Timer;
import java.util.TimerTask;


public class GameMechanics {
	private static final int millisPerProductionTick = 100;
	
	private Map map;//An object which contains data about all the possible positions on the map
	private Timer productionTickTimer;//A timer which continuously executes stuff for the production
	private ArrayList<Building> buildingTypeList;//A list which contains an instance of each possible building Type
	private ArrayList<String> resourceNames;//A list which contains the names of all resources. Their position in the array is the ID.
	private HashMap<String,Integer> resourceIDs;//A hash map which relates item names to their IDs
	private TechTree techTree;//A class which generates and stores the tech tree
	
	public GameMechanics() {
		
		initializeItems();
		initializeBuildingTypeList();
		techTree=new TechTree();
		
		map=new Map(this, 100,100);//create a map with the given number of chunks (length and width)
		
		initialiseProductionTickTimer();//start the timer for the production ticks
		
		//System.out.println(buildingTypeList.get(0).build(map, 1, 1, 0, map.buildingList));//Test: place a coal mine
	}
	
	//define the production ticks
	private void initialiseProductionTickTimer(){
		productionTickTimer = new Timer();
		productionTickTimer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run(){
				//here goes stuff which should be executed each production tick
				
				//loop is executed for each placed building
				for(utilities.DoubleLinkedLockedListNode<Building> iter=map.buildingList.getHead(); iter!=null; iter=iter.getNextNode()){
					synchronized(iter){
						iter.getContent().onProductionTick();
					}
				}
			}
		}, 0, millisPerProductionTick);
	}
	
	//initialize the list of all building types
	private void initializeBuildingTypeList(){
		try {
			buildingTypeList=new ArrayList<Building>();
			File[] classFilesArray=new File(Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().toURI())+File.separator+"buildings").listFiles();
			for(int i=0;i<classFilesArray.length;++i){
				buildingTypeList.add((Building)Class.forName(classFilesArray[i].getParentFile().getName()+"."+classFilesArray[i].getName().split("\\.")[0]).newInstance());
			}
			buildingTypeList.trimToSize();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	//initialize the resources
	private void initializeItems(){
		resourceNames=new ArrayList<String>();
		resourceIDs=new HashMap<>();
		try {
			File resourceDir=new File(System.getProperty("user.dir")+File.separator+"resources");
			String[] resourceDirContent=resourceDir.list();
			for(int i=0;i<resourceDirContent.length;++i){
				File folder=new File(resourceDir+File.separator+resourceDirContent[i]);
				System.out.println("loading items in "+folder);
				if(folder.isDirectory()){
					BufferedReader buffer=new BufferedReader(new FileReader(folder.toString()+File.separator+"resource list.txt"));
					for(String s=buffer.readLine();s!=null;s=buffer.readLine()){
						if(!(s.startsWith("//")||s.isEmpty())){
							String s2=folder.getName()+"."+s;
							resourceNames.add(s2);
							resourceIDs.put(s2, resourceNames.size()-1);
							System.out.println("Added resource "+s2);
						}
					}
					buffer.close();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		resourceNames.trimToSize();
	}
	
	//Get the ID of a resource using its name
	public int getResourceID(String resName){
		return resourceIDs.get(resName);
	}
	//Get the name of a resource using its ID
	public String getResourceName(int resID){
		return resourceNames.get(resID);
	}
	public ArrayList<String> getResourceNameList(){
		return resourceNames;
	}
	public Map getMap() {
		return map;
	}
	public TechTree getTechTree() {
		return techTree;
	}
}
