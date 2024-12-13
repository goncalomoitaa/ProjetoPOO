package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Bomb extends TimedWeapon {
    private static final int BOMB_COUNTDOWN_TIME = 5;

    public Bomb(int x, int y) {
        super(x, y, BOMB_COUNTDOWN_TIME);
    }

    @Override
    public String getName() {
        return "Bomb";
    }

    @Override
    public void engage(Room r) {
        for(GameElements elem : r.surroundingExplodingObjects(getPosition()))
            if(!(elem instanceof Manel))
                r.removeElement(elem);
            else
                ((Manel) elem).die();

        r.addElement(new Explosion(this.getPosition().getX(), this.getPosition().getY()));
        r.removeElement(this);
        return;
    }

    @Override
    public void activate(Point2D pos, Room r) {
        this.setPosition(pos);
        super.setMovable(false);
        super.startCountDown();
        super.setNotUsed();

        r.addElement(this);
    }

    @Override
    public void interact(Manel manel, Room r) {
        if(!super.isActive())
            manel.pickTimedWeapon(this, r);
    }

    @Override
    public String getInteractionMessage() {
        return null;
    }
}
