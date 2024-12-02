package objects;

import pt.iscte.poo.game.Room;

public class Banana extends ElementosAbsorviveis {

    public Banana(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "Banana";
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public int alimentaManel() {
        return -10;
    }

}
