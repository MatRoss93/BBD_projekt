package bbd.projekt.interfaces;

import java.util.Date;

public class Termin {
  Integer idGrafiku;
  Integer idPacjenta;
  Integer idLekarza;
  Integer idPrzychodni;
  Date dataOd;
  Date dataDo;
  String imie;
  String nazwisko;
  Integer telefon;
  String danePacjenta;
  
  
  public String getDanePacjenta() {
    return danePacjenta;
  }
  public void setDanePacjenta(String danePacjenta) {
    this.danePacjenta = danePacjenta;
  }
  public Integer getIdGrafiku() {
    return idGrafiku;
  }
  public void setIdGrafiku(Integer idGrafiku) {
    this.idGrafiku = idGrafiku;
  }
  public Integer getIdPacjenta() {
    return idPacjenta;
  }
  public void setIdPacjenta(Integer idPacjenta) {
    this.idPacjenta = idPacjenta;
  }
  public Integer getIdLekarza() {
    return idLekarza;
  }
  public void setIdLekarza(Integer idLekarza) {
    this.idLekarza = idLekarza;
  }
  public Integer getIdPrzychodni() {
    return idPrzychodni;
  }
  public void setIdPrzychodni(Integer idPrzychodni) {
    this.idPrzychodni = idPrzychodni;
  }
  public Date getDataOd() {
    return dataOd;
  }
  public void setDataOd(Date dataOd) {
    this.dataOd = dataOd;
  }
  public Date getDataDo() {
    return dataDo;
  }
  public void setDataDo(Date dataDo) {
    this.dataDo = dataDo;
  }
  public String getImie() {
    return imie;
  }
  public void setImie(String imie) {
    this.imie = imie;
  }
  public String getNazwisko() {
    return nazwisko;
  }
  public void setNazwisko(String nazwisko) {
    this.nazwisko = nazwisko;
  }
  public Integer getTelefon() {
    return telefon;
  }
  public void setTelefon(Integer telefon) {
    this.telefon = telefon;
  }
  
}
