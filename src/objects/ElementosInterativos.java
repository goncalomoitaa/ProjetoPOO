package objects;

public abstract class ElementosInterativos extends ElementosDeJogo {


    public ElementosInterativos(int x, int y) {
        super(x, y);
    }

    @Override
    public abstract String getName();

    @Override
    public abstract int getLayer();

    public boolean isSolid() {
        return false;
    }

    public abstract void efeitos();

    // Non-abstract methods

}
