package objects;

public class Wall extends StaticElement {

	public Wall(int x, int y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return "Wall";
	}

	@Override
	public int getLayer() {
		return 1;
	}

}
