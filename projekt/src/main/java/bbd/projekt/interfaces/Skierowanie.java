package bbd.projekt.interfaces;

public class Skierowanie {
  Integer idSpecjalisty;
  Integer idBadania;
  String nazwa;
  
  public Integer getIdSpecjalisty() {
    return idSpecjalisty;
  }
  public void setIdSpecjalisty(Integer idSpecjalisty) {
    this.idSpecjalisty = idSpecjalisty;
  }
  public Integer getIdBadania() {
    return idBadania;
  }
  public void setIdBadania(Integer idBadania) {
    this.idBadania = idBadania;
  }
  public String getNazwa() {
    return nazwa;
  }
  public void setNazwa(String nazwa) {
    this.nazwa = nazwa;
  }
}
