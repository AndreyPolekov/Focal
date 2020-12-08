import Controller.BaseWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View/BaseWindow.fxml"));
        Parent root = loader.load();
        BaseWindowController controller = loader.getController();




        primaryStage.setTitle("Focal");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        //Bundesliga
        //LaLiga
        //EnglishPremierLeague

//        Tournament t1 = new Tournament("EnglishPremierLeague");
//        Tournament t2 = new Tournament("LaLiga");
//        Tournament t3 = new Tournament("Bundesliga");


        launch(args);
    }
}
