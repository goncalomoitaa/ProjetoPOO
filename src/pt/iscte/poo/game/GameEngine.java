package pt.iscte.poo.game;

import objects.*;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

public class GameEngine implements Observer {
	
	private Room currentRoom;
	private Manel manel = Manel.getUnicoManel(0, 0);
	private LinkedList<RoomFile> roomFiles;
	private int lastTickProcessed = 0;

	public GameEngine() throws FileNotFoundException {
		this.roomFiles = RoomFile.listaSalas();

		RoomFile roomFile = this.roomFiles.get(0);

		currentRoom = Room.fromFile(roomFile.file());

		prepareRoom();
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
			processTick();
			manel.fall(currentRoom);
		}
		processInteractables();
		processEnemies();
		ImageGUI.getInstance().update();
	}

	private void processInteractables() {
		ArrayList<InteractiveElements> interactiveElements = currentRoom.interactiveElementsAt(manel.getPosition());
		for(InteractiveElements e : interactiveElements) {
			e.interact(manel);
			ImageGUI.getInstance().setStatusMessage(e.getInteractionMessage());
		}
		ImageGUI.getInstance().removeImages(currentRoom.usedElements());
	}

	private void processEnemies() {
		manel.fightEnemy(currentRoom.enemiesAt(manel.getPosition()));
		ArrayList<MovingCharacters> enemies = currentRoom.enemiesAt(manel.getPosition());
		if(enemies.size() > 0) ImageGUI.getInstance().setStatusMessage(manel.getHealtStatusMessage());
		ImageGUI.getInstance().removeImages(currentRoom.deadEnemies());
	}

	private void processTick() {
		System.out.println("Tic Tac : " + lastTickProcessed);
		for(MovingCharacters p : currentRoom.getPersonagensMoveis())
			p.update(currentRoom);

		checkForDoors();
		lastTickProcessed++;
	}

	private void checkForDoors() {
		Door doorAtManelPosition = (Door) currentRoom
				.interactiveElementsAt(manel.getPosition())
				.stream().filter((e) -> e instanceof Door)
				.findFirst()
				.orElse(null);

		if (doorAtManelPosition == null) {
			return;
		}

		currentRoom = Room.fromFile(doorAtManelPosition.getDestinationRoom().getDoorDestinationFile());
		prepareRoom();
	}

	private void prepareRoom() {
		ImageGUI.getInstance().clearImages();
		ImageGUI.getInstance().addImages(currentRoom.getBackground());
		ImageGUI.getInstance().addImages(currentRoom.getElementos());
		ImageGUI.getInstance().update();
	}
}
