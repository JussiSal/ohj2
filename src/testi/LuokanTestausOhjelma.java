package testi;

import java.util.ArrayList;

import model.Lasku;
import model.dao.LaskuDAO;

public class LuokanTestausOhjelma {

	public static void main(String[] args) {
		
		LaskuDAO laskuDAO = new LaskuDAO();
		ArrayList<Lasku> laskut = new ArrayList<>();
		laskut = laskuDAO.findAll();
		int id = laskut.get(2).getId();
		String nimi = laskut.get(2).getNimi();
		System.out.println(id + " " + nimi);
		
	}

}
