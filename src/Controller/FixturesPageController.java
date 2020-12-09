package Controller;

import Model.Data.Fixtures.Matches.FutureMatch;
import Model.Data.Fixtures.Matches.Match;
import Model.Data.Fixtures.Matches.PastMatch;
import Model.Data.Tournament;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class FixturesPageController extends PageController {
    private Tournament currentTournament;

    public void showMatchday(int matchdayNumber) {
        GridPane gridPane = new GridPane();
        gridPane.setTranslateX(30);
        gridPane.setTranslateY(60);
        gridPane.setHgap(35);
        gridPane.setVgap(10);

        ArrayList<Match> matches = currentTournament.getFixtures().getMatchdays().get(matchdayNumber - 1).getMatches();
        for (int i = 0; i < matches.size(); i++) {
            Label date = new Label(matches.get(i).getDate());
            GridPane.setHalignment(date, HPos.LEFT);
            gridPane.add(date, 0, i);

            Label host = new Label(matches.get(i).getHost().name);
            GridPane.setHalignment(host, HPos.RIGHT);
            gridPane.add(host, 1, i);

            Label guest = new Label(matches.get(i).getGuest().name);
            GridPane.setHalignment(guest, HPos.LEFT);
            gridPane.add(guest, 3, i);

            Label label = null;
            if (matches.get(i) instanceof PastMatch) {
                label = new Label(((PastMatch) matches.get(i)).getHostGoals() + " : " + ((PastMatch) matches.get(i)).getGuestGoals());
            } else if (matches.get(i) instanceof FutureMatch) {
                if (((FutureMatch) matches.get(i)).isPostpone()) {
                    label = new Label("pp.");
                } else {
                    label = new Label(((FutureMatch) matches.get(i)).getTime());
                }
            }
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, 2, i);
        }

        pane.getChildren().remove(2);
        pane.getChildren().add(gridPane);
    }
    public AnchorPane getPane() {
        for (int i = 0; i < tournamentButtons.length; i++) {
            if (tournamentButtons[i].isSelected()) {
                currentTournament = tournaments[i];
                break;
            }
        }

        Label label = new Label("Matchday:");
        label.setFont(new Font("Arial", 20));
        label.setTranslateX(120);
        label.setTranslateY(10);
        pane.getChildren().add(label);

        final Spinner<Integer> spinner = new Spinner<Integer>();
        spinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinner.setMaxWidth(80);
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, currentTournament.getFixtures().getMatchdays().size(), currentTournament.getFixtures().getCurrentMatchday());
        spinner.setValueFactory(valueFactory);
        spinner.setTranslateX(230);
        spinner.setTranslateY(10);
        spinner.setOnMouseReleased(keyEvent -> {
            showMatchday(spinner.getValue());
        });
        pane.getChildren().add(spinner);
        pane.getChildren().add(new GridPane());

        showMatchday(spinner.getValue());
        return pane;
    }
}
