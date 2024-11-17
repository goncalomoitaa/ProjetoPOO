package pt.iscte.poo.game;

import pt.iscte.poo.gui.ImageGUI;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		ImageGUI gui = ImageGUI.getInstance();
		GameEngine engine = new GameEngine();
		gui.setStatusMessage("Good luck!");
		gui.registerObserver(engine);
		gui.go();
	}
	
}
