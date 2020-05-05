package model.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.util.Date;
import model.Lasku;

public class LaskuDAO extends DataAccessObject {
	
	/*Haetaan laskut tietokannasta*/
	public ArrayList<Lasku> findAll() {	
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Lasku> laskut = new ArrayList<>();
		Lasku lasku = null; 
		try {
			/* Luodaan yhteys */
			conn = getConnection();
			/* Luodaan komento: haetaan kaikki rivit LASKU-taulusta */
			String sqlSelect = "SELECT * FROM LASKU;";
			/* Valmistellaan komento */
			stmt = conn.prepareStatement(sqlSelect);
			/* L‰hetet‰‰n komento */
			rs = stmt.executeQuery();
			/* K‰yd‰‰n tulostaulun rivit l‰pi ja luetaan readLasku()-metodilla. Lopuksi lis‰t‰‰n lasku listaan*/
			while (rs.next()) {
				lasku = readLasku(rs);
				laskut.add(lasku);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			/* Suljetaan yhteydet*/
			close(rs, stmt, conn);
		}
	
		return laskut;
	}
	
	/*Apumetodi ylemm‰lle metodille*/
	private Lasku readLasku(ResultSet rs) {	
		try {
			/* Haetaan yhden laskun tiedot kyselyn tulostaulun aktiiviselta tietorivilt‰ */
			int id = rs.getInt("id");
			String kuvaus = rs.getString("kuvaus");
			double hinta = rs.getDouble("hinta");
			Date pvm = rs.getDate("pvm");
			int maksaja = rs.getInt("maksaja");
		
			/*  Luodaan ja palautetaan uusi lasku */
			return new Lasku(id, kuvaus, hinta, pvm, maksaja);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*Lis‰t‰‰n lasku tietokantaan*/
	public void addLasku(Lasku lasku) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		
		try {
			connection = getConnection();
			/* Luodaan uusi lasku tietokantaan: */
			String sqlInsert = "INSERT INTO LASKU(id, kuvaus, hinta, pvm, maksaja) VALUES (?, ?, ?, ?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setInt(1, lasku.getId());
			stmtInsert.setString(2, lasku.getKuvaus());
			stmtInsert.setDouble(3, lasku.getHinta());
			java.sql.Date sqlDate = new java.sql.Date(lasku.getPvm().getTime());
			stmtInsert.setDate(4, sqlDate);
			stmtInsert.setInt(5, lasku.getMaksaja());
			stmtInsert.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}
	
	/*Poistetaan lasku tietokannasta*/
	public void removeLasku(int laskuid) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmtDelete = null;
		
		try {
			connection = getConnection();
			String sqlDelete = "DELETE FROM LASKU WHERE id=?";
			stmtDelete = connection.prepareStatement(sqlDelete);
			stmtDelete.setInt(1, laskuid);
			stmtDelete.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtDelete, connection);
		}
		
	}
	
	/*Muutetaan laskun tietoja tietokannassa*/
	public void updateLasku(Lasku lasku) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmtUpdate = null;
		
		try {
			connection = getConnection();
			String sqlUpdate = "UPDATE LASKU SET kuvaus=?, hinta=?, pvm=?, maksaja=? WHERE id=?";
			stmtUpdate = connection.prepareStatement(sqlUpdate);
			stmtUpdate.setString(1, lasku.getKuvaus());
			stmtUpdate.setDouble(2, lasku.getHinta());
			java.sql.Date sqlDate = new java.sql.Date(lasku.getPvm().getTime());
			stmtUpdate.setDate(3, sqlDate);
			stmtUpdate.setInt(4, lasku.getMaksaja());
			stmtUpdate.setInt(5, lasku.getId());
			stmtUpdate.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtUpdate, connection);
		}
		
	}
}
