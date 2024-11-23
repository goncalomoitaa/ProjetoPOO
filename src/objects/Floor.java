package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Floor extends ElementosEstaticos {

	public Floor(int x, int y) {
		super(x, y);
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
	public boolean isSolid() {
		return false;
	}
}
