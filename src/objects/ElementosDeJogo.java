package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class ElementosDeJogo implements ImageTile {

    private Point2D position;

    public ElementosDeJogo(int x, int y) {
        this.position = new Point2D(x, y);
    }

    @Override
    public abstract String getName();

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public abstract int getLayer();

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public abstract boolean isSolid();
}
