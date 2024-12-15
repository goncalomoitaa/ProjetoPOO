package objects;

import pt.iscte.poo.game.Room;

public class WarHammer extends AbsorbableElement {

    public WarHammer(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "War_hammer";
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public void interact(Manel manel, Room r) {
        manel.setPower(50);
        super.setUsed();
    }

    @Override
    public String getInteractionMessage() {
        return "MARTELO EM MÃOS!!!";
    }
}
