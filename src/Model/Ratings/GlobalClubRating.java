package Model.Ratings;

import Model.Data.Table.Club;

public class GlobalClubRating extends ClubRating {
    private static double pointsParameter = 2.0 * (2.8 - 0.1);
    private static double goalsForParameter = 4.0 * (3.0 - 0.3);
    private static double goalsAgainstParameter = 6.0 * (2.0 - 0.3);
    private static double uefaParemeter = 2.0 * (12.0 - 6.0); //ln(120000) - ln(500)

    public GlobalClubRating(Club club) {
        super(club);
    }
    public int calculate() {


        //int rating;
        //elo - base                        [1 - 1.25]
        //uefa - (lnX + 6) / 12                    [1 - 1.5]
        //g - (x + (6 * 2.7))/(6 * 2.7)     [1 - 1.2]
        //p - (x + (2 * 2.7))/(2 * 2.7)     [1 - 1.5]

//        System.out.println(
//                "\n" +
//                        club.name + "\n" +
//                        (double)(club.eloPoints) + "\t" +
//                        (((double)(club.points) / (double)(club.matches)) / pointsParameter + 1)  + "\t" +
//                        ((((double)(club.goalsFor) / (double)(club.matches)) / goalsForParameter) / (((double)(club.goalsAgainst) / (double)(club.matches)) / goalsAgainstParameter + 1) + 1)  + "\t" +
//                        ((Math.log((double)(club.uefaPoints)) - 6) / uefaParemeter + 1) + "\t" +
//                        ""
//        );


        return (int)(
                        (double)(club.eloPoints) *
                        (((double)(club.points) / (double)(club.matches)) / pointsParameter + 1) *
                        ((((double)(club.goalsFor) / (double)(club.matches)) / goalsForParameter) / (((double)(club.goalsAgainst) / (double)(club.matches)) / goalsAgainstParameter + 1) + 1)  *
                        ((Math.log((double)(club.uefaPoints)) - 6) / uefaParemeter + 1)
                );
    }
}
