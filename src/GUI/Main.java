package src.GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("View/MyTunesView.fxml"));

        //Scene scene = new Scene(root);
        //String css = this.getClass().getResource("/GUI/CSS/scratch.css").toExternalForm();
        //scene.getStylesheets().add(css);

        primaryStage.setTitle("MyTunes");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}