package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Banana extends PersonagensMoveis {

    public Banana(int x, int y) {
        super(x, y);
        this.setPower(15);
    }

    @Override
    public String getName() {
        return "Banana";
    }

    @Override
    public int getLayer() {
        return 3;
    }

    @Override
    public void move(Direction d, Room r) {
        if(getPosition().getY() == 9) injure(500);
        setPosition(getPosition().plus(d.asVector()));

        super.fall(r);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void absorveElementoEm(Point2D pos, Room room) {
        return;
    }

    public void update(Room r) {
        move(Direction.DOWN, r);
    }
}