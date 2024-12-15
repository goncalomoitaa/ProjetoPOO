package pt.iscte.poo.game;

import objects.*;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import pt.iscte.poo.tools.Logger;

import static pt.iscte.poo.tools.Logger.MessageType.*;

public class Room {

	private List<GameElements> elementos;
	private Logger logger = Logger.getLogger();

	private String nome;
	private final ArrayList<Background> backgroundTiles;

	public Room(List<GameElements> elementos, String nome) throws FileNotFoundException {
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

	public List<GameElements> objectsAt(Point2D p) {
		List<GameElements> lista = new ArrayList<>();
		for (GameElements elemento : elementos) {
			if (elemento.getPosition().equals(p) && !(elemento instanceof Manel)) {
				lista.add(elemento);
			}
		}

		return lista;
	}

	public ArrayList<InteractiveElements> interactiveElementsAt(Point2D p) {
		ArrayList<InteractiveElements> lista = new ArrayList<>();
		for (GameElements elemento : elementos) {
			if (elemento instanceof InteractiveElements && elemento.getPosition().equals(p)) {
				lista.add((InteractiveElements) elemento);
			}
		}
		return lista;
	}

	public boolean solidPosition(Point2D pos) {
		return objectsAt(pos).stream().anyMatch((GameElements o) -> o.isSolid() );
	}

	public boolean climbablePosition(Point2D pos) {
		return objectsAt(pos).stream().anyMatch((GameElements e) -> e.isClimbable());
	}

	public List<GameElements> getElementos() {
		return elementos;
	}

	public static Room fromFile(File file) throws FileNotFoundException {
		return new Room(readElementsFrom(file), file.getName());
	}

	private static void readDoorsDestinations(File ficheiro, List<DoorDestination> destinations) throws FileNotFoundException {
		String line = null;
		Scanner sc = new Scanner(ficheiro);

		while(sc.hasNextLine()) {
			line = sc.nextLine();
			if(!line.contains(";"))
				break;

			destinations.add(new DoorDestination("rooms/" + line.split(";")[1]));
		}

		sc.close();
	}

	private static void scanLinesForElements(List<GameElements> elementos, List<DoorDestination> doorDestinations, File ficheiro) throws FileNotFoundException {
		String line = null;

		Scanner sc = new Scanner(ficheiro);
		int j = 0;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			if(line.contains(";")) continue;

			char[] tokens = line.toCharArray();
			if(tokens.length < 10) {
				String msg = "Linha de configuração do mapa curto demais.\n";
				msg = msg.concat( "No. de elementos: " + tokens.length + "\n");
				msg = msg.concat("Ficheiro: " + ficheiro.getAbsolutePath() + "\n");
				msg = msg.concat("O restante da linha será preenchido apenas com pano de fundo");
				Logger.getLogger().log(msg, ALERT);
			}

			for (int i = 0; i < tokens.length; i++) {
				GameElements e = createGameElement(tokens[i], i, j);
				if(e != null) {
					elementos.add(e);

					try {
						// To set the value of the destination room for a given door
						if (e instanceof Door)
							((Door) e).setDestinationRoom(doorDestinations.removeFirst());
					} catch(NoSuchElementException exception) {
						Logger.getLogger().log("Tentativa de atribuir destino inexistente para porta", ERROR);
						throw exception;
					}
				}
			}
			j++;
		}

		if(j != 10)
			throw new IllegalArgumentException();
	}
	public static List<GameElements> readElementsFrom(File ficheiro) throws FileNotFoundException {
		if (ficheiro.isDirectory()) return null;

		List<GameElements> elementos = new ArrayList<>();
		List<DoorDestination> doorDestinations = new ArrayList<>();

		readDoorsDestinations(ficheiro, doorDestinations);

		scanLinesForElements(elementos, doorDestinations, ficheiro);

		return elementos;
	}

	private static GameElements createGameElement(char tipo, int x, int y) {
		try {
			return switch (tipo) {
				case 'W' -> new Wall(x, y);
				case 'S' -> new Stairs(x, y);
				case 's' -> new Sword(x, y);
				case 't' -> new Trap(x, y);
				case 'H' -> Manel.getSingleManel(x, y);
				case 'G' -> new DonkeyKong(x, y);
				case '0' -> new Door(x, y);
				case 'm' -> new Meat(x, y);
				case 'P' -> Princess.getSinglePrincess(x, y);
				case 'B' -> new Bat(x, y);
				case 'b' -> new Bomb(x, y);
				default -> null;
			};
		} catch(IllegalArgumentException e) {
			Logger.getLogger().log(e.getMessage(), Logger.MessageType.ERROR);
		}
		return null;
	}

	public void removeAbsorbableElements(List<AbsorbableElements> elements) {
		for(InteractiveElements elem : elements)
			if(elem instanceof AbsorbableElements) {
				ImageGUI.getInstance().removeImage(elem);
				elementos.remove(elem);
			}
	}

	public List<MovingCharacters> getPersonagensMoveis() {
		ArrayList<MovingCharacters> personagensMoveis = new ArrayList<>();
		for(GameElements e : elementos)
			if(e instanceof MovingCharacters && !(e instanceof Manel)) personagensMoveis.add((MovingCharacters) e);

		return personagensMoveis;
	}

	public ArrayList<MovingCharacters> enemiesAt(Point2D pos) {
		ArrayList<MovingCharacters> l = new ArrayList<>();

		for(GameElements e : objectsAt(pos))
			if(e instanceof MovingCharacters) l.add((MovingCharacters) e);

		return l;
	}

	public List<GameElements> deadEnemies() {
		List<GameElements> dead = new ArrayList<GameElements>();
		for(GameElements elem : elementos)
			if(elem instanceof MovingCharacters && !(elem instanceof Manel)) {
				MovingCharacters e = (MovingCharacters) elem;
				if(e.isDead()) dead.add(e);
			}

		return dead;
	}

	public List<AbsorbableElements> usedElements() {
		List<AbsorbableElements> used = new ArrayList<>();

		for(GameElements elem : elementos)
			if(elem instanceof AbsorbableElements && ((AbsorbableElements) elem).isUsed())
				used.add((AbsorbableElements) elem);

		return used;
	}

	public void addElement(GameElements e) {
		elementos.add(e);
		ImageGUI.getInstance().addImage(e);
		if(e instanceof TimedWeapon)
			System.out.println("banana");
	}

	public void removeElement(GameElements e) {
		elementos.remove(e);
		ImageGUI.getInstance().removeImage(e);
	}

	public List<GameElements> elementsBelow(Point2D p) {
		Point2D nextPos = p.plus(Direction.DOWN.asVector());
		return objectsAt(nextPos);
	}

	public List<TimedWeapon> activeTimedWeapons() {
		ArrayList<TimedWeapon> list = new ArrayList<>();
		for(GameElements elem : elementos)
			if(elem instanceof TimedWeapon && ((TimedWeapon) elem).isActive())
				list.add((TimedWeapon) elem);

		return list;
	}

	public List<GameElements> surroundingExplodingObjects(Point2D pos) {
		ArrayList<GameElements> list = new ArrayList<>();

		for(GameElements elem : elementos) {
			if(pos.distanceTo(elem.getPosition()) <= 1 && !elem.isClimbable() && !elem.isSolid())
				list.add(elem);
		}

		return list;
	}
}
