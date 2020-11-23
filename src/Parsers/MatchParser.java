package Parsers;

import Data.Fixtures.Matches.FutureMatch;
import Data.Fixtures.Matches.PastMatch;
import Data.Table.Club;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MatchParser extends Parser {
    private FutureMatch futureMatch;

    public void setMath(FutureMatch futureMatch) {
        this.futureMatch = futureMatch;
    }
    public void parse() {
        try {
            Document document = Jsoup.connect(getTournamentUrl()).get();
            int matchIndex = 0;
            for (Element matchElement :
                    document
                            .select("table[align=\"center\"][width=\"100%\"][class=\"championship\"][cellpadding=\"1\"][cellspacing=\"0\"]")
                            .get(0)
                            .select("tbody")
                            .get(0)
                            .select("tr")
            ) {
                Elements columnElements =
                        matchElement
                                .select("td");
                if (columnElements.size() != 8)
                    continue;
                String result = columnElements.get(7).text();
                if (result.length() < 3)
                    continue;

                PastMatch pastMatch = new PastMatch();
                pastMatch.setHostGoals((int)result.charAt(0) - (int)'0');
                pastMatch.setGuestGoals((int)result.charAt(2) - (int)'0');
                pastMatch.setDate(
                        columnElements
                                .get(1)
                                .select("a")
                                .get(0)
                                .text()
                );
                Club host = null, guest = null;
                String hostName =
                        columnElements
                                .get(3)
                                .select("a")
                                .get(0)
                                .text();
                String guestName =
                        columnElements
                                .get(5)
                                .select("a")
                                .get(0)
                                .text();
                int[] numbers = {
                        Club.getEqualCharCount(hostName, futureMatch.getHost().name),
                        Club.getEqualCharCount(hostName, futureMatch.getGuest().name),
                        Club.getEqualCharCount(guestName, futureMatch.getHost().name),
                        Club.getEqualCharCount(guestName, futureMatch.getGuest().name),
                };
                if (numbers[0] >= numbers[2] && numbers[3] >= numbers[1]) {
                    host = futureMatch.getHost();
                    guest = futureMatch.getGuest();
                }
                if (numbers[1] >= numbers[3] && numbers[2] >= numbers[0]) {
                    host = futureMatch.getGuest();
                    guest = futureMatch.getHost();
                }
                pastMatch.setHost(host);
                pastMatch.setGuest(guest);

                futureMatch.getJointMatches().add(pastMatch);
                matchIndex++;
                if (matchIndex == FutureMatch.getJointMatchesCount()) {
                    break;
                }
            }

            if (next != null)
                next.parse();
        } catch (Exception e) {
            System.out.println("Wildstat exception");
            e.printStackTrace();
        }
    }
    public String getClubUrlPart(Club club) {
        if (tournamentName.equals("EnglishPremierLeague")) {
            switch (club.name) {
                case "Tottenham":
                    return "Tottenham_Hotspur";
                case "Liverpool":
                    return "Liverpool_FC";
                case "Chelsea":
                    return "Chelsea_London";
                case "Leicester City":
                    return "Leicester_City";
                case "Southampton":
                    return "Southampton";
                case "Everton":
                    return "Everton_Liverpool";
                case "Aston Villa":
                    return "Aston_Villa";
                case "West Ham Utd":
                    return "West_Ham_United";
                case "Crystal Palace":
                    return "Crystal_Palace";
                case "Manchester Utd":
                    return "Manchester_United";
                case "Arsenal":
                    return "Arsenal_London";
                case "Wolverhampton":
                    return "Wolverhampton_Wanderers";
                case "Manchester City":
                    return "Manchester_City";
                case "Leeds Utd":
                    return "Leeds_United";
                case "Newcastle Utd":
                    return "Newcastle_United";
                case "Brighton":
                    return "Brighton_Hove_Albion_FC";
                case "Fulham":
                    return "Fulham";
                case "West Brom":
                    return "West_Bromwich_Albion";
                case "Burnley":
                    return "Burnley";
                case "Sheffield Utd":
                    return "Sheffield_United";
            }
        }
        if (tournamentName.equals("LaLiga")) {

        }
        if (tournamentName.equals("Bundesliga")) {

        }
        return null;
    }
    public String getTournamentUrl() {
        String tournamentPart1 = null, tournamentPart2 = null, hostPart = null, guestPart = null;
        if (tournamentName.equals("EnglishPremierLeague")) {
            tournamentPart1 = "23";
            tournamentPart2 = "ENG";
        }
        if (tournamentName.equals("LaLiga")) {
            tournamentPart1 = "24";
            tournamentPart2 = "ESP";
        }
        if (tournamentName.equals("Bundesliga")) {
            tournamentPart1 = "25";
            tournamentPart2 = "GER";
        }
        return "http://wildstat.com/p/" + tournamentPart1 + "01/ch/all" +
                "/club1/" + tournamentPart2 + "_" + getClubUrlPart(futureMatch.getHost()) +
                "/club2/" + tournamentPart2 + "_" + getClubUrlPart(futureMatch.getGuest());
    }
}
