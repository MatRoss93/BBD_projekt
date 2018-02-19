package bbd.projekt.utils;

import java.util.Date;

public class KontekstBezpieczenstwa {
  private UprawnieniaEnum uprawnienia;
  private String login;
  private Date godzinaLogowania;
  private Long idPacjenta;
  private Long idLekarza;
  
  public KontekstBezpieczenstwa() {
  }
  
  public UprawnieniaEnum getUprawnienia() {
    return uprawnienia;
  }
  public void setUprawnienia(UprawnieniaEnum uprawnienia) {
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
  public static KontekstBezpieczenstwa setZalogowany(String login, String uprw, Long idLekarza, Long idPacjenta) {
    KontekstBezpieczenstwa kb = new KontekstBezpieczenstwa();
    kb.setLogin(login);
    kb.setIdPacjenta(idPacjenta);
    kb.setIdLekarza(idLekarza);
    switch (uprw) {
      case "A":
        kb.setUprawnienia(UprawnieniaEnum.ADMINISTRATOR);
        break;
      case "P":
        kb.setUprawnienia(UprawnieniaEnum.PACJENT);
        break;
      case "R":
        kb.setUprawnienia(UprawnieniaEnum.RECEPCJA);
        break;
      case "L":
        kb.setUprawnienia(UprawnieniaEnum.LEKARZ);
        break;
      default:
        kb.setUprawnienia(UprawnieniaEnum.NA);
        break;
    }
    kb.setGodzinaLogowania(new Date());
    return kb;
  }

  public boolean poprawnyKB() {
    if (this.godzinaLogowania != null && this.login != null && this.uprawnienia != null && !this.uprawnienia.equals(UprawnieniaEnum.NA)) {
      return true;
    }
    return false;
  }

  public Long getIdPacjenta() {
    return idPacjenta;
  }

  public void setIdPacjenta(Long idPacjenta) {
    this.idPacjenta = idPacjenta;
  }

  public Long getIdLekarza() {
    return idLekarza;
  }

  public void setIdLekarza(Long idLekarza) {
    this.idLekarza = idLekarza;
  }
  
}
