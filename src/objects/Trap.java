package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;

public class Trap extends InteractiveElements {

    private DoorStatus status;

    public Trap(int x, int y) {
        super(x, y);
        this.status = DoorStatus.CLOSED;
    }

    public DoorStatus getStatus() {
        return status;
    }

    public void setStatus(DoorStatus ds) {
        this.status = ds;
    }

    @Override
    public String getName() {
        if(getStatus() == DoorStatus.OPEN) {
            return "Trap";
        } else {
            return "Wall";
        }
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public void effects() {
        if (getStatus() == DoorStatus.CLOSED) {
            setStatus(DoorStatus.OPEN);
            Manel.getUnicoManel().increseHealth(-20);
            ImageGUI.getInstance().setStatusMessage("Life JumpMan: " + Manel.getUnicoManel().getHealthPoints() + "/100");
        }
    }
}
