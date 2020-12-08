package Model.Ratings;

import Model.Data.Fixtures.Matches.PastMatch;
import Model.Data.Table.Club;

public class CurrentClubRating extends ClubRating {
    private static double pointsParameter = 2.8;
    private static double averagePointsParameter = 1.7;
    private static double goalsParameter = 20.0;

    public CurrentClubRating(Club club) {
        super(club);
    }
    public int calculate() {
        double averagePoints = 0;
        double averageGoalsFor = 0;
        double averageGoalsAgainst = 0;
        for (PastMatch match : club.lastMatches) {
            if (match.getHost().name.equals(club.name)) {
                averageGoalsFor += match.getHostGoals();
                averageGoalsAgainst += match.getGuestGoals();
                if (match.getHostGoals() > match.getGuestGoals()) {
                    averagePoints += 3;
                }
            }
            else {
                averageGoalsFor += match.getGuestGoals();
                averageGoalsAgainst += match.getHostGoals();
                if (match.getHostGoals() < match.getGuestGoals()) {
                    averagePoints += 3;
                }
            }
            if (match.getHostGoals() == match.getGuestGoals()) {
                averagePoints += 1;
            }
        }
//        System.out.println(
//                club + "\n" +
//                        averagePoints + "\t" +
//                        averageGoalsFor + "\t" +
//                        averageGoalsAgainst + "\n"
//        );

        averagePoints /= Club.lastMatchesCount;
        averageGoalsFor /= Club.lastMatchesCount;
        averageGoalsAgainst /= Club.lastMatchesCount;

//        System.out.println(
//                ((averagePoints + pointsParameter) / (averagePointsForSavingRating + pointsParameter)) + "\t" +
//        (averageGoalsFor + goalsParameter) / (averageGoalsAgainst + goalsParameter) + "\t" +
//
//                        (int)
//                                ((double)club.globalRating *
//                                        ((averagePoints + pointsParameter) / (averagePointsForSavingRating + pointsParameter)) *
//                                        ((averageGoalsFor + goalsParameter) / (averageGoalsAgainst + goalsParameter)))
//        );

        return (int)
                ((double)club.globalRating *
                ((averagePoints + pointsParameter) / (averagePointsParameter + pointsParameter)) *
                ((averageGoalsFor + goalsParameter) / (averageGoalsAgainst + goalsParameter)));
    }
}
