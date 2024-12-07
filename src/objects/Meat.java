package objects;

import pt.iscte.poo.gui.ImageGUI;

public class Meat extends AbsorbableElements {
    public Meat(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "GoodMeat";
    }

    @Override
    public int getLayer() {
        return 0;
    }

    @Override
    public void interact(Manel manel) {
        manel.increseHealth(10);
        ImageGUI.getInstance().setStatusMessage("GANDA PREGO!");
        super.setUsed();
    }
}
