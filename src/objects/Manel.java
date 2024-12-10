package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

import java.util.ArrayList;

public class Manel extends MovingCharacters {
	private final int MAX_LIVES = 3;
	private static Manel unicoManel;

	private int lives;

	private Manel(int x, int y){
		super(0, 0);
		setPower(1);
		setLives(MAX_LIVES);
	}

	private void setLives(int lives) { this.lives = lives; }

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

	public void respawn() {
		if(this.lives >= 0) {
			this.lives--;
			this.setHealthPoints(100);
		}
	}

	public int getLives() {
		return this.lives;
	}

	public boolean hasLivesLeft() {
		return getLives() > 0;
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
		String msg = "";
		for(int i = 0; i < getLives(); i++)
			msg = msg.concat("<3");

		for(int i = MAX_LIVES - getLives(); i > 0; i--)
			msg = msg.concat("</3");

		msg = msg.concat(": ").concat("" + getHealthPoints());
		return msg;
	}

	public void fightEnemy(ArrayList<MovingCharacters> enemies) {
		for(MovingCharacters e : enemies) {
			if (e == null) return;
			e.injure(this.getPower());
			injure(e.getPower());
		}
	}
}
