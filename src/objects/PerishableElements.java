package objects;

public abstract class PerishableElements extends AbsorbableElements {

    private int time;

    public PerishableElements(int x, int y) {
        super(x, y);
        time = 0;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
