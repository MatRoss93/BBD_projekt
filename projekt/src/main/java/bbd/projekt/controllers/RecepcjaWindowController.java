package bbd.projekt.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import bbd.projekt.implementation.RecepcjaImpl;
import bbd.projekt.interfaces.Lekarz;
import bbd.projekt.interfaces.Miasto;
import bbd.projekt.interfaces.Pacjent;
import bbd.projekt.interfaces.Przychodnia;
import bbd.projekt.interfaces.Wojew;
import bbd.projekt.utils.KontekstBezpieczenstwa;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class RecepcjaWindowController {
	
	private MainWindowController mainWindowController;
	private KontekstBezpieczenstwa kontekstBezpieczenstwa;
	private RecepcjaImpl recepcjaClient;
	
	@FXML 
	TextField imie;
	@FXML
	TextField godzinaOd;
	@FXML
	TextField godzinaDo;
	@FXML 
	TextField nazwisko;
	@FXML
	TextField tel;
	@FXML
	TextField ulica;
	@FXML
	DatePicker dataWizyty;
	@FXML
	TextField login;
	@FXML 
	ComboBox<Miasto> wybMiasto;
	@FXML 
	ComboBox<Lekarz> listaLekarzy;
	@FXML 
	ComboBox<Wojew> wybWojew;
	@FXML
	ComboBox<Przychodnia> listaPrzychodni;
	@FXML
	ComboBox<Pacjent> listaPacjentow;
	
	String pattern = "yyyy-MM-dd";
	
	public RecepcjaWindowController() {
	    if (kontekstBezpieczenstwa == null) {
	      kontekstBezpieczenstwa = new KontekstBezpieczenstwa();
	    }    
	    recepcjaClient = new RecepcjaImpl();
	  }
	public void initialize() {
		
		dataWizyty.setPromptText(pattern.toLowerCase());
		dataWizyty.setConverter(new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
			
			@Override
			public String toString(LocalDate date) {
				if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		});
		
		listaLekarzy.setConverter(new StringConverter<Lekarz>() {

			@Override
			public String toString(Lekarz lekarz) {
				// TODO Auto-generated method stub
				return lekarz.getImienazwisko();
			}

			@Override
			public Lekarz fromString(String string) {
				// TODO Auto-generated method stub
				return listaLekarzy.getItems().stream().filter(ap -> ap.getImienazwisko().equals(string)).findFirst().orElse(null);
			}
			
		}); 
		
		
		wybMiasto.setConverter(new StringConverter<Miasto>() {
			
			@Override
			public String toString(Miasto miasto) {
				// TODO Auto-generated method stub
				return miasto.getMsto();
			}
			
			@Override
			public Miasto fromString(String string) {
				// TODO Auto-generated method stub
				return wybMiasto.getItems().stream().filter(ap -> 
	              ap.getMsto().equals(string)).findFirst().orElse(null);
			}
		});
		
		wybWojew.setConverter(new StringConverter<Wojew>() {
			
			@Override
			public String toString(Wojew wojewodztwo) {
				// TODO Auto-generated method stub
				return wojewodztwo.getWojew();
			}
			
			@Override
			public Wojew fromString(String string) {
				// TODO Auto-generated method stub
				return wybWojew.getItems().stream().filter(ap -> 
	              ap.getWojew().equals(string)).findFirst().orElse(null);
			}
		});  
		
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
		listaPacjentow.setConverter(new StringConverter<Pacjent>() {

		      @Override
		      public String toString(Pacjent pacjent) {
		        return pacjent.getImie() + " " + pacjent.getNazwisko();
		      }

		      @Override
		      public Pacjent fromString(String string) {
		          return listaPacjentow.getItems().stream().filter(ap -> 
		              (ap.getImie() + " " + ap.getNazwisko()).equals(string)).findFirst().orElse(null);
		      }
		    });
	}
	
	public void setMainWindowController(MainWindowController mainWindowController) {
		this.mainWindowController = mainWindowController;
	}

	public void setKontekstBezpieczenstwa(KontekstBezpieczenstwa kontekstBezpieczenstwa) {
		this.kontekstBezpieczenstwa = kontekstBezpieczenstwa;
	}
	
	public void wybierzMiasto() {
	    
	    List<Miasto> miasto = recepcjaClient.pobierzListeMiast();
	    for (Miasto msto : miasto) {
	      wybMiasto.getItems().add(msto);
	    }
	  }
	
	public void wybierzWojewodztwo() {
	    
	    List<Wojew> wojewodztwo = recepcjaClient.pobierzListeWojew();
	    for (Wojew wojewto : wojewodztwo) {
	      wybWojew.getItems().add(wojewto);
	    }
	  }
	
	public void wybierzPrzychodnie() {
	    
	    List<Przychodnia> przychodnia = recepcjaClient.pobierzListePrzychodni();
	    for (Przychodnia przych : przychodnia) {
	      listaPrzychodni.getItems().add(przych);
	    }
	  }
	
	public void wybierzPacjenta() {
	    
	    List<Pacjent> pacjent = recepcjaClient.pobierzListePacjentow();
	    for (Pacjent pacj : pacjent) {
	      listaPacjentow.getItems().add(pacj);
	    }
	  }
	
	public void pacjentDoBazy() {
		
		recepcjaClient.dodajPacjenta(imie.getText(),nazwisko.getText(),wybMiasto.getValue(),wybWojew.getValue(),ulica.getText(), Integer.parseInt(tel.getText()), login.getText());
		imie.clear();
		nazwisko.clear();
		ulica.clear();
		tel.clear();
		login.clear();
	}
	
	public void dodajDoGrafiku() {
		
		recepcjaClient.dodajDoGrafiku(listaPacjentow.getValue(), listaLekarzy.getValue(), listaPrzychodni.getValue(), godzinaOd.getText(), godzinaDo.getText(),(Date) dataWizyty.getUserData());
		godzinaDo.clear();
		godzinaDo.clear();
	}
	
	@FXML
	public void wypiszLekarzy() {
		List<Lekarz> lekarz = recepcjaClient.pobierzListeLekarzy();
		for(Lekarz lekarze : lekarz) {
			listaLekarzy.getItems().add(lekarze);
			
		}
		
	  } 
}
