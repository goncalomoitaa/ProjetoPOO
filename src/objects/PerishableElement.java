package objects;

public abstract class PerishableElement extends AbsorbableElement {

    private int time;

    public PerishableElement(int x, int y) {
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
