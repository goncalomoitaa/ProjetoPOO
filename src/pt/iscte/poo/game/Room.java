package pt.iscte.poo.game;

import objects.*;
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
			for(int y = 0; y!= 10; y++) {
				ImageGUI.getInstance().addImage(new Floor(x, y));
				//ImageGUI.getInstance().addImages(lerFicheiro());
			}
		}
	}

	public void moveManel(Direction d) {
		manel.move(d);
	}

	public List<ElementosDeJogo> lerFicheiro(String file) {
		List<ElementosDeJogo> elementos = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(file));
			sc.nextLine();
			int j = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] tokens = line.split("");
				for (int i = 0; i < tokens.length; i++) {
					elementos.add(criar(tokens[i], i, j));
					j++;
				}
			}
			sc.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("ficheiro nao encontrado");
		}
		return elementos;
	}


	public static ElementosDeJogo criar(String tipo, int x, int y) {
		switch(tipo) {
			case "W" : return new Wall(x,y);
			case "S" : return new Strairs(x,y);
			case "s" : return new Sword(x,y);
			case "t" : return new Trap(x,y);
			default: throw new IllegalArgumentException();
		}
	}


}