package bbd.projekt.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class StartWindowController {
  
  @FXML
  StackPane startPane;

  public StartWindowController() {
    
  }
  
  public void initialize() {
    AnchorPane anchorPane = null;
    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/LoginWindow.fxml"));
    try {
      anchorPane = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    LoginWindowController loginWindowController = loader.getController();
    loginWindowController.setStartWindowController(this);
    startPane.getChildren().add(anchorPane);
  }
}
