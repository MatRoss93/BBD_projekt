package bbd.projekt.controllers;

import bbd.projekt.database.SqlManager;
import bbd.projekt.utils.FxmlUtils;
import javafx.fxml.FXML;

public class LoginWindowController {
  private static final String TWORZENIE_KONTA_WINDOW_FXML = "/FXML/TworzenieKontaWindow.fxml";
  private static final String MAIN_WINDOW_FXML = "/FXML/MainWindow.fxml";
  private StartWindowController startWindowController;
  
  public LoginWindowController() {
  }

  public void initialize() {
    
  }
  
  public void setStartWindowController(StartWindowController startWindowController) {
    this.startWindowController = startWindowController;
  }
  
  @FXML
  public void zaloguj() {
    MainWindowController mainWindowController = (MainWindowController) FxmlUtils.getController(MAIN_WINDOW_FXML, startWindowController.startPane);
    mainWindowController.setStartWindowController(startWindowController);
  }
  
  @FXML
  public void tworzenieKonta() {

    TworzenieKontaWindowController tworzenieKontaWindowController = 
        (TworzenieKontaWindowController) FxmlUtils.getController(TWORZENIE_KONTA_WINDOW_FXML,startWindowController.startPane);
    
    tworzenieKontaWindowController.setStartWindowController(startWindowController);
  }
}
