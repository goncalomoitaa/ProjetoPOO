package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Floor extends ElementosDeJogo{

	private int x;
	private int y;

	public Floor(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String getName() {
		return "Floor";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public Point2D getPosition() {
		return new Point2D(x, y);
	}


}
