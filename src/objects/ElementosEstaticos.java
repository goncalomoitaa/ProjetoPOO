package objects;

public abstract class ElementosEstaticos extends ElementosDeJogo {

    public ElementosEstaticos(int x, int y) {
        super(x, y);
    }

    public boolean isSolid() {
        return true;
    }

}
