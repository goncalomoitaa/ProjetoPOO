package objects;

import pt.iscte.poo.game.Room;

public interface Interaction {
    public void interact(Manel manel, Room r);

    public String getInteractionMessage();
 }
