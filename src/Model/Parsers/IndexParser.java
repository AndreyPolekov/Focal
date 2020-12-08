package Model.Parsers;

import Model.Data.Table.Club;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class IndexParser extends TableParser {
    public void parse() {
        try {
            Document document = Jsoup.connect(getTournamentUrl()).get();
            Elements tournamentElements =
                    document
                            .select("div[class=\"teams-page-blocks\"]")
                            .get(0)
                            .select("div");
            for (Element tournamentElement : tournamentElements) {
                if (    tournamentElement.attr("class").equals("teams-league-title d-md-flex d-sm-block") &&
                        tournamentElement.text().contains(getTournamentName())
                ) {
                    for (Element clubElement:
                            tournamentElements
                                    .get(tournamentElement.siblingIndex() + 2)
                                    .select("div[class=\"team\"]")
                    ) {
                        System.out.println(clubElement.text());////////////////////////
                        parseClub(clubElement.text());
                    }
                    break;
                }
            }

            if (next != null)
                next.parse();
        } catch (Exception e) {
            System.out.println("1vs1 exception");
            e.printStackTrace();
        }
    }
    public void parseClub(String clubName) throws IOException {
        Document document = Jsoup.connect(getClubUrl(clubName)).get();
        System.out.println("+");

        if (clubName.equals("Tottenham Hotspur"))
            clubName = "Tottenham";
        if (clubName.equals("Arsenal FC"))
            clubName = "Arsenal";
        if (clubName.equals("Brighton and Hove Albion"))
            clubName = "Brighton";
        if (clubName.equals("West Bromwich Albion"))
            clubName = "West Brom";

        Club club = table.defineClub(clubName);
        club.indexPoints = Integer.parseInt(
                document
                        .select("div[class=\"pro-profile-tab-num\"][id=\"indexCounter\"]")
                        .get(0)
                        .text()
        );
    }
    public String getTournamentUrl() {
        return "https://one-versus-one.com/en/teams#league-39";
    }
    public String getClubUrl(String clubName) {
        return "https://one-versus-one.com/en/teams/" + clubName.toLowerCase().replace(' ', '-');
    }
    public String getTournamentName() {
        if (tournamentName.equals("EnglishPremierLeague"))
            return "Premier League";
        if (tournamentName.equals("LaLiga"))
            return "La Liga";
        if (tournamentName.equals("Bundesliga"))
            return "Bundesliga";
        return null;
    }
}
