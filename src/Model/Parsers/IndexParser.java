package Model.Parsers;

import Model.Data.Table.Club;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class IndexParser extends TableParser {
    public void parse() {
        try {
            Document document = Jsoup.connect(getTournamentUrl()).get();

            int tournamentIndex = 0;
            for (Element tournamentElement :
                    document
                            .select("div[class=\"teams-page-blocks\"]")
                            .get(0)
                            .select("div[class=\"teams-league-title d-md-flex d-sm-block\"]")
            ) {
                if (tournamentElement.text().contains(getTournamentName())) {
                    break;
                }
                tournamentIndex++;
            }
            for (Element clubElement :
                    document
                            .select("div[class=\"teams-page-blocks\"]")
                            .get(0)
                            .select("div[class=\"teams-blocks\"]")
                            .get(tournamentIndex)
                            .select("div[class=\"team\"]")
            ) {
                parseClub(clubElement);
            }
            if (next != null)
                next.parse();
        } catch (Exception e) {
            System.out.println("1vs1 exception");
            e.printStackTrace();
        }
    }
    public void parseClub(Element clubElement) throws IOException {
        String clubName = clubElement.text();
        //Document document = Jsoup.connect(getClubUrl(clubName)).get();

        if (clubName.equals("Tottenham Hotspur"))
            clubName = "Tottenham";
        if (clubName.equals("Arsenal FC"))
            clubName = "Arsenal";
        if (clubName.equals("Brighton and Hove Albion"))
            clubName = "Brighton";
        if (clubName.equals("West Bromwich Albion"))
            clubName = "West Brom";

        if (clubName.equals("Real Valladolid"))
            clubName = "Valladolid";
        if (clubName.equals("Deportivo Alaves"))
            clubName = "Alaves";
        if (clubName.equals("Valencia CF"))
            clubName = "Valencia";

        if (clubName.equals("Arminia Bielefeld"))
            clubName = "Bielefeld";

        Club club = table.defineClub(clubName);
//        club.indexPoints = Integer.parseInt(
//                document
//                        .select("div[class=\"pro-profile-tab-num\"][id=\"indexCounter\"]")
//                        .get(0)
//                        .text()
//        );
        club.imageUrl =
                clubElement
                    .select("div[class=\"img-container lazyload\"]")
                    .get(0)
                    .attr("data-bg");
    }
    public String getTournamentUrl() {
        return "https://one-versus-one.com/en/teams";
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
