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

    public abstract void move(Direction d);

    public abstract boolean isSolid();

    public boolean isDead() {
        return this.healthPoints <= 0;
    }

    public void takeHit(int hit) {
        this.healthPoints -= hit;
    }

    public void move(Direction d, Room currentRoom) {
        Point2D nextPos = getPosition().plus(d.asVector());

        ElementosDeJogo elementoNaPosicaoFutura = currentRoom.objetoNaPosicao(nextPos);
        ElementosDeJogo elementoNaPosicaoAtual = currentRoom.objetoNaPosicao(this.getPosition());

        boolean canClimb = elementoNaPosicaoAtual != null && !elementoNaPosicaoAtual.isClimbable();
        if(d == Direction.UP && (canClimb || elementoNaPosicaoAtual == null)) {
            return;
        } else if(elementoNaPosicaoFutura != null && elementoNaPosicaoFutura.isSolid()) {
            bump(elementoNaPosicaoFutura);
        } else {
            setPosition(nextPos);
            absorveElementoEm(nextPos, currentRoom);
        }

        fall(currentRoom);
    }

    private void fall(Room r) {
        if(r.objetoNaPosicao(this.getPosition()) instanceof Stairs) return;

        ElementosDeJogo abaixoDoManel =
            r.objetoNaPosicao(new Point2D(this.getPosition().getX(), this.getPosition().getY() + 1));

        if(abaixoDoManel != null && (abaixoDoManel.isSolid() || abaixoDoManel.canStep())) return;

        move(Direction.DOWN, r);
    }

    private void bump(ElementosDeJogo e) {
        logger.log("Impossível mover-se para a posição: " + e.getPosition().toString(), Logger.MessageType.ERROR);
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
