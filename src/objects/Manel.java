package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

import java.util.ArrayList;

public class Manel extends MovingCharacters {
	private static Manel unicoManel;

	private Manel(int x, int y){
		super(0, 0);
		setPower(1);
	}

	public static Manel getSingleManel() {
		if(unicoManel == null)
			unicoManel = new Manel(0, 0);

		return unicoManel;
	}

	public static Manel getSingleManel(int x, int y) {
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
	public void update(Room r) {
		if(isDead()) {
			r.removeElement(this);
		}
	}

	public String getHealtStatusMessage() {
		return getHealthPoints() + " / 100";
	}

	public void fightEnemy(ArrayList<MovingCharacters> enemies) {
		for(MovingCharacters e : enemies) {
			if (e == null) return;
			e.injure(this.getPower());
			injure(e.getPower());
		}
	}
}
