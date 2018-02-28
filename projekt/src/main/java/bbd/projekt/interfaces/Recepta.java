package bbd.projekt.interfaces;

import java.util.Date;

public class Recepta {

  private String datawyst;
  private String datawazn;
  private String nazwaLeku;
  private Long idLeku;
  public String getDatawyst() {
    return datawyst;
  }
  public void setDatawyst(String datawyst) {
    this.datawyst = datawyst;
  }
  public String getDatawazn() {
    return datawazn;
  }
  public void setDatawazn(String datawazn) {
    this.datawazn = datawazn;
  }
  public String getNazwaLeku() {
    return nazwaLeku;
  }
  public void setNazwaLeku(String nazwaLeku) {
    this.nazwaLeku = nazwaLeku;
  }
  public Long getIdLeku() {
    return idLeku;
  }
  public void setIdLeku(Long idLeku) {
    this.idLeku = idLeku;
  }

}
