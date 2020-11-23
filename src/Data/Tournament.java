package Data;

import Data.Fixtures.Fixtures;
import Data.Fixtures.Matches.FutureMatch;
import Data.Table.Table;
import Parsers.*;

public class Tournament {
    private String name;
    private Table table = new Table();
    private Fixtures fixtures = new Fixtures();

    public Tournament(String name) {
        this.name = name;

        Parser[] parserChain = {
                new StatisticsParser(),
                new EloParser(),
                new UefaParser(),
                //new IndexParser(),
                new FixturesParser()
        };
        for (int i = 0; i < parserChain.length; i++) {
            parserChain[i].setTournamentName(name);
            if (i < parserChain.length - 1) {
                parserChain[i].setNext(parserChain[i + 1]);
                ((TableParser)parserChain[i]).setTable(table);
            }
            else {
                ((FixturesParser)parserChain[i]).setFixtures(fixtures);
                ((FixturesParser)parserChain[i]).setTable(table);
            }
        }
        parserChain[0].parse();

        /////////////////
        System.out.println(
                (FutureMatch) fixtures.getMatchdays().get(9).getMatches().get(3) + "\n\n\n"
        );

        Parser p = new MatchParser();
        p.setTournamentName(name);
        ((MatchParser)p).setMath((FutureMatch) fixtures.getMatchdays().get(9).getMatches().get(3));
        p.parse();


        System.out.println(
                (FutureMatch) fixtures.getMatchdays().get(9).getMatches().get(3) + "\n\n\n"
        );
        ////////////////

        //System.out.println(table + "\n\n\n\n\n");      ////////////////////////////////////////////////
        //System.out.println(fixtures);      ////////////////////////////////////////////////
    }
}
