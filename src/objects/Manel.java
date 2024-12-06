package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Manel extends PersonagensMoveis {
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
		ImageGUI.getInstance().setStatusMessage("Life JumpMan: " + getHealthPoints() + "/100");
	}

	public void fightEnemy(PersonagensMoveis e) {
		if(e == null) return;
		e.injure(this.getPower());
		injure(e.getPower());
		System.out.println(getHealthPoints());
	}

	public void absorveElementoEm(Point2D pos, Room room) {
		for(ElementosDeJogo e : room.objectsAt(pos)) {
			if (e == null) continue;

			heal(e.alimentaManel());
			setPower(e.armaManel());
			room.removeElementoInterativo(e);
		}
	}
}
