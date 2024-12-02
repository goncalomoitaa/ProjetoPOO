package objects;

import pt.iscte.poo.utils.Point2D;

public class Sword extends ElementosAbsorviveis {

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
        return 10;
    }

    @Override
    public String getMensagemDeInteracao() {
        return "WOOHOO, ESPADAAAA!";
    }
}
