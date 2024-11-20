package pt.iscte.poo.game;

import objects.*;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.tools.Logger;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room {

	private Manel manel;
	private List<ElementosDeJogo> elementos;
	private Logger logger;
	
	public Room() throws FileNotFoundException {
		logger = Logger.getLogger();
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

	public ElementosDeJogo objetoNaPosicao(Point2D p) {
		for (ElementosDeJogo elemento : elementos) {
			if (elemento.getPosition().equals(p)) {
				return elemento;
			}
		}
		return null;
	}

	public void moveManel(Direction d) {
		Point2D nextPos = manel.getPosition().plus(d.asVector());
		if(objetoNaPosicao(nextPos) != null && objetoNaPosicao(nextPos).isSolid()) {
			return;
		}

		manel.move(d);
	}

	public List<ElementosDeJogo> lerFicheiro(String nomeFicheiro) throws FileNotFoundException {
		List<ElementosDeJogo> elementos = new ArrayList<>();
		Scanner sc = new Scanner(new File(nomeFicheiro));
		sc.nextLine();
		int j = 0;
		while (sc.hasNextLine()) {
			char[] tokens = sc.nextLine().toCharArray();
			for (int i = 0; i < tokens.length; i++) {
				elementos.add(criar(tokens[i], i, j));
			}
			j++;
		}
		sc.close();
		return elementos;
	}

	public static ElementosDeJogo criar(char tipo, int x, int y) {
		try {
			return switch (tipo) {
				case ' ' -> new Floor(x, y);
				case 'W' -> new Wall(x, y);
				case 'S' -> new Stairs(x, y);
				case 's' -> new Sword(x, y);
				case 't' -> new Trap(x, y);
				case 'H' -> new Manel(x, y);
				case 'G' -> new DonkeyKong(x, y);
				case '0' -> new Door(x, y);
				default -> throw new IllegalArgumentException(
						"O caractere lido não corresponde a um elemento de jogo conhecido: '" + tipo + "'"
				);
			};
		} catch(IllegalArgumentException e) {
			Logger.getLogger().log(e.getMessage(), Logger.MessageType.ERROR);
		}
		return null;
	}
}