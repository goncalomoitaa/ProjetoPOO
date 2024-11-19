package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Manel extends PersonagensMoveis {

	public Manel(int x, int y){
		super(x, y);
	}

	public Manel(Point2D position){
		super(position.getX(), position.getY());
	}


	@Override
	public String getName() {
		return "JumpMan";
	}


	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public void move(Direction d) {
		Point2D nextPos = getPosition().plus(d.asVector());
		if(nextPos.getX() < 0 || nextPos.getY() < 0  || nextPos.getX() >= 10 || nextPos.getY() >= 10) { //se passar do limite ele não se mexe
			return;
		}
		setPosition(nextPos);
	}
	
}
