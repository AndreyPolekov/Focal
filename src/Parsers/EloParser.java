package Parsers;

import Data.Table.Club;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EloParser extends TableParser {
    public void parse() {
        try {
            Document document = Jsoup.connect(getTournamentUrl()).get();
            Elements stringElements =
                    document
                            .select("table[class=\"liste\"]")
                            .get(0)
                            .select("tr");
            for (Element stringElement : stringElements) {
                if (stringElement
                        .select("td")
                        .get(0)
                        .text().equals(getCountry())
                ) {
                    int index = stringElement.siblingIndex();
                    index += 2;
                    for (int i = 0; i < table.getClubs().size(); i++) {
                        String clubName =
                            stringElements
                                .get(index)
                                .select("td")
                                .get(0)
                                .select("a")
                                .get(0)
                                .text();
                        Club club = table.defineClub(clubName);
                        club.eloPoints = Integer.parseInt(
                                stringElements
                                        .get(index)
                                        .select("td")
                                        .get(1)
                                        .text()
                        );
                        index++;
                    }
                    break;
                }
            }

            if (next != null)
                next.parse();
        } catch (Exception e) {
            System.out.println("Clubelo exception");
            e.printStackTrace();
        }
    }
    public String getTournamentUrl() {
        String tournamentUrlPart = null;
        if (tournamentName.equals("EnglishPremierLeague"))
            tournamentUrlPart = "ENG";
        if (tournamentName.equals("LaLiga"))
            tournamentUrlPart = "spain";
        if (tournamentName.equals("Bundesliga"))
            tournamentUrlPart = "germany";
        return "http://clubelo.com/" + tournamentUrlPart;
    }
    public String getCountry() {
        if (tournamentName.equals("EnglishPremierLeague"))
            return "England";
        if (tournamentName.equals("LaLiga"))
            return "Spain";
        if (tournamentName.equals("Bundesliga"))
            return "Germany";
        return null;
    }
}
