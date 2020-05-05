package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Lasku;
import model.dao.LaskuDAO;
import model.dao.MaksajaDAO;

@WebServlet("/maksunlisays")
public class LisaaMaksuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MaksajaDAO maksajat = new MaksajaDAO();
		request.setAttribute("maksajat", maksajat.findAll());
		
		String jsp = "/view/maksunlisays.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String kuvaus = request.getParameter("kuvaus");
		
		String hintaString = request.getParameter("hinta");
		hintaString = hintaString.replace(",", ".");
		double hinta = Double.parseDouble(hintaString);
		
		String pvmString = request.getParameter("pvm");
		
		Date pvm = null;
		try {
			pvm = new SimpleDateFormat("yyyy-MM-dd").parse(pvmString);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String maksajaString = request.getParameter("maksaja");
		int maksaja = Integer.parseInt(maksajaString);
		
		Lasku lasku = new Lasku(kuvaus, hinta, pvm, maksaja);
		
		LaskuDAO laskuDAO = new LaskuDAO();
		
		try {
			laskuDAO.addLasku(lasku);
			response.sendRedirect("maksulista");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

}
