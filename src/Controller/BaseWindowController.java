package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class BaseWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuButton mainMenuButton;

    @FXML
    private Label mainLabel;

    @FXML
    private TabPane tabs;

    @FXML
    private ToggleButton PremierLeagueButton;

    @FXML
    private ToggleButton LaLigaButton;

    @FXML
    private ToggleButton BundesligaButton;

    @FXML
    private ToggleButton SerieAButton;

    @FXML
    private ToggleButton Ligue1Button;

    private ToggleButton[] tournamentButtons;

    @FXML
    void initialize() {
        tournamentButtons = new ToggleButton[] {
                PremierLeagueButton,
                LaLigaButton,
                BundesligaButton,
                SerieAButton,
                Ligue1Button
        };
        for (MenuItem menuItem: mainMenuButton.getItems()) {
            menuItem.setOnAction(actionEvent -> {
                if (mainLabel.getText().equals(menuItem.getText())) {
                    return;
                }
                mainLabel.setText(menuItem.getText());
                for (int i = 0; i < 5; i++) {
                    if (i == 0){
                        tournamentButtons[i].setSelected(true);
                    }
                    else {
                        tournamentButtons[i].setSelected(false);
                    }
                }
                changeTabs();
            });
        }
        for (ToggleButton tournamentButton: tournamentButtons) {
            tournamentButton.setOnAction(actionEvent -> {
                changeButtons(tournamentButton);
                changeTabs();
            });
        }
    }

    public Tab createTab(String name) {
        Tab tab = new Tab(name);
        ScrollPane scrollPane = new ScrollPane();
        AnchorPane anchorPane = new AnchorPane();   //future implementation

        scrollPane.setContent(anchorPane);
        tab.setContent(scrollPane);
        return tab;
    }
    public void changeTabs() {
        tabs.getTabs().clear();
        switch (mainLabel.getText()) {
            case "Tournaments":
                tabs.getTabs().addAll(
                        createTab("Table"),
                        createTab("Fixtures")
                );
                break;
            case "Recommendations":
                tabs.getTabs().addAll(
                        createTab("Last matches"),
                        createTab("Upcoming matches")
                );
                break;
        }
    }
    public void changeButtons(ToggleButton currentButton) {
        if (mainLabel.getText().equals("Tournaments")) {
            for (ToggleButton tournamentButton: tournamentButtons) {
                if (tournamentButton != currentButton) {
                    tournamentButton.setSelected(false);
                }
            }
        }
    }

//    public ArrayList<Integer> getActiveButtonNumbers() {
//        ArrayList<Integer> activeButtonNumbers = new ArrayList<Integer>();
//        for (int i = 0; i < tournamentButtons.length; i++) {
//            if (tournamentButtons[i].isSelected()) {
//                activeButtonNumbers.add(i);
//            }
//        }
//        return activeButtonNumbers;
//    }
}
