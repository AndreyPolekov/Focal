import Data.Fixtures.Matches.FutureMatch;
import Data.Fixtures.Matches.Match;
import Data.Fixtures.Matches.PastMatch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import Data.Tournament;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Focal");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        primaryStage.close();
    }


    public static void main(String[] args) throws IOException {
        //Bundesliga
        //LaLiga
        //EnglishPremierLeague

        Tournament t1 = new Tournament("EnglishPremierLeague");
//        Tournament t2 = new Tournament("LaLiga");
//        Tournament t3 = new Tournament("Bundesliga");


        launch(args);
    }
}
