package pt.iscte.poo.game;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import pt.iscte.poo.tools.Logger;

public class RoomFile {
    String absFilePath;
    Logger logger = Logger.getLogger();

    public File file() {
        return new File(this.absFilePath);
    }

    public RoomFile(String absFilePath) { this.absFilePath = absFilePath; }

    public String getAbsFilePath() { return absFilePath; }

    public static LinkedList<RoomFile> listaSalas() {
        return listaSalas("rooms/");
    }

    public static LinkedList<RoomFile> listaSalas(String dirName) {
        File dir = new File(dirName);
        LinkedList<RoomFile> roomFiles = new LinkedList<>();

        for (File roomFile : ListaDiretorios.collectFiles(dir)) {
            roomFiles.add(new RoomFile(roomFile.getAbsolutePath()));
        }
        return roomFiles;
    }

    private class ListaDiretorios {
        public static ArrayList<File> collectFiles(File dir) {
            ArrayList<File> list = new ArrayList<>();
            collectFilesRec(dir, list);

            list.sort((File f1, File f2) -> String.CASE_INSENSITIVE_ORDER.compare(f1.getName(), f2.getName()));
            return list;
        }

        private static void collectFilesRec(File f, ArrayList<File> list) {
            list.add(f);

            if(f.isDirectory())
                for(File child : f.listFiles())
                    collectFilesRec(child, list);
        }
    }

}
