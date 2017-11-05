package main;

import gameMechanics.GameMechanics;

public class Main {

	
	public static void main(String[] args) {
		//start of program
		GameMechanics gameMec = new gameMechanics.GameMechanics();
		new gameGraphics.GameGraphics(gameMec);
	}

}
