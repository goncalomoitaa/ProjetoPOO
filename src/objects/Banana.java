package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Banana extends ElementosInterativos implements Effect {

    public Banana(int x, int y) {
        super(x, y);
    }

    @Override
    public void impact(Manel player) {
        player.setDamage(-10);
    }

    @Override
    public String getName() {
        return "Banana";
    }

    @Override
    public int getLayer() {
        return 1;
    }

    public void move(Direction d) {
        Point2D nextPos = getPosition().plus(d.asVector());
        setPosition(nextPos);
    }
}
