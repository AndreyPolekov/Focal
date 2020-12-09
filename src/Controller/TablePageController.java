package Controller;

import Model.Data.Table.Club;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class TablePageController extends PageController {
    public void addColumn(TableView<Club> table, String columnName, String parameter) {
        TableColumn<Club, String> column = new TableColumn<Club, String>(columnName);
        column.setCellValueFactory(new PropertyValueFactory<>(parameter));
        table.getColumns().add(column);
    }
    public AnchorPane getPane() {
        TableView<Club> table = new TableView<Club>();
        table.setMaxHeight(360);
        table.setMaxWidth(470);
        for (int i = 0; i < tournamentButtons.length; i++) {
            if (tournamentButtons[i].isSelected()) {
                table.setItems(FXCollections.observableArrayList(tournaments[i].getTable().getClubs()));
                break;
            }
        }

        addColumn(table, "№", "place");
        addColumn(table, "Club", "name");
        addColumn(table, "M", "matches");
        addColumn(table, "W", "wins");
        addColumn(table, "D", "draws");
        addColumn(table, "L", "loses");
        addColumn(table, "GF", "goalsFor");
        addColumn(table, "GA", "goalsAgainst");
        addColumn(table, "GD", "goalsDifference");
        addColumn(table, "Pts", "points");

        pane.getChildren().add(table);
        return pane;
    }
}

//    ImageView imageView = new ImageView();
//    Image image = new Image("https://1vs1-7f65.kxcdn.com/img/teams_new/20-small.png");
//        imageView.setX(100);
//                imageView.setFitWidth(100);
//                imageView.setImage(image);
//        pane.getChildren().add(imageView);
