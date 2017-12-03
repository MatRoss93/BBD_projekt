package bbd.projekt.controllers;

import java.util.List;

import bbd.projekt.implementation.TworzenieKontaImpl;
import bbd.projekt.utils.FxmlUtils;
import bbd.projekt.utils.KontekstBezpieczenstwa;
import bbd.projekt.utils.Uprawnienia;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TworzenieKontaWindowController {
  private static final String LOGIN_WINDOW_FXML = "/FXML/LoginWindow.fxml";
  private StartWindowController startWindowController;
  private KontekstBezpieczenstwa kontekstBezpieczenstwa;
  private TworzenieKontaImpl tworzenieKontaClient;
  
  @FXML
  TextField login;
  
  @FXML
  PasswordField haslo;

  @FXML
  PasswordField haslo2;
  
  @FXML
  ComboBox uprawnienia;
  
  @FXML
  Button utworz;
  
  public TworzenieKontaWindowController() {

  }
  
  public void initialize() {
    if (KontekstBezpieczenstwa.getUprawnienia() != Uprawnienia.ADMINISTRATOR) {
      uprawnienia.setDisable(true);
      uprawnienia.setVisible(false);
    } else {
      uprawnienia.setDisable(false);
      uprawnienia.setVisible(true);
    }
    tworzenieKontaClient = new TworzenieKontaImpl();
  }
  public void setStartWindowController(StartWindowController startWindowController) {
    this.startWindowController = startWindowController;
  }
  
  @FXML
  public void zalozKonto() {
    List<String> listaBledow = tworzenieKontaClient.utworzKonto(login.getCharacters().toString(), haslo.getCharacters().toString(), haslo2.getCharacters().toString());
    if (listaBledow.isEmpty()) {
      LoginWindowController loginWindowController =
          (LoginWindowController) FxmlUtils.getController(LOGIN_WINDOW_FXML, startWindowController.startPane);
      loginWindowController.setStartWindowController(startWindowController);      
    }
  }
  

}
