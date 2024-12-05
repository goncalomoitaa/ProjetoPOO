package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.tools.Logger;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public abstract class PersonagensMoveis extends ElementosDeJogo {
    private Logger logger = Logger.getLogger();

    private int healthPoints, power;

    public PersonagensMoveis(int x, int y) {
        super(x, y);
        this.healthPoints = 100;
        this.power = 0;
    }

    public abstract boolean isSolid();

    public boolean isDead() {
        return this.healthPoints <= 0;
    }

    public abstract void update(Room r);

    public void move(Direction d, Room currentRoom) {
        Point2D nextPos = getPosition().plus(d.asVector());

        boolean nextPosSolid = currentRoom.solidPosition(nextPos);
        boolean currPosClimb = currentRoom.climbablePosition(this.getPosition());
        if((d == Direction.UP && !currPosClimb) || nextPosSolid) {
            bump(nextPos);
        } else {
            setPosition(nextPos);
            absorveElementoEm(nextPos, currentRoom);
        }

        fall(currentRoom);
    }

    public void fall(Room r) {
        if(r.solidPosition(this.getPosition()) || r.climbablePosition(this.getPosition())) return;

        boolean posSolid = r.solidPosition(new Point2D(getPosition().getX(), getPosition().getY() + 1));
        boolean posStairs = r.climbablePosition(new Point2D(getPosition().getX(), getPosition().getY() + 1));
        if(posSolid || posStairs) return;

        move(Direction.DOWN, r);
    }

    private void bump(Point2D pos) {
        logger.log(this.getName() + ": Impossível mover-se para a posição: " + pos.toString(), Logger.MessageType.ALERT);
    }

    public abstract void absorveElementoEm(Point2D pos, Room room);

    public void heal(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    public void injure(int healthPoints) {
        this.healthPoints -= healthPoints;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int newPower) {
        this.power = Math.max(this.power, newPower);
    }
}
