package bbd.projekt.implementation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bbd.projekt.database.SqlManager;
import bbd.projekt.interfaces.Recepta;
import bbd.projekt.interfaces.Skierowanie;
import bbd.projekt.interfaces.Wizyta;
import bbd.projekt.utils.KontekstBezpieczenstwa;

public class PacjentImpl {
  private SqlManager sqlManager;
  private KontekstBezpieczenstwa kontekstBezpieczenstwa;
  
  public PacjentImpl(KontekstBezpieczenstwa kontekstBezpieczenstwa) {
    sqlManager = new SqlManager();
    this.kontekstBezpieczenstwa = kontekstBezpieczenstwa;
  }
  public List<Wizyta> pobierzWizyty() {
    List<Wizyta> wizyty = new ArrayList<Wizyta>();
    List<Map<String, Object>> wizytyMap = new ArrayList<Map<String,Object>>();
    String sql =  
            "     SELECT G.NGRF, Date_Format(G.DTOD, \"%Y-%m-%d\") data, Date_Format(G.DTOD,\"%H:%i\") godzina, P.NAZW, CONCAT(L.IMIE, ' ', L.NAZW) LEKARZ " +
            "       FROM GRF G " +
            "       JOIN PRZ P ON G.NPRZ = P.NPRZ " +
            "       JOIN LEK L ON G.NLEK = L.NLEK " +
            "      WHERE G.NPCJ = ? " +
            "        AND G.DTOD > current_timestamp " +
            "   ORDER BY G.DTOD ASC";
    
    PreparedStatement query = sqlManager.createQuery(sql);
    try {
      query.setLong(1, kontekstBezpieczenstwa.getIdPacjenta());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    wizytyMap = sqlManager.getResultList(query);
    Wizyta wizyta = null;
    for (Map<String,Object> w : wizytyMap) {
      wizyta = new Wizyta();
      wizyta.setData(w.get("data").toString());
      wizyta.setGodzina(w.get("godzina").toString());
      wizyta.setNazwaLek(w.get("LEKARZ").toString());
      wizyta.setNazwaPrzych(w.get("NAZW").toString());
      wizyty.add(wizyta);
    }
    return wizyty;
  }
  
  public List<Recepta> pobierzRecepty() {
    List<Recepta> recepty = new ArrayList<Recepta>();
    List<Map<String, Object>> receptyMap = new ArrayList<Map<String,Object>>();
    String sql = 
           "      SELECT G.NGRF, R.NDRG, Date_Format(R.DWYS, \"%Y-%m-%d\") DWYS, Date_Format(R.DWAZ, \"%Y-%m-%d\") DWAZ, D.DRGS " +
            "       FROM GRF G  " +
            "  LEFT JOIN REC R ON G.NGRF = R.NGRF " +
            "  LEFT JOIN DRG D ON R.NDRG = D.NDRG " +
            "      WHERE G.NPCJ = ?  " +
            "        AND R.DWAZ > current_timestamp " +
            "   ORDER BY G.DTOD ASC";
    
    PreparedStatement query = sqlManager.createQuery(sql);
    try {
      query.setLong(1, kontekstBezpieczenstwa.getIdPacjenta());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    receptyMap = sqlManager.getResultList(query);
    Recepta recepta = null;
    for(Map<String,Object> r : receptyMap) {
      recepta = new Recepta();
      recepta.setDatawazn(r.get("DWAZ").toString());
      recepta.setDatawyst(r.get("DWYS").toString());
      recepta.setIdLeku((Long) r.get("NDRG"));
      recepta.setNazwaLeku(r.get("DRGS").toString());
      recepty.add(recepta);
    }
    return recepty;
  }
  
  public List<Skierowanie> pobierzSkierowania() {
    List<Skierowanie> skierowania = new ArrayList<Skierowanie>();
    List<Map<String, Object>> skierowaniaMap = new ArrayList<Map<String,Object>>();
    String sql =
           "      SELECT G.NGRF, COALESCE(B.BADA, C.SPEC) NAZW " +
            "       FROM GRF G  " +
            "       JOIN SKI S ON G.NGRF = S.NGRF " +
            "  LEFT JOIN BAD B ON S.NBAD = B.NBAD " +
            "  LEFT JOIN SPC C ON S.NSPC = C.NSPC " +
            "      WHERE G.NPCJ = ?  " +
            "   ORDER BY G.DTOD ASC";
    PreparedStatement query = sqlManager.createQuery(sql);
    try {
      query.setLong(1, kontekstBezpieczenstwa.getIdPacjenta());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    skierowaniaMap = sqlManager.getResultList(query);
    Skierowanie ski = null;
    for(Map<String,Object> s : skierowaniaMap) {
      ski = new Skierowanie();
      ski.setNazwa(s.get("NAZW").toString());
      skierowania.add(ski);
    }
    return skierowania;
  }
}
