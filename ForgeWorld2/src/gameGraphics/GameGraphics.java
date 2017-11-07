package gameGraphics;

import gameMechanics.GameMechanics;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;


public class GameGraphics {
	GameMechanics mechanics;
	GraphicsDevice GraphDevice;
	boolean fullscreen = false;
	boolean ingame = true;
	Frame mainFrame;
	TextureHandler textureHandler;
	FramePainter fPainter;
	Timer frameTimer;
	
	public GameGraphics(GameMechanics gameMechanics) {
		GraphDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		mainFrame = new Frame("Forge World");
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
		fPainter = new FramePainter(mainFrame.getWidth(), mainFrame.getHeight(), mechanics
				);
		
		mainFrame.addWindowListener(ListenerHandler.getMainFrameWindowListener(this));
		mainFrame.addMouseListener(ListenerHandler.getMainFrameMouseListener(this));
		
		
		
		
		frameTimer = new Timer();
		frameTimer.schedule(
				new TimerTask() {
					
					@Override
					public void run() {
						paint();
					}
					
				}
				, 0, 60);
	}
	
	public void paint() {
		Graphics g = mainFrame.getGraphics();
		
		
		if(ingame)g.drawImage(fPainter.paint(), 0, 0, mainFrame);
	}
	
	public GameMechanics getGameMechanics() {
		return mechanics;
	}
}
