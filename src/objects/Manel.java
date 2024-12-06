package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Manel extends MovingCharacters {
	private static Manel unicoManel;

	private Manel(int x, int y){
		super(0, 0);
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

	public void fightEnemy(MovingCharacters e) {
		if(e == null) return;
		e.injure(this.getPower());
		injure(e.getPower());
		ImageGUI.getInstance().setStatusMessage("Life JumpMan: " + getHealthPoints() + "/100");
		System.out.println(getHealthPoints());
	}

	public void absorveElementoEm(Point2D pos, Room room) {
		for(InteractiveElements e : room.objectsAbsorbable(pos)) {
			if (!e.getPosition().equals(pos) || e.getClass().equals(Stairs.class)) {
				continue;
			}
			e.effects();
			room.removeElementoInterativo(e);
		}
	}
}
