package objects;

import pt.iscte.poo.game.Room;

public class Sword extends AbsorbableElement {

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
    public void interact(Manel manel, Room r) {
        manel.setPower(60);
        super.setUsed();
    }

    @Override
    public String getInteractionMessage() {
        return "GANDA ESPADA DO REI AFONSO!";
    }
}
