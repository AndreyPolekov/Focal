package Model.Parsers;

import Model.Data.Table.Club;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StatisticsParser extends TableParser {
    public void parse() {
        try {
            Document document = Jsoup.connect(getTournamentUrl()).get();
            for (Element clubElement:
                    document
                            .select("table[cellspacing='0'][cellpadding='2'][width='100%']")
                            .get(3)
                            .select("tr[class='odd'][height='22']")
            ) {
                Club club = new Club();
                Elements parameterElements = clubElement.select("td");
                for (int parameterIndex = 0; parameterIndex < 10; parameterIndex++) {
                    String text = parameterElements.get(parameterIndex).text();
                    int number = 0;
                    if (parameterIndex != 1)
                        number = Integer.parseInt(text);
                    switch (parameterIndex) {
                        case 0:
                            club.place = number;
                            break;
                        case 1:
                            club.name = text;
                            break;
                        case 2:
                            club.matches = number;
                            break;
                        case 3:
                            club.wins = number;
                            break;
                        case 4:
                            club.draws = number;
                            break;
                        case 5:
                            club.loses = number;
                            break;
                        case 6:
                            club.goalsFor = number;
                            break;
                        case 7:
                            club.goalsAgainst = number;
                            break;
                        case 8:
                            club.goalsDifference = number;
                            break;
                        case 9:
                            club.points = number;
                            break;
                    }
                }
                table.getClubs().add(club);
            }

            if (next != null)
                next.parse();
        } catch (Exception e) {
            System.out.println("Soccerstats exception");
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
        return "https://www.soccerstats.com/latest.asp?league=" + tournamentUrlPart;
    }
}
