package pt.iscte.poo.game;

import pt.iscte.poo.utils.Time;

import java.io.*;
import java.util.*;

public class ScoreBoard {

    private static ScoreBoard scoreBoard;
    private PriorityQueue<Time> sortedTimes;
    private Comparator<Time> timeComparator = (a, b) -> a.getTotalSec() - b.getTotalSec();
    //private List<Time> bestTimes;

    private ScoreBoard() {
        sortedTimes = new PriorityQueue<>(timeComparator);
        //bestTimes = new ArrayList<>();
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

    public List<String> saveScore() {
        try {
            PrintWriter pw = new PrintWriter("ScoreBoard.txt");
            List<String> listScore = new ArrayList<>();
            int counter = 0;
            while(!sortedTimes.isEmpty() && counter < 10) {
                listScore.add(sortedTimes.poll().toString());
                pw.println(listScore.getLast());
                counter++;
            }
            pw.close();
            return listScore;
        } catch (FileNotFoundException e) {
            System.err.println("Erro na escrita do ficheiro");
        }
        return null;
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

    public String saveAndDisplay() {
        String text = "==============TOP TIMES==============\n";
        List<String> newList = saveScore();
        int classification = 1;
        for (String s : newList) {
            text += classification + " : " + s + "\n";
            classification++;
        }
        return text;
    }

}
