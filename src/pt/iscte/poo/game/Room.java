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

	private Manel manel;
	private List<ElementosDeJogo> elementos;
	
	public Room() throws FileNotFoundException {
		elementos = lerFicheiro("rooms/room0.txt");
		for(ElementosDeJogo elemento : elementos) {
			if(elemento.getName().equals("JumpMan")) {
				manel = (Manel) elemento;
				break;
			}
		}
		for(int x = 0; x!= 10; x++) {
			for(int y = 0; y!= 10; y++) {
				// criei floor em todos os quadrados
				//mas quando leio o ficheiro tambem deteto e adiciono floors
				//para resolver : só adicionar floor onde necessário
				ImageGUI.getInstance().addImage(new Floor(x, y));
			}
		}
		ImageGUI.getInstance().addImages(elementos);
	}

	public void moveManel(Direction d) {
		manel.move(d);
	}

	public List<ElementosDeJogo> lerFicheiro(String nomeFicheiro) throws FileNotFoundException {
		List<ElementosDeJogo> elementos = new ArrayList<>();
		Scanner sc = new Scanner(new File(nomeFicheiro));
		sc.nextLine();
		int j = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] tokens = line.split("");
			for (int i = 0; i < tokens.length; i++) {
				elementos.add(criar(tokens[i], i, j));
			}
			j++;
		}
		sc.close();
		return elementos;
	}

	public static ElementosDeJogo criar(String tipo, int x, int y) {
		switch(tipo) {
			case " " : return new Floor(x,y);
			case "W" : return new Wall(x,y);
			case "S" : return new Stairs(x,y);
			case "s" : return new Sword(x,y);
			case "t" : return new Trap(x,y);
			case "H" : return new Manel(x,y);
			case "G" : return new DonkeyKong(x, y);
			default: return new Floor(x,y); //caso encontre uma string desconhecida substitui por floor 
		}
	}


}