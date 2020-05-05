package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.LaskuDAO;

@WebServlet("/poista-lasku")
public class LaskunPoistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*Tällä doGet-pyynnöllä otetaan vastaan parametrinä poistettavan laskun id, ja suoritetaan laskun poistaminen. Metodi palauttaa käyttäjän maksulistalle*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		
		LaskuDAO pizza = new LaskuDAO();
		try {
			pizza.removeLasku(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("maksulista");
	}

}
