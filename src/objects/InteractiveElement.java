package objects;

public abstract class InteractiveElement extends GameElement implements Interaction {
    public InteractiveElement(int x, int y) {
        super(x, y);
    }

    public boolean isSolid() {
        return false;
    }

    // Non-abstract methods

}
