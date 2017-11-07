package gameGraphics;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class TextureHandler {
	
	public String basepath = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent().toString();
	private String filespath = basepath+File.separator+"files";
	
	
	private HashMap<String, Image> images;
	
	public TextureHandler() {
		
	}
	
	/**
	 * Loads the images specified in the textures file. Should be called before accessing textures.
	 * @throws FileNotFoundException if the textures file is not found.
	 */
	public void load() throws FileNotFoundException{
		
		
		images = new HashMap<String,Image>();
		File textureLocations = new File(filespath+File.separator+"textures.txt");
		Scanner scanner;
		scanner = new Scanner(textureLocations);
		
		while(scanner.hasNextLine()) {
			
			boolean error = false;
			String errorMsg = "No error";
			Scanner scanLine = new Scanner(scanner.nextLine());
			String imgName;
			String imgPath;
			
			A: if(scanLine.hasNext()) {
				imgName = scanLine.next();
				if(scanLine.hasNext()) {
					imgPath = scanLine.next();
					try {
						images.put(imgName, ImageIO.read(new File(imgPath)));
					} catch (IOException e) {
						error = true;
						errorMsg = "Problem with loading the image "+imgName+" at "+imgPath+" Error Message: "+e.getMessage();
						break A;
					}
				} else {
					error = true;
					errorMsg = "One information token missing";
					break A;
				}
			} else {
				error = true;
				errorMsg = "No information found on line";
				break A;
			}
			scanLine.close();
			
			if(error) {
				System.err.println(errorMsg);
			} else {
				System.out.println("File successfully read");
			}
		}
		scanner.close();
	}
	
	public Image getImage(String imgName) {
		return images.get(imgName);
	}
}
