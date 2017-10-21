package bbd.projekt.controllers;

import bbd.projekt.utils.FxmlUtils;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class StartWindowController {
  
  private static final String LOGIN_WINDOW_FXML = "/FXML/LoginWindow.fxml";
  @FXML
  StackPane startPane;

  public StartWindowController() {
    
  }
  
  public void initialize() {
    LoginWindowController loginWindowController = (LoginWindowController) FxmlUtils.getController(LOGIN_WINDOW_FXML, startPane);
    loginWindowController.setStartWindowController(this);
  }
}
