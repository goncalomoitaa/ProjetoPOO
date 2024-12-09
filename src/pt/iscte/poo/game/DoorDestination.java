package pt.iscte.poo.game;

import java.io.File;

public class DoorDestination {
    private String doorDestinationFile;

    public DoorDestination(String dest) {
        this.doorDestinationFile = dest;
    }

    public File getDoorDestinationFile() {
        return new File(doorDestinationFile);
    }
}