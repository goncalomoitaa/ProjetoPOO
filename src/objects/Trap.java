package objects;

import pt.iscte.poo.utils.Point2D;

public class Trap extends ElementosDeJogo {

    private int x;
    private int y;

    public Trap(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public String getName() {
        return "Trap";
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
