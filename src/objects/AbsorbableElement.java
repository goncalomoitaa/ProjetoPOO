package objects;

public abstract class AbsorbableElement extends InteractiveElement {
    private boolean used = false;

    public AbsorbableElement(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean canStep() {
        return false;
    }

    public boolean isUsed() { return this.used; }

    public void setUsed() { this.used = true; }

    public void setNotUsed() { this.used = false; }
}
