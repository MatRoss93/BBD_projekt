package bbd.projekt.implementation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.exolab.castor.types.DateTime;

import bbd.projekt.database.SqlManager;
import bbd.projekt.interfaces.Lekarz;
import bbd.projekt.interfaces.Miasto;
import bbd.projekt.interfaces.Pacjent;
import bbd.projekt.interfaces.Przychodnia;
import bbd.projekt.interfaces.Wojew;


public class RecepcjaImpl {
	
	private SqlManager sqlManager;
	
	public RecepcjaImpl() {
		sqlManager = new SqlManager();
	}
	
	public List<Przychodnia> pobierzListePrzychodni() {
		List<Przychodnia> listaPrzychodni = new ArrayList<Przychodnia>();
		List<Map<String,Object>> przychodnia = new ArrayList<Map<String,Object>>();
		String sql = "SELECT NPRZ, NAZW FROM PRZ";
		    
		PreparedStatement query = sqlManager.createQuery(sql);
		    
		przychodnia = sqlManager.getResultList(query);
		    
		Przychodnia p = null;
		for (Map<String,Object> przych : przychodnia) {
			p = new Przychodnia();
		    p.setId(((Long)przych.get("NPRZ")));
		    p.setNazwa(przych.get("NAZW").toString());
		    listaPrzychodni.add(p);
		}
		return listaPrzychodni;
	  }
	
	public List<Pacjent> pobierzListePacjentow() {
		List<Pacjent> listaPacjentow = new ArrayList<Pacjent>();
		List<Map<String,Object>> pacjent = new ArrayList<Map<String,Object>>();
		String sql = "SELECT NPCJ,IMIE,NAZW FROM PCJ";
		    
		PreparedStatement query = sqlManager.createQuery(sql);
		    
		pacjent = sqlManager.getResultList(query);
		    
		Pacjent p = null;
		for (Map<String,Object> pacj : pacjent) {
			p = new Pacjent();
		    p.setImie(pacj.get("IMIE").toString());
		    p.setNazwisko(pacj.get("NAZW").toString());
		    listaPacjentow.add(p);
		}
		return listaPacjentow;
	  }
	  
	public List<Miasto> pobierzListeMiast() {
		List<Miasto> listaMiast = new ArrayList<Miasto>();
		List<Map<String,Object>> miasto = new ArrayList<Map<String,Object>>();
		String sql = "SELECT NMST, MSTO FROM MST";
		    
		PreparedStatement query = sqlManager.createQuery(sql);
		    
		miasto = sqlManager.getResultList(query);
		    
		Miasto m = null;
		for (Map<String,Object> msto : miasto) {
			m = new Miasto();
		    m.setIdMst(((Integer)msto.get("NMST")).intValue());
		    m.setMsto(msto.get("MSTO").toString());
		    listaMiast.add(m);
		}
		return listaMiast;
	  }
	
	public List<Wojew> pobierzListeWojew() {
		List<Wojew> listaWojewodztw = new ArrayList<Wojew>();
		List<Map<String,Object>> wojewodztwo = new ArrayList<Map<String,Object>>();
		String sql = "SELECT NWOJ, WOJW FROM WOJ";
		    
		PreparedStatement query = sqlManager.createQuery(sql);
		    
		wojewodztwo = sqlManager.getResultList(query);
		    
		Wojew w = null;
		for (Map<String,Object> wojew : wojewodztwo) {
			w = new Wojew();
		    w.setIdWoj(((Integer)wojew.get("NWOJ")).intValue());
		    w.setWojew(wojew.get("WOJW").toString());
		    listaWojewodztw.add(w);
		}
		return listaWojewodztw;
	  }
	 
	public List<Lekarz> pobierzListeLekarzy() {
		List<Lekarz> listaLekarzy = new ArrayList<Lekarz>();
		List<Map<String, Object>> lekarz = new ArrayList<Map<String, Object>>();
		String sql = "SELECT NLEK, IMIE, NAZW FROM LEK";
		PreparedStatement query = sqlManager.createQuery(sql);
		  
		lekarz = sqlManager.getResultList(query);
		  
		  Lekarz l = null;
		  for(Map<String, Object> lekarze : lekarz) {
			  l = new Lekarz(((Long)lekarze.get("NLEK")),lekarze.get("NAZW").toString(), 
					  lekarze.get("IMIE").toString(),lekarze.get("IMIE").toString() + " " + lekarze.get("NAZW").toString());
			  /*
			  l = new Lekarz();
			  l.setIdLekarza(((Long)lekarze.get("NLEK")));
			  l.setImie(lekarze.get("IMIE").toString());
			  l.setNazwisko(lekarze.get("NAZW").toString());
			  l.setImienazwisko(lekarze.get("IMIE").toString() + " " + lekarze.get("NAZW").toString());
			  */
			  listaLekarzy.add(l);
		  }
		  return listaLekarzy;
	  }
	
	public void dodajDoGrafiku(Pacjent pacjent, Lekarz lekarz, Przychodnia przychodnia, String godzinaOd, String godzinaDo, LocalDate localDate) {
		
		//String data = dataWizyty.getValue().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
		//String terminOd = dataWizyty+" "+godzinaOd;
		//String terminDo = data+" "+godzinaDo;
		
		String sql = "INSERT INTO GRF(NPCJ, NLEK, NPRZ, DTOD, DTDO) VALUES(?,?,?,?,?)";
		PreparedStatement query;
		try {
			
			query = sqlManager.createQuery(sql);
			query.setLong(1, pacjent.getIdPacjenta());
			query.setLong(2, lekarz.getIdLekarza());
			query.setLong(3, przychodnia.getId());
			query.setString(4, (localDate +" "+ godzinaOd).toString());
			query.setString(5, (localDate +" "+ godzinaDo).toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dodajPacjenta(String imie, String nazwisko, Miasto miasto, Wojew wojew, String ulica, int ntel ) {
		  String sql = "INSERT INTO PCJ(IMIE, NAZW, NMST, NWOJ, ULIC, NUMT) VALUES(?,?,?,?,?,?)";
		  PreparedStatement query;
		  //for (Wojew wojew: wojewodztwo) {
			  //for (Miasto msto : miasto) {
				  try {
					  query = sqlManager.createQuery(sql);
					  query.setString(1, imie.toUpperCase());
					  query.setString(2, nazwisko.toUpperCase());
					  query.setInt(3, miasto.getIdMst());
					  query.setInt(4, wojew.getIdWoj());
					  query.setString(5, ulica.toUpperCase());
					  query.setInt(6, ntel);
					  query.executeUpdate();
		      		} catch (SQLException e) {
		      			e.printStackTrace();
		      		}
			  //}
		  //}
		  
	  } 
}
