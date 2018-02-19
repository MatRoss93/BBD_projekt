package bbd.projekt.controllers;

import java.util.List;

import javax.swing.JOptionPane;

import bbd.projekt.implementation.TworzenieKontaImpl;
import bbd.projekt.interfaces.Leki;
import bbd.projekt.interfaces.Uprawnienia;
import bbd.projekt.utils.FxmlUtils;
import bbd.projekt.utils.KontekstBezpieczenstwa;
import bbd.projekt.utils.UprawnieniaEnum;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

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
  ComboBox<Uprawnienia> uprawnienia;
  
  @FXML
  Button utworz;
  
  @FXML
  Button powrot;

  
  public TworzenieKontaWindowController() {
    if (kontekstBezpieczenstwa == null) {
      kontekstBezpieczenstwa = new KontekstBezpieczenstwa();
    }
  }
  
  public void initialize() {
    tworzenieKontaClient = new TworzenieKontaImpl();
    uprawnienia.setConverter(new StringConverter<Uprawnienia>() {

      @Override
      public String toString(Uprawnienia uprawnienia) {
        return uprawnienia.getNazwa();
      }

      @Override
      public Uprawnienia fromString(String string) {
          return uprawnienia.getItems().stream().filter(ap -> 
              ap.getNazwa().equals(string)).findFirst().orElse(null);
      }
    });
  }
  public void setStartWindowController(StartWindowController startWindowController) {
    this.startWindowController = startWindowController;
  }
  
  @FXML
  public void zalozKonto() {
    List<String> listaBledow = tworzenieKontaClient.utworzKonto(login.getCharacters().toString(), 
                                                                haslo.getCharacters().toString(), 
                                                               haslo2.getCharacters().toString(),
                                                          uprawnienia.getValue().getSymbol().toString());
    if (listaBledow.isEmpty() && kontekstBezpieczenstwa.getUprawnienia().equals(UprawnieniaEnum.PACJENT)) {   
      powrotDoOknaLogowania();
    } else if (listaBledow.isEmpty() && kontekstBezpieczenstwa.getUprawnienia().equals(UprawnieniaEnum.ADMINISTRATOR)) {
      JOptionPane.showMessageDialog(null, FxmlUtils.getString("admin.konto.utworzone"));
    }
  }
  
  @FXML
  public void powrot() {
    powrotDoOknaLogowania();
  }
  
  public void powrotDoOknaLogowania() {
    LoginWindowController loginWindowController =
        (LoginWindowController) FxmlUtils.getController(LOGIN_WINDOW_FXML, startWindowController.startPane);
    loginWindowController.setStartWindowController(startWindowController);  
  }
  public KontekstBezpieczenstwa getKontekstBezpieczenstwa() {
    return kontekstBezpieczenstwa;
  }

  public void setKontekstBezpieczenstwa(KontekstBezpieczenstwa kontekstBezpieczenstwa) {
    this.kontekstBezpieczenstwa = kontekstBezpieczenstwa;
    if (kontekstBezpieczenstwa.getUprawnienia() != UprawnieniaEnum.ADMINISTRATOR) {
      uprawnienia.setDisable(true);
      uprawnienia.setVisible(false);
    } else {
      uprawnienia.setDisable(false);
      uprawnienia.setVisible(true);
      powrot.setDisable(true);
      powrot.setVisible(false);
      uzupelnijUprawnienia();
    }
  }
  
  public void uzupelnijUprawnienia() {
    uprawnienia.getItems().add(new Uprawnienia(UprawnieniaEnum.PACJENT));
    uprawnienia.getItems().add(new Uprawnienia(UprawnieniaEnum.RECEPCJA));
    uprawnienia.getItems().add(new Uprawnienia(UprawnieniaEnum.LEKARZ));
    uprawnienia.getItems().add(new Uprawnienia(UprawnieniaEnum.ADMINISTRATOR));
  }
}
