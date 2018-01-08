package bbd.projekt.implementation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import bbd.projekt.database.SqlManager;
import bbd.projekt.utils.KontekstBezpieczenstwa;
import bbd.projekt.utils.PassHash;
import bbd.projekt.utils.PassHash.CannotPerformOperationException;
import bbd.projekt.utils.PassHash.InvalidHashException;

public class LoginImpl {

  private SqlManager sqlManager;
  private KontekstBezpieczenstwa kontekstBezpieczenstwa;
  public LoginImpl() {
    sqlManager = new SqlManager();
    kontekstBezpieczenstwa = new KontekstBezpieczenstwa();
  }
  
  /**
   * Zwraca true jeśli login i haslo poprawne, false w przeciwnym wypadku.
   * @param login
   * @param haslo
   * @return
   */
  public KontekstBezpieczenstwa zaloguj(String login, String haslo) {
    String sql = "SELECT LOGN, HASL, UPRW FROM URZ WHERE LOGN = ?";
    PreparedStatement query = sqlManager.createQuery(sql);
    try {
      query.setString(1, login);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    Map<String, Object> rs = sqlManager.getSingleResult(query);
    if (rs != null) {
      boolean poprawneHaslo = false;
      try {
        poprawneHaslo = PassHash.verifyPassword(haslo, rs.get("HASL").toString());
      } catch (CannotPerformOperationException | InvalidHashException e) {
        e.printStackTrace();
      }
      if (poprawneHaslo) {
        return KontekstBezpieczenstwa.setZalogowany(rs.get("LOGN").toString(), rs.get("UPRW").toString());
      }
    }
   
    return this.kontekstBezpieczenstwa;
  }
  
}
