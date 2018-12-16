package gameMechanics;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GameMechanics {
	private static final int millisPerProductionTick = 100;
	
	private Map map;//An object which contains data about all the possible positions on the map
	private Timer productionTickTimer;//A timer which continuously executes stuff for the production
	private ArrayList<Building> buildingTypeList;//A list which contains an instance of each possible building Type
	private TechTree techTree;//A class which generates and stores the tech tree
	private ResourceList resourceList;//Stores general information about the resources available in the game
	
	public GameMechanics() {
		resourceList=new ResourceList();
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
	
	//Getter methods
	public Map getMap() {
		return map;
	}
	public TechTree getTechTree() {
		return techTree;
	}
	public ResourceList getResourceList() {
		return resourceList;
	}
}
