package gameGraphics;

import gameMechanics.GameMechanics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

public class GameGraphics {
	GameMechanics mechanics;
	GraphicsDevice GraphDevice;
	boolean fullscreen = false;
	JFrame mainFrame;
	TextureHandler textureHandler;
	
	public GameGraphics(GameMechanics gameMechanics) {
		GraphDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		mainFrame = new JFrame("Forge World");
		mechanics = gameMechanics;
		
		try {
			initialize(); //maybe later, this will be called by a different method from gameMechanics
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readData() { //optional, for maybe later use of "saveable" graphic options that are loaded at the start
		//TODO: read saved graphic options
	}
	
	public void initialize() throws FileNotFoundException {
		readData();
		if(fullscreen) {
			GraphDevice.setFullScreenWindow(mainFrame);
		} else {
			mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		}
		mainFrame.setBackground(Color.BLACK);
		mainFrame.setVisible(true);
		textureHandler = new TextureHandler();
		textureHandler.load(); //maybe later, this will be called by a different method from gameMechanics
	}
	
	public void paint() { //test-stuff, not final
		Graphics g = mainFrame.getGraphics();
		g.drawImage(textureHandler.getImage("ground0"), 0, 0, mainFrame);
	}
}
