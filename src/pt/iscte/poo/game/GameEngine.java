package pt.iscte.poo.game;

import objects.*;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.tools.Logger;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Time;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameEngine implements Observer {
	
	private Room currentRoom;
	private Manel manel = Manel.getSingleManel(0, 0);
	private LinkedList<RoomFile> roomFiles;
	private int lastTickProcessed = 0;

	public GameEngine() {
		this.roomFiles = RoomFile.listaSalas();

		RoomFile roomFile = this.roomFiles.get(0);

		loadRoomFile(roomFile);

		prepareRoom();
		ImageGUI.getInstance().addImage(manel);
		ImageGUI.getInstance().update();
	}

	private void loadRoomFile(RoomFile roomFile) {
		try {
			currentRoom = Room.fromFile(roomFile.file());
		} catch(FileNotFoundException e) {
			String errMsg = "We could not find or read file " + roomFile.getAbsFilePath() + "\n";
			errMsg = errMsg.concat("Please, provide the path for a new file we can use to replace it:");

			String newFileName = ImageGUI.getInstance().showInputDialog("Uh-oh!", errMsg);
			loadRoomFile(new RoomFile(newFileName));
		} catch(IllegalArgumentException e) {
			String errMsg = "The input file is invalid for some reason. Please check the logs and try to fix it.";

			Logger.getLogger().log(errMsg, Logger.MessageType.ERROR);
			ImageGUI.getInstance().dispose();
		}
	}

	@Override
	public void update(Observed source) {
		if (ImageGUI.getInstance().wasKeyPressed()) {
			int k = ImageGUI.getInstance().keyPressed();
			System.out.println("Keypressed " + k);
			if (Direction.isDirection(k)) {
				System.out.println("Direction! ");
				manel.move(Direction.directionFor(k), currentRoom);
			} else {
				manel.takeAction(k, currentRoom);
			}
		}

		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
			manel.fall(currentRoom);
		}

		if(manel.isDead()) {
			if (manel.hasLivesLeft())
				resetGame();
			else
				gameOver();
		}

		ImageGUI.getInstance().update();
	}

	private void gameOver() {
		ImageGUI.getInstance().showMessage("Fim de jogo", "Nós que aqui estamos, por vós esperamos!");
		ImageGUI.getInstance().dispose();
	}
	private void resetGame() {
		loadRoomFile(roomFiles.get(0));
		prepareRoom();
		manel.respawn();

		ImageGUI.getInstance().showMessage("Morreste?", "Toma mais cuidado, óh, Manel!");
		ImageGUI.getInstance().setStatusMessage(manel.getHealtStatusMessage());
	}

	private void processInteractables() {
		ArrayList<InteractiveElements> interactiveElements = currentRoom.interactiveElementsAt(manel.getPosition());
		for(InteractiveElements e : interactiveElements) {
			if(e instanceof Princess e1 && e.getPosition().equals(manel.getPosition()) && !e1.getWasRescued()) {
				e1.setWasRescued(true);
				ImageGUI.getInstance().showMessage("WIN", "GG");
				ImageGUI.getInstance().showMessage("Tempo de jogo", new Time(lastTickProcessed / 2).toString());
				ImageGUI.getInstance().dispose();
			}
			e.interact(manel, currentRoom);
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
		elapsedTime();
		processInteractables();
		processEnemies();
		processTimedWeapons();
		lastTickProcessed++;
	}

	private void processTimedWeapons() {
		for(TimedWeapon tw : currentRoom.activeTimedWeapons())
			if(tw.hasTimeLeft())
				tw.tickDown();
			else
				tw.engage(currentRoom);
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

		loadRoomFile(doorAtManelPosition.getDestinationRoom().getDoorDestinationFile());
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
