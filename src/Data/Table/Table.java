package Data.Table;

import java.util.ArrayList;


public class Table {
    private ArrayList<Club> clubs = new ArrayList<Club>();



    private int uefaPoints;

    public ArrayList<Club> getClubs() {
        return clubs;
    }


    public Club defineClub(String clubName) {
        int maxEqualCharCount = 0;
        int[] equalCharCount = new int[clubs.size()];

        for (int i = 0; i < clubs.size(); i++) {
            if (clubName.equals(clubs.get(i).name))
                return clubs.get(i);
            equalCharCount[i] = Club.getEqualCharCount(clubName, clubs.get(i).name);
            if (equalCharCount[i] > maxEqualCharCount)
                maxEqualCharCount = equalCharCount[i];
        }
        for (int i = 0; i < clubs.size(); i++) {
            if (equalCharCount[i] == maxEqualCharCount)
                return clubs.get(i);
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "Table";
        for (Club club:
             clubs) {
            s = s + "\n" + club;
        }
        return s;
    }
    public void setUefaPoints(int uefaPoints) {
        this.uefaPoints = uefaPoints;
    }
}