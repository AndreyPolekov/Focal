package Model.Data.Fixtures.Matches;

import Model.Data.Table.Club;

public class Match {


    protected Club host, guest;
    protected String date;




    protected int matchRating;

    public void setHost(Club host) {
        this.host = host;
    }

    public void setGuest(Club guest) {
        this.guest = guest;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Club getHost() {
        return host;
    }

    public Club getGuest() {
        return guest;
    }

    public int getMatchRating() {
        return matchRating;
    }

    public void setMatchRating(int matchRating) {
        this.matchRating = matchRating;
    }
}
