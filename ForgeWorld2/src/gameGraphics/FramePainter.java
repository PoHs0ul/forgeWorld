package gameGraphics;

import gameMechanics.GameMechanics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class FramePainter {
	
	BufferedImage page; //page to paint the whole frame on before painting it on the screen
	int width, height; //width and height of the game window
	PainterGUI pGUI; //paints the menu, minimap etc.
	PainterMap pMap; //paints the ground, buildings etc.
	
	public FramePainter(int width, int height, GameMechanics mechanics) {
		this.width = width;
		this.height = height;
		page = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		pMap = new PainterMap();
		pGUI = new PainterGUI();
	}
	
	public Image paint() {
		Image img = page;
		Graphics g = img.getGraphics();
		Image imh = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics h = imh.getGraphics();
		pMap.paint(h,1,0,0/*,GameMechanics.getMap()*/);
		pGUI.paint(h);
		g.drawImage(imh, 0, 0, null);
		return img;
	}
}
