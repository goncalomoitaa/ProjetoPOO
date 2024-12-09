package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;

import java.util.List;

public class Bat extends MovingCharacters {

    public Bat(int x, int y) {
        super(x, y);
        setPower(5);
        setHealthPoints(1);
    }

    @Override
    public String getName() {
        return "Bat";
    }

    @Override
    public int getLayer() {
        return 2;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    public boolean shouldGoDown(Room r) {
        List<GameElements> ge = r.elementsBelow(this.getPosition());
        for(GameElements e : ge) {
            if(e instanceof Stairs) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void update(Room r) {
        if(shouldGoDown(r)) {
            this.move(Direction.DOWN, r);
        } else {
            this.move(Direction.random(), r);
        }
    }
}
