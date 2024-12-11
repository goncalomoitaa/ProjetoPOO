package objects;

import pt.iscte.poo.game.Room;

public class Bomb extends TimedWeapons {
    private static final int BOMB_COUNTDOWN_TIME = 5;
    private boolean movable;

    public Bomb(int x, int y) {
        super(x, y);
        this.countDownTime = BOMB_COUNTDOWN_TIME;
        this.movable = true;
    }

    @Override
    public String getName() {
        return "Bomb";
    }

    @Override
    public int getLayer() {
        return 2;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void engage(Room r) {
        return;
    }

    public void drop() {
        this.movable = false;
    }
}
