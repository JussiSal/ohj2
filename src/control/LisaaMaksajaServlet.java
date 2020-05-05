package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Maksaja;
import model.dao.MaksajaDAO;


@WebServlet("/maksajanlisays")
public class LisaaMaksajaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsp = "/view/maksajanlisays.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nimi = request.getParameter("maksaja");
		Maksaja maksaja = new Maksaja(nimi);
		MaksajaDAO maksajaDAO = new MaksajaDAO();
		
		try {
			maksajaDAO.addMaksaja(maksaja);
			response.sendRedirect("maksunlisays");
		} catch (SQLException e) {
			response.sendRedirect("maksajanlisays?viesti=Virheellinen arvo");
		}
	}

}
