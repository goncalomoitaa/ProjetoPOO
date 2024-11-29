package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.tools.Logger;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Manel extends PersonagensMoveis {
	private static Manel unicoManel;

	private Logger logger = Logger.getLogger();

	private Manel(int x, int y){
		super(0, 0,100,10);
	}

	public static Manel getUnicoManel(int x, int y) {
		if(unicoManel == null)
            unicoManel = new Manel(x, y);

		unicoManel.setPosition(new Point2D(x, y));
		return unicoManel;
	}

	public Manel(Point2D position){
		super(position.getX(), position.getY(),100,10);
	}

	@Override
	public String getName() {
		return "JumpMan";
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	public void move(Direction d, Room currentRoom) {
		Point2D nextPos = getPosition().plus(d.asVector());
		ElementosDeJogo e = currentRoom.objetoNaPosicao(nextPos);
		if(e != null && e.isSolid()) {
			bump(e);
		} else {
			setPosition(nextPos);
		}

	}

	private void bump(ElementosDeJogo e) {
		logger.log("Impossível mover-se para a posição: " + e.getPosition().toString(), Logger.MessageType.ERROR);
	}
}
