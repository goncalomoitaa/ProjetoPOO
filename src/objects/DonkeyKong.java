package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.tools.Logger;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class DonkeyKong extends PersonagensMoveis {
    private Logger logger = Logger.getLogger();

    public DonkeyKong(int x, int y) {
        super(x, y);
        this.setPower(5);
    }

    @Override
    public String getName() {
        return "DonkeyKong";
    }

    @Override
    public int getLayer() {
        return 2;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    private void bump(ElementosDeJogo e) {
        logger.log("Movimento impossível para Kong: " + e.getPosition().toString(), Logger.MessageType.ALERT);
    }

    @Override
    public void absorveElementoEm(Point2D pos, Room room) {
        return;
    }

    @Override
    public void update(Room r) {
        if(isDead()) {
            return;
        }
        move(Direction.random(), r);
        throwBanana(r);
    }

    private void throwBanana(Room r) {
        if(Math.random() <= 0.2)
            r.addElement(new Banana(this.getPosition().getX(), this.getPosition().getY()));
    }
}
