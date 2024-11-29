package objects;

public abstract class ElementosInterativos extends ElementosDeJogo implements Effect{


    public ElementosInterativos(int x, int y) {
        super(x, y);
    }

    public boolean isSolid() {
        return false;
    }

    // Non-abstract methods

}
