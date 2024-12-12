package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Explosion extends TimedWeapon {
    public Explosion(int x, int y) {
        super(x, y, 2);
        super.setMovable(false);
        super.setUsed();
    }

    @Override
    public String getName() {
        return "Fire";
    }

    @Override
    public void engage(Room r) {

    }

    @Override
    public void activate(Point2D pos, Room r) {
        return;
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void interact(Manel manel, Room r) {
        return;
    }

    @Override
    public String getInteractionMessage() {
        return null;
    }
}
