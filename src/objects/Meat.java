package objects;

import pt.iscte.poo.game.Room;

public class Meat extends PerishableElements {

    private final int TIME_TO_PERISH = 25;

    public Meat(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        if(getTime() >= TIME_TO_PERISH) {
            return "BadMeat";
        }
        return "GoodMeat";
    }

    @Override
    public int getLayer() {
        return 1;
    }


    @Override
    public void interact(Manel manel, Room r) {
        if(getName().equals("BadMeat")) {
            Manel.getSingleManel().increaseHealth(-10);
            setUsed();
        } else {
            Manel.getSingleManel().increaseHealth(10);
            setUsed();
        }
    }

    @Override
    public String getInteractionMessage() {
        return "GANDA PREGO PRO ALMOÇO!!!";
    }
}
