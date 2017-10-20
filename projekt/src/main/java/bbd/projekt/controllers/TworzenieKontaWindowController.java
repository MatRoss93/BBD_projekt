package bbd.projekt.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class TworzenieKontaWindowController {
  StartWindowController startWindowController;

  public void setStartWindowController(StartWindowController startWindowController) {
    this.startWindowController = startWindowController;
  }
  
  @FXML
  public void zalozKonto() {
    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/LoginWindow.fxml"));
    AnchorPane anchorPane = null;
    
    try {
      anchorPane = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    startWindowController.startPane.getChildren().clear();
    startWindowController.startPane.getChildren().add(anchorPane);
    LoginWindowController loginWindowController = loader.getController();
    loginWindowController.setStartWindowController(startWindowController);
  }
}
