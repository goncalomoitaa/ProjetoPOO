package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class DonkeyKong extends PersonagensMoveis {

    public DonkeyKong(int x, int y) {
        super(x, y);
    }

    public void moveKong(Direction d, Room currentRoom) {
        Point2D nextPos = this.getPosition().plus(d.asVector());
        if(nextPos.getY() != 0 || currentRoom.posicaoPermitida(nextPos) || nextPos.equals(Manel.getUnicoManel().getPosition())) {
            return;
        }
        move(d);
    }

    @Override
    public String getName() {
        return "DonkeyKong";
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
