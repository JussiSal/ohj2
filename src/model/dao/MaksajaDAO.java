package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Maksaja;

public class MaksajaDAO extends DataAccessObject {
	
	public ArrayList<Maksaja> findAll() {	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Maksaja> maksajat = new ArrayList<>();
		Maksaja maksaja = null; 
		try {
			// Luodaan yhteys
			conn = getConnection();
			// Luodaan komento: haetaan kaikki rivit LASKU-taulusta
			String sqlSelect = "SELECT id, nimi FROM MAKSAJA;";
			// Valmistellaan komento:
			stmt = conn.prepareStatement(sqlSelect);
			// L‰hetet‰‰n komento:
			rs = stmt.executeQuery();
			// K‰yd‰‰n tulostaulun rivit l‰pi ja luetaan readLasku()-metodilla:
			while (rs.next()) {
				maksaja = readMaksaja(rs);
				// lis‰t‰‰n lasku listaan
				maksajat.add(maksaja);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn); // Suljetaan
		}
	
		return maksajat;
	}
	
	private Maksaja readMaksaja(ResultSet rs) {	
		try {
			// Haetaan yhden laskun tiedot kyselyn tulostaulun (ResultSet-tyyppinen rs-olion) aktiiviselta tietorivilt‰
			int id = rs.getInt("id");
			String nimi = rs.getString("nimi");
		
			//  Luodaan ja palautetaan uusi lasku
			return new Maksaja(id, nimi);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addMaksaja(Maksaja maksaja) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		
		try {
			// Luodaan yhteys
			connection = getConnection();
			//Luodaan uusi henkilo tietokantaan:
			String sqlInsert = "INSERT INTO MAKSAJA(id, nimi) VALUES (?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setInt(1,  maksaja.getId());
			stmtInsert.setString(2, maksaja.getNimi());;
			stmtInsert.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection); // Suljetaan statement ja yhteys
		}
	}
}
