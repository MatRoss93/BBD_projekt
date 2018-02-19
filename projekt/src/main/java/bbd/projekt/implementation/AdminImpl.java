package bbd.projekt.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bbd.projekt.database.SqlManager;
import bbd.projekt.interfaces.Lekarz;
import bbd.projekt.interfaces.Przychodnia;
import bbd.projekt.utils.KontekstBezpieczenstwa;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class AdminImpl {
  private SqlManager sqlManager;
  private KontekstBezpieczenstwa kontekstBezpieczenstwa;
  public AdminImpl(KontekstBezpieczenstwa kontekstBezpieczenstwa) {
    sqlManager = new SqlManager();
    this.kontekstBezpieczenstwa = kontekstBezpieczenstwa;
  }
  
  public List<Przychodnia> pobierzPrzychodnie() {
    List<Przychodnia> listaPrzychodni = new ArrayList<Przychodnia>();
    List<Map<String, Object>> przychodnie = new ArrayList<Map<String, Object>>();
    String sql =   
        "    SELECT NPRZ, NAZW, MSTO, WOJW, ADRS, NUMT, PONO, POND, WTOO, WTOD, SROO, SROD, CZWO, CZWD, PIOO, PIOD, SOBO,SOBD, NIEO, NIED FROM PRZ P " +
            "INNER JOIN MST M ON P.NMST = M.NMST " +
            "INNER JOIN WOJ W ON P.NWOJ = W.NWOJ " +
            "  ORDER BY NAZW ASC";
    
    PreparedStatement query = sqlManager.createQuery(sql);
    przychodnie = sqlManager.getResultList(query);
    Przychodnia p = null;
    for (Map<String, Object> przychodnia : przychodnie) {
      p = new Przychodnia();
      
      p.setId((Long) przychodnia.get("NPRZ"));
      p.setNazwa((String) przychodnia.get("NAZW"));
      p.setMiasto((String) przychodnia.get("MSTO"));
      p.setWojewodztwo((String) przychodnia.get("WOJW"));
      p.setAdres((String) przychodnia.get("ADRS"));
      p.setTelefon((Integer) przychodnia.get("NUMT"));
      p.setPonOd((Integer) przychodnia.get("PONO"));
      p.setPonDo((Integer) przychodnia.get("POND"));
      p.setWtoOd((Integer) przychodnia.get("WTOO"));
      p.setWtoDo((Integer) przychodnia.get("WTOD"));
      p.setSroOd((Integer) przychodnia.get("SROO"));
      p.setSroDo((Integer) przychodnia.get("SROD"));
      p.setCzwOd((Integer) przychodnia.get("CZWO"));
      p.setCzwDo((Integer) przychodnia.get("CZWD"));
      p.setPiaOd((Integer) przychodnia.get("PIOO"));
      p.setPiaDo((Integer) przychodnia.get("PIOD"));
      p.setSobOd((Integer) przychodnia.get("SOBO"));
      p.setSobDo((Integer) przychodnia.get("SOBD"));
      p.setNieOd((Integer) przychodnia.get("NIEO"));
      p.setNieDo((Integer) przychodnia.get("NIED"));
      
      listaPrzychodni.add(p);
    }
    return listaPrzychodni;
  }
  
  public List<Lekarz> pobierzLekarzyDlaPrzychodni(Long idPrzychodni) {
    List<Lekarz> listaLekarzy = new ArrayList<Lekarz>();
    List<Map<String, Object>> lekarze = new ArrayList<Map<String,Object>>();
    String sql =   
        "    SELECT DISTINCT L.NLEK, S.SPEC, L.IMIE, L.NAZW, U.NURZ FROM LEK L " +
        "INNER JOIN SPC S ON L.NSPC = S.NSPC " +
        " LEFT JOIN URZ U ON L.NLEK = U.NLEK " +
        "      JOIN GRF G ON L.NLEK = G.NLEK " +
        "     WHERE G.NPRZ = ?";
    PreparedStatement query = sqlManager.createQuery(sql);
    try {
      query.setLong(1, idPrzychodni);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    lekarze = sqlManager.getResultList(query);
    Lekarz lek = null;
    for (Map<String,Object> l : lekarze) {
      lek = new Lekarz();
      lek.setIdLekarza((Long) l.get("NLEK"));
      lek.setSpecjalnosc((String) l.get("SPEC"));
      lek.setImie((String) l.get("IMIE"));
      lek.setNazwisko((String) l.get("NAZW"));
      lek.setIdUrzytkownika((Long) l.get("NURZ"));
      listaLekarzy.add(lek);
    }
    
    return listaLekarzy;
  }
  
  public void przygotujRaport(Long przychodnia) {
   try {
    String qn = "./src/main/resources/JRXML/AdminRaport.jrxml";
    FileInputStream input = new FileInputStream(new File(qn));
    //System.out.println(input.toString());
    JasperDesign jd = JRXmlLoader.load(new FileInputStream(new File(qn)));
    JasperReport jr = JasperCompileManager.compileReport(jd);
    //JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(qn);
    HashMap<String,Object> parametry = new HashMap<String, Object>();
    parametry.put("przychodnia", przychodnia);
    JasperPrint jp = sqlManager.fillReport(jr, parametry);
    //JasperViewer jv = new JasperViewer(jp);
    //jv.setVisible(true);
    OutputStream output = new FileOutputStream(new File("C:\\Users\\Wojciech Serafin\\Desktop\\JasperReport.pdf")); 
    JasperExportManager.exportReportToPdfStream(jp, output); 
    output.flush();
    output.close();
   } catch (JRException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   } catch (FileNotFoundException e) {
    e.printStackTrace();
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  }
}
