package objects;

public class Sword extends AbsorbableElements {

    public Sword(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "Sword";
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public int armaManel() {
        return 100;
    }

    @Override
    public String getMensagemDeInteracao() {
        return "WOOHOO, ESPADAAAA!";
    }
}
