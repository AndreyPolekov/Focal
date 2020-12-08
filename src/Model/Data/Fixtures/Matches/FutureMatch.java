package Model.Data.Fixtures.Matches;

import java.util.ArrayList;

public class FutureMatch extends Match {


    private String time;
    private boolean postpone = false;



    private static int jointMatchesCount = 5;



    private ArrayList<PastMatch> jointMatches = new ArrayList<PastMatch>();



    private int hostPercent, guestPercent, drawPercent;

    public void setPostpone(boolean postpone) {
        this.postpone = postpone;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        String s =
                date +
                "\t" + host.name +
                "\t" + (postpone ? "postpone" : time) +
                "\t" + guest.name +
                "\t" + matchRating +
                "\t" + hostPercent +
                "\t" + drawPercent +
                "\t" + guestPercent;
//        for (PastMatch pastMatch : jointMatches) {
//            s = s + "\n\t" + pastMatch;
//        }
        return s;
    }
    public static int getJointMatchesCount() {
        return jointMatchesCount;
    }
    public ArrayList<PastMatch> getJointMatches() {
        return jointMatches;
    }

    public void setHostPercent(int hostPercent) {
        this.hostPercent = hostPercent;
    }

    public void setGuestPercent(int guestPercent) {
        this.guestPercent = guestPercent;
    }

    public void setDrawPercent(int drawPercent) {
        this.drawPercent = drawPercent;
    }
}
