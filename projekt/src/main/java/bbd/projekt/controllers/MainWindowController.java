package bbd.projekt.controllers;

import java.util.Optional;

import bbd.projekt.dialogs.DialogsBoxes;
import bbd.projekt.utils.FxmlUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.stage.Stage;

public class MainWindowController {

  private static final String LOGIN_WINDOW_FXML = "/FXML/LoginWindow.fxml";
  
  private StartWindowController startWindowController;

  public void setStartWindowController(StartWindowController startWindowController) {
    this.startWindowController = startWindowController;
  }

  @FXML
  public void wyloguj() {
    powrotDoOknaLogowania();
  }
  
  @FXML
  public void zamknij() {
	Optional<ButtonType> result = DialogsBoxes.confirmCloseApplication();
	if(result.get()==ButtonType.OK) {
		Platform.exit();
		System.exit(0);
	}
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
    DialogsBoxes.dialogAboutApplication();
  }
  
  public void powrotDoOknaLogowania() {
    LoginWindowController loginWindowController =
        (LoginWindowController) FxmlUtils.getController(LOGIN_WINDOW_FXML, startWindowController.startPane);
    loginWindowController.setStartWindowController(startWindowController);  
  }
  
  
}
