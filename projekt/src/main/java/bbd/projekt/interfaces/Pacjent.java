package bbd.projekt.interfaces;

public class Pacjent {
	
	private Long idPacjenta;
	private String imie;
	private String nazwisko;
	private Integer idMiasta;
	private Integer idWojew;
	private Integer telefon;
	private String ulica;
	
	
	public Long getIdPacjenta() {
		return idPacjenta;
	}
	public void setIdPacjenta(Long idPacjenta) {
		this.idPacjenta = idPacjenta;
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
	public int getIdMiasta() {
		return idMiasta;
	}
	public void setIdMiasta(int idMiasta) {
		this.idMiasta = idMiasta;
	}
	public int getIdWojew() {
		return idWojew;
	}
	public void setIdWojew(int idWojew) {
		this.idWojew = idWojew;
	}
	public int getTelefon() {
		return telefon;
	}
	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
}
