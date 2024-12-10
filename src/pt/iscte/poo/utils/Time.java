package pt.iscte.poo.utils;

public class Time {

    private final int sec; //como Time é um objeto imutável os seus atributos terão que ser final para não poderem ser modificados
    private final int min;

    public Time(int sec, int min) {
        if((sec < 0 || sec > 59) || min < 0)
            throw new IllegalArgumentException();
        this.sec = sec;
        this.min = min;
    }

    public Time(int sec) {
        this(Math.max(0, sec) % 60, Math.max(0, sec) / 60);
    }

    public Time(String secString) {
        String[] split = secString.split(":");
        if(split.length != 2)
            throw new IllegalArgumentException();

        this.sec = Integer.parseInt(split[1]);
        this.min = Integer.parseInt(split[0]);
    }

    public int getSec() {
        return sec;
    }

    public int getMin() {
        return min;
    }

    public int getTotalSec() {
        return sec + 60 * min;
    }

    public Time addTime(Time other) {
        int sec = getTotalSec() + other.getTotalSec();
        return new Time(sec);
    }

    public Time subTime(Time other) {
        int sec = getTotalSec() - other.getTotalSec();
        return new Time(sec);
    }

    public boolean isLessThan(Time other) {
        if(this.getTotalSec() < other.getTotalSec()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBiggerThan(Time other) {
        if(this.getTotalSec() > other.getTotalSec()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return getMin() + ":" + getSec() / 10 + getSec() % 10;
    }


}
