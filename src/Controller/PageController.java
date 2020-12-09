package Controller;

import Model.Data.Tournament;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public abstract class PageController {
    protected Tournament[] tournaments;
    protected ToggleButton[] tournamentButtons;
    protected AnchorPane pane = new AnchorPane();

    public abstract AnchorPane getPane();


    public void setTournaments(Tournament[] tournaments) {
        this.tournaments = tournaments;
    }

    public void setTournamentButtons(ToggleButton[] tournamentButtons) {
        this.tournamentButtons = tournamentButtons;
    }
}
