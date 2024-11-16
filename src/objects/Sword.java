package objects;

import pt.iscte.poo.utils.Point2D;

public class Sword extends ElementosDeJogo{

    private int x;
    private int y;

    public Sword(int x, int y) {
        super();
        this.x = x;
        this.y = y;
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
    public Point2D getPosition() {
        return new Point2D(x,y);
    }
}
