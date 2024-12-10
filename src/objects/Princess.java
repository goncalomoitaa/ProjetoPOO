package objects;

import pt.iscte.poo.utils.Point2D;

import java.awt.*;

public class Princess extends InteractiveElements {

    private static Princess singlePrincess;
    private boolean wasRescued;

    private Princess(int x, int y) {
        super(x, y);
        wasRescued = false;
    }

    public static Princess getSinglePrincess() {
        if (singlePrincess == null) {
            singlePrincess = new Princess(0, 0);
        }
        return singlePrincess;
    }

    public static Princess getSinglePrincess(int x, int y) {
        if(singlePrincess == null) {
            singlePrincess = new Princess(x, y);
        }
        singlePrincess.setPosition(new Point2D(x, y));
        return singlePrincess;
    }

    public boolean getWasRescued() {
        return wasRescued;
    }

    public void setWasRescued(boolean wasRescued) {
        this.wasRescued = wasRescued;
    }

    @Override
    public String getName() {
        return "Princess";
    }

    @Override
    public int getLayer() {
        return 2;
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
