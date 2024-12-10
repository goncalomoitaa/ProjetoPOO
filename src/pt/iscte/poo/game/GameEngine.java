package pt.iscte.poo.game;

import objects.*;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameEngine implements Observer {
	
	private Room currentRoom;
	private Manel manel = Manel.getSingleManel(0, 0);
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
		elapsedTime();
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
		currentRoom.removeAbsorbableElements(currentRoom.usedElements());
		ImageGUI.getInstance().removeImages(currentRoom.usedElements());
	}

	private void processEnemies() {
		ArrayList<MovingCharacters> enemies = currentRoom.enemiesAt(manel.getPosition());
		manel.fightEnemy(enemies);
		if(!enemies.isEmpty()) ImageGUI.getInstance().setStatusMessage(manel.getHealtStatusMessage());
		ImageGUI.getInstance().removeImages(currentRoom.deadEnemies());
		for(GameElements e : currentRoom.deadEnemies()) {
				currentRoom.removeElement(e);
		}
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

	public void elapsedTime() {
		for(GameElements e : currentRoom.getElementos()) {
			if(e instanceof PerishableElements e1) {
				e1.setTime(ImageGUI.getInstance().getTicks());
			}
		}
	}

	private void prepareRoom() {
		ImageGUI.getInstance().clearImages();
		ImageGUI.getInstance().addImages(currentRoom.getBackground());
		ImageGUI.getInstance().addImages(currentRoom.getElementos());
		ImageGUI.getInstance().update();
	}
}
