package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DonkeyKong extends PersonagensMoveis {

    private final List<Banana> bananas;

    public DonkeyKong(int x, int y) {
        super(x, y);
        bananas = new ArrayList<>();
    }

    @Override
    public void move(Direction d) {
        Point2D nextPos = getPosition().plus(d.asVector());
        setPosition(nextPos);
    }

    public void move(Direction d, Room currentRoom) {
        Point2D nextPos = this.getPosition().plus(d.asVector());
        if(nextPos.getY() != 0 || currentRoom.posicaoPermitida(nextPos) || nextPos.equals(Manel.getUnicoManel().getPosition())) {
            return;
        }
        setPosition(nextPos);
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

    public List<Banana> attackKong() {
		List<Banana> bananas = new ArrayList<>();
			Random random = new Random();
			int nDeAtaques = random.nextInt(2);
			while (nDeAtaques > 0) {
				bananas.add(new Banana(this.getPosition().getX(), this.getPosition().getY()));
				nDeAtaques--;
			}
		return bananas;
	}

}
