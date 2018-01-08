package bbd.projekt.utils;

import java.util.Date;

public class KontekstBezpieczenstwa {
  private Uprawnienia uprawnienia;
  private String login;
  private Date godzinaLogowania;
  
  public KontekstBezpieczenstwa() {
  }
  
  public Uprawnienia getUprawnienia() {
    return uprawnienia;
  }
  public void setUprawnienia(Uprawnienia uprawnienia) {
    this.uprawnienia = uprawnienia;
  }
  public String getLogin() {
    return login;
  }
  public void setLogin(String login) {
    this.login = login;
  }
  public Date getGodzinaLogowania() {
    return godzinaLogowania;
  }
  public void setGodzinaLogowania(Date godzinaLogowania) {
    this.godzinaLogowania = godzinaLogowania;
  }
  public static KontekstBezpieczenstwa setZalogowany(String login, String uprw) {
    KontekstBezpieczenstwa kb = new KontekstBezpieczenstwa();
    kb.setLogin(login);
    switch (uprw) {
      case "A":
        kb.setUprawnienia(Uprawnienia.ADMINISTRATOR);
        break;
      case "P":
        kb.setUprawnienia(Uprawnienia.PACJENT);
        break;
      case "R":
        kb.setUprawnienia(Uprawnienia.RECEPCJA);
        break;
      case "L":
        kb.setUprawnienia(Uprawnienia.LEKARZ);
        break;
      default:
        kb.setUprawnienia(Uprawnienia.NA);
        break;
    }
    kb.setGodzinaLogowania(new Date());
    return kb;
  }

  public boolean poprawnyKB() {
    if (this.godzinaLogowania != null && this.login != null && this.uprawnienia != null && !this.uprawnienia.equals(Uprawnienia.NA)) {
      return true;
    }
    return false;
  }
  
}
