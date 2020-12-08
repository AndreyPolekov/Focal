package Model.Data.Fixtures;

import Model.Data.Fixtures.Matches.Match;

import java.util.ArrayList;

public class Matchday {


    private ArrayList<Match> matches = new ArrayList<Match>();

    public ArrayList<Match> getMatches() {
        return matches;
    }
    @Override
    public String toString() {
        String s = "Matchday";
        for (Match match:
                matches) {
            s = s + "\n" + match;
        }
        return s;
    }
}
