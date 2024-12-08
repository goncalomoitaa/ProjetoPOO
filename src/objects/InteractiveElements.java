package objects;

public abstract class InteractiveElements extends GameElements implements Interaction {

    private boolean interact;

    public InteractiveElements(int x, int y) {
        super(x, y);
    }

    public boolean isSolid() {
        return false;
    }

    // Non-abstract methods

}
