package Data.Table;

import Data.Fixtures.Matches.Match;

import java.util.ArrayList;

public class Club {
    //soccerstats
    public String name;
    public int place;
    public int matches;
    public int wins;
    public int draws;
    public int loses;
    public int goalsFor;
    public int goalsAgainst;
    public int goalsDifference;
    public int points;

    //clubelo
    public int eloPoints;

    //uefa
    public int uefaPoints = 0;

    //1vs1
    public int indexPoints = 0;

    public static int lastMatchesCount = 4;
    public ArrayList<Match> lastMatches = new ArrayList<Match>();


    @Override
    public String toString() {
        String s =
                name + "\n" +
                place +
                "\t" + matches +
                "\t" + wins +
                "\t" + draws +
                "\t" + loses +
                "\t" + goalsFor +
                "\t" + goalsAgainst +
                "\t" + goalsDifference +
                "\t" + points +
                "\t" + eloPoints +
                "\t" + uefaPoints +
                "\t" + indexPoints;
        for (Match m :
                lastMatches) {
            s = s + "\n" + m;
        }
        return s;
    }
    public static int getEqualCharCount(String s1, String s2) {
        int equalCharCount = 0;
        String stringToCheck;
        StringBuilder stringToFind;
        if (s1.length() < s2.length()) {
            stringToCheck = s1;
            stringToFind = new StringBuilder(s2);
        }
        else {
            stringToCheck = s2;
            stringToFind = new StringBuilder(s1);
        }
        for (int i = 0; i < stringToCheck.length(); i++) {
            for (int j = 0; j < stringToFind.length(); j++) {
                if (stringToCheck.charAt(i) == stringToFind.charAt(j)) {
                    equalCharCount++;
                    stringToFind.deleteCharAt(j);
                    break;
                }
            }
        }
        return equalCharCount;
    }

}
