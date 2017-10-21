package bbd.projekt.controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.stage.Stage;

public class MainWindowController {

  StartWindowController startWindowController;

  public void setStartWindowController(StartWindowController startWindowController) {
    this.startWindowController = startWindowController;
  }

  @FXML
  public void wyloguj() {
    
  }
  
  @FXML
  public void zamknij() {
    Platform.exit();
    System.exit(0);
  }
  
  @FXML
  public void stylModena() {
    Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
  }
  
  @FXML
  public void stylCaspian() {
    Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
  }
  
  @FXML
  public void zawszeNaWierzchu(ActionEvent event) {
    Stage stage = (Stage) startWindowController.startPane.getScene().getWindow();
    stage.setAlwaysOnTop(((CheckMenuItem) event.getSource()).selectedProperty().get());
  }
  
  @FXML
  public void oAplikacji() {
    
  }
  
  
  
  
}
