package objects;

public class Meat extends ElementosInterativos {
    public Meat(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "GoodMeat";
    }

    @Override
    public int getLayer() {
        return 0;
    }

    @Override
    public void impact(Manel player) {
        player.setLife(10);
    }

    @Override
    public boolean isPickable() {
        return true;
    }
}
