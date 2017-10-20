package bbd.projekt.utils;

import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class FxmlUtils {
    
  public static ResourceBundle getResourceBundle() {
    return ResourceBundle.getBundle("bundles.Application");
  }
  
  public static Pane loadFXML(String fxmlPath) {
    
    FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
    loader.setResources(getResourceBundle());
    try {
      return loader.load();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static Object loadFXML(String fxmlPath, StackPane startPane) {
    
    FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
    loader.setResources(getResourceBundle());
    Pane pane = null;
    try {
      pane = loader.load();
    } catch (Exception e) {
      e.printStackTrace();
    }
    startPane.getChildren().clear();
    startPane.getChildren().add(pane);
    return loader.getController();
  }

  public static Object getController(String fxmlPath) {
    FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
    loader.setResources(getResourceBundle());
    try {
      Pane pane = loader.load();
      return loader.getController();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static String getString(String key) {
    return getResourceBundle().getString(key);
  }

}
