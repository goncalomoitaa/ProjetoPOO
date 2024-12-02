package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public abstract class PersonagensMoveis extends ElementosDeJogo {

    public PersonagensMoveis(int x, int y) {
        super(x, y);
    }

    public void move(Direction d) {
        Point2D nextPos = this.getPosition().plus(d.asVector());
        this.setPosition(nextPos);
    }

    public abstract boolean isSolid();

}
