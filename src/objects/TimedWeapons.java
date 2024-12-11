package objects;

import pt.iscte.poo.game.Room;

public abstract class TimedWeapons extends GameElements {
    int countDownTime;

    public TimedWeapons(int x, int y) {
        super(x, y);
        this.countDownTime = 0; // Só para garantir que não é nunca nulo
    }

    public abstract void engage(Room r);
}
