package bbd.projekt.interfaces;

public class Lekarz {
  private Long idLekarza;
  private String specjalnosc;
  private String imie;
  private String nazwisko;
  private Long idUrzytkownika;
  private String imienazwisko;
  
  public Lekarz(Long id, String im, String nazw, String imNazw)
  {
	  this.idLekarza = id;
	  this.imie= im;
	  this.nazwisko = nazw;
	  this.imienazwisko = imNazw;
  }
  
  public Lekarz(){}
  public Long getIdLekarza() {
    return idLekarza;
  }
  public void setIdLekarza(Long idLekarza) {
    this.idLekarza = idLekarza;
  }
  public String getSpecjalnosc() {
    return specjalnosc;
  }
  public void setSpecjalnosc(String specjalnosc) {
    this.specjalnosc = specjalnosc;
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
  public Long getIdUrzytkownika() {
    return idUrzytkownika;
  }
  public void setIdUrzytkownika(Long idUrzytkownika) {
    this.idUrzytkownika = idUrzytkownika;
  }
  public String getImienazwisko() {
	  return imienazwisko;
  }
  public void setImienazwisko(String imienazwisko) {
	  this.imienazwisko = imienazwisko;
  }
  
}
