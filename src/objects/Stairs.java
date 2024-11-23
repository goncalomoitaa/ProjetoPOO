package objects;

import pt.iscte.poo.utils.Point2D;

public class Stairs extends ElementosInterativos {

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

    @Override
    public void efeitos() {

    }

    @Override
    public boolean isClimbable() {
        return true;
    }
}
