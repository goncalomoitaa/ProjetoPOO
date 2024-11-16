package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

import java.awt.*;

public class Wall implements ImageTile{

	private Point2D position;

	public Wall(int x, int y) {
		position = new Point2D(x, y);
	}

	@Override
	public String getName() {
		return "Wall";
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public Point2D getPosition() {
		return new Point2D(position.getX(), position.getY());
	}

	
}
