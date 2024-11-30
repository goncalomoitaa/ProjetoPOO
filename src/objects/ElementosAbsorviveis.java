package objects;

public abstract class ElementosAbsorviveis extends ElementosInterativos {
    public ElementosAbsorviveis(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean canStep() {
        return false;
    }
}
