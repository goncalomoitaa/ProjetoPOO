package objects;

import pt.iscte.poo.game.Room;

public class Stairs extends InteractiveElements {

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
    public boolean isClimbable() {
        return true;
    }

    @Override
    public void interact(Manel manel, Room r) {
        return;
    }

    @Override
    public String getInteractionMessage() {
        return null;
    }
}
