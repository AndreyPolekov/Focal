package Model.Data.Fixtures;

import java.util.ArrayList;

public class Fixtures {


    private ArrayList<Matchday> matchdays = new ArrayList<Matchday>();




    private int currentMatchday;    //index

    public ArrayList<Matchday> getMatchdays() {
        return matchdays;
    }

    @Override
    public String toString() {
        String s = "Fixtures";
        for (Matchday matchday:
                matchdays) {
            s = s + "\n" + matchday;
        }
        return s;
    }
    public void setCurrentMatchday(int currentMatchday) {
        this.currentMatchday = currentMatchday;
    }
    public int getCurrentMatchday() {
        return currentMatchday;
    }
}
