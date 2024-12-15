package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public abstract class TimedWeapon extends AbsorbableElement {
    private int countDownTime;
    private boolean active, movable;

    public TimedWeapon(int x, int y, int countDownTime) {
        super(x, y);
        this.countDownTime = countDownTime;
        this.movable = true;
    }

    public abstract void engage(Room r);

    public abstract void activate(Point2D pos, Room r);

    public void setMovable(boolean m) { this.movable = m; }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    public void tickDown() {
        if(this.active)
            this.countDownTime--;
    }

    public boolean hasTimeLeft() {
        return timeLeft() > 0;
    }

    private int timeLeft() {
        return countDownTime;
    }

    public boolean isActive() { return this.active; }

    public void startCountDown() {
        this.active = true;
    }

    public abstract void interact(Manel manel, Room r);
}
