package Ratings;

import Data.Table.Club;

public abstract class ClubRating implements Rating {
    protected Club club;

    public ClubRating(Club club) {
        this.club = club;
    }
    public abstract int calculate();
}
