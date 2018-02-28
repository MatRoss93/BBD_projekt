package bbd.projekt.controllers;


import java.util.Date;
import java.util.List;

import bbd.projekt.implementation.PacjentImpl;
import bbd.projekt.interfaces.Recepta;
import bbd.projekt.interfaces.Skierowanie;
import bbd.projekt.interfaces.Wizyta;
import bbd.projekt.utils.KontekstBezpieczenstwa;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
public class PacjentWindowController {
	  private MainWindowController mainWindowController;
	  private KontekstBezpieczenstwa kontekstBezpieczenstwa;
	  private PacjentImpl pacjentClient;

    @FXML
    Tab twTab;

    @FXML
    TableView <Wizyta>twTableView;

    @FXML
    TableColumn<Wizyta, String> dataTableColumn;

    @FXML
    TableColumn<Wizyta, String> godzTableColumn;

    @FXML
    TableColumn<Wizyta, String> przyTableColumn;
    
    @FXML
    TableColumn<Wizyta, String> lekTableColumn;

    @FXML
    Tab recTab;

    @FXML
    TableView <Recepta>recTableView;

    @FXML
    TableColumn<Recepta, String> datawystTableColumn;

    @FXML
    TableColumn<Recepta, String> datawazTableColumn;

    @FXML
    TableColumn<Recepta, String> nlekuTableColumn;


    @FXML
    Tab skTab;

    @FXML
    TableView <Skierowanie> scTableView;

    @FXML
    TableColumn<Skierowanie,String> skiTableColumn;


    public PacjentWindowController() {
        if (kontekstBezpieczenstwa == null) {
          kontekstBezpieczenstwa = new KontekstBezpieczenstwa();
        }    
    }
	public void initialize() {
	  dataTableColumn.setCellValueFactory(new PropertyValueFactory<Wizyta, String>("data"));
	  godzTableColumn.setCellValueFactory(new PropertyValueFactory<Wizyta, String>("godzina"));
	  przyTableColumn.setCellValueFactory(new PropertyValueFactory<Wizyta, String>("nazwaPrzych"));
	  lekTableColumn.setCellValueFactory(new PropertyValueFactory<Wizyta, String>("nazwaLek"));
	  
	  datawystTableColumn.setCellValueFactory(new PropertyValueFactory<Recepta, String>("datawyst"));
	  datawazTableColumn.setCellValueFactory(new PropertyValueFactory<Recepta, String>("datawazn"));
	  nlekuTableColumn.setCellValueFactory(new PropertyValueFactory<Recepta, String>("nazwaLeku"));
	  
	  skiTableColumn.setCellValueFactory(new PropertyValueFactory<Skierowanie, String>("nazwa"));
	  
      pacjentClient = new PacjentImpl(kontekstBezpieczenstwa);
	}
	public void setKontekstBezpieczenstwa(KontekstBezpieczenstwa kontekstBezpieczenstwa) {
	  this.kontekstBezpieczenstwa = kontekstBezpieczenstwa;
      pacjentClient = new PacjentImpl(kontekstBezpieczenstwa);
      uzupelnijTabele();
	}
	public void setMainWindowController(MainWindowController mainWindowController) {
      this.mainWindowController = mainWindowController;
	}
	public void uzupelnijTabele() {
	  List<Wizyta> wizyty = pacjentClient.pobierzWizyty();
	  for(Wizyta w : wizyty) {
	    twTableView.getItems().add(w);
	  }
	  
      List<Recepta> recepty = pacjentClient.pobierzRecepty();
      for (Recepta r : recepty) {
        recTableView.getItems().add(r);
      }
      
      List<Skierowanie> skierowania = pacjentClient.pobierzSkierowania();
      for (Skierowanie s : skierowania) {
        scTableView.getItems().add(s);
      }
	}
	
	
	 
}