package objects;

import pt.iscte.poo.utils.Direction;

public abstract class PersonagensMoveis extends ElementosDeJogo {

    public PersonagensMoveis(int x, int y) {
        super(x, y);
    }

    public abstract void move(Direction d);

    public abstract boolean isSolid();

}
