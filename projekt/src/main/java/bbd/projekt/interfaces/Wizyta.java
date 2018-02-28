package bbd.projekt.interfaces;

public class Wizyta {
  
  private String data;
  private String godzina;
  private String nazwaPrzych;
  private String nazwaLek;
  public Wizyta() {
    
  }
  public String getData() {
    return data;
  }
  public void setData(String data) {
    this.data = data;
  }
  public String getGodzina() {
    return godzina;
  }
  public void setGodzina(String godzina) {
    this.godzina = godzina;
  }
  public String getNazwaPrzych() {
    return nazwaPrzych;
  }
  public void setNazwaPrzych(String nazwaPrzych) {
    this.nazwaPrzych = nazwaPrzych;
  }
  public String getNazwaLek() {
    return nazwaLek;
  }
  public void setNazwaLek(String nazwaLek) {
    this.nazwaLek = nazwaLek;
  }
  
}