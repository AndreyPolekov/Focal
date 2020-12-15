package Model.Parsers;

import Model.Data.Fixtures.Matches.FutureMatch;
import Model.Data.Fixtures.Matches.PastMatch;
import Model.Data.Table.Club;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MatchParser extends Parser {
    private FutureMatch futureMatch;

    public void setMath(FutureMatch futureMatch) {
        this.futureMatch = futureMatch;
    }
    public void parse() {
        try {
            Document document = Jsoup.connect(getTournamentUrl()).get();

            futureMatch.setJointMatches(new ArrayList<PastMatch>());
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
            switch (club.name) {
                case "Real Sociedad":
                    return "Real_Sociedad_San_Sebastian";
                case "Atletico Madrid":
                    return "Atletico_Madrid";
                case "Real Madrid":
                    return "Real_Madrid";
                case "Villarreal":
                    return "Villarreal_CF";
                case "FC Sevilla":
                    return "FC_Sevilla";
                case "Granada":
                    return "Granada_CF";
                case "Cadiz":
                    return "Cadiz_CF";
                case "FC Barcelona":
                    return "FC_Barcelona";
                case "Celta Vigo":
                    return "Real_Celta_de_Vigo";
                case "Real Betis":
                    return "Real_Betis_Sevilla";
                case "Eibar":
                    return "SD_Eibar";
                case "Valencia":
                    return "FC_Valencia";
                case "Athletic Bilbao":
                    return "Athletic_Bilbao";
                case "Elche":
                    return "Elche_CF";
                case "Alaves":
                    return "CD_Alaves_Vitoria";
                case "Getafe":
                    return "Getafe_CF";
                case "Valladolid":
                    return "Real_Valladolid";
                case "Levante":
                    return "Levante_UD_Valencia";
                case "Huesca":
                    return "SD_Huesca";
                case "Osasuna":
                    return "Atletico_Osasuna_Pamplona";
            }
        }
        if (tournamentName.equals("Bundesliga")) {
            switch (club.name) {
                case "Leverkusen":
                    return "Bayer_Leverkusen";
                case "Bayern Munich":
                    return "Bayern_Muenchen";
                case "RB Leipzig":
                    return "RB_Leipzig";
                case "Wolfsburg":
                    return "VfL_Wolfsburg";
                case "Dortmund":
                    return "Borussia_Dortmund";
                case "Union Berlin":
                    return "Union_Berlin";
                case "Stuttgart":
                    return "VfB_Stuttgart";
                case "Monchengladbach":
                    return "Borussia_Moenchengladbach";
                case "E. Frankfurt":
                    return "Eintracht_Frankfurt";
                case "FC Augsburg":
                    return "FC_Augsburg";
                case "Hertha Berlin":
                    return "Hertha_BSC_Berlin";
                case "Hoffenheim":
                    return "1899_Hoffenheim";
                case "Werder Bremen":
                    return "Werder_Bremen";
                case "Freiburg":
                    return "Freiburger_SC";
                case "FC Koln":
                    return "1_FC_Koeln";
                case "Bielefeld":
                    return "DSC_Arminia_Bielefeld";
                case "FSV Mainz":
                    return "FSV_Mainz";
                case "Schalke 04":
                    return "Schalke_04";
            }
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
