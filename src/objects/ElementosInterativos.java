package objects;

public abstract class ElementosInterativos extends ElementosDeJogo {


    public ElementosInterativos(int x, int y) {
        super(x, y);
    }

    public boolean isSolid() {
        return false;
    }

    public abstract void efeitos();

    // Non-abstract methods

}
