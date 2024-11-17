package objects;

public class DonkeyKong extends ElementosDeJogo {

    public DonkeyKong(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "DonkeyKong";
    }

    @Override
    public int getLayer() {
        return 1;
    }
}
