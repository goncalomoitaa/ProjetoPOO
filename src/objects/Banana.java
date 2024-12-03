package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Banana extends ElementosAbsorviveis {

    public Banana(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "Banana";
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public int alimentaManel() {
        return -10;
    }

    public void move(Direction d) {
       setPosition(new Point2D(getPosition().getX() + d.asVector().getX(), getPosition().getY() + d.asVector().getY()));
    }
}
