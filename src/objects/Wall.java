package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

import java.awt.*;

public class Wall extends ElementosDeJogo{

	private int x;
	private int y;

	public Wall(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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
		return new Point2D(x,y);
	}

	
}
