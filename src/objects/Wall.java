package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

import java.awt.*;

public class Wall extends ElementosEstaticos {

	public Wall(int x, int y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return "Wall";
	}

	@Override
	public int getLayer() {
		return 1;
	}

}
