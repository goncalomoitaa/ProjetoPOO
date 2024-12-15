package objects;

public class Background extends StaticElement {

	public Background(int x, int y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return "Floor";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public boolean canStep() {
		return false;
	}
}
