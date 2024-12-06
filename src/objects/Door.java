package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;

public class Door extends InteractiveElements {

    private DoorStatus status;

    public Door(int x, int y, DoorStatus status) {
        super(x, y);
        this.status = status;
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
        status = ds;
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public void effects() {
        if (getStatus() == DoorStatus.CLOSED) {
            setStatus(DoorStatus.OPEN);
            ImageGUI.getInstance().setStatusMessage("PORTA ABERTA");
        }
    }
}
