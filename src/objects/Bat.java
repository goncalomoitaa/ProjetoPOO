package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Bat extends MovingCharacters {

    public Bat(int x, int y) {
        super(x, y);
        setPower(5);
        setHealthPoints(1);
    }

    @Override
    public String getName() {
        return "Bat";
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
        if(r.elementTypeBelow(this)) {
            this.move(Direction.DOWN, r);
        } else {
            this.move(Direction.random(), r);
        }
    }
}
