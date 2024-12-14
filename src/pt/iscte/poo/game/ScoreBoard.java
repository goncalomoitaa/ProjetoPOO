package pt.iscte.poo.game;

import pt.iscte.poo.utils.Time;

import java.io.*;
import java.util.*;

public class ScoreBoard {

    private static ScoreBoard scoreBoard;
    private PriorityQueue<Time> sortedTimes;
    private Comparator<Time> timeComparator = (a, b) -> a.getTotalSec() - b.getTotalSec();
    private List<Time> bestTimes;

    private ScoreBoard() {
        sortedTimes = new PriorityQueue<>(timeComparator);
        bestTimes = new ArrayList<>();
    }

    public static ScoreBoard getScoreBoard() {
        if (scoreBoard == null) {
            scoreBoard = new ScoreBoard();
        }
        return scoreBoard;
    }

    public void addBestTime(Time bestTime) {
        sortedTimes.offer(bestTime);
    }

    public void saveScore() {
        try {
            PrintWriter pw = new PrintWriter("ScoreBoard.txt");
            for (Time time : sortedTimes) {
                pw.println(time);
            }
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

    public List<Time> getSortedTimes() {
        for (Time time : sortedTimes) {
            if(bestTimes.size() < 10) {
                bestTimes.add(time);
                bestTimes.sort(timeComparator);
            }
        }
        return bestTimes;
    }

    public String text() {
        String s = "";
        for (Time time : getSortedTimes()) {
            s = s.concat(time.toString()).concat("\n");
        }
        return s;
    }

}
