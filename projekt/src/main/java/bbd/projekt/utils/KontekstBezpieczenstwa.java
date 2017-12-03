package bbd.projekt.utils;

import java.util.Date;

public class KontekstBezpieczenstwa {
  private static Uprawnienia uprawnienia;
  private static String login;
  private static Date godzinaLogowania;
  
  public KontekstBezpieczenstwa() {
  }
  
  public static Uprawnienia getUprawnienia() {
    return uprawnienia;
  }
  public static void setUprawnienia(Uprawnienia uprawnienia) {
    KontekstBezpieczenstwa.uprawnienia = uprawnienia;
  }
  public static String getLogin() {
    return login;
  }
  public static void setLogin(String login) {
    KontekstBezpieczenstwa.login = login;
  }
  public static Date getGodzinaLogowania() {
    return godzinaLogowania;
  }
  public static void setGodzinaLogowania(Date godzinaLogowania) {
    KontekstBezpieczenstwa.godzinaLogowania = godzinaLogowania;
  }
  
  

  
}
