package Model.Data.Table;

import Model.Data.Fixtures.Matches.PastMatch;

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
    public String imageUrl;

    //ratings
    public int globalRating;
    public int currentRating;

    public static int lastMatchesCount = 4;
    public ArrayList<PastMatch> lastMatches = new ArrayList<PastMatch>();


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
                "\t" + indexPoints +
                "\t" + globalRating +
                "\t" + currentRating +
                "\t" + imageUrl;
//        for (Match m :
//                lastMatches) {
//            s = s + "\n" + m;
//        }
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

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public int getMatches() {
        return matches;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLoses() {
        return loses;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalsDifference() {
        return goalsDifference;
    }

    public int getPoints() {
        return points;
    }
}
