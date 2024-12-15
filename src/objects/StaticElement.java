package objects;

public abstract class StaticElement extends GameElement {

    public StaticElement(int x, int y) {
        super(x, y);
    }

    public boolean isSolid() {
        return true;
    }

}
