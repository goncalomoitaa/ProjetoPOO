package objects;

import pt.iscte.poo.gui.ImageGUI;

public class Princess extends InteractiveElements {
    public Princess(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "Princess";
    }

    @Override
    public int getLayer() {
        return 0;
    }

    @Override
    public void interact(Manel manel) {
        if(ImageGUI.getInstance().getTicks() % 2 == 0) {
            ImageGUI.getInstance().showMessage("WIN", "TIME: " + ImageGUI.getInstance().getTicks()/2);
        } else {
            ImageGUI.getInstance().showMessage("WIN", "TIME: " + (ImageGUI.getInstance().getTicks()/2 + 1));
        }
        ImageGUI.getInstance().dispose();
    }

    @Override
    public String getInteractionMessage() {
        return "Ah! Meu herói!";
    }
}
