package bbd.projekt.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class LoginWindowController {
  StartWindowController startWindowController;

  public LoginWindowController() {
    
  }

  public void setStartWindowController(StartWindowController startWindowController) {
    this.startWindowController = startWindowController;
  }
  
  @FXML
  public void zaloguj() {
    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/MainWindow.fxml"));
    BorderPane borderPane = null;
    try {
      borderPane = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    startWindowController.startPane.getChildren().clear();
    startWindowController.startPane.getChildren().add(borderPane);
  }
  
  @FXML
  public void tworzenieKonta() {
    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/TworzenieKontaWindow.fxml"));
    AnchorPane anchorPane = null;
    try {
      anchorPane = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    startWindowController.startPane.getChildren().clear();
    startWindowController.startPane.getChildren().add(anchorPane);
    TworzenieKontaWindowController tworzenieKontaWindowController = loader.getController();
    tworzenieKontaWindowController.setStartWindowController(startWindowController);
  }
}
