package objects;

import pt.iscte.poo.utils.Direction;

public abstract class PersonagensMoveis extends ElementosDeJogo {

    public PersonagensMoveis(int x, int y) {
        super(x, y);
    }

    public abstract void move(Direction d);

    @Override
    public abstract String getName();

    @Override
    public abstract int getLayer();

    public abstract boolean isSolid();
}
