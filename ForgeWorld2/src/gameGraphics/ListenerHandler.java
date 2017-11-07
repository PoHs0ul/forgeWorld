package gameGraphics;

//import java.awt.event.MouseAdapter; //same as below
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
//import java.awt.event.WindowAdapter; //maybe change to adapter later if some Events are not needed
import java.awt.event.WindowListener;

public class ListenerHandler {
	
	public static WindowListener getMainFrameWindowListener(GameGraphics gGraph) {
		return new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				//TODO: display "do you want to quit?" -> send signal to GameMechanics
				System.exit(0);
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				System.out.println("minimized");
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				System.out.println("unminimized");
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public static MouseListener getMainFrameMouseListener(final GameGraphics gGraph) {
		return new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Send signal to GameMechanics
				gGraph.getGameMechanics();
				System.out.println("click");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Send signal to GameMechanics
				gGraph.getGameMechanics();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Send signal to GameMechanics
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Send signal to GameMechanics
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Send signal to GameMechanics
				
			}
			
		};
	}
	
	public static KeyListener getMainFrameKeyListener() {
		return new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
}
