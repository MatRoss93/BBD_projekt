package bbd.projekt.controllers;

import bbd.projekt.utils.FxmlUtils;
import javafx.fxml.FXML;

public class TworzenieKontaWindowController {
  private static final String LOGIN_WINDOW_FXML = "/FXML/LoginWindow.fxml";
  StartWindowController startWindowController;

  public void setStartWindowController(StartWindowController startWindowController) {
    this.startWindowController = startWindowController;
  }
  
  @FXML
  public void zalozKonto() {
    LoginWindowController loginWindowController =
        (LoginWindowController) FxmlUtils.getController(LOGIN_WINDOW_FXML, startWindowController.startPane);
    loginWindowController.setStartWindowController(startWindowController);
  }
}
