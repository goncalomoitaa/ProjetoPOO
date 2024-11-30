package pt.iscte.poo.game;

import objects.*;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.tools.Logger;

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
			if (elemento.getPosition().equals(p) && !(elemento instanceof Manel)) {
				return elemento;
			}
		}
		return null;
	}

	public void moveWitchGravity() {
		if(podeEscalar(null, new Point2D(manel.getPosition().getX(),manel.getPosition().getY() + gravidade)) || objetoNaPosicao(new Point2D(manel.getPosition().getX(),manel.getPosition().getY() + gravidade)).isSolid()) {
			return; //caso seja escalável ou o bloco de baixo do Manel seja isSolid(), ele não cai
		}
			manel.move(Direction.DOWN);
	}

	public void moveKong(Direction d) {
		Point2D nextPos = kong.getPosition().plus(d.asVector());
			if(nextPos.getY() != 0 || posicaoPermitida(nextPos)) {
				return;
			}
			kong.move(d);
	}

	public boolean podeEscalar(Direction direction, Point2D point2D) { //
		ElementosDeJogo obj = objetoNaPosicao(point2D);
		return obj != null &&
				obj.isClimbable();
	}

	public boolean posicaoPermitida(Point2D pos) {
		return objetoNaPosicao(pos) != null && objetoNaPosicao(pos).isSolid();
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
				case 'H' -> Manel.getUnicoManel(x, y);
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

	public void removeElementoInterativo(ElementosDeJogo e) {
		if(e.getMensagemDeInteracao() != null) ImageGUI.getInstance().setStatusMessage(e.getMensagemDeInteracao());
		if(e instanceof ElementosAbsorviveis) {
			ImageGUI.getInstance().removeImage(e);
			elementos.remove(e);
		}
	}
}
