package Model.Parsers;

import Model.Data.Fixtures.Fixtures;
import Model.Data.Fixtures.Matchday;
import Model.Data.Fixtures.Matches.FutureMatch;
import Model.Data.Fixtures.Matches.Match;
import Model.Data.Fixtures.Matches.PastMatch;
import Model.Data.Table.Club;
import Model.Data.Table.Table;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class FixturesParser extends Parser {
    private Fixtures fixtures;
    private Table table;

    public void setTable(Table table) {
        this.table = table;
    }
    public void setFixtures(Fixtures fixtures) {
        this.fixtures = fixtures;
    }

    public void parse() {
        try {
            Document document = Jsoup.connect(getTournamentUrl()).get();
            boolean isCurrentMatchdayFound = false;
            int externalMatchdayIndex = 0;
            int internalMatchdayIndex = 0;
            int matchdaySize = table.getClubs().size() / 2;
            Matchday matchday = new Matchday();
            Elements stringElements =
                    document
                            .select("table[cellpadding=\"1\"][cellspacing=\"0\"][width=\"100%\"][style=\"margin-left:10px;\"]")
                            .get(0)
                            .select("tbody")
                            .get(0)
                            .select("tr");
            stringElements.remove(0);
            stringElements.remove(0);
            for (Element stringElement : stringElements) {
                Match match;
                Elements columnElements = stringElement.select("td");
                if (columnElements.size() == 1)
                    continue;
                String column2 = columnElements.get(2).text();
                if (column2.contains("-")) {
                    match = new PastMatch();
                    ((PastMatch)match).setHostGoals(Integer.parseInt(column2.substring(0, column2.indexOf(' '))));
                    ((PastMatch)match).setGuestGoals(Integer.parseInt(column2.substring(column2.lastIndexOf(' ') + 1, column2.length())));
                }
                else {
                    match = new FutureMatch();
                    if (column2.equals("pp.")){
                        ((FutureMatch)match).setPostpone(true);
                    }
                    else {
                        ((FutureMatch)match).setTime(column2);
                        if (!isCurrentMatchdayFound) {
                            isCurrentMatchdayFound = true;
                            fixtures.setCurrentMatchday(externalMatchdayIndex);
                        }
                    }
                }
                match.setDate(columnElements.get(0).text());
                match.setHost(table.defineClub(columnElements.get(1).text()));
                match.setGuest(table.defineClub(columnElements.get(3).text()));

                matchday.getMatches().add(match);
                internalMatchdayIndex++;
                if (internalMatchdayIndex == matchdaySize) {
                    internalMatchdayIndex = 0;
                    externalMatchdayIndex++;
                    fixtures.getMatchdays().add(matchday);
                    matchday = new Matchday();
                }
            }

            ArrayList<Matchday> matchdays = fixtures.getMatchdays();
            for (Club club :  table.getClubs()) {
                int matchIndex = 0;
                for (int matchdayIndex = fixtures.getCurrentMatchday(); matchdayIndex >= 0; matchdayIndex--) {
                    ArrayList<Match> matches = matchdays.get(matchdayIndex).getMatches();
                    for (Match match : matches) {
                        if (    match.getHost().name.equals(club.name) ||
                                match.getGuest().name.equals(club.name)
                        ) {
                            if (match instanceof PastMatch) {
                                club.lastMatches.add((PastMatch)match);
                                matchIndex++;
                            }
                            break;
                        }
                    }
                    if (matchIndex == Club.lastMatchesCount)
                        break;
                }
            }

            if (next != null)
                next.parse();
        } catch (Exception e) {
            System.out.println("Soccerstats exception 2");
            e.printStackTrace();
        }
    }
    public String getTournamentUrl() {
        String tournamentUrlPart = null;
        if (tournamentName.equals("EnglishPremierLeague"))
            tournamentUrlPart = "england";
        if (tournamentName.equals("LaLiga"))
            tournamentUrlPart = "spain";
        if (tournamentName.equals("Bundesliga"))
            tournamentUrlPart = "germany";
        return "https://www.soccerstats.com/results.asp?league=" + tournamentUrlPart + "&pmtype=bydate";
    }
}
