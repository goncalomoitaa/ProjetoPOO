package pt.iscte.poo.game;

import objects.*;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class GameEngine implements Observer {
	
	private Room currentRoom;
	private Manel manel = Manel.getUnicoManel(0, 0);
	private List<DonkeyKong> kongs;
	private List<Banana> bananas;
	private LinkedList<RoomFile> roomFiles;
	private int lastTickProcessed = 0;

	public GameEngine() throws FileNotFoundException {
		this.roomFiles = RoomFile.listaSalas();

		RoomFile roomFile = this.roomFiles.get(1);

		currentRoom = Room.aPartirDoFicheiro(roomFile.file());
		ImageGUI.getInstance().addImages(currentRoom.getBackground());
		ImageGUI.getInstance().addImages(currentRoom.getElementos());
		ImageGUI.getInstance().addImage(manel);
		kongs = currentRoom.getkongs();
		for(DonkeyKong kong : kongs) {
			bananas = kong.attackKong();
			ImageGUI.getInstance().addImages(bananas);
		}
		ImageGUI.getInstance().addImages(kongs);
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
		} else {
			manel.fall(currentRoom);
		}

		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
			for(DonkeyKong kong : kongs) {
				kong.move(Direction.random(), currentRoom);
			}
			for(Banana banana : bananas) {
				banana.move(Direction.DOWN);
			}
		}
		ImageGUI.getInstance().update();
	}

	private void processTick() {
		System.out.println("Tic Tac : " + lastTickProcessed);
		lastTickProcessed++;
	}
}
