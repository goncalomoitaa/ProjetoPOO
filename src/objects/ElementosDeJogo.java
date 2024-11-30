package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class ElementosDeJogo implements ImageTile {

    private Point2D position;
    TipoDeElemento tipo;

    public ElementosDeJogo(int x, int y, TipoDeElemento tipo) {
        this.position = new Point2D(x, y);
        this.tipo = tipo;
    }

    // Abstract methods
    @Override
    public abstract String getName();

    @Override
    public Point2D getPosition() {
        return position;
    }

    public TipoDeElemento getTipo() {
        return tipo;
    }

    @Override
    public abstract int getLayer();

    public abstract boolean isSolid();

    // Non-abstract methods
    public void setPosition(Point2D position) {
        this.position = position;
    }

    public boolean isClimbable() {
        return false;
    }

    public boolean isPickable() {
        return false;
    }
}
