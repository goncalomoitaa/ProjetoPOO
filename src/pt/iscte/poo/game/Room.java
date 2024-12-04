package pt.iscte.poo.game;

import objects.*;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.tools.Logger;

import static pt.iscte.poo.tools.Logger.MessageType.*;

public class Room {

	private List<ElementosDeJogo> elementos;
	private Logger logger = Logger.getLogger();

	private String nome;
	private final ArrayList<Background> backgroundTiles;

	public Room(List<ElementosDeJogo> elementos, String nome) throws FileNotFoundException {
		logger.log("Criando Room " + nome, INFO);
		this.nome = nome;
		this.elementos = elementos;
		logger.log("Room criada: " + nome, INFO);
		this.backgroundTiles = new ArrayList<>();

		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++)
				backgroundTiles.add(new Background(i, j));
	}

	public List<Background> getBackground() {
		return backgroundTiles;
	}

	public void atualiza() {
		ImageGUI.getInstance().addImages(elementos);
	}

	public List<ElementosDeJogo> objectsAt(Point2D p) {
		List<ElementosDeJogo> lista = new ArrayList<>();
		for (ElementosDeJogo elemento : elementos) {
			if (elemento.getPosition().equals(p) && !(elemento instanceof Manel)) {
				lista.add(elemento);
			}
		}

		return lista;
	}

	public boolean solidPosition(Point2D pos) {
		return objectsAt(pos).stream().anyMatch((ElementosDeJogo o) -> o.isSolid() );
	}

	public boolean climbablePosition(Point2D pos) {
		return objectsAt(pos).stream().anyMatch((ElementosDeJogo e) -> e.isClimbable());
	}

	public List<ElementosDeJogo> getElementos() {
		return elementos;
	}

	public static Room aPartirDoFicheiro(File ficheiro) {
		try {
			return new Room(lerFicheiro(ficheiro), ficheiro.getName());
		} catch(FileNotFoundException e) {
			Logger.getLogger().log(e.getMessage(), ERROR);
		}

		return null;
	}

	public static List<ElementosDeJogo> lerFicheiro(File ficheiro) {
		try {
			if (ficheiro.isDirectory()) return null;

			List<ElementosDeJogo> elementos = new ArrayList<>();
			Scanner sc = new Scanner(ficheiro);
			sc.nextLine(); // TODO: substituir pela extração das infos da room (nome e proxima room);

			int j = 0;
			while (sc.hasNextLine()) {
				char[] tokens = sc.nextLine().toCharArray();
				for (int i = 0; i < tokens.length; i++) {
					ElementosDeJogo e = criarElementoDeJogo(tokens[i], i, j);
					if(e != null) elementos.add(e);
				}
				j++;
			}
			sc.close();
			return elementos;
		} catch(FileNotFoundException e) {
			Logger.getLogger().log(e.getMessage(), ERROR);
		}

		return new ArrayList<>();
	}

	private static ElementosDeJogo criarElementoDeJogo(char tipo, int x, int y) {
		try {
			return switch (tipo) {
				case 'W' -> new Wall(x, y);
				case 'S' -> new Stairs(x, y);
				case 's' -> new Sword(x, y);
				case 't' -> new Trap(x, y);
				case 'H' -> Manel.getUnicoManel(x, y);
				case 'G' -> new DonkeyKong(x, y);
				case '0' -> new Door(x, y);
				case 'm' -> new Meat(x, y);
				case 'P' -> new Princess(x, y);
				default -> null;
			};
		} catch(IllegalArgumentException e) {
			Logger.getLogger().log(e.getMessage(), Logger.MessageType.ERROR);
		}
		return null;
	}

	public String getNome() {
		return this.nome;
	}

	public void removeElementoInterativo(ElementosDeJogo e) {
		if(e.getMensagemDeInteracao() != null) ImageGUI.getInstance().setStatusMessage(e.getMensagemDeInteracao());
		if(e instanceof ElementosAbsorviveis) {
			ImageGUI.getInstance().removeImage(e);
			elementos.remove(e);
		}
	}

	public List<DonkeyKong> getKongs() {
		ArrayList<DonkeyKong> kongs = new ArrayList<>();
		for(ElementosDeJogo e : elementos)
			if(e instanceof DonkeyKong) kongs.add((DonkeyKong) e);

		return kongs;
	}

	public PersonagensMoveis enemyAt(Point2D pos) {
		for(ElementosDeJogo e : objectsAt(pos))
			if(e instanceof PersonagensMoveis) return (PersonagensMoveis) e;

		return null;
	}

	public List<ElementosDeJogo> deadEnemies() {
		List<ElementosDeJogo> dead = new ArrayList<ElementosDeJogo>();
		for(ElementosDeJogo elem : elementos)
			if(elem instanceof PersonagensMoveis) {
				PersonagensMoveis e = (PersonagensMoveis) elem;
				if(e.isDead()) dead.add(e);
			}

		return dead;
	}
}
