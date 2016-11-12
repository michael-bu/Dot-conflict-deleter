package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class Main extends Application {


    public static Window primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("program.fxml"));
        primaryStage.setTitle("Dot Conflict Resolver");
        primaryStage.setScene(new Scene(root, 600, 270));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
