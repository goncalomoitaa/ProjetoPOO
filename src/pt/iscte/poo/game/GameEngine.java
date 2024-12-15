package pt.iscte.poo.game;

import objects.*;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Time;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.awt.event.KeyEvent.VK_W;

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
		ScoreBoard.getScoreBoard().loadScores();
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
			} else {
				manel.takeAction(k, currentRoom);
			}
		}

		processInteractables();
		processEnemies();
		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
			manel.fall(currentRoom);
		}

		if(manel.isDead()) {
			if (manel.hasLivesLeft()) {
				resetGame();
			}
			else {
				gameOver();
			}
		}

		ImageGUI.getInstance().update();
	}

	private void gameOver() {
		ImageGUI.getInstance().showMessage("Fim de jogo", "Nós que aqui estamos, por vós esperamos!");
		ImageGUI.getInstance().dispose();
	}
	private void resetGame() {
		currentRoom = Room.fromFile(new File(roomFiles.get(0).getAbsFilePath()));
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
				Time t = new Time(lastTickProcessed / 2);
				ImageGUI.getInstance().showMessage("Tempo de jogo", t.toString());
				String nickName = askName();
				ScoreBoard.getScoreBoard().addBestTime(nickName, t);
				ScoreBoard.getScoreBoard().saveScore();
				ImageGUI.getInstance().showMessage("SCOREBOARD", ScoreBoard.getScoreBoard().saveAndDisplay());
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

	public String askName() {
		String nome = null;
		while(true) {
			nome = ImageGUI.getInstance().showInputDialog("NOME DE JOGADOR", "INSERE O TEU NOME DE JOGADOR: ");
			if(nome == null) {
				break;
			}
			if(nome.isEmpty() || nome.isBlank()) {
				continue;
			}
			return nome;
		}
		return nome;
	}

	private void prepareRoom() {
		ImageGUI.getInstance().clearImages();
		ImageGUI.getInstance().addImages(currentRoom.getBackground());
		ImageGUI.getInstance().addImages(currentRoom.getElementos());
		ImageGUI.getInstance().update();
	}
}
