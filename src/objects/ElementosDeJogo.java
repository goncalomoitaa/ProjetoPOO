package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class ElementosDeJogo implements ImageTile {

    public ElementosDeJogo() {
    }

    @Override
    public abstract String getName();

    @Override
    public abstract Point2D getPosition();

    @Override
    public abstract int getLayer();
}
