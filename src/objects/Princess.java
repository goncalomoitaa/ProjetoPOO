package objects;

public class Princess extends InteractiveElements {
    public Princess(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "salazar";
    }

    @Override
    public int getLayer() {
        return 0;
    }

    @Override
    public void interact(Manel manel) {
        return;
    }

    @Override
    public String getInteractionMessage() {
        return "Ah! Meu herói!";
    }
}
