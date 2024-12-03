package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.tools.Logger;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class DonkeyKong extends PersonagensMoveis {
    private Logger logger = Logger.getLogger();

    public DonkeyKong(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(Direction d) {
        Point2D nextPos = getPosition().plus(d.asVector());
        setPosition(nextPos);
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
        return true;
    }

    public void move(Room currentRoom) {
        Direction d = Direction.random();
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
        }
    }

    private void bump(ElementosDeJogo e) {
        logger.log("Movimento impossível para Kong: " + e.getPosition().toString(), Logger.MessageType.ERROR);
    }

}
