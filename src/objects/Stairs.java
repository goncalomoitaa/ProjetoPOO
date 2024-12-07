package objects;

public class Stairs extends InteractiveElements {

    public Stairs(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "Stairs";
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public boolean isClimbable() {
        return true;
    }

    @Override
    public void interact(Manel manel) {
        return;
    }
}
