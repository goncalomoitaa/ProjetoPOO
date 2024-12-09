package objects;

public abstract class AbsorbableElements extends InteractiveElements {

    private boolean used = false;
    private int time;

    public AbsorbableElements(int x, int y) {
        super(x, y);
        time = 0;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean canStep() {
        return false;
    }

    public boolean isUsed() { return this.used; }

    public void setUsed() { this.used = true; }
}
