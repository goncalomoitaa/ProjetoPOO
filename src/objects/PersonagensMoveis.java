package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public abstract class PersonagensMoveis extends ElementosDeJogo {

    private int life;
    private int damage;

    public PersonagensMoveis(int x, int y, int life, int damage) {
        super(x, y, TipoDeElemento.DINAMICO);
        this.life = life;
        this.damage = damage;
    }

    public void move(Direction d) {
        Point2D nextPos = getPosition().plus(d.asVector());
        setPosition(nextPos);
    }

    public abstract boolean isSolid();

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life += life;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage += damage;
    }

}
