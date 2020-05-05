package model;

public class Maksaja {
	
	private int id;
	private String nimi;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	@Override
	public String toString() {
		return "Maksaja id:" + id + ", nimi:" + nimi;
	}
	
	public Maksaja(int id, String nimi) {
		this.id = id;
		this.nimi = nimi;
	}
	
	public Maksaja(String nimi) {
		this.id = 0;
		this.nimi = nimi;
	}
	
	public Maksaja() {
		
	}
	
}
