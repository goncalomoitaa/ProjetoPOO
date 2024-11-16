package pt.iscte.poo.game;

import objects.Manel;
import objects.Wall;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room {
	
	private Point2D heroStartingPosition = new Point2D(1, 1);
	private Manel manel;
	
	public Room() {
		manel = new Manel(heroStartingPosition); //poem o manel no inicio
		ImageGUI.getInstance().addImage(manel);
		for(int x = 0; x!= 10; x++) {
			ImageGUI.getInstance().addImage(new Wall(x, 5));
		}
	}

	public void moveManel(Direction d) {
		manel.move(d);
	}

//	public List<String> readFiles(File file) throws FileNotFoundException {
//		List<String> lines = new ArrayList<String>();
//		Scanner sc = new Scanner(new FileReader(file));
//		while(sc.hasNextLine()) {
//			lines.add(sc.nextLine());
//		}
//		return lines;
//	}
//
//	public List<ImageTile> criarMundo(File file) throws FileNotFoundException {
//		List<String> lines = readFiles(file);
//
//	}
//	public static String criar(String tipo, int x, int y) {
//		switch(tipo) {
//			case "W": return new Wall(x,y).toString();
//		}
//	}


}