import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Stage loadingWindow = new Stage();
//        FXMLLoader loadingWindowLoader = new FXMLLoader(getClass().getResource("View/LoadingWindow.fxml"));
//        Parent loadingWindowRoot = loadingWindowLoader.load();
//        loadingWindow.setTitle("loading");
//        loadingWindow.setScene(new Scene(loadingWindowRoot));
//        loadingWindow.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("View/BaseWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Focal");
        primaryStage.setScene(new Scene(root));
        //loadingWindow.close();
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
