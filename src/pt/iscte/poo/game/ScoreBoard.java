package pt.iscte.poo.game;

import pt.iscte.poo.utils.Time;

import java.io.*;
import java.util.*;

public class ScoreBoard {

    private static ScoreBoard scoreBoard;
    private PriorityQueue<Player> sortedTimes;
    private Comparator<Player> timeComparator = (a, b) -> a.getTime().getTotalSec() - b.getTime().getTotalSec();

    private ScoreBoard() {
        sortedTimes = new PriorityQueue<>(timeComparator);
    }

    public static ScoreBoard getScoreBoard() {
        if (scoreBoard == null) {
            scoreBoard = new ScoreBoard();
        }
        return scoreBoard;
    }

    public void addBestTime(String nickName, Time bestTime) {
        sortedTimes.offer(new Player(nickName, bestTime));
    }

    public void saveScore() {
        try {
            PrintWriter pw = new PrintWriter("ScoreBoard.txt");
            int counter = 0;
            while(!sortedTimes.isEmpty() && counter < 10) {
                Player player = sortedTimes.poll();
                pw.println(player.getNickName() + " " + player.getTime());
                counter++;
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.err.println("Erro na escrita do ficheiro");
        }
    }

    public void loadScores() {
        try {
            Scanner sc = new Scanner(new File("ScoreBoard.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split(" ");
                String nickName = split[0];
                Time time = new Time(split[1]);
                if(line.isEmpty()) continue;
                addBestTime(nickName, time);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("Erro na abertura do ficheiro");
        }
    }

    public String saveAndDisplay() {
        try {
            Scanner sc = new Scanner(new File("ScoreBoard.txt"));
            String bestTimes = "==============TOP TIMES==============\n";
            int classification = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split(" ");
                String name = split[0];
                String time = split[1];
                bestTimes += classification + " :    " + "NICKNAME : " + name + "    " + time + "sec\n";
                classification++;
            }
            sc.close();
            return bestTimes;
        } catch (FileNotFoundException e) {
            System.err.println("Erro na abertura do ficheiro");
        }
        return null;
    }

    public static class Player {

        private final String nome;
        private final Time time;

        public Player(String nome, Time time) {
            this.nome = nome;
            this.time = time;
        }

        public String getNickName() {
            return nome;
        }

        public Time getTime() {
            return time;
        }
    }

}
