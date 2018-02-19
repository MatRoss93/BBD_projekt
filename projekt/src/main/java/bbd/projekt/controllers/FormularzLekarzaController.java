package bbd.projekt.controllers;

import java.util.List;

import bbd.projekt.implementation.FormularzLekarzaImpl;
import bbd.projekt.interfaces.Specjalizacja;
import bbd.projekt.utils.FxmlUtils;
import bbd.projekt.utils.KontekstBezpieczenstwa;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class FormularzLekarzaController {

  private static final String LOGIN_WINDOW_FXML = "/FXML/LoginWindow.fxml";
  
  private StartWindowController startWindowController;
  private FormularzLekarzaImpl formLekClient;
  private KontekstBezpieczenstwa kontekstBezpieczenstwa;
  
  @FXML
  ComboBox<Specjalizacja> listaSpecjalizacji;
  
  @FXML
  TextField imie;
  
  @FXML
  TextField nazwisko;
  
  public FormularzLekarzaController() {
    formLekClient = new FormularzLekarzaImpl();
  }

  public void initialize() {
    listaSpecjalizacji.setConverter(new StringConverter<Specjalizacja>() {

      @Override
      public String toString(Specjalizacja spec) {
        return spec.getNazwaSpecjalizacji();
      }

      @Override
      public Specjalizacja fromString(String string) {
          return listaSpecjalizacji.getItems().stream().filter(ap -> 
              ap.getNazwaSpecjalizacji().equals(string)).findFirst().orElse(null);
      }
    });
    uzupelnijSpecjalizacje();
  }
  public void uzupelnijSpecjalizacje() {
    List<Specjalizacja> spec = formLekClient.pobierzSpecjalizacje();
    
    for (Specjalizacja s : spec) {
      listaSpecjalizacji.getItems().add(s);
    }
  }
  
  @FXML
  private void utworzLekarza() {
    formLekClient.uzupelnijDaneLekarza(listaSpecjalizacji.getValue(), imie.getText(), nazwisko.getText(), kontekstBezpieczenstwa.getLogin());
    
    LoginWindowController loginWindowController =
        (LoginWindowController) FxmlUtils.getController(LOGIN_WINDOW_FXML, startWindowController.startPane);
    loginWindowController.setStartWindowController(startWindowController);  
  }

  public void setStartWindowController(StartWindowController startWindowController) {
    this.startWindowController = startWindowController;
  }
  public void setKontekstBezpieczenstwa(KontekstBezpieczenstwa kontekstBezpieczenstwa) {
    this.kontekstBezpieczenstwa = kontekstBezpieczenstwa;
  }
}
