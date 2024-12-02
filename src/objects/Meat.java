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
        return 1;
    }

    @Override
    public int alimentaManel() {
        return 10;
    }

    @Override
    public String getMensagemDeInteracao() {
        return "GANDA PREGO PRO ALMOÇO!";
    }
}
