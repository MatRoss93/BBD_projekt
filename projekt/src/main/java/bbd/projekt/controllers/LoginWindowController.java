package bbd.projekt.controllers;

import javax.swing.JOptionPane;

import bbd.projekt.implementation.LoginImpl;
import bbd.projekt.utils.FxmlUtils;
import bbd.projekt.utils.KontekstBezpieczenstwa;
import bbd.projekt.utils.Uprawnienia;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginWindowController {
  private static final String TWORZENIE_KONTA_WINDOW_FXML = "/FXML/TworzenieKontaWindow.fxml";
  private static final String MAIN_WINDOW_FXML = "/FXML/MainWindow.fxml";
  private static final String LEKARZ_WINDOW_FXML = "/FXML/LekarzWindow.fxml";
  private StartWindowController startWindowController;
  private KontekstBezpieczenstwa kontekstBezpieczenstwa;
  private LoginImpl loginClient;
  
  @FXML
  TextField login;
  
  @FXML
  PasswordField haslo;
  
  public LoginWindowController() {
  }

  public void initialize() {
    kontekstBezpieczenstwa = new KontekstBezpieczenstwa();
    loginClient = new LoginImpl();
  }
  
  public void setStartWindowController(StartWindowController startWindowController) {
    this.startWindowController = startWindowController;
  }
  
  @FXML
  public void zaloguj() {
    this.kontekstBezpieczenstwa = loginClient.zaloguj(login.getText(), haslo.getText());
    if(!this.kontekstBezpieczenstwa.equals(null) && this.kontekstBezpieczenstwa.poprawnyKB()) {
      MainWindowController mainWindowController = (MainWindowController) FxmlUtils.getController(MAIN_WINDOW_FXML, startWindowController.startPane);
      mainWindowController.setStartWindowController(startWindowController); 
      mainWindowController.setKontekstBezpieczenstwa(kontekstBezpieczenstwa);
      if (kontekstBezpieczenstwa.getUprawnienia() == Uprawnienia.LEKARZ) {
        LekarzWindowController lekarzController = (LekarzWindowController) FxmlUtils.getController(LEKARZ_WINDOW_FXML, mainWindowController.applicationPane);
        lekarzController.setKontekstBezpieczenstwa(kontekstBezpieczenstwa);
        lekarzController.setMainWindowController(mainWindowController);
        lekarzController.pobierzPacjentow();
      }
    } else {
      JOptionPane.showMessageDialog(null, FxmlUtils.getString("logowanie.blad.niepoprawne"));
    }
  }
  
  @FXML
  public void tworzenieKonta() {
    TworzenieKontaWindowController tworzenieKontaWindowController = 
        (TworzenieKontaWindowController) FxmlUtils.getController(TWORZENIE_KONTA_WINDOW_FXML,startWindowController.startPane);
    tworzenieKontaWindowController.setKontekstBezpieczenstwa(kontekstBezpieczenstwa);
    tworzenieKontaWindowController.setStartWindowController(startWindowController);
  }
}
