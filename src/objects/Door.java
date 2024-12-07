package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;

public class Door extends InteractiveElements {

    private DoorStatus status;

    public Door(int x, int y) {
        super(x, y);
        this.status = DoorStatus.CLOSED;
    }

    @Override
    public String getName() {
        if(getStatus() == DoorStatus.OPEN) {
            return "DoorOpen";
        } else {
            return "DoorClosed";
        }
    }

    public DoorStatus getStatus() {
        return status;
    }

    public void setStatus(DoorStatus ds) {
        this.status = ds;
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public void interact(Manel manel) {
        if (getStatus() == DoorStatus.CLOSED) {
            setStatus(DoorStatus.OPEN);
            ImageGUI.getInstance().setStatusMessage("PORTA ABERTA!!!");
        }
    }

    @Override
    public String getInteractionMessage() {
        return null;
    }
}
