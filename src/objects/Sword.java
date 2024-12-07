package objects;

import pt.iscte.poo.gui.ImageGUI;

public class Sword extends AbsorbableElements {

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
    public void interact(Manel manel) {
        manel.setPower(100);
        super.setUsed();
    }
}
