package objects;

public class Door extends InteractiveElements {
    public Door(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "DoorClosed";
    }

    @Override
    public int getLayer() {
        return 0;
    }

}
