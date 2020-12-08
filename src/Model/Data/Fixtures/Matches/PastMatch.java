package Model.Data.Fixtures.Matches;

public class PastMatch extends Match {


    private int hostGoals;
    private int guestGoals;

    public void setHostGoals(int hostGoals) {
        this.hostGoals = hostGoals;
    }

    public void setGuestGoals(int guestGoals) {
        this.guestGoals = guestGoals;
    }

    public int getHostGoals() {
        return hostGoals;
    }

    public int getGuestGoals() {
        return guestGoals;
    }

    @Override
    public String toString() {
        return
                date +
                "\t" + host.name +
                "\t" + hostGoals + "-" + guestGoals +
                "\t" + guest.name;
    }
}
