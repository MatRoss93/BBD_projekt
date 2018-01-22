package bbd.projekt.controllers;

import java.util.ArrayList;
import java.util.List;

import bbd.projekt.implementation.LekarzImpl;
import bbd.projekt.interfaces.Leki;
import bbd.projekt.interfaces.Termin;
import bbd.projekt.utils.KontekstBezpieczenstwa;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class LekarzWindowController {

  private MainWindowController mainWindowController;
  private KontekstBezpieczenstwa kontekstBezpieczenstwa;
  private LekarzImpl lekarzClient;
  private List<Leki> receptaDoBazy;

  @FXML
  ComboBox<Termin> listaPacjentow;
  
  @FXML
  TextField imie;
  
  @FXML
  TextField nazwisko;
  
  @FXML
  TextField nrTel;
  
  @FXML
  TabPane receptaSkierowanie;
  
  @FXML
  ListView recepta;
  
  @FXML 
  TextField lekText;
  
  @FXML
  ComboBox<Leki> lekList;
  
  
  public LekarzWindowController() {
    if (kontekstBezpieczenstwa == null) {
      kontekstBezpieczenstwa = new KontekstBezpieczenstwa();
    }    
    lekarzClient = new LekarzImpl();
  }
  public void initialize() {
    imie.setEditable(false);
    imie.setVisible(false);
    nazwisko.setEditable(false);
    nazwisko.setVisible(false);
    nrTel.setEditable(false);
    nrTel.setVisible(false);
    receptaSkierowanie.setVisible(false);
    
    listaPacjentow.setConverter(new StringConverter<Termin>() {

      @Override
      public String toString(Termin termin) {
        return termin.getDanePacjenta();
      }

      @Override
      public Termin fromString(String string) {
          return listaPacjentow.getItems().stream().filter(ap -> 
              ap.getDanePacjenta().equals(string)).findFirst().orElse(null);
      }
    });
    lekList.setConverter(new StringConverter<Leki>() {

      @Override
      public String toString(Leki leki) {
        return leki.getNazwaLeku();
      }

      @Override
      public Leki fromString(String string) {
          return lekList.getItems().stream().filter(ap -> 
              ap.getNazwaLeku().equals(string)).findFirst().orElse(null);
      }
    });
    
  }
  
  public void setMainWindowController(MainWindowController mainWindowController) {
    this.mainWindowController = mainWindowController;
  }

  public void setKontekstBezpieczenstwa(KontekstBezpieczenstwa kontekstBezpieczenstwa) {
    this.kontekstBezpieczenstwa = kontekstBezpieczenstwa;
  }
  
  public void pobierzPacjentow() {
    List<Termin> pacjenci = lekarzClient.pobierzPacjentowLekarza(kontekstBezpieczenstwa);
    
    for (Termin pacjent : pacjenci) {
      listaPacjentow.getItems().add(pacjent);
    }
  }
  
  @FXML
  public void wybierzPacjenta() {

    Termin termin = listaPacjentow.getValue();
    
    if (termin == null) {
      return;
    }
    
    imie.setText(termin.getImie());
    nazwisko.setText(termin.getNazwisko());
    nrTel.setText(termin.getTelefon().toString());
    
    imie.setVisible(true);
    nazwisko.setVisible(true);
    nrTel.setVisible(true);
    receptaSkierowanie.setVisible(true);
  }
  
  @FXML
  public void szukajLekow() {
    lekList.getItems().clear();
    List<Leki> leki = lekarzClient.pobierzListeLekow(lekText.getText());
    for (Leki lek : leki) {
      lekList.getItems().add(lek);
    }
  }
  
  @FXML
  public void dodajDoRecepty() {
    recepta.getItems().add(lekList.getValue().getNazwaLeku());
    if (receptaDoBazy == null) {
      receptaDoBazy = new ArrayList<Leki>();
    }
    receptaDoBazy.add(lekList.getValue());
  }
  
  @FXML
  public void zatwierdzRecepte() {
    lekarzClient.dodajRecepteDoBazy(receptaDoBazy, listaPacjentow.getValue());
  }
  
  
}
