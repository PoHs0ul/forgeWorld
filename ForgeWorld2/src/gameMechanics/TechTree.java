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

public class TechTree {
	HashMap<String,TechTreeItem> techItems;
	
	TechTree(){
		try {
			File techTreeFile=new File(Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent()+File.separator+"files"+File.separator+"TechTreeData");
			BufferedReader buffer=new BufferedReader(new FileReader(techTreeFile));
			for(String s=buffer.readLine();s!=null;s=buffer.readLine()){
				if(!(s.isEmpty()||s.startsWith("//"))){
					if(s.contains(";")&&s.contains("{")&&s.indexOf(";")<s.indexOf("{")){
						String newItemName=s.substring(s.indexOf(";"),s.indexOf("{"));
						ArrayList<TechTreeItem> newItemRequirements=new ArrayList<TechTreeItem>();
						if(!s.contains("}")){
							s=buffer.readLine();
							while(!s.contains("}")){
								if(s.contains(";")){
									TechTreeItem item=techItems.get(s.substring(s.indexOf(";")));
									if(item==null){
										System.out.println("No tech item '"+s.substring(s.indexOf(";"))+"' which is required for '"+newItemName+"' found. Either it is not specified or introduced afterwards. Ignoring it.");
									}else{
										newItemRequirements.add(item);
									}
								}else{
									System.out.println("Impossible to interpret '"+s+"' in file '"+techTreeFile+"'. Ignoring it.");
								}
								s=buffer.readLine();
							}
						}
						techItems.put(newItemName,new TechTreeItem(newItemName, newItemRequirements));
					}else{
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
	}
}
