package Model.Ratings;

import Model.Data.Fixtures.Matches.FutureMatch;
import Model.Data.Fixtures.Matches.Match;
import Model.Data.Fixtures.Matches.PastMatch;

public class MatchRating implements Rating {
    private Match match;

    private static double goalsParameter = 1.7 / 7.0;

    private static double averageGoalsParameter = 0.2 / 5.0;
    private static double baseDrawParameter = 0.3 / 5000.0;
    private static double averagePointsDrawParameter = 0.3 / 0.5;
    private static double resultParameter = 0.2 / 0.5;
    private static double deltaAveragePointsParameter = 0.25 / 3.0;


    public MatchRating(Match match) {
        this.match = match;
    }
    public int calculate() {
        if (match instanceof FutureMatch) {
            double hostPercent = match.getHost().currentRating;
            double guestPercent = match.getGuest().currentRating;
            double drawPercent = (double)((match.getHost().currentRating + match.getGuest().currentRating) / 2) /
                    ((double)Math.abs(match.getHost().currentRating - match.getGuest().currentRating) * baseDrawParameter + 1.0);
            double hostAveragePoints = 0;
            double guestAveragePoints = 0;
            double averageGoals = 0;
            for (PastMatch pastMatch : ((FutureMatch) match).getJointMatches()) {
                averageGoals += pastMatch.getHostGoals();
                averageGoals += pastMatch.getGuestGoals();
                if (pastMatch.getHostGoals() == pastMatch.getGuestGoals()) {
                    hostAveragePoints += 1;
                    guestAveragePoints += 1;
                    continue;
                }
                if (match.getHost().name.equals(pastMatch.getHost().name)) {
                    if (pastMatch.getHostGoals() > pastMatch.getGuestGoals()) {
                        hostAveragePoints += 3;
                    }
                    else {
                        guestAveragePoints += 3;
                    }
                }
                else {
                    if (pastMatch.getHostGoals() > pastMatch.getGuestGoals()) {
                        guestAveragePoints += 3;
                    }
                    else {
                        hostAveragePoints += 3;
                    }
                }
            }
            hostAveragePoints /= FutureMatch.getJointMatchesCount();
            guestAveragePoints /= FutureMatch.getJointMatchesCount();
            averageGoals /= FutureMatch.getJointMatchesCount();

            double deltaAveragePoints = (hostAveragePoints - guestAveragePoints) * deltaAveragePointsParameter;
            if (deltaAveragePoints >= 0) {
                hostPercent *= deltaAveragePoints + 1;
            }
            else {
                guestPercent *= (-1.0) * deltaAveragePoints + 1;
            }

            drawPercent /= ((hostAveragePoints + guestAveragePoints) / 2 - 1) * averagePointsDrawParameter + 1;
            drawPercent /= averageGoals * averageGoalsParameter + 1;

            double percentSum = hostPercent + drawPercent + guestPercent;
            ((FutureMatch) match).setHostPercent((int)(hostPercent / percentSum * 100));
            ((FutureMatch) match).setDrawPercent((int)(drawPercent / percentSum * 100));
            ((FutureMatch) match).setGuestPercent((int)(guestPercent / percentSum * 100));

            return (int)
                    ((double)(match.getGuest().currentRating + match.getHost().currentRating) *
                            (averageGoals * goalsParameter + 1) *
                            (((hostAveragePoints + guestAveragePoints) / 2 - 1) * averagePointsDrawParameter + 1) / 10);
        }
        else if (match instanceof PastMatch) {
            return (int)
                    ((double)(match.getGuest().currentRating + match.getHost().currentRating) *
                            ((double)(((PastMatch) match).getHostGoals() + ((PastMatch) match).getGuestGoals()) * goalsParameter + 1) / 10);
        }
        return 0;
    }
}
