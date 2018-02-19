package bbd.projekt.interfaces;

import bbd.projekt.utils.UprawnieniaEnum;

public class Uprawnienia {
  private String nazwa;
  private String symbol;
  
  public Uprawnienia(UprawnieniaEnum e) {
    this.nazwa = e.name().toString();
    switch (e) {
    case ADMINISTRATOR:
      this.symbol = "A";
      break;
    case LEKARZ:
      this.symbol = "L";
      break;
    case PACJENT:
      this.symbol = "P";
      break;
    case RECEPCJA:
      this.symbol = "R";
      break;
    default:
      this.symbol = "NA";
      break;
    }
  }
  
  public String getNazwa() {
    return nazwa;
  }
  public void setNazwa(String nazwa) {
    this.nazwa = nazwa;
  }
  public String getSymbol() {
    return symbol;
  }
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}
