package bbd.projekt.controllers;

import javax.swing.JOptionPane;

import bbd.projekt.implementation.LoginImpl;
import bbd.projekt.utils.FxmlUtils;
import bbd.projekt.utils.KontekstBezpieczenstwa;
import bbd.projekt.utils.UprawnieniaEnum;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginWindowController {
  private static final String TWORZENIE_KONTA_WINDOW_FXML = "/FXML/TworzenieKontaWindow.fxml";
  private static final String MAIN_WINDOW_FXML = "/FXML/MainWindow.fxml";
  private static final String LEKARZ_WINDOW_FXML = "/FXML/LekarzWindow.fxml";
  private static final String ADMIN_WINDOW_FXML = "/FXML/AdminWindow.fxml";
  private static final String FORMULARZ_LEKARZA_FXML = "/FXML/FormularzLekarza.fxml";
  private static final String RECEPCJA_WINDOW_FXML = "/FXML/RecepcjaWindow.fxml";
  private static final String PACJENT_WINDOW_FXML = "/FXML/PacjentWindow.fxml";
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
      if (kontekstBezpieczenstwa.getUprawnienia() == UprawnieniaEnum.LEKARZ) {
        if (kontekstBezpieczenstwa.getIdLekarza() != null) {
        LekarzWindowController lekarzController = (LekarzWindowController) FxmlUtils.getController(LEKARZ_WINDOW_FXML, mainWindowController.applicationPane);
        lekarzController.setKontekstBezpieczenstwa(kontekstBezpieczenstwa);
        lekarzController.setMainWindowController(mainWindowController);
        lekarzController.pobierzPacjentow();
        } else {
          FormularzLekarzaController FLController = (FormularzLekarzaController) FxmlUtils.getController(FORMULARZ_LEKARZA_FXML, mainWindowController.applicationPane);
          FLController.setStartWindowController(startWindowController);
          FLController.setKontekstBezpieczenstwa(kontekstBezpieczenstwa);
        }
      } else if (kontekstBezpieczenstwa.getUprawnienia() == UprawnieniaEnum.ADMINISTRATOR) {
        AdminWindowController adminController = (AdminWindowController) FxmlUtils.getController(ADMIN_WINDOW_FXML, mainWindowController.applicationPane);
        adminController.setKontekstBezpieczenstwa(kontekstBezpieczenstwa);
        adminController.setMainWindowController(mainWindowController);
        adminController.setStartWindowController(startWindowController);
      } else if (kontekstBezpieczenstwa.getUprawnienia() == UprawnieniaEnum.RECEPCJA) {
    	  RecepcjaWindowController recepcjaController = (RecepcjaWindowController) FxmlUtils.getController(RECEPCJA_WINDOW_FXML, mainWindowController.applicationPane);
    	  recepcjaController.setKontekstBezpieczenstwa(kontekstBezpieczenstwa);
    	  recepcjaController.setMainWindowController(mainWindowController);
    	  recepcjaController.wybierzMiasto();
    	  recepcjaController.wybierzWojewodztwo();
    	  recepcjaController.wypiszLekarzy();
    	  recepcjaController.wybierzPrzychodnie();
    	  recepcjaController.wybierzPacjenta();
      } else if (kontekstBezpieczenstwa.getUprawnienia() == UprawnieniaEnum.PACJENT) {
        PacjentWindowController pacjentController = (PacjentWindowController) FxmlUtils.getController(PACJENT_WINDOW_FXML, mainWindowController.applicationPane);
        pacjentController.setKontekstBezpieczenstwa(kontekstBezpieczenstwa);
        pacjentController.setMainWindowController(mainWindowController);
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
