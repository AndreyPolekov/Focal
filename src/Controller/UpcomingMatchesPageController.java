package Controller;

import Model.Data.Fixtures.Matches.FutureMatch;
import Model.Data.Fixtures.Matches.Match;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UpcomingMatchesPageController extends PageController {
    public AnchorPane getPane() {
        ArrayList<Pair<FutureMatch, String>> upcomingMatches = new ArrayList<Pair<FutureMatch, String>>();
        for (int i = 0; i < tournamentButtons.length; i++) {
            if (tournamentButtons[i].isSelected()) {
                for (int j = tournaments[i].getFixtures().getCurrentMatchday(), k = 0; k < 3; j++, k++) {
                    for (Match match: tournaments[i].getFixtures().getMatchdays().get(j - 1).getMatches()) {
                        if (match instanceof FutureMatch) {
                            if (((FutureMatch) match).isPostpone()) {
                                continue;
                            }
                            match.setRatingSum((match.getHost().currentRating + match.getGuest().currentRating) / 10);
                            Pair<FutureMatch, String> pair = new Pair<>((FutureMatch)match, tournaments[i].getName());
                            upcomingMatches.add(pair);
                        }
                    }
                }
            }
        }

        Comparator<Pair<FutureMatch, String>> comparator = (Pair<FutureMatch, String> a, Pair<FutureMatch, String> b) -> {
            return b.getKey().getRatingSum() - a.getKey().getRatingSum();
        };
        Collections.sort(upcomingMatches, comparator);

        int string = 0, column = 0;
        for (int i = 0; i < upcomingMatches.size() * 0.25; i++) {
            StackPane stackPane = new StackPane();
            stackPane.setLayoutX(155 * column);
            stackPane.setLayoutY(180 * string);
            final Pair<FutureMatch, String> match = upcomingMatches.get(i);
            stackPane.setOnMouseClicked(mouseEvent -> {
                Stage futureMatchWindow = new Stage();
                futureMatchWindow.setTitle(
                        match.getKey().getHost().name +
                        " vs " +
                        match.getKey().getGuest().name
                );
                futureMatchWindow.setScene(new Scene((new FutureMatchWindowController(match)).getPane()));
                futureMatchWindow.show();
            });

            ImageView background = new ImageView();
            File file = new File("src/View/Images/MatchBackground.png");
            background.setImage(new Image(file.toURI().toString()));
            background.setFitWidth(120);
            background.setFitHeight(140);
            background.setTranslateX(30);
            background.setTranslateY(30);
            stackPane.getChildren().add(background);

            ImageView hostImage = new ImageView();
            hostImage.setImage(new Image(upcomingMatches.get(i).getKey().getHost().imageUrl));
            hostImage.setFitWidth(40);
            hostImage.setFitHeight(40);
            hostImage.setTranslateX(9);
            hostImage.setTranslateY(-11);
            stackPane.getChildren().add(hostImage);

            ImageView guestImage = new ImageView();
            guestImage.setImage(new Image(upcomingMatches.get(i).getKey().getGuest().imageUrl));
            guestImage.setFitWidth(40);
            guestImage.setFitHeight(40);
            guestImage.setTranslateX(47);
            guestImage.setTranslateY(70);
            stackPane.getChildren().add(guestImage);

            Label time = new Label(upcomingMatches.get(i).getKey().getTime());
            time.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            time.setTranslateX(26);
            time.setTranslateY(29);
            stackPane.getChildren().add(time);

            Label rating = new Label(Integer.toString(upcomingMatches.get(i).getKey().getRatingSum()));
            rating.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 24));
            rating.setTranslateX(-25);
            rating.setTranslateY(67);
            stackPane.getChildren().add(rating);

            String dateString = upcomingMatches.get(i).getKey().getDate();
            dateString = " " + dateString.substring(0, dateString.indexOf(' ')) + "\n" + dateString.substring(dateString.indexOf(' ') + 1, dateString.length());
            Label date = new Label(dateString);
            date.setFont(Font.font("Arial", 14));
            date.setTranslateX(73);
            date.setTranslateY(-15);
            stackPane.getChildren().add(date);

            pane.getChildren().add(stackPane);

            column++;
            if (column == 3) {
                column = 0;
                string++;
            }
        }

        StackPane stackPane = new StackPane();
        stackPane.setMinWidth(10);
        stackPane.setMinHeight(220);
        stackPane.setLayoutX(155 * column);
        stackPane.setLayoutY(180 * string);
        pane.getChildren().add(stackPane);

        return pane;
    }
}
