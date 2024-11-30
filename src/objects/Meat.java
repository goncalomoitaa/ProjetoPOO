package objects;

public class Meat extends ElementosAbsorviveis {
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
    public void efeitos() {

    }

    @Override
    public int alimentaManel() {
        return 10;
    }
}
