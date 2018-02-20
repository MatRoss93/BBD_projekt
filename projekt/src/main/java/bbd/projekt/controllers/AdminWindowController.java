package bbd.projekt.controllers;

import java.util.List;

import javax.swing.JOptionPane;

import bbd.projekt.implementation.AdminImpl;
import bbd.projekt.interfaces.Lekarz;
import bbd.projekt.interfaces.Przychodnia;
import bbd.projekt.utils.FxmlUtils;
import bbd.projekt.utils.KontekstBezpieczenstwa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

public class AdminWindowController {
  
  private static final String TWORZENIE_KONTA_WINDOW_FXML = "/FXML/TworzenieKontaWindow.fxml";

  private MainWindowController mainWindowController;
  private StartWindowController startWindowController;
  private KontekstBezpieczenstwa kontekstBezpieczenstwa;
  private AdminImpl adminClient;
  
  @FXML
  ComboBox<Lekarz> listaLekarzy;
  
  @FXML
  ComboBox<Przychodnia> listaPrzychodni;
  
  @FXML
  Button generujRaport;
  
  @FXML
  Button utworzKonto;
  
  @FXML
  Pane adminPane;
  
  public AdminWindowController() {
  }

  public void initialize() {
    adminClient = new AdminImpl(kontekstBezpieczenstwa);
    listaPrzychodni.setConverter(new StringConverter<Przychodnia>() {

      @Override
      public String toString(Przychodnia przychodnia) {
        return przychodnia.getNazwa();
      }

      @Override
      public Przychodnia fromString(String string) {
          return listaPrzychodni.getItems().stream().filter(ap -> 
              ap.getNazwa().equals(string)).findFirst().orElse(null);
      }
    });
    
    listaLekarzy.setConverter(new StringConverter<Lekarz>() {

      @Override
      public String toString(Lekarz lekarz) {
        return lekarz.getImie() + " " + lekarz.getNazwisko();
      }

      @Override
      public Lekarz fromString(String string) {
          return listaLekarzy.getItems().stream().filter(ap -> 
              (ap.getImie() + " " + ap.getNazwisko()).equals(string)).findFirst().orElse(null);
      }
    });
    uzupelnijPrzychodnie();
    listaLekarzy.setDisable(true);
    generujRaport.setDisable(true);
    listaLekarzy.setVisible(false);
  }
  
  @FXML
  public void tworzenieKonta() {
    TworzenieKontaWindowController tworzenieKontaWindowController = 
        (TworzenieKontaWindowController) FxmlUtils.getController(TWORZENIE_KONTA_WINDOW_FXML,this.adminPane);
    tworzenieKontaWindowController.setKontekstBezpieczenstwa(kontekstBezpieczenstwa);
  }
  @FXML
  public void wybierzPoradnie() {
    if (listaPrzychodni.getValue() == null || listaPrzychodni.getValue().getId() == null) {
      JOptionPane.showMessageDialog(null, FxmlUtils.getString("admin.najpierw.wybierz.przychodnie"));
    } else {
      uzupelnijLekarzy(listaPrzychodni.getValue().getId());
      listaLekarzy.setDisable(false);
      if (listaLekarzy.getItems().isEmpty()) {
        generujRaport.setDisable(true);
      } else {
        generujRaport.setDisable(false);
      }
    }
  }
  @FXML
  public void generujRaport() {
    adminClient.przygotujRaport(listaPrzychodni.getValue());
  }
  public void setMainWindowController(MainWindowController mainWindowController) {
    this.mainWindowController = mainWindowController;
  }

  public void setKontekstBezpieczenstwa(KontekstBezpieczenstwa kontekstBezpieczenstwa) {
    this.kontekstBezpieczenstwa = kontekstBezpieczenstwa;
  }
  public void setStartWindowController(StartWindowController startWindowController) {
    this.startWindowController = startWindowController;
  }
  public void uzupelnijPrzychodnie() {
    List<Przychodnia> przychodnie = adminClient.pobierzPrzychodnie();
    for (Przychodnia p : przychodnie) {
      listaPrzychodni.getItems().add(p);
    }
  }
  public void uzupelnijLekarzy(Long idPrzychodni) {
    listaLekarzy.getItems().clear();
    List<Lekarz> lekarze = adminClient.pobierzLekarzyDlaPrzychodni(idPrzychodni);
    for (Lekarz l : lekarze) {
      listaLekarzy.getItems().add(l);
    }
  }
}
