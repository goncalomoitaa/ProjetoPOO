package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class DonkeyKong extends PersonagensMoveis {

    public DonkeyKong(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(Direction d) {
        Point2D nextPos = getPosition().plus(d.asVector());
        setPosition(nextPos);
    }

    @Override
    public String getName() {
        return "DonkeyKong";
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
