package objects;

public class Meat extends AbsorbableElements {
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
    public int alimentaManel() {
        return 10;
    }

    @Override
    public String getMensagemDeInteracao() {
        return "GANDA PREGO PRO ALMOÇO!";
    }
}
