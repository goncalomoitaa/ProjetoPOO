package objects;

import pt.iscte.poo.utils.Point2D;

public class Trap extends ElementosEstaticos {

    public Trap(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "Trap";
    }

    @Override
    public int getLayer() {
        return 1;
    }

}
