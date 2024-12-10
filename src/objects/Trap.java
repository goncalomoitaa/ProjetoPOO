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
    public void interact(Manel manel) {
        if (getStatus() == DoorStatus.CLOSED) {
            setStatus(DoorStatus.OPEN);
            manel.getUnicoManel().increaseHealth(-20);
        }
    }

    @Override
    public String getInteractionMessage() {
        return Manel.getUnicoManel().getHealtStatusMessage();
    }
}
