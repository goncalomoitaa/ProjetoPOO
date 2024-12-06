package objects;

public abstract class StaticElements extends GameElements {

    public StaticElements(int x, int y) {
        super(x, y);
    }

    public boolean isSolid() {
        return true;
    }

}
