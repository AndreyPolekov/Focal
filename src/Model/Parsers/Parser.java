package Model.Parsers;

public abstract class Parser {
    protected Parser next = null;
    protected String tournamentName;

    public void setNext(Parser next) {
        this.next = next;
    }
    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
    public abstract void parse();
    public abstract String getTournamentUrl();
}
