package bbd.projekt.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import bbd.projekt.implementation.LekarzImpl;
import bbd.projekt.interfaces.Badanie;
import bbd.projekt.interfaces.Leki;
import bbd.projekt.interfaces.Skierowanie;
import bbd.projekt.interfaces.Specjalista;
import bbd.projekt.interfaces.Termin;
import bbd.projekt.utils.FxmlUtils;
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
  private List<Skierowanie> skierowanieDoBazy;
  
  
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
  
  @FXML
  TextField szukajSpecjalisty;
  
  @FXML
  ComboBox<Specjalista> specjalistaList;
  
  @FXML
  TextField szukajBadania;
  
  @FXML
  ComboBox<Badanie> badanieList;

  @FXML
  ListView skierowanie;
  
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
    specjalistaList.setConverter(new StringConverter<Specjalista>() {

      @Override
      public String toString(Specjalista specjalista) {
        return specjalista.getNazwaSpecjalisty();
      }

      @Override
      public Specjalista fromString(String string) {
          return specjalistaList.getItems().stream().filter(ap -> 
              ap.getNazwaSpecjalisty().equals(string)).findFirst().orElse(null);
      }
    });
    badanieList.setConverter(new StringConverter<Badanie>() {

      @Override
      public String toString(Badanie badania) {
        return badania.getNazwaBadania();
      }

      @Override
      public Badanie fromString(String string) {
          return badanieList.getItems().stream().filter(ap -> 
              ap.getNazwaBadania().equals(string)).findFirst().orElse(null);
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
    if (lekList.getValue() != null) {
      recepta.getItems().add(lekList.getValue().getNazwaLeku());
      if (receptaDoBazy == null) {
        receptaDoBazy = new ArrayList<Leki>();
      }
      receptaDoBazy.add(lekList.getValue());
    }
  }
  
  @FXML
  public void zatwierdzRecepte() {
    if (listaPacjentow.getValue() != null) {
      lekarzClient.dodajRecepteDoBazy(receptaDoBazy, listaPacjentow.getValue());
      JOptionPane.showMessageDialog(null, FxmlUtils.getString("lekarz.dodano.do.bazy.recepta"));
    }
  }
  
  
  @FXML
  public void szukajSpecjalistow() {
    specjalistaList.getItems().clear();
    List<Specjalista> spec = lekarzClient.pobierzListeSpecjalistow(szukajSpecjalisty.getText());
    for (Specjalista s : spec) {
      specjalistaList.getItems().add(s);      
    }
  }
  
  @FXML
  public void szukajBadan() {
    badanieList.getItems().clear();
    List<Badanie> bad = lekarzClient.pobierzListeBadan(szukajBadania.getText());
    for (Badanie b : bad) {
      badanieList.getItems().add(b);      
    }
  }
  
  @FXML
  public void dodajSpecDoSkierowania() {
    if (specjalistaList.getValue() != null) {
      skierowanie.getItems().add(specjalistaList.getValue().getNazwaSpecjalisty());
      if (skierowanieDoBazy == null) {
        skierowanieDoBazy = new ArrayList<Skierowanie>();
      }
      Skierowanie s = new Skierowanie();
      s.setIdSpecjalisty(specjalistaList.getValue().getIdSpecjalisty());
      skierowanieDoBazy.add(s);
    }
  }
  
  @FXML
  public void dodajBadDoSkierowania() {
    if (badanieList.getValue() != null) {
      skierowanie.getItems().add(badanieList.getValue().getNazwaBadania());
      if (skierowanieDoBazy == null) {
        skierowanieDoBazy = new ArrayList<Skierowanie>();
      }
      Skierowanie s = new Skierowanie();
      s.setIdBadania(badanieList.getValue().getIdBadania());
      skierowanieDoBazy.add(s);
    }
  }
  
  @FXML
  public void zatwierdzSkierowanie() {
    if (listaPacjentow.getValue() != null) {
      lekarzClient.dodajSkierowanieDoBazy(skierowanieDoBazy, listaPacjentow.getValue());
      JOptionPane.showMessageDialog(null, FxmlUtils.getString("lekarz.dodano.do.bazy.skierowanie"));
    }
  }
  
}
