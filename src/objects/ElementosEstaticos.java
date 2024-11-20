package objects;

public abstract class ElementosEstaticos extends ElementosDeJogo {

    public ElementosEstaticos(int x, int y) {
        super(x, y);
    }

    @Override
    public abstract String getName();

    @Override
    public abstract int getLayer();

    public boolean isSolid() {
        return true;
    }
}
