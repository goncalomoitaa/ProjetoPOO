package objects;

import pt.iscte.poo.gui.ImageGUI;

public class Meat extends AbsorbableElements {
    public Meat(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        if(ImageGUI.getInstance().getTicks() >= 20) {
            return "BadMeat";
        } else {
            return "GoodMeat";
        }
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public void interact(Manel manel) {
        if(getName().equals("BadMeat")) {
            manel.increseHealth(-10);
            super.setUsed();
        }
        manel.increseHealth(10);
        super.setUsed();
    }

    @Override
    public String getInteractionMessage() {
        if(getName().equals("BadMeat")) {
            return "HMMM...SABOR DE APOCALIPSE!";
        }
        return "GANDA PREGO PRO ALMOÇO!!!";
    }
}
