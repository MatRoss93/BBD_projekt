package bbd.projekt.implementation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import bbd.projekt.database.SqlManager;
import bbd.projekt.interfaces.Leki;
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
}
