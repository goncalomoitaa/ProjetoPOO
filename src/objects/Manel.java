package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

import java.util.ArrayList;

public class Manel extends MovingCharacters {
	private static Manel unicoManel;
	private int lives;

	private Manel(int x, int y){
		super(0, 0);
		this.lives = 3;
	}

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
		return "capitaofalcao";
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
		String hearts = "";

		for(int i = lives; i > 0; i--)
			hearts = hearts.concat("<3");

		for(int i = 3 - lives; i > 0; i--)
			hearts = hearts.concat("</3");

		return hearts.concat(": " + getHealthPoints() + " / 100");
	}

	public void fightEnemy(ArrayList<MovingCharacters> enemies) {
		for(MovingCharacters e : enemies) {
			if (e == null) return;
			e.injure(this.getPower());
			injure(e.getPower());
		}
	}

	@Override
	public void die() {
		if(lives >= 0) {
			this.setHealthPoints(100);
			this.lives--;
		}
	}

	public int getLives() {
		return this.lives;
	}
}
