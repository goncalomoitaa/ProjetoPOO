package pt.iscte.poo.game;

import objects.*;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class GameEngine implements Observer {
	
	private Room currentRoom;
	private Manel manel = Manel.getUnicoManel(0, 0);
	private LinkedList<RoomFile> roomFiles;
	private int lastTickProcessed = 0;

	public GameEngine() throws FileNotFoundException {
		this.roomFiles = RoomFile.listaSalas();

		RoomFile roomFile = this.roomFiles.get(0);

		currentRoom = Room.aPartirDoFicheiro(roomFile.file());

		ImageGUI.getInstance().addImages(currentRoom.getBackground());
		ImageGUI.getInstance().addImages(currentRoom.getElementos());
		ImageGUI.getInstance().addImage(manel);
		ImageGUI.getInstance().update();
	}

	@Override
	public void update(Observed source) {
		if (ImageGUI.getInstance().wasKeyPressed()) {
			int k = ImageGUI.getInstance().keyPressed();
			System.out.println("Keypressed " + k);
			if (Direction.isDirection(k)) {
				System.out.println("Direction! ");
				manel.move(Direction.directionFor(k), currentRoom);
			}
		}
		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			manel.fightEnemy(currentRoom.enemyAt(manel.getPosition()));
			ImageGUI.getInstance().removeImages(currentRoom.deadEnemies());
			processTick();
		}
		ImageGUI.getInstance().update();
	}

	private void processTick() {
		System.out.println("Tic Tac : " + lastTickProcessed);
		for(MovingCharacters p : currentRoom.getPersonagensMoveis())
			p.update(currentRoom);

		lastTickProcessed++;
	}
}
