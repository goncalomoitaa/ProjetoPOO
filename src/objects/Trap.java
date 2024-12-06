package objects;

public class Trap extends StaticElements {

    public Trap(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "Trap";
    }

    @Override
    public int getLayer() {
        return 1;
    }

}
