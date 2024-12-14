package pt.iscte.poo.game;

import java.io.File;

public class DoorDestination {
    private String doorDestinationFile;

    public DoorDestination(String dest) {
        this.doorDestinationFile = dest;
    }

    public RoomFile getDoorDestinationFile() { return new RoomFile(doorDestinationFile); }
}
