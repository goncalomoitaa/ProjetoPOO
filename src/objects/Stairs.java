package objects;

import pt.iscte.poo.utils.Point2D;

public class Stairs extends ElementosDeJogo {

    public Stairs(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "Stairs";
    }

    @Override
    public int getLayer() {
        return 1;
    }

}
