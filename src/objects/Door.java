package objects;

public class Door extends  ElementosInterativos {
    public Door(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "DoorClosed";
    }

    @Override
    public int getLayer() {
        return 0;
    }

    @Override
    public void efeitos() {

    }

}
