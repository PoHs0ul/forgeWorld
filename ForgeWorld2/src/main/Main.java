package main;

import gameMechanics.GameMechanics;

public class Main {

	//lol
	
	public static void main(String[] args) {
		GameMechanics gameMec = new gameMechanics.GameMechanics();
		new gameGraphics.GameGraphics(gameMec);
	}

}
