package bbd.projekt.implementation;

import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar.Separator;

import bbd.projekt.database.SqlManager;
import bbd.projekt.utils.FxmlUtils;
import bbd.projekt.utils.PassHash;
import bbd.projekt.utils.PassHash.CannotPerformOperationException;

public class TworzenieKontaImpl {
  
  private SqlManager sqlManager;
  
  public TworzenieKontaImpl() {
    sqlManager = new SqlManager();
  }
  
  public List<String> utworzKonto(String login, String haslo, String haslo2) {
    List<String> listaBledow = new ArrayList<String>();
    if (login.equals(null) || 
        login.length() < 5 || 
        login.length() > 15) {
    	
      listaBledow.add(FxmlUtils.getString("tworzenie.konta.blad.login.ilosc"));
    }
    if (login.equals(null) || 
        getSpecialCharacterCount(login)) {
      listaBledow.add(FxmlUtils.getString("tworzenie.konta.blad.login.znaki"));
    }
    if (!sprawdzCzyLoginWolny(login)) {
      listaBledow.add(FxmlUtils.getString("tworzenie.konta.blad.login.istnieje"));
    }
    if (haslo.equals(null) || 
        haslo.length() < 8 || 
        haslo.length() > 20) {
      listaBledow.add(FxmlUtils.getString("tworzenie.konta.blad.haslo.ilosc"));
    }
    if (haslo.equals(null) || 
        getSpecialCharacterCount(haslo.toString())) {
      listaBledow.add(FxmlUtils.getString("tworzenie.konta.blad.haslo.znaki"));
    }
    if (!haslo.equals(haslo2)) {
      listaBledow.add(FxmlUtils.getString("tworzenie.konta.blad.haslo2"));      
    }
    
    if (listaBledow == null || listaBledow.isEmpty()) {
      try {
		haslo = PassHash.createHash(haslo);
      } catch (CannotPerformOperationException e) {
		e.printStackTrace();
      }
      dodajUrzytkownika(login,haslo,null,null,"P");
    } else {
    	String listString = "";
    	for (String s : listaBledow) {
    		listString += s +"\n";
    	}
    	JOptionPane.showMessageDialog(null, listString);
    }
    return listaBledow;
  }
  
  public boolean getSpecialCharacterCount(String s) {
    if (s == null || s.trim().isEmpty()) {
        return true;
    }
    Pattern p = Pattern.compile("[^A-Za-z0-9]");
    Matcher m = p.matcher(s);
    boolean b = m.find();
    return b;
  }
  
  private boolean dodajUrzytkownika(String login,String haslo,Long npcj,Long nlek,String uprw) {
    String sql = "INSERT INTO URZ(LOGN,HASL,NPCJ,NLEK,UPRW) VALUES (?,?,?,?,?)";
    try {
      PreparedStatement query = sqlManager.createQuery(sql);
      query.setString(1, login);
      query.setString(2, haslo);
      if (npcj != null) {
        query.setLong(3, npcj);
      } else {
        query.setNull(3, Types.NUMERIC);
      }
      if(nlek != null) {
        query.setLong(4, nlek);
      } else {
        query.setNull(4, Types.NUMERIC);
      }
      query.setString(5, uprw);
      query.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  private boolean sprawdzCzyLoginWolny(String login) {
    String sql =   "SELECT LOGN FROM URZ WHERE LOGN = ?";
    PreparedStatement query = sqlManager.createQuery(sql);
    try {
      query.setString(1, login);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    Map<String, Object> rs = sqlManager.getSingleResult(query);
    if (rs == null || rs.isEmpty()) {
      return true;
    }
    return false;
  }
}
