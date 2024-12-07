package objects;

public abstract class AbsorbableElements extends InteractiveElements {
    private boolean used = false;

    public AbsorbableElements(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean canStep() {
        return false;
    }

    public boolean isUsed() { return this.used; }

    public void setUsed() { this.used = true; }
}
