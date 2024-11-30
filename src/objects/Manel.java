package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.tools.Logger;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Manel extends PersonagensMoveis {
	private static Manel unicoManel;

	private Logger logger = Logger.getLogger();

	private Manel(int x, int y){
		super(0, 0);
		this.vida = 100;
		this.poder = 0;
	}

	private int vida, poder;

	public static Manel getUnicoManel() {
		if(unicoManel == null)
			unicoManel = new Manel(0, 0);

		return unicoManel;
	}

	public static Manel getUnicoManel(int x, int y) {
		if(unicoManel == null)
            unicoManel = new Manel(x, y);

		unicoManel.setPosition(new Point2D(x, y));
		return unicoManel;
	}

	public Manel(Point2D position) {
		super(position.getX(), position.getY());
	}

	@Override
	public String getName() {
		return "JumpMan";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public void move(Direction d) {
		Point2D nextPos = getPosition().plus(d.asVector());
		setPosition(nextPos);
	}

	public void move(Direction d, Room currentRoom) {
		Point2D nextPos = getPosition().plus(d.asVector());

		ElementosDeJogo e = currentRoom.objetoNaPosicao(nextPos);
		if(e != null && e.isSolid()) {
			bump(e);
		} else {
			setPosition(nextPos);
			absorveElementoEm(nextPos, currentRoom);
		}
	}

	private void absorveElementoEm(Point2D pos, Room room) {
		ElementosDeJogo e = room.objetoNaPosicao(pos);

		if(e == null) return;

		this.vida += e.alimentaManel();
		this.poder = Math.max(this.poder, e.armaManel());
		room.removeElementoInterativo(e);
	}

	private void bump(ElementosDeJogo e) {
		logger.log("Impossível mover-se para a posição: " + e.getPosition().toString(), Logger.MessageType.ERROR);
	}
}
