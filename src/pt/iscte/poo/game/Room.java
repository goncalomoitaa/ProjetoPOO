package pt.iscte.poo.game;

import objects.*;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.tools.Logger;
import pt.iscte.poo.utils.Vector2D;

import static pt.iscte.poo.tools.Logger.MessageType.*;

public class Room {

	private int gravidade = 1;
	private DonkeyKong kong;
	private Manel manel;
	private List<ElementosDeJogo> elementos;
	private Logger logger = Logger.getLogger();

	private String nome;
	
	public Room(List<ElementosDeJogo> elementos, String nome) throws FileNotFoundException {
		logger.log("Criando Room " + nome, INFO);
		this.nome = nome;
		this.elementos = elementos;
		logger.log("Room criada: " + nome, INFO);
	}

	public void atualiza() {
		for(ElementosDeJogo elemento : elementos) {
			if (elemento.getName().equals("JumpMan")) {
				manel = (Manel) elemento;
			}
		}
		for(ElementosDeJogo elemento : elementos) {
			if(elemento.getName().equals("DonkeyKong")) {
				kong = (DonkeyKong) elemento;
			}
		}
		for(int x = 0; x!= 10; x++) {
			for(int y = 0; y!= 10; y++) {
				// criei floor em todos os quadrados
				// mas quando leio o ficheiro tambem deteto e adiciono floors
				// para resolver : só adicionar floor onde necessário
				ImageGUI.getInstance().addImage(new Background(x, y));
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

	public void  moveWitchGravity() {
		if(podeEscalar(null, new Point2D(manel.getPosition().getX(),manel.getPosition().getY() + gravidade)) || objetoNaPosicao(new Point2D(manel.getPosition().getX(),manel.getPosition().getY() + gravidade)).isSolid()) {
			return; //caso seja escalável ou o bloco de baixo do Manel seja isSolid(), ele não cai
		}
			manel.move(Direction.DOWN);
	}

	public void moveManel(Direction d) {
		Point2D nextPos = manel.getPosition().plus(d.asVector());
        if(d == Direction.UP && !podeEscalar(d, manel.getPosition())) {
			logger.log("Não é possível escalar nessa posição: " + nextPos, ALERT);
			return;
		} else if(posicaoPermitida(nextPos)) {
			logger.log("Não é possível andar para esta posição (objeto sólido): " + nextPos, ALERT);
			return;
		}
            manel.move(d);
	}

	public void moveKong(Direction d) {
		Point2D nextPos = kong.getPosition().plus(d.asVector());
			if(nextPos.getY() != 0 || posicaoPermitida(nextPos)) {
				return;
			}
			kong.move(d);
	}

	private boolean podeEscalar(Direction direction, Point2D point2D) { //
		ElementosDeJogo obj = objetoNaPosicao(point2D);
		return obj != null &&
				obj.isClimbable();
	}

	private boolean posicaoPermitida(Point2D pos) {
		return objetoNaPosicao(pos) != null && objetoNaPosicao(pos).isSolid();
	}

	public static LinkedList<Room> carregaSalas() {
		return carregaSalas("rooms/");
	}

	public static LinkedList<Room> carregaSalas(String dirName) {
		try {
			File dir = new File(dirName);
			LinkedList<Room> rooms = new LinkedList<>();

			for (File roomFile : ListaDiretorios.collectFiles(dir)) {
				List<ElementosDeJogo> e = lerFicheiro(roomFile);
				if(e == null) continue;
				rooms.add(new Room(e, roomFile.getName()));
			}
			return rooms;
		} catch(FileNotFoundException e) {
//			logger.log("Diretoria rooms/ não encontrada, impossível carregar o jogo", ERROR);
		}

		return new LinkedList<>();
	}

	private class ListaDiretorios {
		public static ArrayList<File> collectFiles(File dir) {
			ArrayList<File> list = new ArrayList<>();
			collectFilesRec(dir, list);

			list.sort((File f1, File f2) -> String.CASE_INSENSITIVE_ORDER.compare(f1.getName(), f2.getName()));
			return list;
		}

		private static void collectFilesRec(File f, ArrayList<File> list) {
			list.add(f);

			if(f.isDirectory())
				for(File child : f.listFiles())
					collectFilesRec(child, list);
		}
	}

	private static List<ElementosDeJogo> lerFicheiro(File ficheiro) {
		try {
			if (ficheiro.isDirectory()) return null;

			List<ElementosDeJogo> elementos = new ArrayList<>();
			Scanner sc = new Scanner(ficheiro);
			sc.nextLine(); // TODO: substituir pela extração das infos da room (nome e proxima room);

			int j = 0;
			while (sc.hasNextLine()) {
				char[] tokens = sc.nextLine().toCharArray();
				for (int i = 0; i < tokens.length; i++) {
					elementos.add(criarElementoDeJogo(tokens[i], i, j));
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
				case ' ' -> new Background(x, y);
				case 'W' -> new Wall(x, y);
				case 'S' -> new Stairs(x, y);
				case 's' -> new Sword(x, y);
				case 't' -> new Trap(x, y);
				case 'H' -> new Manel(x, y);
				case 'G' -> new DonkeyKong(x, y);
				case '0' -> new Door(x, y);
				case 'm' -> new Meat(x, y);
				case 'P' -> new Princess(x, y);
				default -> throw new IllegalArgumentException(
					"O caractere lido não corresponde a um elemento de jogo conhecido: '" + tipo + "'"
				);
			};
		} catch(IllegalArgumentException e) {
			Logger.getLogger().log(e.getMessage(), Logger.MessageType.ERROR);
		}
		return null;
	}

	public String getNome() {
		return this.nome;
	}
}