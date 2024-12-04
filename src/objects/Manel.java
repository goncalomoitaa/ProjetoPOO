package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.tools.Logger;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Manel extends PersonagensMoveis {
	private static Manel unicoManel;

	private Logger logger = Logger.getLogger();

	private Manel(int x, int y){
		super(0, 0);
		this.healthPoints = 100;
		this.power = 0;
	}

	private int healthPoints, power;

	public static Manel getUnicoManel() {
		if(unicoManel == null)
			unicoManel = new Manel(0, 0);

		return unicoManel;
	}

	public static Manel getUnicoManel(int x, int y) {
		if(unicoManel == null)
            unicoManel = new Manel(x, y);

		unicoManel.setPosition(new Point2D(x, y));
		return unicoManel;
	}

	public Manel(Point2D position) {
		super(position.getX(), position.getY());
	}

	@Override
	public String getName() {
		return "JumpMan";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public void move(Direction d) {
		Point2D nextPos = getPosition().plus(d.asVector());
		setPosition(nextPos);
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
	}

	public void fightEnemy(Enemy e) {
		if(e == null) return;
		this.healthPoints -= e.hurtHero();
		e.takeHit(this.power);
	}

	private void absorveElementoEm(Point2D pos, Room room) {
		ElementosDeJogo e = room.objetoNaPosicao(pos);

		if(e == null) return;

		this.healthPoints += e.alimentaManel();
		this.power = Math.max(this.power, e.armaManel());
		room.removeElementoInterativo(e);
	}

	private void bump(ElementosDeJogo e) {
		logger.log("Impossível mover-se para a posição: " + e.getPosition().toString(), Logger.MessageType.ERROR);
	}

	public void fall(Room r) {
		if(r.objetoNaPosicao(this.getPosition()) instanceof Stairs) return;

		ElementosDeJogo abaixoDoManel =
				r.objetoNaPosicao(new Point2D(this.getPosition().getX(), this.getPosition().getY() + 1));

		if(abaixoDoManel != null && (abaixoDoManel.isSolid() || abaixoDoManel.canStep())) return;

		move(Direction.DOWN, r);
	}
}
