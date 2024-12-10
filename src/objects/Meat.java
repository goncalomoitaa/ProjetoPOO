package objects;

public class Meat extends PerishableElements {

    public Meat(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        if(getTime() >= 25) {
            return "BadMeat";
        }
        return "GoodMeat";
    }

    @Override
    public int getLayer() {
        return 1;
    }


    @Override
    public void interact(Manel manel) {
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
