package bbd.projekt.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SqlManager {
  private static final String URL = "jdbc:mysql://serwer1796722.home.pl/25678166_0000001?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";
  private static final String USERNAME = "25678166_0000001";
  private static final String PASSWORD = "Monocentropus1";

  private Connection connection;
  private PreparedStatement statement;
  
  public SqlManager() {
    try {
      connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Połączenie z bazą nie działa!");
    }
    System.out.println("Połączenie z bazą działa!");
  }
  
  public PreparedStatement createQuery(String sql) {

    try {
      statement = connection.prepareStatement(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return statement;
  }
  
  public List<Map<String, Object>> getResultList(PreparedStatement statement) {

    List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
    ResultSet rs = null;
    try {
      rs = statement.executeQuery();
      
      ResultSetMetaData md = rs.getMetaData();
      int columns = md.getColumnCount();
      while (rs.next()){
        HashMap<String, Object> row = new HashMap<String,Object>(columns);
        for(int i = 1; i <= columns; i++){           
          row.put(md.getColumnName(i), rs.getObject(i));
        }
        map.add(row);
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return map;
  }
  
  public Map<String, Object> getSingleResult(PreparedStatement statement) {

    ResultSet rs = null;
    try {
      rs = statement.executeQuery();
      
      ResultSetMetaData md = rs.getMetaData();
      int columns = md.getColumnCount();
      if (rs.next()){
        HashMap<String, Object> map = new HashMap<String,Object>(columns);
        for(int i = 1; i <= columns; i++){           
          map.put(md.getColumnName(i), rs.getObject(i));
        }
        return map;
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }  
}
