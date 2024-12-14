package pt.iscte.poo.game;

import pt.iscte.poo.utils.Time;

import java.io.*;
import java.util.*;

public class ScoreBoard {

    private static ScoreBoard scoreBoard;
    private PriorityQueue<Time> bestTimes;
    private Comparator<Time> timeComparator = (a, b) -> a.getTotalSec() - b.getTotalSec();

    private ScoreBoard() {
        bestTimes = new PriorityQueue<>(10, timeComparator);
    }

    public static ScoreBoard getScoreBoard() {
        if (scoreBoard == null) {
            scoreBoard = new ScoreBoard();
        }
        return scoreBoard;
    }

    public void addBestTime(Time bestTime) {
        bestTimes.offer(bestTime);
    }

    public void saveScore() {
        try {
            PrintWriter pw = new PrintWriter(new File("ScoreBoard.txt"));
            String s = "";
            for (Time time : bestTimes) {
                s = s.concat(time.toString()).concat("\n");
            }
            pw.print(s);
            pw.close();
        } catch (FileNotFoundException e) {
            System.err.println("Erro na escrita do ficheiro");
        }
    }

    public void LoadScores() {
        try {
            Scanner sc = new Scanner(new File("ScoreBoard.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.isEmpty()) continue;
                addBestTime(new Time(line));
            }
            sc.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
