package bbd.projekt.utils;

import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class FxmlUtils {
    
  public static ResourceBundle getResourceBundle() {
    return ResourceBundle.getBundle("bundles.Application");
  }
  
  /**
   * Zwraca Pane z fxmlPath
   * @param fxmlPath
   * @return
   */
  
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
  
/**
 * Dodaje fxml z fxmlPath do startPane i zwraca jego controller
 * @param fxmlPath
 * @param startPane
 * @return
 */

  public static Object getController(String fxmlPath, StackPane startPane) {
    
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
  
  /**
   * Zwraca wartość dla podanego klucza z pliku Application.properties
   * @param key
   * @return
   */
  public static String getString(String key) {
    return getResourceBundle().getString(key);
  }

}
