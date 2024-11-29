package objects;

import pt.iscte.poo.utils.Point2D;

public class Sword extends ElementosInterativos{

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
    public void impact(Manel player) {
        player.setDamage(15);
    }

}
