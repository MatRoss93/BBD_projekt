package bbd.projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Project bbd main window
 *
 */
public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    StackPane stackPane = FXMLLoader.load(this.getClass().getResource("/FXML/StartWindow.fxml"));
    Scene scene = new Scene(stackPane);
    primaryStage.setResizable(false);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Projekt BBD");
    primaryStage.show();
  }
}
