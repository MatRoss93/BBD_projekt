package bbd.projekt.implementation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import bbd.projekt.database.SqlManager;
import bbd.projekt.interfaces.Badanie;
import bbd.projekt.interfaces.Leki;
import bbd.projekt.interfaces.Skierowanie;
import bbd.projekt.interfaces.Specjalista;
import bbd.projekt.interfaces.Termin;
import bbd.projekt.utils.KontekstBezpieczenstwa;

public class LekarzImpl {
  
  private SqlManager sqlManager;

  public LekarzImpl() {
    sqlManager = new SqlManager();
    
  }
  
  public List<Termin> pobierzPacjentowLekarza(KontekstBezpieczenstwa kontekstBezpieczenstwa) {
   
    List<Map<String, Object>> pacjenci = new ArrayList<Map<String,Object>>();
    
    String sql = 
        "SELECT G.NGRF,G.NPCJ,G.NLEK,G.NPRZ,G.DTOD,G.DTDO,P.IMIE,P.NAZW,P.NUMT " +
        "  FROM GRF G, PCJ P " +
        " WHERE P.NPCJ = G.NPCJ " +
        "   AND NLEK = (SELECT NLEK " +
        "                 FROM URZ " +
        "                WHERE LOGN = ?) " +
        "   AND G.AKTW = 'T' " +
        "   AND P.AKTW = 'T' " +
        "   AND DATE(G.DTOD) = DATE(current_timestamp) ";
    
    PreparedStatement query = sqlManager.createQuery(sql);
    try {
      query.setString(1, kontekstBezpieczenstwa.getLogin());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    pacjenci = sqlManager.getResultList(query);
    
    List<Termin> terminy = new ArrayList<Termin>();
    Termin termin = null;
    for (Map<String, Object> pacjent : pacjenci) {
      termin = new Termin();
      termin.setIdGrafiku(((Long)pacjent.get("NGRF")).intValue());
      termin.setIdPacjenta(((Long)pacjent.get("NPCJ")).intValue());
      termin.setIdLekarza(((Long)pacjent.get("NLEK")).intValue());
      termin.setIdPrzychodni(((Long)pacjent.get("NPRZ")).intValue());
      termin.setDataOd((Date)pacjent.get("DTOD"));
      termin.setDataDo((Date)pacjent.get("DTDO"));
      termin.setImie(pacjent.get("IMIE").toString());
      termin.setNazwisko(pacjent.get("NAZW").toString());
      termin.setTelefon((Integer)pacjent.get("NUMT"));
      termin.setDanePacjenta(pacjent.get("IMIE").toString() + " " + pacjent.get("NAZW").toString() + ", tel." + pacjent.get("NUMT").toString());
      terminy.add(termin);
    }
    
    return terminy;
  }
  
  public List<Leki> pobierzListeLekow(String nazwa) {
    List<Leki> listaLekow = new ArrayList<Leki>();
    List<Map<String,Object>> leki = new ArrayList<Map<String,Object>>();
    String sql = "SELECT NDRG, DRGS FROM DRG WHERE DRGS LIKE CONCAT ('%', ?, '%')";
    
    PreparedStatement query = sqlManager.createQuery(sql);
    try {
      query.setString(1, nazwa);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    leki = sqlManager.getResultList(query);
    
    Leki l = null;
    for (Map<String,Object> lek : leki) {
      l = new Leki();
      l.setIdLeku(((Long)lek.get("NDRG")).intValue());
      l.setNazwaLeku(lek.get("DRGS").toString());
      listaLekow.add(l);
    }
    return listaLekow;
  }
  
  public void dodajRecepteDoBazy(List<Leki> leki, Termin termin) {
    String sql = "INSERT INTO REC(NDRG, NGRF, REFU, DWYS, DWAZ) VALUES(?, ?, ?, current_timestamp, DATE_ADD(current_timestamp, interval '3' MONTH))";
    PreparedStatement query;
    for (Leki lek : leki) {
      try {
        query = sqlManager.createQuery(sql);
        query.setLong(1, lek.getIdLeku());
        query.setLong(2, termin.getIdGrafiku());
        query.setLong(3, 0);
        query.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  
  public List<Specjalista> pobierzListeSpecjalistow(String nazwa) {
    List<Specjalista> listaSpecjalistow = new ArrayList<Specjalista>();
    List<Map<String,Object>> specjalisci = new ArrayList<Map<String,Object>>();
    String sql = "SELECT NSPC, SPEC FROM SPC WHERE SPEC LIKE CONCAT ('%', ?, '%')";
    
    PreparedStatement query = sqlManager.createQuery(sql);
    try {
      query.setString(1, nazwa);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    specjalisci = sqlManager.getResultList(query);
    
    Specjalista s = null;
    for (Map<String,Object> specjalista : specjalisci) {
      s = new Specjalista();
      s.setIdSpecjalisty(((Long)specjalista.get("NSPC")).intValue());
      s.setNazwaSpecjalisty(specjalista.get("SPEC").toString());
      listaSpecjalistow.add(s);
    }
    return listaSpecjalistow;
  }
  
  
  public List<Badanie> pobierzListeBadan(String nazwa) {
    List<Badanie> listaBadan = new ArrayList<Badanie>();
    List<Map<String,Object>> badania = new ArrayList<Map<String,Object>>();
    String sql = "SELECT NBAD, BADA FROM BAD WHERE BADA LIKE CONCAT ('%', ?, '%')";
    
    PreparedStatement query = sqlManager.createQuery(sql);
    try {
      query.setString(1, nazwa);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    badania = sqlManager.getResultList(query);
    
    Badanie b = null;
    for (Map<String,Object> badanie : badania) {
      b = new Badanie();
      b.setIdBadania(((Long)badanie.get("NBAD")).intValue());
      b.setNazwaBadania(badanie.get("BADA").toString());
      listaBadan.add(b);
    }
    return listaBadan;
  }
  public void dodajSkierowanieDoBazy(List<Skierowanie> skierowanie, Termin termin) {
    String sql = "INSERT INTO SKI(NSPC, NBAD, NGRF) VALUES(?, ?, ?)";
    PreparedStatement query;
    for (Skierowanie s : skierowanie) {
      try {
        query = sqlManager.createQuery(sql);
        if (s.getIdSpecjalisty() != null) {
          query.setLong(1, s.getIdSpecjalisty());
          query.setNull(2, 0); 
        }
        if (s.getIdBadania() != null) { 
          query.setNull(1, 0); 
          query.setLong(2, s.getIdBadania());
        }
        query.setLong(3, termin.getIdGrafiku());
        query.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  
  
}
