package objects;

import pt.iscte.poo.utils.Direction;

public abstract class PersonagensMoveis extends ElementosDeJogo {

    private int healthPoints;
    public PersonagensMoveis(int x, int y) {
        super(x, y);
        this.healthPoints = 100;
    }

    public abstract void move(Direction d);

    public abstract boolean isSolid();

    public boolean isDead() {
        return this.healthPoints <= 0;
    }

    public void takeHit(int hit) {
        this.healthPoints -= hit;
    }
}
