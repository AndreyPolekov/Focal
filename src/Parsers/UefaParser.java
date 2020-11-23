package Parsers;

import Data.Table.Club;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class UefaParser extends TableParser {
    public void parse() {
        try {
            boolean firstClub = true;
            int tournamentRating = 0;
            Document document = Jsoup.connect(getTournamentUrl()).get();
            for (Element stringElement :
                    document
                            .select("tbody")
                            .get(2)
                            .select("tr")
            ) {
                if (stringElement.select("th").size() != 1)
                    continue;
                if (getCountry().equals(
                        stringElement
                                .select("td")
                                .get(3)
                                .text()
                )) {
                    if (firstClub) {
                        firstClub = false;
                        tournamentRating = (int)(1000 * Float.parseFloat(
                                stringElement
                                        .select("td,th")
                                        .get(10)
                                        .text()));
                        table.setUefaPoints(tournamentRating);
                    }
                    int uefaPoints = (int)(1000 * Float.parseFloat(
                            stringElement
                                    .select("td,th")
                                    .get(9)
                                    .text()));
                    if (uefaPoints <= tournamentRating) {
                        break;
                    }
                    String clubName =
                            stringElement
                                    .select("td")
                                    .get(2)
                                    .text();

                    if (clubName.equals("Tottenham Hotspur"))
                        clubName = "Tottenham";

                    Club club = table.defineClub(clubName);
                    club.uefaPoints = uefaPoints;
                }
            }

            if (next != null)
                next.parse();
        } catch (Exception e) {
            System.out.println("Kassiesa exception");
            e.printStackTrace();
        }
    }
    public String getTournamentUrl() {
        return "https://kassiesa.net/uefa/data/method5/trank2020.html";
    }
    public String getCountry() {
        if (tournamentName.equals("EnglishPremierLeague"))
            return "Eng";
        if (tournamentName.equals("LaLiga"))
            return "Esp";
        if (tournamentName.equals("Bundesliga"))
            return "Ger";
        return null;
    }
}
