package Controller;

import Model.Data.Fixtures.Matches.FutureMatch;
import Model.Data.Table.Club;
import Model.Parsers.MatchParser;
import Model.Parsers.Parser;
import Model.Ratings.MatchRating;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Pair;


public class FutureMatchWindowController {
    private static final int width = 600;
    private static final int height = 400;
    private static final int probabilitiesPaneHeight = 50;
    private AnchorPane pane = new AnchorPane();

    public FutureMatchWindowController(Pair<FutureMatch, String> match) {
        Parser parser = new MatchParser();
        parser.setTournamentName(match.getValue());
        ((MatchParser)parser).setMath(match.getKey());
        parser.parse();
        match.getKey().setMatchRating((new MatchRating((match.getKey()))).calculate());

        pane.setPrefWidth(width);
        pane.setPrefHeight(height);

        Rectangle background = new Rectangle(0, 0, width, height);
        background.setFill(Color.rgb(200,200,200));
        pane.getChildren().add(background);

        AnchorPane hostPane = getClubPane(match.getKey().getHost());
        hostPane.setTranslateX(width / 3 * 0);
        hostPane.setTranslateY(probabilitiesPaneHeight);
        pane.getChildren().add(hostPane);

        AnchorPane guestPane = getClubPane(match.getKey().getGuest());
        guestPane.setTranslateX(width / 3 * 2);
        guestPane.setTranslateY(probabilitiesPaneHeight);
        pane.getChildren().add(guestPane);

        AnchorPane probabilitiesPane = getProbabilitiesPane(match.getKey());
        probabilitiesPane.setTranslateX(0);
        probabilitiesPane.setTranslateY(0);
        pane.getChildren().add(probabilitiesPane);

        AnchorPane matchPane = getMatchPane(match.getKey());
        matchPane.setTranslateX(width / 3 * 1);
        matchPane.setTranslateY(probabilitiesPaneHeight);
        pane.getChildren().add(matchPane);
    }
    public AnchorPane getClubPane(Club club) {
        AnchorPane clubPane = new AnchorPane();

        Rectangle background = new Rectangle(0, 0, width / 3, height - probabilitiesPaneHeight);
        background.setFill(Color.WHITE);
        clubPane.getChildren().add(background);

        ImageView image = new ImageView();
        image.setImage(new Image(club.imageUrl));
        image.setFitWidth(100);
        image.setFitHeight(100);
        image.setTranslateX(50);
        image.setTranslateY(15);
        clubPane.getChildren().add(image);

        Label place = new Label(Integer.toString(club.place));
        place.setFont(new Font("Arial", 24));
        place.setTranslateX(40 + 120);
        place.setTranslateY(10);
        clubPane.getChildren().add(place);

        Label name = new Label(club.name);
        name.setFont(new Font("Arial", 22));
        name.setTranslateX(100 - (club.name.length() / 2) * 11);
        name.setTranslateY(140);
        clubPane.getChildren().add(name);

        Label currentRating = new Label(Integer.toString(club.currentRating));
        currentRating.setFont(new Font("Arial", 40));
        currentRating.setTranslateX(60);
        currentRating.setTranslateY(170);
        clubPane.getChildren().add(currentRating);

        Label globalRating = new Label(Integer.toString(club.globalRating));
        globalRating.setFont(new Font("Arial", 26));
        globalRating.setTranslateX(75);
        globalRating.setTranslateY(210);
        clubPane.getChildren().add(globalRating);

        for (int i = 0; i < club.lastMatches.size(); i++) {
            Label host = new Label(club.lastMatches.get(i).getHost().name);
            host.setFont(new Font("Arial", 12));
            host.setTranslateX(10);
            host.setTranslateY(250 + i * 20);
            clubPane.getChildren().add(host);

            Label result = new Label(club.lastMatches.get(i).getHostGoals() + " : " + club.lastMatches.get(i).getGuestGoals());
            result.setFont(new Font("Arial", 12));
            result.setTranslateX(100 - 15);
            result.setTranslateY(250 + i * 20);
            clubPane.getChildren().add(result);

            Label guest = new Label( club.lastMatches.get(i).getGuest().name);
            guest.setFont(new Font("Arial", 12));
            guest.setTranslateX(100 + 25);
            guest.setTranslateY(250 + i * 20);
            clubPane.getChildren().add(guest);
        }

        return clubPane;
    }
    public AnchorPane getMatchPane(FutureMatch match) {
        AnchorPane matchPane = new AnchorPane();

        Label date = new Label(match.getDate());
        date.setFont(new Font("Arial", 22));
        date.setTranslateX(100 - 60);
        date.setTranslateY(15);
        matchPane.getChildren().add(date);

        Label name = new Label(match.getTime());
        name.setFont(new Font("Arial", 28));
        name.setTranslateX(100 - 30);
        name.setTranslateY(35);
        matchPane.getChildren().add(name);

        Label matchRating = new Label(Integer.toString(match.getMatchRating()));
        matchRating.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        matchRating.setTranslateX(35);
        matchRating.setTranslateY(85);
        matchPane.getChildren().add(matchRating);

        Label ratingSum = new Label(Integer.toString(match.getRatingSum()));
        ratingSum.setFont(new Font("Arial", 38));
        ratingSum.setTranslateX(60);
        ratingSum.setTranslateY(140);
        matchPane.getChildren().add(ratingSum);

        for (int i = 0; i < match.getJointMatches().size(); i++) {
            Label host = new Label(match.getJointMatches().get(i).getHost().name);
            host.setFont(new Font("Arial", 13));
            host.setTranslateX(10);
            host.setTranslateY(220 + i * 25);
            matchPane.getChildren().add(host);

            Label result = new Label(match.getJointMatches().get(i).getHostGoals() + " : " + match.getJointMatches().get(i).getGuestGoals());
            result.setFont(new Font("Arial", 13));
            result.setTranslateX(100 - 15);
            result.setTranslateY(220 + i * 25);
            matchPane.getChildren().add(result);

            Label guest = new Label(match.getJointMatches().get(i).getGuest().name);
            guest.setFont(new Font("Arial", 13));
            guest.setTranslateX(100 + 25);
            guest.setTranslateY(220 + i * 25);
            matchPane.getChildren().add(guest);
        }

        return matchPane;
    }
    public AnchorPane getProbabilitiesPane(FutureMatch match) {
        AnchorPane probabilitiesPane = new AnchorPane();
        int panelWidth = width - 100;

        int hostWidth = panelWidth * match.getHostPercent() / 100;
        int drawWidth = panelWidth * match.getDrawPercent() / 100;
        int guestWidth = panelWidth * match.getGuestPercent() / 100;


        showProbability(
                probabilitiesPane,
                Color.rgb(40,40,40),
                0,
                hostWidth,
                match.getHostPercent()
        );
        showProbability(
                probabilitiesPane,
                Color.rgb(140,140,140),
                hostWidth,
                drawWidth,
                match.getDrawPercent()
        );
        showProbability(
                probabilitiesPane,
                Color.rgb(90,90,90),
                hostWidth + drawWidth,
                guestWidth,
                match.getGuestPercent()
        );

        return probabilitiesPane;
    }
    public void showProbability(AnchorPane probabilitiesPane, Color rectangleColor, int rectangleOffset, int rectangleWidth, int percents) {
        int panelWidth = width - 100;
        int panelHeight = 15;
        int panelX = (width - panelWidth) / 2;
        int panelY = 10;

        int verticalDistance = 5;
        int labelCorrection = 10;

        Rectangle rectangle = new Rectangle(panelX + rectangleOffset, panelY, rectangleWidth, panelHeight);
        rectangle.setFill(rectangleColor);
        probabilitiesPane.getChildren().add(rectangle);

        Label hostLabel = new Label(Integer.toString(percents) + "%");
        hostLabel.setFont(new Font("Arial", 14));
        hostLabel.setTranslateX(panelX + rectangleOffset + rectangleWidth / 2 - labelCorrection);
        hostLabel.setTranslateY(panelY + panelHeight + verticalDistance);
        probabilitiesPane.getChildren().add(hostLabel);
    }
    public AnchorPane getPane() {
        return pane;
    }
}

