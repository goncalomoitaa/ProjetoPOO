package objects;

import pt.iscte.poo.utils.Point2D;

public class Sword extends ElementosDeJogo{

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
}
