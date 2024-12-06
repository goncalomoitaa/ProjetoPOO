package objects;

public abstract class AbsorbableElements extends InteractiveElements {
    public AbsorbableElements(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean canStep() {
        return false;
    }
}
