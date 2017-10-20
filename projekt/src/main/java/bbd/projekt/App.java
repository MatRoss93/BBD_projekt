package bbd.projekt;

import bbd.projekt.utils.FxmlUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Project bbd main window
 *
 */
public class App extends Application {
  private static final String START_WINDOW_FXML = "/FXML/StartWindow.fxml";

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Scene scene = new Scene(FxmlUtils.loadFXML(START_WINDOW_FXML));
    primaryStage.setResizable(false);
    primaryStage.setScene(scene);
    primaryStage.setTitle(FxmlUtils.getString("aplikacja.tytul"));
    primaryStage.show();
  }
}
