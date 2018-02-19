package bbd.projekt.implementation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bbd.projekt.database.SqlManager;
import bbd.projekt.interfaces.Specjalizacja;

public class FormularzLekarzaImpl {

  private SqlManager sqlManager;
  
  public FormularzLekarzaImpl() {
    sqlManager = new SqlManager();
  }
  
  public List<Specjalizacja> pobierzSpecjalizacje() {
    List<Specjalizacja> specjalizacje = new ArrayList<Specjalizacja>();
    List<Map<String,Object>> spec = new ArrayList<Map<String,Object>>();
    String sql = "SELECT NSPC, SPEC FROM SPC WHERE AKTW = 'T'";
    PreparedStatement query = sqlManager.createQuery(sql);
    spec = sqlManager.getResultList(query);
    Specjalizacja sp = null;
    for (Map<String,Object> s : spec) {
      sp = new Specjalizacja();
      sp.setIdSpecjaizacji((Long) s.get("NSPC"));
      sp.setNazwaSpecjalizacji(s.get("SPEC").toString());
      specjalizacje.add(sp);
    }
    return specjalizacje;
  }
  
  public void uzupelnijDaneLekarza(Specjalizacja s, String imie, String nazwisko, String login) {
    String sql = "INSERT INTO LEK(NSPC, IMIE, NAZW) values(?, ?, ?)";
    PreparedStatement query = sqlManager.createQuery(sql);
    try {
      query.setLong(1, s.getIdSpecjaizacji());
      query.setString(2, imie);
      query.setString(3, nazwisko);
      query.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    sql =   
        "UPDATE URZ  " +
        "   SET NLEK = (SELECT NLEK  " +
        "                 FROM LEK  " +
        "                WHERE NSPC = ?  " +
        "                  AND IMIE = ?  " +
        "                  AND NAZW = ?  " +
        "             ORDER BY DTWO DESC " +
        "                LIMIT 1)  " +
        " WHERE LOGN = ?";
    query = sqlManager.createQuery(sql);
    try {
      query.setLong(1, s.getIdSpecjaizacji());
      query.setString(2, imie);
      query.setString(3, nazwisko);
      query.setString(4, login);
      query.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
  }
}
