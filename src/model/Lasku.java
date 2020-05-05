package model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.dao.MaksajaDAO;

public class Lasku {
	
	private int id;
	private String kuvaus;
	private double hinta;
	private Date pvm;
	private int maksaja;
	private DecimalFormat kaksiDec = new DecimalFormat("#.00");
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKuvaus() {
		return kuvaus;
	}
	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}
	public double getHinta() {
		return hinta;
	}
	/*t‰m‰ palauttaa hinnan kahdella desimaalilla*/
	public String getHintaDec() {
		String hintaString = kaksiDec.format(hinta);
		return hintaString.replace(".", ",");
	}
	public void setHinta(double hinta) {
		this.hinta = hinta;
	}
	public Date getPvm() {
		return pvm;
	}
	/*t‰m‰ palauttaa p‰iv‰m‰‰r‰n oikeassa muodossa*/
	public String getPvmDMY() {
		String pvmDMY = new SimpleDateFormat("dd.MM.yyyy").format(pvm);
		return pvmDMY;
	}
	public void setPvm(Date pvm) {
		this.pvm = pvm;
	}
	public int getMaksaja() {
		return maksaja;
	}
	/*t‰m‰ palauttaa maksajan id:n sijasta maksajan nimen*/
	public String getNimi() {
		MaksajaDAO maksajatDAO = new MaksajaDAO();
		ArrayList<Maksaja> maksajat = new ArrayList<>();
		maksajat = maksajatDAO.findAll();
		String nimi = "";
		for (int i = 0; i < maksajat.size(); i++) {
			if (maksajat.get(i).getId() == this.maksaja) {
				nimi = maksajat.get(i).getNimi();
				break;
			}
		}
		return nimi;
	}
	public void setMaksaja(int maksaja) {
		this.maksaja = maksaja;
	}
	
	public Lasku() {
		
	}
	
	public Lasku(int id, String kuvaus, double hinta, Date pvm, int maksaja) {

		this.id = id;
		this.kuvaus = kuvaus;
		this.hinta = hinta;
		this.pvm = pvm;
		this.maksaja = maksaja;
	}
	
	public Lasku(String kuvaus, double hinta, Date pvm, int maksaja) {

		this.id = 0;
		this.kuvaus = kuvaus;
		this.hinta = hinta;
		this.pvm = pvm;
		this.maksaja = maksaja;
	}
	
	@Override
	public String toString() {
		return "Lasku [id=" + id + ", kuvaus=" + kuvaus + ", hinta=" + hinta + ", pvm=" + pvm + ", maksaja=" + maksaja
				+ "]";
	}
		
}
