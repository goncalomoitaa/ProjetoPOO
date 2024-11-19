package objects;

import pt.iscte.poo.utils.Direction;

public class DonkeyKong extends PersonagensMoveis {

    public DonkeyKong(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(Direction d) {
        //d.random();
    }

    @Override
    public String getName() {
        return "DonkeyKong";
    }

    @Override
    public int getLayer() {
        return 1;
    }
}
